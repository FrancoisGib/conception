package app.vehicles.utilities;

import app.vehicles.Bike;

public class LuggageRack extends BikeDecorator {
    public static final String DESCRIPTION = " / Luggage rack";

    public LuggageRack(Bike bike) {
        super(bike);
    }

    public String getDescription() {
        return super.getDescription() + DESCRIPTION;
    }
}
