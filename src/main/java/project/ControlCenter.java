package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import project.persons.workers.Repairer;
import project.persons.workers.Worker;
import project.redistributions.Redistribution;
import project.redistributions.RoundRobin;
import project.stations.RentalStation;
import project.vehicles.State;
import project.vehicles.Vehicle;

/**
 * The ControlCenter class represents the central control center of a rental
 * system.
 * It implements the Observer and Timer interfaces.
 */
public class ControlCenter implements Observer, Timer {
    /**
     * The singleton instance of the ControlCenter class.
     */
    private static ControlCenter instance;


    /**
     * The list of rental stations managed by the control center.
     */
    private List<RentalStation> stations;

    /**
     * The list of rented vehicles.
     */
    private List<Rent> rentedVehicles = new ArrayList<>();

    /**
     * The list of repairers managed by the control center.
     */
    private List<Repairer> repairers;

    /**
     * The redistribution strategy used by the control center.
     */
    private Redistribution redistribution = new RoundRobin();

    /**
     * The displayer used by the control center.
     */
    private Displayer displayer = new Displayer();

    /**
     * The counter used to determine when to redistribute the vehicles.
     */
    private Map<RentalStation, Integer> redistributionCounter = new HashMap<>();

    /**
     * The number of ticks before the redistribution process is triggered.
     */
    private static final int TICK_BEFORE_REDISTRIBUTION = 3;

    /**
     * Represents a control center for managing rental stations and repairers.
     */
    private ControlCenter(List<RentalStation> stations, List<Repairer> repairers) {
        this.stations = stations;
        this.repairers = repairers;
        repairers.forEach((repairer) -> repairer.attach(this));
        stations.forEach((station) -> station.attach(this));
    }

    /**
     * Returns the singleton instance of the ControlCenter class.
     * If the instance does not exist, it creates a new instance with the given
     * lists of rental stations and repairers.
     * 
     * @param stations  the list of rental stations
     * @param repairers the list of repairers
     * @return the singleton instance of the ControlCenter class
     */
    public static ControlCenter getInstance(List<RentalStation> stations, List<Repairer> repairers) {
        if (instance == null) {
            instance = new ControlCenter(stations, repairers);
        }
        return instance;
    }

    /**
     * Redistributes the stations by initializing the colors, performing the
     * redistribution,
     * and displaying the redistribution results.
     */
    private void redistribute() {
        this.displayer.initColors(stations);
        this.redistribution.redistribute(stations);
        this.displayer.displayRedistribution(stations);
    }

    /**
     * Registers a rented vehicle in the control center.
     * 
     * @param vehicle The rented vehicle.
     * @param station The rental station where the vehicle is rented from.
     */
    public void vehicleRented(Vehicle vehicle, RentalStation station) {
        Rent newRent = new Rent(vehicle);
        newRent.attach(this);
        this.rentedVehicles.add(newRent);
        this.displayer.vehicleRented(vehicle, station);
    }

    /**
     * Stores a vehicle in the specified rental station.
     * If the vehicle is currently rented, it will be removed from the list of
     * rented vehicles.
     *
     * @param vehicle the vehicle to be stored
     * @param station the rental station where the vehicle is stored
     */
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

    /**
     * Marks a vehicle as out of service and assigns a repairer to it if available.
     * If a repairer is available, the vehicle is accepted by the repairer and the
     * displayer is notified. If no repairer is available, a message is printed.
     *
     * @param vehicle The vehicle to mark as out of service.
     */
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
        } else {
            this.displayer.noRepairerAvailable();
        }
    }

    /**
     * Marks the specified vehicle as stolen and notifies the displayer.
     *
     * @param vehicle the vehicle that has been stolen
     */
    public void vehicleStolen(Vehicle vehicle) {
        vehicle.setState(State.STOLEN);
        this.displayer.vehicleStolen(vehicle);
    }

    /**
     * Notifies the control center that a stolen vehicle has been recovered and is
     * back in possession.
     * 
     * @param vehicle the recovered vehicle
     */
    public void vehicleBackFromStolen(Vehicle vehicle) {
        this.displayer.vehicleBackFromStolen(vehicle);
    }

    /**
     * Notifies the control center that a vehicle has been repaired.
     *
     * @param vehicle The vehicle that has been repaired.
     */
    public void vehicleRepaired(Vehicle vehicle) {
        this.displayer.vehicleRepaired(vehicle);
    }

    /**
     * Executes a single tick of the control center.
     * This method checks if any rental station is empty or full, and if so, it sets
     * the 'found' flag to true.
     * If the 'found' flag is true, it increments the redistribution counter by 1,
     * otherwise it resets the counter to 0.
     * If the redistribution counter reaches the threshold value, it triggers the
     * redistribution process.
     * Finally, it increments the rental duration of all rented vehicles and
     * displays the current state of the rental stations.
     */
    public void tick() {
        this.displayer.displayStations(stations);
        boolean found = false;
        Iterator<RentalStation> iterator = this.stations.iterator();
        while (!found && iterator.hasNext()) {
            RentalStation station = iterator.next();
            if (station.isEmpty() || station.isFull()) {
                this.redistributionCounter.compute(station, (k, v) -> v == null ? 1 : v + 1);
                if (this.redistributionCounter.get(station) >= TICK_BEFORE_REDISTRIBUTION) {
                    this.redistribute();
                    this.redistributionCounter.clear();
                    found = true;
                }
            }
            else
                this.redistributionCounter.replace(station, 0);
        }
        this.rentedVehicles.forEach(rent -> rent.increment());
    }
}
