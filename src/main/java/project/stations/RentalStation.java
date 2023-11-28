package project.stations;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import project.Observer;
import project.Simulation;
import project.Timer;

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

    public boolean storeVehicle(Vehicle vehicle) {
        for (ParkingSpace space : spaces) {
            if (!space.isOccupied()) {
                if (vehicle.getLives() == 0) {
                    vehicle.setState(State.OUT_OF_SERVICE);
                    this.observer.vehicleOutOfService(vehicle);
                }
                else {
                    vehicle.setState(State.STORED);
                }
                this.observer.vehicleStored(vehicle, this);
                space.store(vehicle);
                return true;
            }
        }
        return false;
    }

/*public boolean storeVehicle(Vehicle vehicle) {
        for (ParkingSpace space : spaces) {
            if (!space.isOccupied()) {
                if (vehicle.getState() == State.STOLEN)
                    this.observer.vehicleBackFromStolen(vehicle);
                if (vehicle.getLives() == 0) {
                    vehicle.setState(State.OUT_OF_SERVICE);
                    this.observer.vehicleOutOfService(vehicle);
                }
                else {
                    vehicle.setState(State.STORED);
                    this.observer.vehicleStored(vehicle, this);
                }
                space.store(vehicle);
                return true;
            }
        }
        return false;
    }*/

    public Vehicle rentVehicle() {
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
        return null;
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
                Vehicle stolenVehicle = vehicleSpace.remove();
                stolenVehicle.setState(State.STOLEN);
                this.onlyOneVehicleCount = 0;
            }
        }
    }
}
