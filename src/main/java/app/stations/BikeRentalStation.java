package app.stations;

import java.util.List;

import app.stations.spaces.ParkingSpace;
import app.vehicles.Bike;

public class BikeRentalStation extends RentalStation<Bike> {
    public BikeRentalStation(int id, List<ParkingSpace<Bike>> bikes) {
        super(id, bikes);
    }
}
