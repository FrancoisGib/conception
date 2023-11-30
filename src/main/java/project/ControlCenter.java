package project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import project.displayers.Displayer;
import project.persons.workers.Repairer;
import project.persons.workers.Worker;
import project.redistributions.Redistribution;
import project.redistributions.RoundRobin;
import project.stations.RentalStation;
import project.vehicles.State;
import project.vehicles.Vehicle;

public class ControlCenter implements Observer, Timer {
    private static ControlCenter instance;

    protected List<RentalStation> stations;

    protected List<Rent> rentedVehicles = new ArrayList<>();

    protected List<Repairer> repairers = new ArrayList<>();

    protected Redistribution redistribution = new RoundRobin();

    protected Displayer displayer = new Displayer();

    @Getter
    protected int redistributionCounter = 0;

    protected static final int TICK_BEFORE_REDISTRIBUTION = 2;

    private ControlCenter(List<RentalStation> stations, List<Repairer> repairers) {
        this.stations = stations;
        this.repairers = repairers;
        stations.forEach((station) -> station.attach(this));
    }

    public static ControlCenter getInstance(List<RentalStation> stations, List<Repairer> repairers) {
        if (instance == null) {
            instance = new ControlCenter(stations, repairers);
        }
        return instance;
    }

    private void redistribute() {
        this.displayer.initColors(stations);
        try {
            this.redistribution.redistribute(stations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.displayer.displayRedistribution(stations);
    }

    public void vehicleRented(Vehicle vehicle, RentalStation station) {
        Rent newRent = new Rent(vehicle);
        newRent.attach(this);
        this.rentedVehicles.add(newRent);
        this.displayer.vehicleRented(vehicle, station);
    }

    public void vehicleStored(Vehicle vehicle, RentalStation station) {
        int i = 0;
        boolean found = false;
        while (i < rentedVehicles.size() && !found) {
            Rent rent = rentedVehicles.get(i);
            if (rent.getVehicle() == vehicle) {
                found = true;
                rentedVehicles.remove(rent);
            }
            i++;
        }
        this.displayer.vehicleStored(vehicle, station);
    }

    public void vehicleOutOfService(Vehicle vehicle) {
        boolean found = false;
        Iterator<Repairer> it = this.repairers.iterator();
        Worker repairer = null;
        while (it.hasNext() && !found) {
            Worker possibleRepairer = it.next();
            if (!possibleRepairer.isOccupied()) {
                found = true;
                repairer = possibleRepairer;
            }
        }
        if (found) {
            vehicle.accept(repairer);
            this.displayer.vehicleOutOfService(vehicle);
        }
        else System.out.println("No repairer available, wait");
    }

    public void vehicleStolen(Vehicle vehicle) {
        vehicle.setState(State.STOLEN);
        this.displayer.vehicleStollen(vehicle);
    } 

    public void vehicleBackFromStolen(Vehicle vehicle) {
        this.displayer.vehicleBackFromStolen(vehicle);
    }

    public void vehicleRepaired(Vehicle vehicle) {
        this.displayer.vehicleRepaired(vehicle);
    }

    public void tick() {
        boolean found = false;
        Iterator<RentalStation> iterator = this.stations.iterator();
        while(!found && iterator.hasNext()) {
            RentalStation station = iterator.next();
            if (station.isEmpty() || station.isFull()) {
                found = true;
            }
        }
        this.redistributionCounter = found ? this.redistributionCounter + 1 : 0;
        if (this.redistributionCounter == TICK_BEFORE_REDISTRIBUTION) {
            this.redistribute();
            this.redistributionCounter = 0;
        }
        this.rentedVehicles.forEach(rent -> rent.increment());
        this.displayer.displayStations(stations);
    }
}
