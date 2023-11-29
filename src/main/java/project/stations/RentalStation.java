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

public class RentalStation implements Subject, Timer {
    public static final int MAX_CAPACITY = 20;
    public static final int MIN_CAPACITY = 10;
    public static final int TIME_BEFORE_VEHICLE_STOLLEN = Simulation.TIME_BEFORE_VEHICLE_STOLLEN;

    protected int onlyOneVehicleCount = 0;

    @Getter
    protected int id;

    @Getter
    protected List<ParkingSpace> spaces = new ArrayList<>();

    protected Observer observer;

    @Getter
    protected int capacity;

    public RentalStation(int id, List<ParkingSpace> spaces) {
        this.spaces = spaces;
        this.id = id;
        this.capacity = spaces.size();
    }

    public void storeVehicle(Vehicle vehicle) throws StationFullException {
        boolean found = false;
        Iterator<ParkingSpace> it = this.spaces.iterator();
        while (!found && it.hasNext()) {
            try {
                it.next().store(vehicle);
                found = true;
            } catch (SpaceFullException e) {
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
        } else
            throw new StationFullException();
    }

    public Vehicle rentVehicle() throws StationEmptyException {
        for (ParkingSpace space : this.spaces) {
            if (!space.isEmpty() && space.getVehicle().isRentable()) {
                try {
                    Vehicle vehicle = space.remove();
                    this.rented(vehicle);
                    this.observer.vehicleRented(vehicle, this);
                    return vehicle;
                } catch (SpaceEmptyException e) {
                }
            }
        }
        throw new StationEmptyException();
    }

    private void rented(Vehicle vehicle) {
        int newVehicleLives = vehicle.getLives() - 1;
        vehicle.setLives(newVehicleLives);
        if (newVehicleLives > 0)
            vehicle.setState(State.RENTED);

    }

    public boolean isEmpty() {
        for (ParkingSpace space : this.spaces) {
            if (!space.isEmpty() && space.getVehicle().isRentable())
                return false;
        }
        return true;
    }

    public boolean isFull() {
        for (ParkingSpace space : this.spaces) {
            if (space.isEmpty())
                return false;
        }
        return true;
    }

    public void attach(Observer observer) {
        this.observer = observer;
    }

    public void tick() {
        int cpt = 0;
        ParkingSpace vehicleSpace = null;
        for (ParkingSpace space : spaces) {
            if (!space.isEmpty()) {
                cpt++;
                vehicleSpace = space;
                Vehicle vehicle = space.getVehicle();
                if (vehicle.getState() == State.OUT_OF_SERVICE)
                    this.observer.vehicleOutOfService(vehicle);
            }
        }
        if (cpt == 1) {
            this.onlyOneVehicleCount++;
            if (this.onlyOneVehicleCount == TIME_BEFORE_VEHICLE_STOLLEN) {
                try {
                    Vehicle stolenVehicle = vehicleSpace.remove();
                    stolenVehicle.setState(State.STOLEN);
                } catch (SpaceEmptyException e) {
                } finally {
                    this.onlyOneVehicleCount = 0;
                }
            }
        }
    }
}
