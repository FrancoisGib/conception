package app.vehicles.bikes.utilities;

import app.vehicles.bikes.Bike;

public class LuggageRack extends BikeDecorator {
    public static final String DESCRIPTION = "Luggage Rack";

    public LuggageRack(Bike vehicle) {
        super(vehicle);
        this.description = DESCRIPTION;
    }
}
