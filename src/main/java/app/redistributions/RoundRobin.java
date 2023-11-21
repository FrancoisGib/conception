package app.redistributions;

import java.util.ArrayList;
import java.util.List;

import app.stations.RentalStation;
import app.stations.spaces.SpaceEmptyException;
import app.vehicles.Vehicle;

public class RoundRobin<V extends Vehicle> implements Redistribution<V> {

    public void redistribute(List<RentalStation<V>> stations) {
        try {
            ArrayList<V> vehicles = new ArrayList<>();
            for (RentalStation<V> station : stations) {
                station.getSpaces().forEach((space) -> {
                    if (space.isOccupied()) {
                        try {
                            vehicles.add(space.remove());
                        } catch (SpaceEmptyException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            int cpt = 0;
            while (!vehicles.isEmpty()) {
                int stationIndex = cpt % stations.size();
                RentalStation<V> station = stations.get(stationIndex);
                if (station.getCapacity() > (int)(cpt / stations.size()))
                    station.storeVehicle(vehicles.remove(0));
                cpt++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}