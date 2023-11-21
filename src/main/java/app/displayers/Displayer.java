package app.displayers;

import java.util.List;

import app.stations.RentalStation;
import app.vehicles.Vehicle;

public interface Displayer<V extends Vehicle> {
    public void vehicleRented(V vehicle, RentalStation<V> station);
    public void vehicleStored(V vehicle, RentalStation<V> station);
    public void displayStations(List<RentalStation<V>> stations);
}
