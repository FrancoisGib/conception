package app.vehicles.bikes;

import app.vehicles.ClassicVehicle;
import lombok.Getter;
import lombok.Setter;

public class ClassicBike extends ClassicVehicle implements Bike {
    public static final String DESCRIPTION = "Bike";

    public static final int INITIAL_LIVES = 10;

    @Getter @Setter
    public BikeState state;

    public ClassicBike(int id) {
        super(id);
        this.lives = INITIAL_LIVES;
        this.description = DESCRIPTION;
    }

    public boolean isRentable() {
        return this.state == BikeState.STORED;
    }
}