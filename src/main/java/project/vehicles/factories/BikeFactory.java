package project.vehicles.factories;

import project.vehicles.bikes.Bike;
import project.vehicles.bikes.ClassicBike;

public class BikeFactory implements VehicleFactory {
    public Bike createVehicle(int id) {
        return new ClassicBike(id);
    }
}
