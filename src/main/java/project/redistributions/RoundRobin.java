package project.redistributions;

import java.util.List;

import project.stations.ParkingSpace;
import project.stations.RentalStation;
import project.vehicles.Vehicle;

public class RoundRobin extends Redistribution {

    public void redistribute(List<RentalStation> stations) {
        List<Vehicle> vehicles = this.getAllVehicles(stations);
        int cpt = 0;
        while (!vehicles.isEmpty()) {
            int stationIndex = cpt % stations.size();
            RentalStation station = stations.get(stationIndex);
            if (!station.isFull()) {
                ParkingSpace space = this.getFirstFreeSlot(station);
                if (space != null)
                    space.store(vehicles.remove(0));
            }
            cpt++;
        }
    }
}