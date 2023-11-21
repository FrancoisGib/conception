package app.stations.factories;

import java.util.ArrayList;
import java.util.List;

import app.stations.RentalStation;
import app.stations.spaces.ParkingSpace;
import app.stations.spaces.SpaceOccupiedException;
import app.vehicles.Bike;
import app.vehicles.factories.BikeFactory;

public class BikeStationFactory implements StationFactory<Bike> {
    public RentalStation<Bike> createStation(int id) {
        int capacity = (int) (Math.random() * (RentalStation.MAX_CAPACITY + RentalStation.MIN_CAPACITY)
                - RentalStation.MIN_CAPACITY);
        List<ParkingSpace<Bike>> spaces = new ArrayList<>();
        BikeFactory factory = new BikeFactory();
        for (int i = 0; i < capacity; i++) {
            ParkingSpace<Bike> space = new ParkingSpace<Bike>();
            try {
                space.store(factory.createVehicle(i));
            } catch (SpaceOccupiedException e) {
                e.printStackTrace();
            }
            spaces.add(space);
        }
        return new RentalStation<Bike>(id, spaces);
    }
}
