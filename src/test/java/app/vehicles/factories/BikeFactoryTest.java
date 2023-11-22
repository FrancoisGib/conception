package app.vehicles.factories;

import org.junit.jupiter.api.BeforeEach;

public class BikeFactoryTest extends VehicleFactoryTest {
    @BeforeEach
    public void init() {
        this.factory = new BikeFactory();
    }
}
