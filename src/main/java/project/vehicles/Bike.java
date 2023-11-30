package project.vehicles;

import project.persons.workers.Worker;

public class Bike extends Vehicle {
    public static final String DESCRIPTION = "Bike";
    public static final int INITIAL_LIVES = 3;
    public static final int REPARATION_TIME = 5;

    public Bike(int id) {
        super(id);
        this.description = DESCRIPTION;
        this.lives = INITIAL_LIVES;
    }

    public void accept(Worker worker) {
        worker.visit(this);
    }
}
