package project.mocks;

import project.persons.workers.Worker;
import project.vehicles.Vehicle;

public class MockVehicle extends Vehicle {

    public boolean acceptCalled = false;
    public boolean setLivesCalled = false;

    public MockVehicle() {
        super(1);
    }

    public void accept(Worker worker) {
        this.acceptCalled = true;
    }

    public void setLives(int lives) {
        this.setLivesCalled = true;
        super.setLives(lives);
    }
}