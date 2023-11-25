package app.persons.visitors;

import app.Timer;
import app.vehicles.Vehicle;

public abstract class Worker implements Timer {
    public int cpt = 0;

    public int reparationTime;

    public Vehicle vehicle;

    public int initialVehicleLives;

    public abstract void tick();
}
