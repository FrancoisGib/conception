package app.vehicles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BikeTest {
    Bike bike;

    @BeforeEach
    public void init() {
        this.bike = new ClassicBike(0);
    }

    @Test
    public void cannotBeRentedBecauseAlreadyRented() {
        bike.setState(BikeState.RENTED);
        assertFalse(bike.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseStollen() {
        bike.setState(BikeState.STOLLEN);
        assertFalse(bike.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseInReparation() {
        bike.setState(BikeState.REPARATION);
        assertFalse(bike.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseOutOfService() {
        bike.setState(BikeState.OUT_OF_SERVICE);
        assertFalse(bike.isRentable());
    }

    @Test
    public void canBeRented() {
        bike.setState(BikeState.STORED);
        assertTrue(bike.isRentable());
    }
}
