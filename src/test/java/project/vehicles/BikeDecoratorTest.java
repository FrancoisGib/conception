package project.vehicles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockBike;
import project.mocks.MockBikeDecorator;
import project.mocks.MockRepairer;
import project.vehicles.bikes.Bike;
import project.vehicles.bikes.ClassicBike;
import project.vehicles.bikes.utilities.Backpack;
import project.vehicles.bikes.utilities.BikeDecorator;
import project.vehicles.bikes.utilities.LuggageRack;

public class BikeDecoratorTest extends VehicleDecoratorTest {
    protected static final String SEPARATOR = " / ";

    MockBike vehicle;

    String baseDescription;

    int initialLives;

    BikeDecorator decorator;

    Bike classicVehicle;

    @BeforeEach
    public void init() {
        super.init();
        this.vehicle = new MockBike();
        this.decorator = new MockBikeDecorator(this.vehicle);
        this.baseDescription = Bike.DESCRIPTION;
        this.initialLives = Bike.INITIAL_LIVES;
        this.classicVehicle = new ClassicBike(0);
    }

    @Test
    public void isDecoratedWithBackpack() {
        classicVehicle = new Backpack(classicVehicle);
        assertInstanceOf(Backpack.class, classicVehicle);
        assertEquals(baseDescription + SEPARATOR + Backpack.DESCRIPTION, classicVehicle.getDescription());
    }

    @Test
    public void isDecoratedWithLuggageRack() {
        classicVehicle = new LuggageRack(classicVehicle);
        assertInstanceOf(LuggageRack.class, classicVehicle);
        assertEquals(baseDescription + SEPARATOR + LuggageRack.DESCRIPTION, classicVehicle.getDescription());
    }

    @Test
    public void isDecoratedWithBackpackAndLuggageRack() {
        classicVehicle = new LuggageRack(new Backpack(classicVehicle));
        assertInstanceOf(LuggageRack.class, classicVehicle);
        assertEquals(baseDescription + SEPARATOR + Backpack.DESCRIPTION + SEPARATOR + LuggageRack.DESCRIPTION, classicVehicle.getDescription());
    }

    @Test
    public void checkAcceptCallOnVehicle() {
        MockRepairer repairer = new MockRepairer();
        assertFalse(vehicle.acceptCalled);
        decorator.accept(repairer);
        assertTrue(vehicle.acceptCalled);
    }
}
