package app.vehicles;

import app.persons.visitors.Visitor;
import lombok.Getter;
import lombok.Setter;

public abstract class ClassicVehicle implements Vehicle, Rentable {
    @Getter
    protected int id;

    @Getter
    protected String description;

    @Getter @Setter
    protected int lives;

    @Getter @Setter
    protected State state;

    public ClassicVehicle(int id) {
        this.id = id;
    }

    public boolean isRentable() {
        if (this.state == State.STORED && this.lives > 0)
            return true;
        this.state = State.OUT_OF_SERVICE;
        return false;
    }

    public void rented() {
        this.lives--;
        this.setState(State.RENTED);
    }

    public abstract void accept(Visitor visitor);
}

