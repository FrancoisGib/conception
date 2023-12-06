package project.vehicles.factories;

import project.vehicles.Vehicle;

/**
 * The VehicleFactory interface represents a factory for creating vehicles.
 */
public interface VehicleFactory {
    /**
     * Creates a vehicle with the specified ID.
     *
     * @param id the ID of the vehicle
     * @return the created vehicle
     */
    public Vehicle createVehicle(int id);
}
