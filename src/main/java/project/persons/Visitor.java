package project.persons;

import project.vehicles.bikes.Bike;
import project.vehicles.scooters.Scooter;

public interface Visitor {
    public void visit(Bike bike);
    public void visit(Scooter scooter);
}
