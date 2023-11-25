package app.persons.visitors;

import app.vehicles.State;
import app.vehicles.bikes.Bike;
import app.vehicles.scooters.Scooter;

public class Repairer extends Worker implements Visitor {

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
                this.vehicle = null;
            }
        }
    }
}
