package project.persons;

import lombok.Getter;
import project.Displayer;
import project.Timer;
import project.stations.RentalStation;
import project.stations.StationEmptyException;
import project.stations.StationFullException;
import project.vehicles.Vehicle;

public class Client implements Timer {
    protected Vehicle vehicle;

    protected int rentCounter = 0;

    protected int maxRentCounter;

    protected RentalStation station;

    protected Displayer displayer = new Displayer();

    @Getter
    private int id;

    public Client(int id, int maxRentCounter) {
        this.id = id;
        this.maxRentCounter = maxRentCounter;
    }

    public void rentVehicle(RentalStation station) {
        this.station = station;
        try {
            this.vehicle = station.rentVehicle();
        } catch (StationEmptyException e) {
            this.displayer.stationEmpty(station);
        }
    }

    private void storeVehicle() {
        try {
            this.station.storeVehicle(this.vehicle);
            this.rentCounter = 0;
            this.vehicle = null;
        } catch (StationFullException e) {
            this.displayer.stationFull(station);
        }
    }

    public boolean hasVehicle() {
        return this.vehicle != null;
    }

    public void tick() {
        if (this.vehicle != null) {
            this.rentCounter++;
            if (this.rentCounter >= this.maxRentCounter) {
                this.storeVehicle();
            }
        }
    }
}