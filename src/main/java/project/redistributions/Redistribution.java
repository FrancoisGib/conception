package project.redistributions;

import java.util.ArrayList;
import java.util.List;

import project.stations.ParkingSpace;
import project.stations.RentalStation;
import project.vehicles.Vehicle;

public abstract class Redistribution {
    public abstract void redistribute(List<RentalStation> stations);

    protected List<Vehicle> getAllVehicles(List<RentalStation> stations) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (RentalStation station : stations) {
            station.getSpaces().forEach((space) -> {
                if (space.isOccupied()) {
                    vehicles.add(space.remove());
                }
            });
        }
        return vehicles;
    }

    protected ParkingSpace getFirstFreeSlot(RentalStation station) {
        for (ParkingSpace space : station.getSpaces()) {
            if (!space.isOccupied())
                return space;
        }
        return null;
    }
}
