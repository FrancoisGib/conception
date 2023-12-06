package project.persons.workers;

import project.Observer;
import project.Timer;
import project.vehicles.Bike;
import project.vehicles.Scooter;
import project.vehicles.Vehicle;

/**
 * The abstract class representing a worker.
 * A worker is responsible for performing tasks and can be associated with a vehicle.
 * It implements the Timer interface to handle time-based operations.
 */
public abstract class Worker implements Timer {
    protected int cpt = 0;

    protected int reparationTime;

    protected Vehicle vehicle;

    protected Observer observer;

    /**
     * Performs the worker's task for a specific time interval.
     */
    public abstract void tick();

    /**
     * Checks if the worker is currently occupied with a vehicle.
     * @return true if the worker is occupied, false otherwise
     */
    public boolean isOccupied() {
        return this.vehicle != null;
    }

    /**
     * Attaches an observer to the worker.
     * @param observer the observer to attach
     */
    public void attach(Observer observer) {
        this.observer = observer;
    }

    /**
     * Visits a bike and performs specific actions.
     * @param bike the bike to visit
     */
    public abstract void visit(Bike bike);

    /**
     * Visits a scooter and performs specific actions.
     * @param scooter the scooter to visit
     */
    public abstract void visit(Scooter scooter);
}
