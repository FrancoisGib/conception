package project.vehicles;

import project.persons.workers.Worker;

/**
 * Represents a Bike, which is a type of Vehicle.
 */
public class Bike extends Vehicle {
    public static final String DESCRIPTION = "Bike";
    public static final int INITIAL_LIVES = 3;
    public static final int REPARATION_TIME = 5;

    /**
     * Constructs a Bike object with the specified ID.
     *
     * @param id the ID of the Bike
     */
    public Bike(int id) {
        super(id);
        this.description = DESCRIPTION;
        this.lives = INITIAL_LIVES;
    }

    /**
     * Accepts a Worker and allows them to visit the Bike.
     *
     * @param worker the Worker visiting the Bike
     */
    public void accept(Worker worker) {
        worker.visit(this);
    }
}
