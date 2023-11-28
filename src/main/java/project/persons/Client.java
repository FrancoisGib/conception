package project.persons;

import lombok.Getter;
import project.Timer;
import project.stations.RentalStation;
import project.vehicles.State;
import project.vehicles.Vehicle;

public class Client implements Timer {
    protected Vehicle vehicle;

    protected int rentCounter = 0;

    protected int maxRentCounter;

    protected RentalStation station;

    @Getter
    private int id;

    public Client(int id, int maxRentCounter) {
        this.id = id;
        this.maxRentCounter = maxRentCounter;
    }

    public void rentVehicle(RentalStation station) {
        this.station = station;
        this.vehicle = station.rentVehicle();
        if (this.vehicle == null)
            System.out.println("Empty station " + this.station.getId() + " : come back later !");
    }

    private void storeVehicle() {
        boolean res = this.station.storeVehicle(this.vehicle);
        if (res) {
            this.rentCounter=0;
            this.vehicle=null;
        }
        else
            System.out.println("The station is full, return the vehicle later");

    }

    public boolean hasVehicle() {
        return this.vehicle != null;
    }

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