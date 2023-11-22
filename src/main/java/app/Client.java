package app;

import lombok.Getter;
import app.stations.RentalStation;
import app.vehicles.Vehicle;

public class Client implements Timer {
    private Vehicle vehicle = null;

    private int rentCounter = 0;

    private int maxRentCounter;

    private RentalStation station;

    @Getter
    private int id;

    public Client(int id) {
        this.id = id;
    }

    public void rentVehicle(RentalStation station) {
    this.station = station;
        try {
            this.vehicle = this.station.rentVehicle();
            this.maxRentCounter = (int) (Math.random() * Simulation.CLIENT_MAX_RENT_LOOP);
        } catch (Exception e) {
            System.out.println("Empty station " + this.station.getId() + " : come back later !");
        }
    }

    public void storeVehicle() {
        try {
            this.station.storeVehicle(this.vehicle);
            this.rentCounter = 0;
            this.vehicle = null;
        } catch (Exception e) {
            System.out.println("The station is full, return the vehicle later");
        }
    }

    public boolean hasVehicle() {
        return this.vehicle != null;
    }

    public void tick() {
        if (this.vehicle != null) {
            this.rentCounter++;
            if (this.rentCounter >= this.maxRentCounter || (Math.random() < 0.05))
                this.storeVehicle();
        }
    }
}