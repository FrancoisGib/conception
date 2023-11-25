package app.mocks;

import app.persons.visitors.Repairer;
import app.vehicles.Vehicle;
import app.vehicles.bikes.Bike;
import app.vehicles.scooters.Scooter;

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
