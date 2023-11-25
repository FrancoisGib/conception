package app.persons.visitors;

import app.vehicles.bikes.Bike;
import app.vehicles.scooters.Scooter;

public interface Visitor {
    public void visit(Bike bike);
    public void visit(Scooter scooter);
}
