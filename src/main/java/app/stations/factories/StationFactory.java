package app.stations.factories;

import app.stations.RentalStation;
import app.vehicles.Vehicle;

public interface StationFactory<V extends Vehicle> {
    public RentalStation<V> createStation(int id);
        
}
