package project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import project.persons.Client;
import project.persons.Thief;
import project.persons.workers.Repairer;
import project.stations.RentalStation;
import project.stations.factories.BikeStationFactory;
import project.stations.factories.StationFactory;

/**
 * The Simulation class represents a simulation of a rental system.
 * It simulates the behavior of clients renting vehicles from rental stations,
 * the control center managing the rental process, and repairers maintaining the vehicles.
 */

public class Simulation {
    protected static final int RENT_RATIO = 10;

    protected static final int SIMULATION_TIME = 60;

    protected static final int CLIENT_MAX_RENT_TIME = 6;

    protected static final int CLIENT_MIN_RENT_TIME = 3;

    public static final int TIME_BEFORE_VEHICLE_STOLLEN = CLIENT_MAX_RENT_TIME + 1; // must be greater or equals to 0;

    protected static final int NUMBER_OF_STATIONS = 4;

    protected static final int NUMBER_OF_CLIENTS = 100;

    protected static final int NUMBER_OF_REPAIRERS = 10;

    protected ControlCenter controlCenter;

    protected List<RentalStation> stations = new ArrayList<>();

    protected ArrayList<Client> clients = new ArrayList<>();

    protected List<Repairer> repairers = new ArrayList<>();

    protected int loop = 0;

    /**
     * Represents a simulation of a bike rental system.
     * The simulation initializes clients, stations, repairers, and a control center.
     */
    public Simulation() {
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++)
            this.clients.add(new Client(i,
                    (int) (Math.random() * (CLIENT_MAX_RENT_TIME - CLIENT_MIN_RENT_TIME)) + CLIENT_MIN_RENT_TIME));
        this.clients.add(new Thief(11));
        StationFactory stationFactory = new BikeStationFactory();
        for (int i = 0; i < NUMBER_OF_STATIONS; i++) {
            int capacity = (int) (Math.random() * (RentalStation.MAX_CAPACITY - RentalStation.MIN_CAPACITY))
                    + RentalStation.MIN_CAPACITY;
            this.stations.add(stationFactory.createStation(i, capacity));
        }
        for (int i = 0; i < NUMBER_OF_REPAIRERS; i++)
            this.repairers.add(new Repairer());
        this.controlCenter = ControlCenter.getInstance(this.stations, this.repairers);
    }

    /**
     * Starts the simulation by scheduling a timer task to run the loop method periodically.
     * The simulation will run until the specified simulation time is reached.
     */
    public void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loop();
                if (loop >= SIMULATION_TIME) {
                    System.out.println("End of simulation");
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }

    /**
     * Starts the simulation without using a timer.
     * Executes the simulation loop for a fixed number of times.
     */
    public void startWithoutTimer() {
        for (int j = 0; j < SIMULATION_TIME; j++) {
            this.loop();
        }
    }

    /**
     * Executes the main simulation loop.
     * This method iterates over the clients, performs rental operations,
     * updates the clients' status, and ticks the control center and repairers.
     */
    protected void loop() {
        int i = 0;
        Iterator<Client> it = this.clients.iterator();
        while (it.hasNext()) {
            Client client = it.next();
            int cpt = 0;
            if (i % RENT_RATIO == 0) {
                while (!client.hasVehicle() && cpt < this.stations.size()) {
                    RentalStation station = stations.get((loop + i + cpt) % stations.size());
                    client.rentVehicle(station);
                    cpt++;
                }
            }
            i++;
            client.tick();
        }
        controlCenter.tick();
        this.repairers.forEach(repairer -> repairer.tick());
        this.loop++;
    }
}