package project.vehicles.bikes.utilities;

import project.vehicles.bikes.Bike;

public class Backpack extends BikeDecorator {
    public static final String DESCRIPTION = "Backpack";

    public Backpack(Bike bike) {
        super(bike);
        this.description = DESCRIPTION;
    }
}
