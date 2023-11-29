package project.vehicles.utilities;

import project.vehicles.Bike;

public class Backpack extends VehicleDecorator {
    public static final String DESCRIPTION = "Backpack";

    public Backpack(Bike bike) {
        super(bike);
        this.description = DESCRIPTION;
    }
}
