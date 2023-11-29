package project.mocks;

import project.persons.Visitor;
import project.vehicles.Vehicle;

public class MockVehicle extends Vehicle {
    public static final String DESCRIPTION = "MockVehicle";

    public boolean called = false;

    public MockVehicle(int id) {
        super(id);
        this.description = DESCRIPTION;
    }

    public void accept(Visitor visitor) {
        this.called = true;
    }
}
