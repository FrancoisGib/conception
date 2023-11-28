package project.mocks;

import project.vehicles.State;
import project.vehicles.scooters.Scooter;
import project.vehicles.scooters.utilities.ScooterDecorator;

public class MockScooterDecorator extends ScooterDecorator {
    public boolean called = false;

    public MockScooterDecorator(Scooter scooter) {
        super(scooter);
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


