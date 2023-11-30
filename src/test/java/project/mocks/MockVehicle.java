package project.mocks;

import project.persons.workers.Worker;
import project.vehicles.Vehicle;

public class MockVehicle extends Vehicle {
    private static final String DESCRIPTION = "Mock Vehicle";

    public boolean acceptCalled = false;
    public boolean setLivesCalled = false;
    public boolean getDescriptionCalled = false;

    public MockVehicle(int id) {
        super(id);
        this.description = DESCRIPTION;
        this.lives = 1;
    }

    public void accept(Worker worker) {
        this.acceptCalled = true;
    }

    public void setLives(int lives) {
        this.setLivesCalled = true;
        super.setLives(lives);
    }

    public String getDescription() {
        getDescriptionCalled = true;
        return super.getDescription();
    }
}