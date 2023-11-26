package project.mocks;

import project.persons.workers.Repairer;
import project.vehicles.Vehicle;
import project.vehicles.bikes.Bike;
import project.vehicles.scooters.Scooter;

public class MockRepairer extends Repairer {
    public boolean called = false;

    public void visit(Bike bike) {
        called = true;
        super.visit(bike);
    }

    public void visit(Scooter scooter) {
        called = true;
        super.visit(scooter);
    }

    public void visit(Vehicle vehicle) {
        called = true;
    }
}
