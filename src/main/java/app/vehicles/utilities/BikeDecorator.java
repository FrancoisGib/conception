package app.vehicles.utilities;

import app.vehicles.Bike;
import app.vehicles.BikeState;
import lombok.Getter;
import lombok.Setter;

public abstract class BikeDecorator extends VehicleDecorator implements Bike {
    @Getter @Setter
    protected BikeState state;

    @Getter @Setter
    protected int lives;

    public BikeDecorator(Bike bike) {
        super(bike);
        this.vehicle = bike;
    }
}
