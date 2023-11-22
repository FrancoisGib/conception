package app.stations.spaces;

import app.vehicles.Vehicle;
import lombok.Getter;

public class ParkingSpace {
    @Getter
    Vehicle vehicle;

    public void store(Vehicle vehicle) throws SpaceOccupiedException {
        if (this.vehicle != null)
            throw new SpaceOccupiedException();
        this.vehicle = vehicle;
    }

    public Vehicle remove() throws SpaceEmptyException {
        if (this.vehicle == null)
            throw new SpaceEmptyException();
        Vehicle vehicle = this.vehicle;
        this.vehicle = null;
        return vehicle;
    }

    public boolean isOccupied() {
        return this.vehicle != null;
    }
}
