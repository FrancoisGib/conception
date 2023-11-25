package app.vehicles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.mocks.MockBikeDecorator;
import app.vehicles.bikes.Bike;
import app.vehicles.bikes.ClassicBike;
import app.vehicles.bikes.utilities.Backpack;
import app.vehicles.bikes.utilities.BikeDecorator;
import app.vehicles.bikes.utilities.LuggageRack;

public class BikeDecoratorTest extends DecoratorTest {
    protected static final String SEPARATOR = " / ";

    Bike vehicle;

    String baseDescription;

    int initialLives;

    BikeDecorator decorator;

    @BeforeEach
    public void init() {
        super.init();
        this.vehicle = new ClassicBike(0);
        this.baseDescription = Bike.DESCRIPTION;
        this.initialLives = Bike.INITIAL_LIVES;
        this.decorator = new MockBikeDecorator();
    }

    @Test
    public void isDecoratedWithBackpack() {
        vehicle = new Backpack(vehicle);
        assertInstanceOf(Backpack.class, vehicle);
        assertEquals(baseDescription + SEPARATOR + Backpack.DESCRIPTION, vehicle.getDescription());
    }

    @Test
    public void isDecoratedWithLuggageRack() {
        vehicle = new LuggageRack(vehicle);
        assertInstanceOf(LuggageRack.class, vehicle);
        assertEquals(baseDescription + SEPARATOR + LuggageRack.DESCRIPTION, vehicle.getDescription());
    }

    @Test
    public void isDecoratedWithBackpackAndLuggageRack() {
        vehicle = new LuggageRack(new Backpack(vehicle));
        assertInstanceOf(LuggageRack.class, vehicle);
        assertEquals(baseDescription + SEPARATOR + Backpack.DESCRIPTION + SEPARATOR + LuggageRack.DESCRIPTION, vehicle.getDescription());
    }
}
