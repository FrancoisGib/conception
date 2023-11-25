package app.mocks;

import app.Observer;
import app.persons.Client;
import app.stations.RentalStation;
import app.vehicles.Vehicle;

public class MockObserver implements Observer {
    public int cpt = 0;

    public void vehicleRented(Vehicle vehicle, Client client, RentalStation station){
        this.cpt++;
    }

    public void vehicleStored(Vehicle vehicle, RentalStation station) {
        this.cpt++;
    }

    public void vehicleOutOfService(Vehicle vehicle) {
        this.cpt++;
    }

    public void vehicleStollen(Vehicle vehicle, Client client) {
        this.cpt++;
    }
}
