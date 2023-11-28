package project.mocks;

import project.Observer;
import project.stations.RentalStation;
import project.vehicles.Vehicle;

public class MockObserver implements Observer {
    public int cpt = 0;

    public void vehicleRented(Vehicle vehicle, RentalStation station){
        cpt++;
    }

    public void vehicleStored(Vehicle vehicle, RentalStation station) {
        cpt++;
    }

    public void vehicleOutOfService(Vehicle vehicle) {
        cpt++;
    }

    public void vehicleStolen(Vehicle vehicle) {
        cpt++;
    }

    public void vehicleBackFromStolen(Vehicle vehicle) {
        cpt++;
    }
}
