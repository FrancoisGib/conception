package project.stations.spaces;

import lombok.Getter;
import project.vehicles.Vehicle;

public class ParkingSpace {
    @Getter
    Vehicle vehicle;

    public void store(Vehicle vehicle) throws SpaceFullException {
        if (this.vehicle != null)
            throw new SpaceFullException();
        this.vehicle = vehicle;
    }

    public Vehicle remove() throws SpaceEmptyException {
        if (this.vehicle == null)
            throw new SpaceEmptyException();
        Vehicle currentVehicle = this.vehicle;
        this.vehicle = null;
        return currentVehicle;
    }

    public boolean isEmpty() {
        return this.vehicle == null;
    }
}
