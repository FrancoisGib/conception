package app.vehicles;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.mocks.MockVehicle;

public abstract class VehicleTest {
    protected Vehicle vehicle;

    @BeforeEach
    public void init() {
        this.vehicle = new MockVehicle();
    }

    @Test
    public void cannotBeRented() {
        assertFalse(vehicle.isRentable());
    }
}
