package app.mocks;

import app.vehicles.Vehicle;
import app.vehicles.utilities.VehicleDecorator;

public class MockDecoratorComponent extends VehicleDecorator<Vehicle> {
    public static final String DESCRIPTION = " / Decorator";

    public MockDecoratorComponent() {
        super(new MockVehicle());
    }

    public String getDescription() {
        return super.getDescription() + DESCRIPTION;
    }
}
