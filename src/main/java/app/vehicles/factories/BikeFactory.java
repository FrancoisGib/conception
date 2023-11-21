package app.vehicles.factories;

import app.vehicles.Bike;
import app.vehicles.ClassicBike;

public class BikeFactory implements VehicleFactory<Bike> {
    public Bike createVehicle(int id) {
        return new ClassicBike(id);
    }
}
