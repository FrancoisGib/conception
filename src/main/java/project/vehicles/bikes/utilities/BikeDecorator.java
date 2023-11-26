package project.vehicles.bikes.utilities;

import project.persons.Visitor;
import project.vehicles.VehicleDecorator;
import project.vehicles.bikes.Bike;

public abstract class BikeDecorator extends VehicleDecorator implements Bike {
    protected Bike vehicle;

    public BikeDecorator(Bike bike) {
        super(bike);
        this.vehicle = bike;
    }

    public void accept(Visitor visitor) {
        this.vehicle.accept(visitor);
    }
}