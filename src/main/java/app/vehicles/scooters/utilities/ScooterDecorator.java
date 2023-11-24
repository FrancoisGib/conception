package app.vehicles.scooters.utilities;

import app.vehicles.VehicleDecorator;
import app.vehicles.scooters.ClassicScooter;
import app.vehicles.scooters.Scooter;

public abstract class ScooterDecorator extends VehicleDecorator implements Scooter {
    public ScooterDecorator(ClassicScooter scooter) {
        super(scooter);
        this.vehicle = scooter;
    }
}
