package app.mocks;

import app.persons.visitors.Visitor;
import app.vehicles.scooters.utilities.ScooterDecorator;

public class MockScooterDecorator extends ScooterDecorator {
    protected MockScooter vehicle;

    public boolean acceptCalled = false;
    public boolean setLivesCalled = false;

    public MockScooterDecorator() {
        super(new MockScooter());
    }

    public void accept(Visitor visitor) {
        this.acceptCalled = true;
        this.vehicle.accept(visitor);
    }

    public void setLives(int lives) {
        this.setLivesCalled = true;
        super.setLives(lives);
    }

    public MockScooter getVehicle() {
        return this.vehicle;
    }
}


