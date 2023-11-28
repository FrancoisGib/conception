package project.vehicles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockVehicleDecorator;
import project.mocks.MockVehicle;


public abstract class VehicleDecoratorTest {
    protected static final String SEPARATOR = " / ";

    MockVehicle vehicle;

    String baseDescription;

    int initialLives;

    MockVehicleDecorator decorator;

    int initialId;

    @BeforeEach
    public void init() {
        this.initialId = 1;
        this.vehicle = new MockVehicle(this.initialId);
        this.baseDescription = MockVehicle.DESCRIPTION;
        this.decorator = new MockVehicleDecorator(this.vehicle);
        this.initialLives = 0;
    }

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
    public void decoratorGetParentId() {
        assertFalse(decorator.called);
        int id = decorator.getId();
        assertSame(id, vehicle.getId());
        assertSame(this.initialId, id);
        //assertNotSame(0, id);
        assertTrue(decorator.called);
    }

    @Test
    public void decoratorGetParentLives() {
        assertFalse(decorator.called);
        decorator.setLives(10);
        int lives = decorator.getLives();
        assertSame(lives, vehicle.getLives());
        assertTrue(decorator.called);
    }

    @Test
    public void decoratorGetParentState() {
        assertFalse(decorator.called);
        State state = State.RENTED;
        decorator.setState(state);
        assertNotNull(state);
        assertSame(state, decorator.getState());
        assertTrue(decorator.called);
    }

    @Test
    public void decrementLivesAndStateChangeWhenRented() {
        State state = State.STORED;
        decorator.setState(state);
        decorator.setLives(initialLives);
        assertSame(initialLives, decorator.getLives());
        assertSame(State.STORED, decorator.getState());
        decorator.rented();
        assertSame(initialLives - 1, decorator.getLives());
        assertSame(State.RENTED, decorator.getState());
    }

    public abstract void checkAcceptCallOnVehicle();
}
