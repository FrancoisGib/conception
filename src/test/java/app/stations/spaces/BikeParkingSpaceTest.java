package app.stations.spaces;

import org.junit.jupiter.api.BeforeEach;

import app.vehicles.ClassicBike;

public class BikeParkingSpaceTest extends ParkingSpaceTest<ClassicBike> {
    
    @Override
    @BeforeEach
    public void init() {
        super.init();
        this.vehicle = new ClassicBike(0);
    }
}
