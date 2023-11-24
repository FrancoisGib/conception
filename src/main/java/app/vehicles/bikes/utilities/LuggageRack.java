package app.vehicles.bikes.utilities;

import app.vehicles.bikes.Bike;

public class LuggageRack extends BikeDecorator {
    public static final String DESCRIPTION = " / Luggage rack";

    public LuggageRack(Bike bike) {
        super(bike);
        this.description = DESCRIPTION;
    }
}
