package project.persons.workers;

import project.Timer;
import project.persons.Visitor;
import project.vehicles.Vehicle;

public abstract class Worker implements Timer, Visitor {
    public int cpt = 0;

    public int reparationTime;

    public Vehicle vehicle;

    public abstract void tick();

    public boolean isOccupied() {
        return this.vehicle != null;
    }
}
