package project.vehicles.utilities;

import project.vehicles.Scooter;

public class FrontBag extends VehicleDecorator {
    public static final String DESCRIPTION = "Front bag";

    public FrontBag(Scooter scooter) {
        super(scooter);
        this.description = DESCRIPTION;
    }
}