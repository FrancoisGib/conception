package app;

import java.util.ArrayList;
import java.util.List;

import app.controlcenter.ControlCenter;
import app.stations.RentalStation;
import app.stations.factories.BikeStationFactory;
import app.vehicles.Bike;

public class BikeSimulation extends Simulation<Bike> {
    public BikeSimulation() {
        super();
        List<RentalStation<Bike>> stations = new ArrayList<>();
        for (int i = 0; i < 4; i ++) {
            stations.add(new BikeStationFactory().createStation(i));
        }
        this.controlCenter = new ControlCenter<Bike>(stations);
        this.stations = stations;
        for (int i = 0; i < 50; i++)
            this.clients.add(new Client<Bike>(i));
    }
}
