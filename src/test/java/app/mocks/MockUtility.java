package app.mocks;

import app.persons.visitors.Visitor;
import app.vehicles.Vehicle;
import app.vehicles.VehicleDecorator;

public class MockUtility extends VehicleDecorator {
    public static final String DESCRIPTION = "Mock Utility";

    public boolean called = false;

    public MockUtility(Vehicle vehicle) {
        super(vehicle);
        this.description = DESCRIPTION;
    }

    public void accept(Visitor visitor) {
        called = true;
    }
}
