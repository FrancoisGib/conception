package project.mocks;

import project.Observer;
import project.stations.RentalStation;
import project.vehicles.Vehicle;

public class MockObserver implements Observer {
        public boolean vehicleRentedCalled = false;
        public boolean vehicleStoredCalled = false;
        public boolean vehicleOutOfServiceCalled = false;
        public boolean vehicleStolenCalled = false;
        public boolean vehicleBackFromStolenCalled = false;
        public boolean vehicleRepairedCalled = false;

        public void vehicleRented(Vehicle vehicle,RentalStation station) {
            vehicleRentedCalled = true;
        }
        public void vehicleStored(Vehicle vehicle, RentalStation station) {
            vehicleStoredCalled = true;
        }
        public void vehicleOutOfService(Vehicle vehicle) {
            vehicleOutOfServiceCalled = true;
        }
        public void vehicleStolen(Vehicle vehicle) {
            vehicleStolenCalled = true;
        }
        public void vehicleBackFromStolen(Vehicle vehicle) {
            vehicleBackFromStolenCalled = true;
        }
        public void vehicleRepaired(Vehicle vehicle) {
            vehicleRepairedCalled = true;
        }
    }