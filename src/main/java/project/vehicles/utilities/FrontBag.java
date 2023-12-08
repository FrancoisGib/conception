package project.vehicles.utilities;

import project.vehicles.Vehicle;

/**
 * Represents a front bag for a scooter.
 * This class is a concrete decorator that adds a front bag to a scooter.
 */
public class FrontBag extends VehicleDecorator {
    public static final String DESCRIPTION = "Front bag";

    /**
     * Constructs a new FrontBag object.
     * 
     * @param vehicle the vehicle to decorate with a front bag
     */
    public FrontBag(Vehicle vehicle) {
        super(vehicle);
        this.description = DESCRIPTION;
    }
}