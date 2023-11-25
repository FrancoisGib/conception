package app.vehicles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.mocks.MockDecorator;
import app.mocks.MockUtility;
import app.mocks.MockVehicle;


public abstract class DecoratorTest {
    protected static final String SEPARATOR = " / ";

    Vehicle vehicle;

    String baseDescription;

    int initialLives;

    MockDecorator decorator;

    @BeforeEach
    public void init() {
        this.vehicle = new MockVehicle();
        this.baseDescription = MockVehicle.DESCRIPTION;
        this.initialLives = 0;
        this.decorator = new MockDecorator();
    }

    @Test
    public void isDecoratedWithComponent() {
        vehicle = new MockUtility(vehicle);
        assertInstanceOf(MockUtility.class, vehicle);
        assertEquals(this.baseDescription + SEPARATOR + MockUtility.DESCRIPTION, vehicle.getDescription());
    }

    @Test
    public void isNotDecorated() {
        vehicle = new MockVehicle();
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
    public void decoratorGetParentId() {
        assertSame(decorator.getId(), vehicle.getId());
    }

    @Test
    public void decoratorGetParentLives() {
        assertSame(decorator.getLives(), vehicle.getLives());
    }

    @Test
    public void decoratorGetParentState() {
        assertSame(decorator.getState(), vehicle.getState());
    }
}
