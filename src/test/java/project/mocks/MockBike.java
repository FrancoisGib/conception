package project.mocks;

import project.persons.workers.Worker;
import project.vehicles.Bike;

public class MockBike extends Bike {

    public boolean acceptCalled = false;
    public boolean setLivesCalled = false;

    public MockBike() {
        super(1);
    }

    public void accept(Worker worker) {
        this.acceptCalled = true;
        super.accept(worker);
    }

    public void setLives(int lives) {
        this.setLivesCalled = true;
        super.setLives(lives);
    }
}
