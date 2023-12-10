package project.vehicles.factories;

import project.vehicles.Bike;

/**
 * A factory class for creating Bike objects.
 */
public class BikeFactory implements VehicleFactory<Bike> {
    /**
     * The counter for the number of bikes created.
     */
    private int cpt = 0;

    @Override
    /**
     * Creates a Bike object.
     * @return a Bike object.
     */
    public Bike createVehicle() {
        return new Bike(this.cpt++);
    }
}
