package project.persons.workers;

import project.Observer;
import project.Timer;
import project.vehicles.Bike;
import project.vehicles.Scooter;
import project.vehicles.Vehicle;

public abstract class Worker implements Timer {
    protected int cpt = 0;

    protected int reparationTime;

    protected Vehicle vehicle;

    protected Observer observer;

    public abstract void tick();

    public boolean isOccupied() {
        return this.vehicle != null;
    }

    public void attach(Observer observer) {
        this.observer = observer;
    }

    public abstract void visit(Bike bike);

    public abstract void visit(Scooter scooter);
}
