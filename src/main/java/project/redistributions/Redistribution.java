package project.redistributions;

import java.util.ArrayList;
import java.util.List;

import project.stations.RentalStation;
import project.stations.StationEmptyException;
import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceEmptyException;
import project.vehicles.State;
import project.vehicles.Vehicle;

/**
 * The abstract class Redistribution represents a strategy for redistributing
 * vehicles among rental stations.
 * Subclasses of Redistribution must implement the redistribute method.
 */
public abstract class Redistribution {
    /**
     * Redistributes vehicles among rental stations.
     *
     * @param stations the list of rental stations
     */
    public abstract void redistribute(List<RentalStation> stations);

    /**
     * Retrieves all vehicles from the rental stations.
     *
     * @param stations the list of rental stations
     * @return a list of all vehicles from the rental stations
     */
    protected List<Vehicle> getAllVehicles(List<RentalStation> stations) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (RentalStation station : stations) {
            station.getSpaces().forEach((space) -> {
                try {
                    if (!space.isEmpty()) {
                        State vehicleState = space.getVehicle().getState();
                        if (!(vehicleState == State.REPARATION || vehicleState == State.OUT_OF_SERVICE))
                            vehicles.add(space.remove());
                    }
                } catch (SpaceEmptyException e) {
                }
            });
        }
        return vehicles;
    }

    /**
     * Retrieves the first available parking space in a rental station.
     *
     * @param station the rental station
     * @return the first available parking space
     * @throws StationEmptyException if the rental station is empty
     */
    protected ParkingSpace getFirstFreeSpace(RentalStation station) throws StationEmptyException {
        for (ParkingSpace space : station.getSpaces()) {
            if (space.isEmpty())
                return space;
        }
        throw new StationEmptyException();
    }
}
