package app.stations;

import app.controlcenter.Observer;
import app.vehicles.Vehicle;

public interface Subject<V extends Vehicle> {
    public void attach(Observer<V> o);
}
