package app.vehicles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.vehicles.bikes.Bike;
import app.vehicles.bikes.ClassicBike;
import app.vehicles.bikes.utilities.Backpack;
import app.vehicles.bikes.utilities.LuggageRack;

public class BikeDecoratorTest extends VehicleDecoratorTest {

    @Override
    @BeforeEach
    public void init() {
        this.vehicle = new ClassicBike(0);
        this.basicDescription = ClassicBike.DESCRIPTION;
    }

    @Test
    public void isDecoratedWithBackpack() {
        Bike bike = new Backpack(new ClassicBike(0));
        assertInstanceOf(Backpack.class, bike);
        assertEquals(basicDescription + Backpack.DESCRIPTION, bike.getDescription());
    }

    @Test
    public void isDecoratedWithLuggageRack() {
        Bike bike = new LuggageRack(new ClassicBike(0));
        assertInstanceOf(LuggageRack.class, bike);
        assertEquals(basicDescription + LuggageRack.DESCRIPTION, bike.getDescription());
    }

    @Test
    public void isDecoratedWithBackpackAndLuggageRack() {
        Bike bike = new LuggageRack(new Backpack(new ClassicBike(0)));
        assertInstanceOf(LuggageRack.class, bike);
        assertEquals(basicDescription + Backpack.DESCRIPTION + LuggageRack.DESCRIPTION, bike.getDescription());
    }
}
