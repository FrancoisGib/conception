package project.vehicles;

import project.persons.workers.Worker;

/**
 * Represents a Scooter, which is a type of Vehicle.
 */
public class Scooter extends Vehicle {
    public static final String DESCRIPTION = "Scooter";
    public static final int INITIAL_LIVES = 20;
    public static final int REPARATION_TIME = 3;

    /**
     * Constructs a Scooter object with the specified ID.
     *
     * @param id the ID of the Scooter
     */
    public Scooter(int id) {
        super(id);
        this.description = DESCRIPTION;
        this.lives = INITIAL_LIVES;
    }

    /**
     * Accepts a Worker and allows them to visit this Scooter.
     *
     * @param worker the Worker to accept
     */
    public void accept(Worker worker) {
        worker.visit(this);
    }
}

