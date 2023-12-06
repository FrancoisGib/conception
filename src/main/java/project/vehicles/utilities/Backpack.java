package project.vehicles.utilities;

import project.vehicles.Bike;

/**
 * Represents a backpack that can be added to a bike.
 * Extends the VehicleDecorator class.
 */
public class Backpack extends VehicleDecorator {
    public static final String DESCRIPTION = "Backpack";

    /**
     * Constructs a Backpack object with the specified bike.
     * Sets the description to the predefined constant value.
     *
     * @param bike the bike to which the backpack is added
     */
    public Backpack(Bike bike) {
        super(bike);
        this.description = DESCRIPTION;
    }
}
