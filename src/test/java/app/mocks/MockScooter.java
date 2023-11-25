package app.mocks;

import app.persons.visitors.Visitor;
import app.vehicles.scooters.ClassicScooter;

public class MockScooter extends ClassicScooter {
    public boolean acceptCalled = false;
    public boolean setLivesCalled = false;

    public MockScooter() {
        super(0);
    }

    public void accept(Visitor visitor) {
        super.accept(visitor);
        this.acceptCalled = true;
    }

    public void setLives(int lives) {
        this.setLivesCalled = true;
        super.setLives(lives);
    }
}