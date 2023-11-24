package app.vehicles.scooters;

import app.vehicles.ClassicVehicle;

public class ClassicScooter extends ClassicVehicle implements Scooter {
    public static final String DESCRIPTION = "Scooter";

    public ClassicScooter(int id) {
        super(id);
        this.description = DESCRIPTION;
    }
}