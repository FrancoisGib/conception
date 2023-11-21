package app.vehicles;

import lombok.Getter;
import lombok.Setter;

public class ClassicBike implements Bike {
    public static final String DESCRIPTION = "Bike";

    public static final int INITIAL_LIVES = 10;

    @Getter @Setter
    protected BikeState state;

    @Getter @Setter
    protected int lives;

    @Getter
    protected int id;

    public ClassicBike(int id) {
        this.id = id;
        this.lives = INITIAL_LIVES;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public boolean isRentable() {
        return this.state == BikeState.STORED;
    }
}