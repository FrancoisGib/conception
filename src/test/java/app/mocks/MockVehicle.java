package app.mocks;

import app.vehicles.Vehicle;

public class MockVehicle implements Vehicle {
    public static final String DESCRIPTION = "Vehicle";

    public int getId() {
        return 0;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public boolean isRentable() {
        return true;
    }
}
