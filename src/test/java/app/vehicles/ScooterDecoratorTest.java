package app.vehicles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.mocks.MockScooterDecorator;
import app.vehicles.scooters.ClassicScooter;
import app.vehicles.scooters.Scooter;
import app.vehicles.scooters.utilities.FrontBasket;

public class ScooterDecoratorTest extends DecoratorTest {
    protected static final String SEPARATOR = " / ";

    Scooter vehicle;

    String baseDescription;

    int initialLives;

    MockScooterDecorator decorator;

    @BeforeEach
    public void init() {
        super.init();
        this.vehicle = new ClassicScooter(0);
        this.baseDescription = Scooter.DESCRIPTION;
        this.initialLives = Scooter.INITIAL_LIVES;
        this.decorator = new MockScooterDecorator();
    }

    @Test
    public void isDecoratedWithFrontBasket() {
        vehicle = new FrontBasket(vehicle);
        assertInstanceOf(FrontBasket.class, vehicle);
        assertEquals(baseDescription + SEPARATOR + FrontBasket.DESCRIPTION, vehicle.getDescription());
    }
}
