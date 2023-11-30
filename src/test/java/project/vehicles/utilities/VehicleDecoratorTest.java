package project.vehicles.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.vehicles.State;
import project.vehicles.Vehicle;
import project.mocks.MockVehicle;


public abstract class VehicleDecoratorTest {
    protected static final String SEPARATOR = " / ";

    public Vehicle vehicle;

    protected String baseDescription;

    protected int initialLives;

    public VehicleDecorator decorator;

    protected abstract Vehicle createVehicle();

    protected abstract VehicleDecorator createDecorator();

    @BeforeEach
    public void init() {
        this.vehicle = this.createVehicle();
        this.decorator = this.createDecorator();
    };

    @Test
    public void isDecoratedWithComponent() {
        assertInstanceOf(MockVehicleDecorator.class, decorator);
        assertEquals(this.baseDescription + SEPARATOR + MockVehicleDecorator.DESCRIPTION, decorator.getDescription());
    }

    @Test
    public void isNotDecorated() {
        assertInstanceOf(MockVehicle.class, vehicle);
        assertEquals(this.baseDescription, vehicle.getDescription());
    }

    @Test
    public void canBeRented() {
        decorator.setState(State.STORED);
        decorator.setLives(1);
        assertTrue(decorator.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseLivesLessThanOne() {;
        decorator.setState(State.STORED);
        decorator.setLives(0);
        assertFalse(decorator.isRentable());
    }

    @Test
    public abstract void vehicleAcceptCalledWhenAcceptVisitor();

    protected class MockVehicleDecorator {
        public static final String DESCRIPTION = "Mock Vehicle Decorator";
    }
}
