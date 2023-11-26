package project.vehicles.scooters;

import project.persons.Visitor;
import project.vehicles.ClassicVehicle;

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

