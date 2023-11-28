package project.stations;

import lombok.Getter;
import project.vehicles.Vehicle;

public class ParkingSpace {
    @Getter
    Vehicle vehicle;

    public boolean store(Vehicle vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }

    public Vehicle remove() {
        if (this.vehicle == null)
            return null;
        Vehicle currentVehicle = this.vehicle;
        this.vehicle = null;
        return currentVehicle;
    }

    public boolean isOccupied() {
        return this.vehicle != null;
    }
}
