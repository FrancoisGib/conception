package project.mocks;

import project.stations.RentalStation;
import project.stations.factories.StationFactory;

public class MockStationFactory extends StationFactory {
    public boolean called = false;

    public MockStationFactory(MockVehicleFactory factory) {
        super(factory);
    }

    public RentalStation createStation(int id, int capacity) {
        this.called = true;
        return super.createStation(id, capacity);
    }
} 
