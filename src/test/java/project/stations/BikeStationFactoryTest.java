package project.stations;

import org.junit.jupiter.api.BeforeEach;

import project.mocks.MockBikeStationFactory;

import project.vehicles.factories.BikeFactory;

public class BikeStationFactoryTest extends StationFactoryTest {
    BikeFactory vehicleFactory;

    MockBikeStationFactory factory;

    @BeforeEach
    @Override
    public void init() {
        super.init();
        this.vehicleFactory = new BikeFactory();
        this.factory = new MockBikeStationFactory(this.vehicleFactory);
    }
}
