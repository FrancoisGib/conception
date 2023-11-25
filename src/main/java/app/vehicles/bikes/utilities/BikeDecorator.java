package app.vehicles.bikes.utilities;

import app.persons.visitors.Visitor;
import app.vehicles.VehicleDecorator;
import app.vehicles.bikes.Bike;

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