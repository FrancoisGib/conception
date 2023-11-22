package app.mocks;

import app.vehicles.utilities.VehicleDecorator;

public class MockDecoratorComponent extends VehicleDecorator {
    public static final String DESCRIPTION = " / Decorator";

    public MockDecoratorComponent() {
        super(new MockVehicle());
    }

    public String getDescription() {
        return super.getDescription() + DESCRIPTION;
    }
}
