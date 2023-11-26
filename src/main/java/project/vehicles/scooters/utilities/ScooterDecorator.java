package project.vehicles.scooters.utilities;

import project.persons.Visitor;
import project.vehicles.VehicleDecorator;
import project.vehicles.scooters.Scooter;

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
