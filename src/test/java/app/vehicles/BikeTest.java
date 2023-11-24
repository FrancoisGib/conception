package app.vehicles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.vehicles.bikes.Bike;
import app.vehicles.bikes.BikeState;
import app.vehicles.bikes.ClassicBike;

public class BikeTest extends VehicleTest {
    private Bike vehicle;

    @BeforeEach
    public void init() {
        super.init();
        this.vehicle = new ClassicBike(0);
        this.vehicle.setState(BikeState.RENTED);
    }

    @Test
    public void cannotBeRentedBecauseAlreadyRented() {
        vehicle.setState(BikeState.RENTED);
        assertFalse(vehicle.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseStollen() {
        vehicle.setState(BikeState.STOLLEN);
        assertFalse(vehicle.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseInReparation() {
        vehicle.setState(BikeState.REPARATION);
        assertFalse(vehicle.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseOutOfService() {
        vehicle.setState(BikeState.OUT_OF_SERVICE);
        assertFalse(vehicle.isRentable());
    }

    @Test
    public void canBeRented() {
        vehicle.setState(BikeState.STORED);
        assertTrue(vehicle.isRentable());
    }
}
