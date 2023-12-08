package project.mocks;

import project.Observer;
import project.persons.workers.Repairer;
import project.vehicles.Bike;
import project.vehicles.Scooter;

public class MockRepairer extends Repairer {
    public boolean acceptBikeCalled = false;
    public boolean acceptScooterCalled = false;
    public boolean attachCalled = false;

    public void visit(Bike bike) {
        acceptBikeCalled = true;
        super.visit(bike);
    }

    public void visit(Scooter scooter) {
        acceptScooterCalled = true;
        super.visit(scooter);
    }

    public void attach(Observer observer) {
        attachCalled = true;
        super.attach(observer);
    }
}