package project.persons.workers;

import project.Timer;
import project.vehicles.Bike;
import project.vehicles.Scooter;
import project.vehicles.Vehicle;

public abstract class Worker implements Timer {
    public int cpt = 0;

    public int reparationTime;

    public Vehicle vehicle;

    public abstract void tick();

    public boolean isOccupied() {
        return this.vehicle != null;
    }

    public abstract void visit(Bike bike);

    public abstract void visit(Scooter scooter);
}
