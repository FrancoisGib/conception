package app.vehicles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.mocks.MockDecoratorComponent;
import app.mocks.MockVehicle;

public abstract class VehicleDecoratorTest {

    protected Vehicle vehicle;

    protected String basicDescription;

    @BeforeEach
    public void init() {
        this.vehicle = new MockVehicle();
        this.basicDescription = MockVehicle.DESCRIPTION;
    }

    @Test
    public void isNotDecoratedInstanceAndDescriptionCheck() {
        assertFalse(vehicle instanceof VehicleDecorator);
        assertEquals(basicDescription, vehicle.getDescription());
    }

    @Test
    public void isDecoratedInstanceAndDescriptionCheck() {
        vehicle = new MockDecoratorComponent(this.vehicle);
        assertInstanceOf(VehicleDecorator.class, vehicle);
        assertEquals(basicDescription + MockDecoratorComponent.DESCRIPTION, vehicle.getDescription());
    }
}
