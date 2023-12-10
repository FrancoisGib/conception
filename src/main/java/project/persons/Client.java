package project.persons;

import lombok.Getter;
import project.Displayer;
import project.Timer;
import project.stations.RentalStation;
import project.stations.StationEmptyException;
import project.stations.StationFullException;
import project.vehicles.Vehicle;


/**
 * Represents a client who can rent and store vehicles at a rental station.
 * Implements the Timer interface because it has a time unit to represent real time.
 */
public class Client implements Timer {
    
    /**
     * Represents a client in the project..
     */
    protected Vehicle vehicle;

    /**
     * The number of loops since the client has rented a vehicle.
     */
    protected int rentCounter = 0;

    /**
     * The maximum duration a client can rent a vehicle.
     */
    protected int maxRentCounter;

    /**
        * The rental station associated with the client.
        */
    protected RentalStation station;

    /**
     * The displayer used by the client.
     */
    protected Displayer displayer = new Displayer();

    @Getter
    /**
     * The unique identifier for a client.
     */
    private int id;

    /**
     * Constructs a new Client object with the specified ID and maximum rent counter.
     *
     * @param id             the ID of the client
     * @param maxRentCounter the maximum number of times the client can rent
     */
    public Client(int id, int maxRentCounter) {
        this.id = id;
        this.maxRentCounter = maxRentCounter;
    }

    /**
     * Rents a vehicle from the specified rental station.
     *
     * @param station the rental station to rent from
     */
    public void rentVehicle(RentalStation station) {
        this.station = station;
        try {
            this.vehicle = station.rentVehicle();
        } catch (StationEmptyException e) {
            this.displayer.stationEmpty(station);
        }
    }

    /**
     * Stores the vehicle at the rental station.
     */
    private void storeVehicle() {
        try {
            this.station.storeVehicle(this.vehicle);
            this.rentCounter = 0;
            this.vehicle = null;
        } catch (StationFullException e) {
            this.displayer.stationFull(station);
        }
    }

    /**
     * Returns whether the client has a vehicle.
     *
     * @return true if the client has a vehicle, false otherwise
     */
    public boolean hasVehicle() {
        return this.vehicle != null;
    }


    /**
     * If the client has a vehicle, increments the rent counter.
     * If the rent counter exceeds the maximum rent counter, the vehicle is stored.
     */
    public void tick() {
        if (this.vehicle != null) {
            this.rentCounter++;
            if (this.rentCounter >= this.maxRentCounter) {
                this.storeVehicle();
            }
        }
    }
}