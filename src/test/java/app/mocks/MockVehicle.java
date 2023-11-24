package app.mocks;

import app.vehicles.ClassicVehicle;

public class MockVehicle extends ClassicVehicle {
    public static final String DESCRIPTION = "Vehicle";

    public MockVehicle() {
        super(0);
        this.description = "Vehicle";
    }
}
