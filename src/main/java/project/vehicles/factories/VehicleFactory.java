package project.vehicles.factories;

import project.vehicles.Vehicle;

/**
 * The VehicleFactory interface represents a factory for creating vehicles.
 */
public interface VehicleFactory<T extends Vehicle> {
    /**
     * Creates a vehicle.
     *
     * @return the created vehicle
     */
    public Vehicle createVehicle();
}
