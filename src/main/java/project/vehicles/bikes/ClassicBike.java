package project.vehicles.bikes;

import project.persons.Visitor;
import project.vehicles.ClassicVehicle;

public class ClassicBike extends ClassicVehicle implements Bike {

    public ClassicBike(int id) {
        super(id);
        this.description = DESCRIPTION;
        this.lives = INITIAL_LIVES;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
