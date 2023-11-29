package project.vehicles.utilities;

import project.persons.Visitor;
import project.vehicles.Vehicle;

public abstract class VehicleDecorator extends Vehicle {
    public static final String SEPARATOR = " / ";

    protected Vehicle vehicle;

    protected String description;

    protected int id;

    public VehicleDecorator(Vehicle vehicle) {
        super(vehicle.getId());
        this.vehicle = vehicle;
    }

    public void accept(Visitor visitor) {
        this.vehicle.accept(visitor);
    }

    public String getDescription() {
        return this.vehicle.getDescription() + SEPARATOR  + this.description;
    }
}
