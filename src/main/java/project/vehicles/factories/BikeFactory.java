package project.vehicles.factories;

import project.vehicles.Bike;

public class BikeFactory implements VehicleFactory {
    public Bike createVehicle(int id) {
        return new Bike(id);
    }
}
