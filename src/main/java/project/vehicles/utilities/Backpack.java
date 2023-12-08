package project.vehicles.utilities;

import project.vehicles.Vehicle;

/**
 * Represents a backpack that can be added to a bike.
 * Extends the VehicleDecorator class.
 */
public class Backpack extends VehicleDecorator {
    public static final String DESCRIPTION = "Backpack";

    /**
     * Constructs a Backpack object with the specified vehicle.
     * Sets the description to the predefined constant value.
     *
     * @param vehicle the bike to which the backpack is added
     */
    public Backpack(Vehicle vehicle) {
        super(vehicle);
        this.description = DESCRIPTION;
    }
}
