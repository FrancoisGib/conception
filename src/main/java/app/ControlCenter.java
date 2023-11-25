package app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.displayers.Displayer;
import app.persons.Client;
import app.redistributions.Redistribution;
import app.redistributions.RoundRobin;
import app.stations.RentalStation;
import app.vehicles.Vehicle;
import lombok.Getter;

public class ControlCenter implements Observer, Timer {
    public static final int TIME_BEFORE_VEHICLE_STOLLEN = 5; // must be greater or equals to 0;

    protected List<RentalStation> stations;

    protected List<Rent> rentedVehicles = new ArrayList<>();

    protected Redistribution redistribution = new RoundRobin();

    protected Displayer displayer = new Displayer();

    @Getter
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

    public void vehicleRented(Vehicle vehicle, Client client, RentalStation station) {
        Rent newRent = new Rent(vehicle, client);
        newRent.attach(this);
        this.rentedVehicles.add(newRent);
        this.displayer.vehicleRented(vehicle, station);
    }

    public void vehicleStored(Vehicle vehicle, RentalStation station) {
        this.displayer.vehicleStored(vehicle, station);
    }

    public void vehicleOutOfService(Vehicle vehicle) {

    }

    public void vehicleStollen(Vehicle vehicle, Client client) {
        this.displayer.vehicleStollen(vehicle, client);
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
        this.rentedVehicles.forEach(rent -> rent.tick());

    }
}
