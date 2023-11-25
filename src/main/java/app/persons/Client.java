package app.persons;

import lombok.Getter;
import app.Simulation;
import app.Timer;
import app.stations.RentalStation;
import app.stations.StationEmptyException;
import app.stations.spaces.SpaceEmptyException;
import app.vehicles.Vehicle;

public class Client implements Timer {
    protected Vehicle vehicle;

    protected int rentCounter = 0;

    protected int maxRentCounter = (int) (Math.random() * Simulation.CLIENT_MAX_RENT_LOOP);

    protected RentalStation station;

    @Getter
    private int id;

    public Client(int id) {
        this.id = id;
    }

    public void rentVehicle(RentalStation station) {
        this.station = station;
        try {
            this.vehicle = station.rentVehicle(this);
        } catch (StationEmptyException e) {
            System.out.println("Empty station " + this.station.getId() + " : come back later !");
        }
        catch (SpaceEmptyException e) {
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