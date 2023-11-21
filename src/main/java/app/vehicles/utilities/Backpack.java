package app.vehicles.utilities;

import app.vehicles.Bike;

public class Backpack extends BikeDecorator {
    public static final String DESCRIPTION = " / Backpack";
    
    public Backpack(Bike bike) {
        super(bike);
    }

    public String getDescription() {
        return super.getDescription() + DESCRIPTION;
    }
}
