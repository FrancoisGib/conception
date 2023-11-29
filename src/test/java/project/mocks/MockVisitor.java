package project.mocks;

import project.persons.Visitor;
import project.vehicles.Bike;
import project.vehicles.Scooter;

public class MockVisitor implements Visitor {
    public boolean called = false;

    public void visit(Bike bike) {
        bike.setLives(Bike.INITIAL_LIVES);
        this.called = true;
    }

    public void visit(Scooter scooter) {
        this.called = true;
        scooter.setLives(Scooter.INITIAL_LIVES);
    }
}

