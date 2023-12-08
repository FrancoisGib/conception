package project.redistributions;

import java.util.List;

import project.stations.RentalStation;
import project.stations.StationEmptyException;
import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceFullException;
import project.vehicles.Vehicle;

/**
 * The RoundRobin class represents a redistribution strategy that follows the
 * round-robin algorithm.
 * It extends the Redistribution class and implements the redistribute method.
 */
public class RoundRobin extends Redistribution {

    /**
     * Redistributes vehicles among rental stations using the round-robin algorithm.
     * 
     * @param stations the list of rental stations
     */
    public void redistribute(List<RentalStation> stations) {
        List<Vehicle> vehicles = this.getAllVehicles(stations);
        int cpt = 0;
        while (!vehicles.isEmpty()) {
            int stationIndex = cpt % stations.size();
            RentalStation station = stations.get(stationIndex);
            if (!station.isFull()) {
                try {
                    ParkingSpace space = this.getFirstFreeSpace(station);
                    space.store(vehicles.remove(0));
                } catch (SpaceFullException e) {
                } catch (StationEmptyException e) {
                }
            }
            cpt++;
        }
    }
}
