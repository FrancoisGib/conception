package app.vehicles.scooters.utilities;

import app.persons.visitors.Visitor;
import app.vehicles.VehicleDecorator;
import app.vehicles.scooters.Scooter;

public class ScooterDecorator extends VehicleDecorator implements Scooter {
    protected Scooter vehicle;

    public ScooterDecorator(Scooter scooter) {
        super(scooter);
        this.vehicle = scooter;
    }

    public void accept(Visitor visitor) {
        this.vehicle.accept(visitor);
    }
}
