package project.mocks;

import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceEmptyException;
import project.stations.spaces.SpaceFullException;
import project.vehicles.Vehicle;

public class MockParkingSpace extends ParkingSpace {
    public int cpt = 0;

    protected MockVehicle vehicle;

    @Override
    public void store(Vehicle vehicle) throws SpaceFullException {
        this.cpt++;
        super.store(vehicle);
    }

    @Override
    public Vehicle remove() throws SpaceEmptyException {
        this.cpt++;
        return super.remove();
    }
}
