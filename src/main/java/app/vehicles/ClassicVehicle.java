package app.vehicles;

import lombok.Getter;
import lombok.Setter;

public abstract class ClassicVehicle implements Vehicle {
    @Getter
    protected String description;

    @Getter
    protected int id;

    @Getter @Setter
    protected int lives;

    public ClassicVehicle(int id) {
        this.id = id;
    }

    public boolean isRentable() {
        return false;
    }
}
