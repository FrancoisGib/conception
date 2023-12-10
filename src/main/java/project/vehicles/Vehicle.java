package project.vehicles;

import lombok.Getter;
import lombok.Setter;
import project.persons.workers.Worker;

/**
 * The abstract base class to represent vehicles.
 */
public abstract class Vehicle {
    @Getter
    /**
     * The unique identifier for a vehicle.
     */
    protected int id;

    @Getter
    /**
     * The description of the vehicle.
     */
    protected String description;

    @Getter @Setter
    /**
     * The number of lives of the vehicle.
     */
    protected int lives;

    @Getter @Setter
    /**
     * The state of the vehicle.
     */
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

