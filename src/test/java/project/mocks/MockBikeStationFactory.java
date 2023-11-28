package project.mocks;

import project.stations.factories.StationFactory;
import project.vehicles.factories.BikeFactory;

public class MockBikeStationFactory extends StationFactory {
    public MockBikeStationFactory(BikeFactory factory) {
        super(factory);
    }
} 
