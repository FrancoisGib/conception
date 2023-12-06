package project.persons;

import lombok.Getter;
import project.Timer;
import project.stations.RentalStation;
import project.stations.StationEmptyException;
import project.stations.StationFullException;
import project.vehicles.State;
import project.vehicles.Vehicle;

/**
 * The Client class represents a client who can rent and store vehicles at a rental station.
 * It implements the Timer interface to keep track of the rental duration.
 */
public class Client implements Timer {
    protected Vehicle vehicle;
    protected int rentCounter = 0;
    protected int maxRentCounter;
    protected RentalStation station;

    @Getter
    private int id;

    /**
     * Constructs a Client object with the specified ID and maximum rental counter.
     *
     * @param id              the ID of the client
     * @param maxRentCounter  the maximum number of rentals allowed for the client
     */
    public Client(int id, int maxRentCounter) {
        this.id = id;
        this.maxRentCounter = maxRentCounter;
    }

    /**
     * Rents a vehicle from the specified rental station.
     *
     * @param station  the rental station from which to rent the vehicle
     */
    public void rentVehicle(RentalStation station) {
        this.station = station;
        try {
            this.vehicle = station.rentVehicle();
        } catch (StationEmptyException e) {
            System.out.println("Empty station " + this.station.getId() + " : come back later !");
        }
    }

    /**
     * Stores the currently rented vehicle at the rental station.
     * Resets the rent counter and sets the vehicle reference to null.
     */
    private void storeVehicle() {
        try {
            this.station.storeVehicle(this.vehicle);
            this.rentCounter = 0;
            this.vehicle = null;
        } catch (StationFullException e) {
            System.out.println("The station is full, return the vehicle later");
        }
    }

    /**
     * Checks if the client currently has a rented vehicle.
     *
     * @return true if the client has a rented vehicle, false otherwise
     */
    public boolean hasVehicle() {
        return this.vehicle != null;
    }

    /**
     * Updates the rent counter and stores the vehicle if the maximum rental counter is reached.
     */
    public void tick() {
        if (this.vehicle != null) {
            this.rentCounter++;
            if (this.rentCounter >= this.maxRentCounter) {
                if (this.rentCounter > this.maxRentCounter) // else the vehicle would be considered as stolen
                    this.vehicle.setState(State.RENTED);
                this.storeVehicle();
            }
        }
    }
}