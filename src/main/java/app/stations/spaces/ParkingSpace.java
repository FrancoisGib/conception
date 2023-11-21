package app.stations.spaces;

import app.vehicles.Vehicle;
import lombok.Getter;

public class ParkingSpace<V extends Vehicle> {
    @Getter
    V vehicle;

    public void store(V vehicle) throws SpaceOccupiedException {
        if (this.vehicle != null)
            throw new SpaceOccupiedException();
        this.vehicle = vehicle;
    }

    public V remove() throws SpaceEmptyException {
        if (this.vehicle == null)
            throw new SpaceEmptyException();
        V vehicle = this.vehicle;
        this.vehicle = null;
        return vehicle;
    }

    public boolean isOccupied() {
        return this.vehicle != null;
    }
}
