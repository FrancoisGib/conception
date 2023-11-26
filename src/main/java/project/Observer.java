package project;

import project.stations.RentalStation;
import project.vehicles.Vehicle;

public interface Observer {
    public void vehicleRented(Vehicle vehicle,RentalStation station);
    public void vehicleStored(Vehicle vehicle, RentalStation station);
    public void vehicleOutOfService(Vehicle vehicle);
    public void vehicleStollen(Vehicle vehicle);
}
