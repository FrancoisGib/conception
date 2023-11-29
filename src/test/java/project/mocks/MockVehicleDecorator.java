package project.mocks;

import project.vehicles.utilities.VehicleDecorator;

public class MockVehicleDecorator extends VehicleDecorator {

    public static final String DESCRIPTION = "MockDecorator";

    public MockVehicleDecorator(MockVehicle vehicle) {
        super(vehicle);
        this.description = DESCRIPTION;
    }
} 
