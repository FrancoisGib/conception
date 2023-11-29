package project.vehicles.utilities;

import project.vehicles.Bike;

public class LuggageRack extends VehicleDecorator {
    public static final String DESCRIPTION = "Luggage Rack";

    public LuggageRack(Bike vehicle) {
        super(vehicle);
        this.description = DESCRIPTION;
    }
}
