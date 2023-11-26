package project.mocks;

import project.Observer;
import project.stations.RentalStation;
import project.vehicles.Vehicle;

public class MockObserver implements Observer {
    public boolean called = false;

    public void vehicleRented(Vehicle vehicle, RentalStation station){
        called = true;
    }

    public void vehicleStored(Vehicle vehicle, RentalStation station) {
        called = true;
    }

    public void vehicleOutOfService(Vehicle vehicle) {
        called = true;
    }

    public void vehicleStollen(Vehicle vehicle) {
        called = true;
    }
}
