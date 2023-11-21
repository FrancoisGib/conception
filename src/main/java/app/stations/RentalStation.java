package app.stations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.controlcenter.Observer;
import app.stations.spaces.ParkingSpace;
import app.stations.spaces.SpaceEmptyException;
import app.stations.spaces.SpaceOccupiedException;
import app.vehicles.Vehicle;
import lombok.Getter;

public class RentalStation<V extends Vehicle> implements Subject<V> {
    public static final int MAX_CAPACITY = 20;
    public static final int MIN_CAPACITY = 10;

    @Getter
    protected int id;

    @Getter
    protected List<ParkingSpace<V>> spaces = new ArrayList<>();

    protected Observer<V> observer;

    @Getter
    protected int capacity;

    public RentalStation(int id, List<ParkingSpace<V>> spaces) {
        this.spaces = spaces;
        this.id = id;
        this.capacity = spaces.size();
    }

    public void storeVehicle(V vehicle) throws StationFullException, SpaceOccupiedException {
        Iterator<ParkingSpace<V>> iterator = this.spaces.iterator();
        boolean found = false;
        while (!found && iterator.hasNext()) {
            ParkingSpace<V> space = iterator.next();
            if (!space.isOccupied()) {
                space.store(vehicle);
                found = true;
                this.observer.vehicleStored(vehicle, this);
            }
        }
        throw new StationFullException();
    }

    public V rentVehicle() throws StationEmptyException, SpaceEmptyException {
        for (ParkingSpace<V> space : this.spaces) {
            if (space.isOccupied()) {
                V vehicle = space.remove();
                this.observer.vehicleRented(vehicle, this);
                return vehicle;
            }
        }
        throw new StationEmptyException();
    }

    public boolean isEmpty() {
        for (ParkingSpace<V> space : this.spaces) {
            if (space.isOccupied())
                return false;
        }
        return true;
    }

    public void attach(Observer<V> observer) {
        this.observer = observer;
    }
}
