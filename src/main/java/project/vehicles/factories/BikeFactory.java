package project.vehicles.factories;

import project.vehicles.Bike;

/**
 * A factory class for creating Bike objects.
 */
public class BikeFactory implements VehicleFactory {
    public Bike createVehicle(int id) {
        return new Bike(id);
    }
}
