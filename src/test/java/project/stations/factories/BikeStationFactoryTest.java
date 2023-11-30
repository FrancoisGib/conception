package project.stations.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import project.mocks.MockBike;
import project.vehicles.factories.BikeFactory;
import project.vehicles.factories.VehicleFactory;


public class BikeStationFactoryTest extends StationFactoryTest {

    protected BikeStationFactory createStationFactory() {
        return new BikeStationFactory();
    }

    protected VehicleFactory createVehicleFactory() {
        return new MockBikeFactory();
    }

    @Test
    public void createStationCheckNumberOfCallOnBikeFactoryCreateVehicleMethod() {
        MockBikeFactory bikeFactory = new MockBikeFactory();
        MockBikeStationFactory bikeStationFactory = new MockBikeStationFactory(bikeFactory);
        bikeStationFactory.createStation(ID, CAPACITY);
        assertEquals(CAPACITY, bikeFactory.cpt);
    }
    
    private class MockBikeStationFactory extends BikeStationFactory {
        public MockBikeStationFactory(MockBikeFactory factory) {
            super();
            this.factory = factory;
        }
    }

    private class MockBikeFactory extends BikeFactory {
        private int cpt = 0;

        public MockBike createVehicle(int id) {
            this.cpt++;
            return new MockBike();
        }
    }

    

}
