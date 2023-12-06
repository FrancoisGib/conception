package project.stations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import project.Observer;
import project.Simulation;
import project.Timer;
import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceEmptyException;
import project.stations.spaces.SpaceFullException;
import project.vehicles.State;
import project.vehicles.Vehicle;

public class RentalStation implements Timer {
    public static final int MAX_CAPACITY = 20;
    public static final int MIN_CAPACITY = 10;
    public static final int TIME_BEFORE_VEHICLE_STOLLEN = Simulation.TIME_BEFORE_VEHICLE_STOLLEN;

    private int onlyOneVehicleCount = 0;

    @Getter
    private int id;

    @Getter
    private List<ParkingSpace> spaces = new ArrayList<>();

    private Observer observer;

    @Getter
    private int capacity;

    /**
     * Represents a rental station for parking spaces.
     */
    public RentalStation(int id, List<ParkingSpace> spaces) {
        this.spaces = spaces;
        this.id = id;
        this.capacity = spaces.size();
    }

    /**
     * Stores a vehicle in the rental station.
     *
     * @param vehicle the vehicle to be stored
     * @throws StationFullException if the rental station is full and cannot store the vehicle
     */
    public void storeVehicle(Vehicle vehicle) throws StationFullException {
        boolean found = false;
        Iterator<ParkingSpace> it = this.spaces.iterator();
        while (!found && it.hasNext()) {
            try {
                it.next().store(vehicle);
                found = true;
            } catch (SpaceFullException e) {
                // Ignore and continue to the next space
            }
        }
        if (found) {
            if (vehicle.getLives() == 0) {
                vehicle.setState(State.OUT_OF_SERVICE);
                this.observer.vehicleOutOfService(vehicle);
            } else {
                vehicle.setState(State.STORED);
            }
            this.observer.vehicleStored(vehicle, this);
        } else {
            throw new StationFullException();
        }
    }

    /**
     * Rents a vehicle from the rental station.
     *
     * @return The rented vehicle.
     * @throws StationEmptyException if the rental station is empty and there are no available vehicles to rent.
     */
    public Vehicle rentVehicle() throws StationEmptyException {
        for (ParkingSpace space : this.spaces) {
            if (!space.isEmpty() && space.getVehicle().isRentable()) {
                try {
                    Vehicle vehicle = space.remove();
                    int newVehicleLives = vehicle.getLives() - 1;
                    vehicle.setLives(newVehicleLives);
                    vehicle.setState(State.RENTED);
                    this.observer.vehicleRented(vehicle, this);
                    return vehicle;
                } catch (SpaceEmptyException e) {
                }
            }
        }
        throw new StationEmptyException();
    }

    /**
     * Checks if the rental station is empty.
     * 
     * @return true if the rental station is empty, false otherwise.
     */
    public boolean isEmpty() {
        for (ParkingSpace space : this.spaces) {
            if (!space.isEmpty() && space.getVehicle().isRentable())
                return false;
        }
        return true;
    }

    /**
     * Checks if the rental station is full.
     * 
     * @return true if all parking spaces are occupied, false otherwise.
     */
    public boolean isFull() {
        for (ParkingSpace space : this.spaces) {
            if (space.isEmpty())
                return false;
        }
        return true;
    }

    /**
     * Attaches an observer to the rental station.
     * 
     * @param observer the observer to attach
     */
    public void attach(Observer observer) {
        this.observer = observer;
    }

    /**
     * Performs a tick of the rental station, checking if there is only one vehicle present and if it has been stolen.
     * If there is only one vehicle present for a certain amount of time, the vehicle is marked as stolen and the observer is notified.
     */
    public void tick() {
        int cpt = 0;
        ParkingSpace vehicleSpace = null;
        for (ParkingSpace space : spaces) {
            if (!space.isEmpty()) {
                cpt++;
                vehicleSpace = space;
            }
        }
        if (cpt == 1) {
            this.onlyOneVehicleCount++;
            if (this.onlyOneVehicleCount == TIME_BEFORE_VEHICLE_STOLLEN) {
                try {
                    Vehicle stolenVehicle = vehicleSpace.remove();
                    stolenVehicle.setState(State.STOLEN);
                    this.observer.vehicleStolen(stolenVehicle);
                } catch (SpaceEmptyException e) {
                } finally {
                    this.onlyOneVehicleCount = 0;
                }
            }
        }
    }
}
