package project.stations.spaces;

import lombok.Getter;
import project.vehicles.Vehicle;

/**
 * Represents a parking space where a vehicle can be stored.
 */
public class ParkingSpace {
    @Getter
    private Vehicle vehicle;

    /**
     * Stores a vehicle in the parking space.
     *
     * @param vehicle the vehicle to be stored
     * @throws SpaceFullException if the parking space is already occupied
     */
    public void store(Vehicle vehicle) throws SpaceFullException {
        if (this.vehicle != null)
            throw new SpaceFullException();
        this.vehicle = vehicle;
    }

    /**
     * Removes the vehicle from the parking space.
     *
     * @return the vehicle that was removed
     * @throws SpaceEmptyException if the parking space is empty
     */
    public Vehicle remove() throws SpaceEmptyException {
        if (this.vehicle == null)
            throw new SpaceEmptyException();
        Vehicle currentVehicle = this.vehicle;
        this.vehicle = null;
        return currentVehicle;
    }

    /**
     * Checks if the parking space is empty.
     *
     * @return true if the parking space is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.vehicle == null;
    }
}
