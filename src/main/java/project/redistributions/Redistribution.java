package project.redistributions;

import java.util.ArrayList;
import java.util.List;

import project.stations.RentalStation;
import project.stations.StationEmptyException;
import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceEmptyException;
import project.vehicles.Vehicle;

public abstract class Redistribution {
    public abstract void redistribute(List<RentalStation> stations);

    protected List<Vehicle> getAllVehicles(List<RentalStation> stations) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (RentalStation station : stations) {
            station.getSpaces().forEach((space) -> {
                try {
                    vehicles.add(space.remove());
                } catch (SpaceEmptyException e) {
                }
            });
        }
        return vehicles;
    }

    protected ParkingSpace getFirstFreeSpace(RentalStation station) throws StationEmptyException {
        for (ParkingSpace space : station.getSpaces()) {
            if (space.isEmpty())
                return space;
        }
        throw new StationEmptyException();
    }
}
