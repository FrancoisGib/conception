package project;

import lombok.Getter;
import lombok.Setter;
import project.stations.Subject;
import project.vehicles.State;
import project.vehicles.Vehicle;

public class Rent implements Timer, Subject {
    @Getter
    private Vehicle vehicle;


    private Observer observer;

    @Getter @Setter
    private int time;

    public Rent(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.time = 0;
    }

    public void tick() {
        if (this.time == Simulation.TIME_BEFORE_VEHICLE_STOLLEN) {
            this.vehicle.setState(State.STOLEN);
            this.observer.vehicleStollen(vehicle);
        }
        this.time++;
    }

    public void attach(Observer observer) {
        this.observer = observer;
    }
}
