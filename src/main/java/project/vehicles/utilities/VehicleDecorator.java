package project.vehicles.utilities;

import project.persons.workers.Worker;
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

    public void accept(Worker worker) {
        this.vehicle.accept(worker);
    }

    public String getDescription() {
        return this.vehicle.getDescription() + SEPARATOR  + this.description;
    }
}
