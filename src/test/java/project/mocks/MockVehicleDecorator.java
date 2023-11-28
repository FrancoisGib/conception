package project.mocks;

import project.persons.Visitor;
import project.vehicles.State;
import project.vehicles.VehicleDecorator;

public class MockVehicleDecorator extends VehicleDecorator {

    public static final String DESCRIPTION = "MockDecorator";

    public boolean called = false;

    public MockVehicleDecorator(MockVehicle vehicle) {
        super(vehicle);
        this.description = DESCRIPTION;
    }

    @Override
    public void accept(Visitor visitor) {
        this.called = true;
    }

    @Override
    public int getId() {
        this.called = true;
        return super.getId();
    }

    @Override
    public int getLives() {
        this.called = true;
        return super.getLives();
    }

    @Override
    public State getState() {
        this.called = true;
        return super.getState();
    }
} 
