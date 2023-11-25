package app.mocks;

import app.persons.visitors.Visitor;
import app.vehicles.VehicleDecorator;

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
