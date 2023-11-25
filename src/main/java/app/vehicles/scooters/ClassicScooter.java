package app.vehicles.scooters;

import app.persons.visitors.Visitor;
import app.vehicles.ClassicVehicle;

public class ClassicScooter extends ClassicVehicle implements Scooter {

    public ClassicScooter(int id) {
        super(id);
        this.description = DESCRIPTION;
        this.lives = INITIAL_LIVES;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

