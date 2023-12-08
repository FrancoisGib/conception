package project.vehicles.factories;

import project.vehicles.Bike;

/**
 * A factory class for creating Bike objects.
 */
public class BikeFactory implements VehicleFactory<Bike> {
    private int cpt = 0;

    public Bike createVehicle() {
        return new Bike(this.cpt++);
    }
}
