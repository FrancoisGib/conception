package project.mocks;

import project.persons.Visitor;
import project.vehicles.bikes.ClassicBike;

public class MockBike extends ClassicBike {

    public boolean acceptCalled = false;
    public boolean setLivesCalled = false;

    public MockBike() {
        super(1);
    }

    public void accept(Visitor visitor) {
        this.acceptCalled = true;
        super.accept(visitor);
    }

    public void setLives(int lives) {
        this.setLivesCalled = true;
        super.setLives(lives);
    }
}