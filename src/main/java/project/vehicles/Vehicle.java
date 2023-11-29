package project.vehicles;

import lombok.Getter;
import lombok.Setter;
import project.persons.Visitor;

public abstract class Vehicle {
    @Getter
    protected int id;

    @Getter
    protected String description;

    @Getter @Setter
    protected int lives;

    @Getter @Setter
    protected State state;

    public Vehicle(int id) {
        this.id = id;
    }

    public boolean isRentable() {
        return this.state == State.STORED && this.lives > 0;
    }

    public abstract void accept(Visitor visitor);
}

