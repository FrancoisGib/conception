package project.vehicles.utilities;

import project.persons.workers.Worker;
import project.vehicles.Vehicle;

/**
 * The abstract class VehicleDecorator represents a decorator for a Vehicle object.
 * It extends the Vehicle class and provides additional functionality to the decorated vehicle.
 */
public abstract class VehicleDecorator extends Vehicle {
    public static final String SEPARATOR = " / ";

    protected Vehicle vehicle;

    protected String description;

    protected int id;

    /**
     * Constructs a VehicleDecorator object with the specified vehicle to be decorated.
     *
     * @param vehicle the vehicle to be decorated
     */
    public VehicleDecorator(Vehicle vehicle) {
        super(vehicle.getId());
        this.vehicle = vehicle;
    }

    /**
     * Accepts a Worker object and delegates the acceptance to the decorated vehicle.
     *
     * @param worker the worker to accept
     */
    public void accept(Worker worker) {
        this.vehicle.accept(worker);
    }

    /**
     * Returns the description of the decorated vehicle.
     * The description includes the description of the decorated vehicle and the decorator's description, separated by a separator.
     *
     * @return the description of the decorated vehicle
     */
    public String getDescription() {
        return this.vehicle.getDescription() + SEPARATOR  + this.description;
    }
}
