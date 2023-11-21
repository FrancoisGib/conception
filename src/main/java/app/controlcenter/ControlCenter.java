package app.controlcenter;

import java.util.Iterator;
import java.util.List;

import app.Timer;
import app.displayers.ClassicDisplayer;
import app.displayers.Displayer;
import app.redistributions.Redistribution;
import app.redistributions.RoundRobin;
import app.stations.RentalStation;
import app.vehicles.Vehicle;

public class ControlCenter<V extends Vehicle> implements Observer<V>, Timer {
    protected List<RentalStation<V>> stations;

    protected Redistribution<V> redistribution = new RoundRobin<>();

    protected Displayer<V> displayer = new ClassicDisplayer<>();

    protected int redistributionCounter = 0;

    protected static final int TICK_BEFORE_REDISTRIBUTION = 2;

    public ControlCenter(List<RentalStation<V>> stations) {
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

    public void vehicleRented(V vehicle, RentalStation<V> station) {
        this.displayer.vehicleRented(vehicle, station);
    }

    public void vehicleStored(V vehicle, RentalStation<V> station) {
        this.displayer.vehicleStored(vehicle, station);
    }

    public void tick() {
        boolean found = false;
        Iterator<RentalStation<V>> iterator = this.stations.iterator();
        while(!found && iterator.hasNext()) {
            if (iterator.next().isEmpty()) {
                found = true;
                this.redistributionCounter++;
            }
        }
        if (this.redistributionCounter == TICK_BEFORE_REDISTRIBUTION) {
            this.displayer.displayStations(stations);
            this.redistribution.redistribute(this.stations);
            this.redistributionCounter = 0;
            this.displayer.displayStations(stations);
        }
        
    }
}
