package app.mocks;

import app.vehicles.Vehicle;
import app.vehicles.VehicleDecorator;

public class MockDecoratorComponent extends VehicleDecorator {
    public static final String DESCRIPTION = " / Decorator";

    public MockDecoratorComponent(Vehicle vehicle) {
        super(vehicle);
        this.description = DESCRIPTION;
    }
}
