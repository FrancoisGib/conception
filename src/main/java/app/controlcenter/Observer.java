package app.controlcenter;

import app.stations.RentalStation;
import app.vehicles.Vehicle;

public interface Observer {
    public void vehicleRented(Vehicle vehicle, RentalStation station);
    public void vehicleStored(Vehicle vehicle, RentalStation station);
}
