package app.vehicles.factories;

import app.vehicles.bikes.Bike;
import app.vehicles.bikes.ClassicBike;

public class BikeFactory implements VehicleFactory {
    public Bike createVehicle(int id) {
        return new ClassicBike(id);
    }
}
