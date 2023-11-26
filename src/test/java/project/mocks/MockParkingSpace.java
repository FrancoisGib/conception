package project.mocks;

import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceEmptyException;
import project.stations.spaces.SpaceOccupiedException;
import project.vehicles.Vehicle;

public class MockParkingSpace extends ParkingSpace {
    public int cpt = 0;

    public MockParkingSpace() {
        super();
    }

    @Override
    public void store(Vehicle vehicle) {
        try  {
            super.store(vehicle);
        }
        catch (SpaceOccupiedException e) {}
        this.cpt++;
    }

    @Override
    public Vehicle remove() throws SpaceEmptyException {
        Vehicle vehicle = super.remove();
        this.cpt++;
        return vehicle;
    }
}
