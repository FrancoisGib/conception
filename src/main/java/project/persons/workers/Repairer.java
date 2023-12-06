package project.persons.workers;

import project.vehicles.Bike;
import project.vehicles.Scooter;
import project.vehicles.State;

/**
 * The Repairer class represents a worker who specializes in repairing vehicles.
 * It extends the Worker class.
 */
public class Repairer extends Worker {
    protected int initialVehicleLives;

    /**
     * Visits a Bike object and sets its state to REPARATION.
     * Updates the worker's vehicle, reparation time, and initial vehicle lives accordingly.
     * 
     * @param bike The Bike object to be visited.
     */
    public void visit(Bike bike) {
        bike.setState(State.REPARATION);
        this.vehicle = bike;
        this.reparationTime = Bike.REPARATION_TIME;
        this.initialVehicleLives = Bike.INITIAL_LIVES;
    }

    /**
     * Visits a Scooter object and sets its state to REPARATION.
     * Updates the worker's vehicle, reparation time, and initial vehicle lives accordingly.
     * 
     * @param scooter The Scooter object to be visited.
     */
    public void visit(Scooter scooter) {
        scooter.setState(State.REPARATION);
        this.vehicle = scooter;
        this.reparationTime = Scooter.REPARATION_TIME;
        this.initialVehicleLives = Scooter.INITIAL_LIVES;
    }

    /**
     * Performs a tick of the repair process.
     * If a vehicle is being repaired, increments the counter and checks if the reparation time has been reached.
     * If the reparation time has been reached, resets the counter, restores the vehicle's initial lives,
     * sets the vehicle's state to STORED, notifies the observer about the repaired vehicle,
     * and clears the worker's vehicle reference.
     */
    public void tick() {
        if (this.vehicle != null) {
            this.cpt++;
            if (this.cpt == this.reparationTime) {
                this.cpt = 0;
                this.vehicle.setLives(this.initialVehicleLives);
                this.vehicle.setState(State.STORED);
                this.observer.vehicleRepaired(vehicle);
                this.vehicle = null;
            }
        }
    }
}
