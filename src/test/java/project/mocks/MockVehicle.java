package project.mocks;

import project.persons.Visitor;
import project.vehicles.ClassicVehicle;

public class MockVehicle extends ClassicVehicle {
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
