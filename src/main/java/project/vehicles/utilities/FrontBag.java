package project.vehicles.utilities;

import project.vehicles.Scooter;

/**
 * Represents a front bag for a scooter.
 * This class is a concrete decorator that adds a front bag to a scooter.
 */
public class FrontBag extends VehicleDecorator {
    public static final String DESCRIPTION = "Front bag";

    /**
     * Constructs a new FrontBag object.
     * 
     * @param scooter the scooter to decorate with a front bag
     */
    public FrontBag(Scooter scooter) {
        super(scooter);
        this.description = DESCRIPTION;
    }
}