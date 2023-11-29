package project.persons;

import project.vehicles.Bike;
import project.vehicles.Scooter;

public interface Visitor {
    public void visit(Bike bike);
    public void visit(Scooter scooter);
}
