package project;

import lombok.Getter;
import project.vehicles.Vehicle;

/**
 * The Rent class represents a rental of a vehicle.
 * It keeps track of the rented vehicle, the rental time, and an observer.
 */
public class Rent {
    @Getter
    private Vehicle vehicle;

    private Observer observer;

    @Getter
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
        if (this.time == Simulation.TIME_BEFORE_VEHICLE_STOLLEN) {
            this.observer.vehicleStolen(vehicle);
        }
        this.time++;
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
