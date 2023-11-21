package app.redistributions;

import java.util.List;

import app.stations.RentalStation;
import app.vehicles.Vehicle;

public interface Redistribution<V extends Vehicle> {
    public void redistribute(List<RentalStation<V>> stations);
}
