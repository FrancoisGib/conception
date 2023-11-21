package app.displayers;

import java.util.List;

import app.stations.RentalStation;
import app.stations.spaces.ParkingSpace;
import app.vehicles.Vehicle;

public class ClassicDisplayer<V extends Vehicle> implements Displayer<V> {
    public void vehicleRented(V vehicle, RentalStation<V> station) {
        System.out.println("Vehicle rented --> Vehicle id: " + vehicle.getId() + " | Description: "
                + vehicle.getDescription() + " from the station " + station.getId());
    }

    public void vehicleStored(V vehicle, RentalStation<V> station) {
        System.out.println("Vehicle stored --> Vehicle id: " + vehicle.getId() + " | Description: "
                + vehicle.getDescription() + " from the station " + station.getId());
    }

    public void displayStations(List<RentalStation<V>> stations) {
        stations.forEach(station -> {
            List<ParkingSpace<V>>spaces = station.getSpaces();
            int cpt = 0;
            for (int i = 0; i < spaces.size(); i++) {
                cpt++;
            }
            System.out.println("Station " + station.getId() + " : " + cpt + " vehicles");
        });
    }
}
