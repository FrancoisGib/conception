package app;

import java.util.Iterator;
import java.util.List;

import app.displayers.Displayer;
import app.redistributions.Redistribution;
import app.redistributions.RoundRobin;
import app.stations.RentalStation;
import app.vehicles.Vehicle;

public class ControlCenter implements Observer, Timer {
    protected List<RentalStation> stations;

    protected Redistribution redistribution = new RoundRobin();

    protected Displayer displayer = new Displayer();

    protected int redistributionCounter = 0;

    protected static final int TICK_BEFORE_REDISTRIBUTION = 5;

    public ControlCenter(List<RentalStation> stations) {
        this.stations = stations;
        stations.forEach((station) -> station.attach(this));
    }

    public void redistribute() {
        this.displayer.displayStations(stations);
        try {
            this.redistribution.redistribute(stations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.displayer.displayStations(stations);
    }

    public void vehicleRented(Vehicle vehicle, RentalStation station) {
        this.displayer.vehicleRented(vehicle, station);
    }

    public void vehicleStored(Vehicle vehicle, RentalStation station) {
        this.displayer.vehicleStored(vehicle, station);
    }

    public void tick() {
        boolean found = false;
        Iterator<RentalStation> iterator = this.stations.iterator();
        while(!found && iterator.hasNext()) {
            if (iterator.next().isEmpty()) {
                found = true;
            }
        }
        this.redistributionCounter = found ? this.redistributionCounter++ : 0;
        if (this.redistributionCounter == TICK_BEFORE_REDISTRIBUTION) {
            this.redistribution.redistribute(this.stations);
            this.redistributionCounter = 0;
        }
        
    }
}
