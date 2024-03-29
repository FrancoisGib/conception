package project;

import lombok.Getter;
import project.vehicles.Vehicle;

/**
 * The Rent class represents a rental of a vehicle.
 * It keeps track of the rented vehicle, the rental time, and an observer.
 */
public class Rent {
    @Getter
    /**
     * The vehicle that is being rented.
     */
    private Vehicle vehicle;

    /**
     * The observer attached to the rental.
     */
    private Observer observer;

    /**
     * Represents the time duration of a rental.
     */
    private int time;

    /**
     * Constructs a Rent object with the specified vehicle.
     * The initial rental time is set to 0.
     *
     * @param vehicle the vehicle to be rented
     */
    public Rent(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.time = 0;
    }

    /**
     * Increments the rental time by 1.
     * If the rental time reaches the threshold for vehicle theft,
     * the observer's vehicleStolen method is called.
     */
    public void increment() {
        this.time++;
        if (this.time == Simulation.TIME_BEFORE_VEHICLE_STOLEN) {
            this.observer.vehicleStolen(vehicle);
        }
    }

    /**
     * Attaches an observer to the rental.
     *
     * @param observer the observer to be attached
     */
    public void attach(Observer observer) {
        this.observer = observer;
    }
}
