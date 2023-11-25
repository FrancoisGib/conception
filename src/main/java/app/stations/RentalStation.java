package app.stations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.Observer;
import app.Timer;
import app.persons.Client;
import app.stations.spaces.ParkingSpace;
import app.stations.spaces.SpaceEmptyException;
import app.stations.spaces.SpaceOccupiedException;
import app.vehicles.State;
import app.vehicles.Vehicle;
import lombok.Getter;

public class RentalStation implements Subject, Timer {
    public static final int MAX_CAPACITY = 20;
    public static final int MIN_CAPACITY = 10;
    public static final int TIME_BEFORE_VEHICLE_STOLLEN = 2;

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
                found = true;
                this.observer.vehicleStored(vehicle, this);
            }
        }
        if (!found)
            throw new StationFullException();
    }

    public Vehicle rentVehicle(Client client) throws StationEmptyException, SpaceEmptyException {
        for (ParkingSpace space : this.spaces) {
            if (space.isOccupied() && space.getVehicle().isRentable()) {
                Vehicle vehicle = space.remove();
                vehicle.rented();
                this.observer.vehicleRented(vehicle, client,this);
                return vehicle;
            }
        }
        throw new StationEmptyException();
    }

    public boolean isEmpty() {
        for (ParkingSpace space : this.spaces) {
            if (space.isOccupied())
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
                    stollenVehicle.setState(State.STOLLEN);
                } catch (SpaceEmptyException e) {
                    e.printStackTrace();
                }
                this.onlyOneVehicleCount = 0;
            }
        }
        else
            this.onlyOneVehicleCount = 0;
    }
}
