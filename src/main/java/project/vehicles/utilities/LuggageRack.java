package project.vehicles.utilities;

import project.vehicles.Vehicle;

/**
 * The LuggageRack class represents a decorator for a vehicle object that adds a luggage rack feature.
 * It extends the VehicleDecorator class.
 */
public class LuggageRack extends VehicleDecorator {
    public static final String DESCRIPTION = "Luggage Rack";

    /**
     * Constructs a new LuggageRack object with the specified Vehicle object as the base vehicle.
     * Sets the description of the LuggageRack to the predefined DESCRIPTION constant.
     * 
     * @param vehicle the Bike object to decorate with a luggage rack
     */
    public LuggageRack(Vehicle vehicle) {
        super(vehicle);
        this.description = DESCRIPTION;
    }
}
