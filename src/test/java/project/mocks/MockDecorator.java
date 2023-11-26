package project.mocks;

import project.persons.Visitor;
import project.vehicles.VehicleDecorator;

public class MockDecorator extends VehicleDecorator {
    public static final String DESCRIPTION = "MockDecorator";

    public int cpt = 0;

    public MockDecorator() {
        super(new MockVehicle());
        this.description = DESCRIPTION;
    }

    public void accept(Visitor visitor) {
        this.cpt++;
    }
} 
