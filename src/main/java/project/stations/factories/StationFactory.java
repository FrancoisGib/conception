package project.stations.factories;

import java.util.ArrayList;
import java.util.List;

import project.stations.RentalStation;
import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceFullException;
import project.vehicles.State;
import project.vehicles.Vehicle;
import project.vehicles.factories.VehicleFactory;

/**
 * The StationFactory class is an abstract class that represents a factory for creating rental stations.
 * It provides a method to create a rental station with a given ID and capacity.
 */
public abstract class StationFactory {
    protected VehicleFactory factory;

    public StationFactory(VehicleFactory factory) {
        this.factory = factory;
    }

    /**
     * Creates a rental station with the specified ID and capacity.
     *
     * @param id       the ID of the rental station
     * @param capacity the capacity of the rental station
     * @return the created RentalStation object
     */
    public RentalStation createStation(int id, int capacity) {
        List<ParkingSpace> spaces = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Vehicle newVehicle = this.factory.createVehicle(i + (id * RentalStation.MAX_CAPACITY));
            newVehicle.setState(State.STORED);
            ParkingSpace space = new ParkingSpace();
            try {
                space.store(newVehicle);
            } catch (SpaceFullException e) {
                // Handle space full exception
            }
            spaces.add(space);
        }
        return new RentalStation(id, spaces);
    }
}
