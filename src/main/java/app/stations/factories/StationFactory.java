package app.stations.factories;

import java.util.ArrayList;
import java.util.List;

import app.stations.RentalStation;
import app.stations.spaces.ParkingSpace;
import app.stations.spaces.SpaceOccupiedException;
import app.vehicles.factories.VehicleFactory;

public abstract class StationFactory {
    private VehicleFactory factory;

    public StationFactory(VehicleFactory factory) {
        this.factory = factory;
    }

    public RentalStation createStation(int id) {
        int capacity = (int) (Math.random() * (RentalStation.MAX_CAPACITY));
        List<ParkingSpace> spaces = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            ParkingSpace space = new ParkingSpace();
            try {
                space.store(this.factory.createVehicle(i));
            } catch (SpaceOccupiedException e) {
                e.printStackTrace();
            }
            spaces.add(space);
        }
        return new RentalStation(id, spaces);
    }
        
}
