package app.controlcenter;

import app.stations.RentalStation;
import app.vehicles.Vehicle;

public interface Observer<V extends Vehicle> {
    public void vehicleRented(V vehicle, RentalStation<V> station);
    public void vehicleStored(V vehicle, RentalStation<V> station);
}
