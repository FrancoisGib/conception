package project.vehicles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockRepairer;
import project.mocks.MockScooter;
import project.mocks.MockScooterDecorator;
import project.vehicles.scooters.ClassicScooter;
import project.vehicles.scooters.Scooter;
import project.vehicles.scooters.utilities.FrontBasket;

public class ScooterDecoratorTest extends VehicleDecoratorTest {
    protected static final String SEPARATOR = " / ";

    String baseDescription;

    MockScooter vehicle;

    int initialLives;

    MockScooterDecorator decorator;

    Scooter classicVehicle;

    @BeforeEach
    public void init() {
        super.init();
        this.vehicle = new MockScooter();
        this.baseDescription = Scooter.DESCRIPTION;
        this.initialLives = Scooter.INITIAL_LIVES;
        this.decorator = new MockScooterDecorator(this.vehicle);
        this.classicVehicle = new ClassicScooter(0);
    }

    @Test
    public void isDecoratedWithFrontBasket() {
        classicVehicle = new FrontBasket(classicVehicle);
        assertInstanceOf(FrontBasket.class, classicVehicle);
        assertEquals(baseDescription + SEPARATOR + FrontBasket.DESCRIPTION, classicVehicle.getDescription());
    }

    @Test
    public void checkAcceptCallOnVehicle() {
        MockRepairer repairer = new MockRepairer();
        assertFalse(vehicle.acceptCalled);
        decorator.accept(repairer);
        assertTrue(vehicle.acceptCalled);
    }
}
