package project.vehicles.utilities;

import project.vehicles.Bike;

/**
 * The LuggageRack class represents a decorator for a Bike object that adds a luggage rack feature.
 * It extends the VehicleDecorator class.
 */
public class LuggageRack extends VehicleDecorator {
    public static final String DESCRIPTION = "Luggage Rack";

    /**
     * Constructs a new LuggageRack object with the specified Bike object as the base vehicle.
     * Sets the description of the LuggageRack to the predefined DESCRIPTION constant.
     * 
     * @param vehicle the Bike object to decorate with a luggage rack
     */
    public LuggageRack(Bike vehicle) {
        super(vehicle);
        this.description = DESCRIPTION;
    }
}
