package project.vehicles;

import lombok.Getter;
import lombok.Setter;
import project.persons.workers.Worker;

/**
 * The abstract base class for all vehicles.
 */
public abstract class Vehicle {
    @Getter
    protected int id;

    @Getter
    protected String description;

    @Getter @Setter
    protected int lives;

    @Getter @Setter
    protected State state;

    /**
     * Constructs a new Vehicle object with the specified ID.
     *
     * @param id the ID of the vehicle
     */
    public Vehicle(int id) {
        this.id = id;
    }

    /**
     * Checks if the vehicle is rentable.
     *
     * @return true if the vehicle is rentable, false otherwise
     */
    public boolean isRentable() {
        return this.state == State.STORED && this.lives > 0;
    }

    /**
     * Accepts a worker to perform an operation on the vehicle.
     *
     * @param worker the worker performing the operation
     */
    public abstract void accept(Worker worker);
}

