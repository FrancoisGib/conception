package app;

import app.persons.Client;
import app.stations.RentalStation;
import app.vehicles.Vehicle;

public interface Observer {
    public void vehicleRented(Vehicle vehicle, Client client,RentalStation station);
    public void vehicleStored(Vehicle vehicle, RentalStation station);
    public void vehicleOutOfService(Vehicle vehicle);
    public void vehicleStollen(Vehicle vehicle, Client client);
}
