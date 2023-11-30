package project;

import lombok.Getter;
import project.vehicles.Vehicle;

public class Rent {
    @Getter
    private Vehicle vehicle;

    private Observer observer;

    @Getter
    private int time;

    public Rent(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.time = 0;
    }

    public void increment() {
        if (this.time == Simulation.TIME_BEFORE_VEHICLE_STOLLEN) {
            this.observer.vehicleStolen(vehicle);
        }
        this.time++;
    }

    public void attach(Observer observer) {
        this.observer = observer;
    }
}
