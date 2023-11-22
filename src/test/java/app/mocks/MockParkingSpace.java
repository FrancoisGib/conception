package app.mocks;

import app.stations.spaces.ParkingSpace;
import app.stations.spaces.SpaceEmptyException;
import app.stations.spaces.SpaceOccupiedException;
import app.vehicles.Vehicle;

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
