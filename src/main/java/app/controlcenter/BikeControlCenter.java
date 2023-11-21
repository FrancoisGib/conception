package app.controlcenter;

import java.util.List;

import app.stations.RentalStation;
import app.vehicles.Bike;

public class BikeControlCenter extends ControlCenter<Bike> {
    public BikeControlCenter(List<RentalStation<Bike>> stations) {
        super(stations);
    }
}
