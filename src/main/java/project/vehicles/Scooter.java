package project.vehicles;

import project.persons.workers.Worker;

public class Scooter extends Vehicle {
    public static final String DESCRIPTION = "Scooter";
    public static final int INITIAL_LIVES = 20;
    public static final int REPARATION_TIME = 3;

    public Scooter(int id) {
        super(id);
        this.description = DESCRIPTION;
        this.lives = INITIAL_LIVES;
    }

    public void accept(Worker worker) {
        worker.visit(this);
    }
}

