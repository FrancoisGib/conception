package app.vehicles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.mocks.MockDecoratorComponent;
import app.mocks.MockVehicle;
import app.vehicles.utilities.VehicleDecorator;

public abstract class VehicleDecoratorTest {
    Vehicle vehicle;

    @BeforeEach
    public void init() {
        this.vehicle = new MockVehicle();
    }

    @Test
    public void isNotDecoratedInstanceAndDescriptionCheck() {
        assertFalse(vehicle instanceof VehicleDecorator);
        assertEquals(vehicle.getDescription(), vehicle.getDescription());
    }

    @Test
    public void isDecoratedInstanceAndDescriptionCheck() {
        vehicle = new MockDecoratorComponent();
        assertInstanceOf(VehicleDecorator.class, vehicle);
        assertEquals(vehicle.getDescription(), vehicle.getDescription());
    }
}
