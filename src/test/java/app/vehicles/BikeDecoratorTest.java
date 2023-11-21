package app.vehicles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.vehicles.utilities.Backpack;
import app.vehicles.utilities.LuggageRack;

public class BikeDecoratorTest extends VehicleDecoratorTest {

    @Override
    @BeforeEach
    public void init() {
        this.vehicle = new ClassicBike(0);
    }

    @Test
    public void isDecoratedWithBackpack() {
        Bike bike = new Backpack(new ClassicBike(0));
        assertInstanceOf(Backpack.class, bike);
        assertEquals(ClassicBike.DESCRIPTION + Backpack.DESCRIPTION, bike.getDescription());
    }

    @Test
    public void isDecoratedWithLuggageRack() {
        Bike bike = new LuggageRack(new ClassicBike(0));
        assertInstanceOf(LuggageRack.class, bike);
        assertEquals(ClassicBike.DESCRIPTION + LuggageRack.DESCRIPTION, bike.getDescription());
    }

    @Test
    public void isDecoratedWithBackpackAndLuggageRack() {
        Bike bike = new LuggageRack(new Backpack(new ClassicBike(0)));
        assertInstanceOf(LuggageRack.class, bike);
        assertEquals(ClassicBike.DESCRIPTION + Backpack.DESCRIPTION + LuggageRack.DESCRIPTION, bike.getDescription());
    }
}
