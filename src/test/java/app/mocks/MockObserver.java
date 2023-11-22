package app.mocks;

import app.Observer;
import app.stations.RentalStation;
import app.vehicles.Vehicle;

public class MockObserver implements Observer {
    public int cpt = 0;

    public void vehicleRented(Vehicle vehicle, RentalStation station){
        this.cpt++;
    }

    public void vehicleStored(Vehicle vehicle, RentalStation station) {
        this.cpt++;
    }
}
