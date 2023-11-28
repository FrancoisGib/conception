package project.mocks;

import project.stations.ParkingSpace;
import project.vehicles.Vehicle;

public class MockParkingSpace extends ParkingSpace {
    public int cpt = 0;

    protected MockVehicle vehicle;

    @Override
    public boolean store(Vehicle vehicle) {
        this.cpt++;
        return super.store(vehicle);
    }

    @Override
    public Vehicle remove() {
        this.cpt++;
        return super.remove();
    }
}
