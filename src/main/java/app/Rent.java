package app;

import app.persons.Client;
import app.stations.Subject;
import app.vehicles.State;
import app.vehicles.Vehicle;
import lombok.Getter;
import lombok.Setter;

public class Rent implements Timer, Subject {

    private Vehicle vehicle;

    private Client client;

    private Observer observer;

    @Getter @Setter
    private int time;

    public Rent(Vehicle vehicle, Client client) {
        this.vehicle = vehicle;
        this.client = client;
        this.time = 0;
    }

    public void tick() {
        if (this.time == ControlCenter.TIME_BEFORE_VEHICLE_STOLLEN) {
            this.vehicle.setState(State.STOLLEN);
            this.observer.vehicleStollen(vehicle, client);
        }
        this.time++;
    }

    public void attach(Observer observer) {
        this.observer = observer;
    }
}
