package app.vehicles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.mocks.MockVehicle;

public class VehicleTest {
    protected MockVehicle vehicle;

    @BeforeEach
    public void init() {
        this.vehicle = new MockVehicle();
    }

    @Test
    public void cannotBeRentedBecauseAlreadyRented() {
        vehicle.setState(State.RENTED);
        assertFalse(vehicle.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseStollen() {
        vehicle.setState(State.STOLLEN);
        assertFalse(vehicle.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseInReparation() {
        vehicle.setState(State.REPARATION);
        assertFalse(vehicle.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseOutOfService() {
        vehicle.setState(State.OUT_OF_SERVICE);
        assertFalse(vehicle.isRentable());
    }

    @Test
    public void canBeRented() {
        vehicle.setState(State.STORED);
        vehicle.setLives(1);
        assertTrue(vehicle.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseLivesLessThanOne() {
        vehicle.setState(State.STORED);
        vehicle.setLives(0);
        assertFalse(vehicle.isRentable());
    }
}
