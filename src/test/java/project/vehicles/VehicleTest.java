package project.vehicles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class VehicleTest {
    protected Vehicle vehicle;

    protected abstract Vehicle createVehicle();

    @BeforeEach
    public void init() {
        this.vehicle = this.createVehicle();
    }

    @Test
    public void cannotBeRentedBecauseAlreadyRented() {
        vehicle.setState(State.RENTED);
        assertFalse(vehicle.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseStollen() {
        vehicle.setState(State.STOLEN);
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
