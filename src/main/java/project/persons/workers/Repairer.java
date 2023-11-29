package project.persons.workers;

import project.vehicles.Bike;
import project.vehicles.Scooter;
import project.vehicles.State;

public class Repairer extends Worker {
    public int initialVehicleLives;

    public void visit(Bike bike) {
        bike.setState(State.REPARATION);
        this.vehicle = bike;
        this.reparationTime = Bike.REPARATION_TIME;
        this.initialVehicleLives = Bike.INITIAL_LIVES;
    }

    public void visit(Scooter scooter) {
        scooter.setState(State.REPARATION);
        this.vehicle = scooter;
        this.reparationTime = Scooter.REPARATION_TIME;
        this.initialVehicleLives = Scooter.INITIAL_LIVES;
    }

    public void tick() {
        if (this.vehicle != null) {
            if (this.cpt < this.reparationTime)
                this.cpt++;
            else {
                this.cpt = 0;
                this.vehicle.setLives(this.initialVehicleLives);
                this.vehicle.setState(State.STORED);
                System.out.println("The vehicle " + vehicle.getId() + " has been repaired, it is now available");
                this.vehicle = null;
            }
        }
    }
}
