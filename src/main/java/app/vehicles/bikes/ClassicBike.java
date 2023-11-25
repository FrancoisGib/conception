package app.vehicles.bikes;

import app.persons.visitors.Visitor;
import app.vehicles.ClassicVehicle;

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
