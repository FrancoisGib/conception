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
import project.stations.spaces.SpaceOccupiedException;
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

    public void storeVehicle(Vehicle vehicle) throws StationFullException, SpaceOccupiedException {
        Iterator<ParkingSpace> iterator = this.spaces.iterator();
        boolean found = false;
        while (!found && iterator.hasNext()) {
            ParkingSpace space = iterator.next();
            if (!space.isOccupied()) {
                space.store(vehicle);
                if (vehicle.getLives() == 0)
                    vehicle.setState(State.OUT_OF_SERVICE);
                found = true;
                this.observer.vehicleStored(vehicle, this);
                if (vehicle.getState() == State.OUT_OF_SERVICE)
                    this.observer.vehicleOutOfService(vehicle);
                else
                    vehicle.setState(State.STORED);
            }
        }
        if (!found)
            throw new StationFullException();
    }

    public Vehicle rentVehicle() throws StationEmptyException, SpaceEmptyException {
        for (ParkingSpace space : this.spaces) {
            if (space.isOccupied()) {
                Vehicle spaceVehicle = space.getVehicle();
                if (spaceVehicle.isRentable()) {
                    Vehicle vehicle = space.remove();
                    this.rented(vehicle);
                    this.observer.vehicleRented(vehicle, this);
                    return vehicle;
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
            if (space.isOccupied()) {
                if (space.getVehicle().isRentable())
                    return false;
                return false;
            }
        }
        return true;
    }

    public boolean isFull() {
        for (ParkingSpace space : this.spaces) {
            if (!space.isOccupied())
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
            if (space.isOccupied()) {
                cpt++;
                Vehicle vehicle = space.getVehicle();
                vehicleSpace = space;
                if (vehicle.getState() == State.OUT_OF_SERVICE)
                    this.observer.vehicleOutOfService(space.getVehicle());
            }
        }
        if (cpt == 1) {
            this.onlyOneVehicleCount++;
            if (this.onlyOneVehicleCount == TIME_BEFORE_VEHICLE_STOLLEN) {
                try {
                    Vehicle stollenVehicle = vehicleSpace.remove();
                    stollenVehicle.setState(State.STOLEN);
                } catch (SpaceEmptyException e) {
                    e.printStackTrace();
                }
                this.onlyOneVehicleCount = 0;
            }
        } else
            this.onlyOneVehicleCount = 0;
    }
}
