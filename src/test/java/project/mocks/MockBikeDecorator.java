package project.mocks;

import project.vehicles.State;
import project.vehicles.bikes.Bike;
import project.vehicles.bikes.utilities.BikeDecorator;

public class MockBikeDecorator extends BikeDecorator {
    public boolean called = false;

    public MockBikeDecorator(Bike bike) {
        super(bike);
    }

    @Override
    public int getId() {
        this.called = true;
        return super.getId();
    }

    @Override
    public int getLives() {
        this.called = true;
        return super.getLives();
    }

    @Override
    public State getState() {
        this.called = true;
        return super.getState();
    }
}
