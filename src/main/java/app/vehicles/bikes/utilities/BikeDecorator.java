package app.vehicles.bikes.utilities;

import app.vehicles.VehicleDecorator;
import app.vehicles.bikes.Bike;
import app.vehicles.bikes.BikeState;
import lombok.Getter;
import lombok.Setter;

public abstract class BikeDecorator extends VehicleDecorator implements Bike {
    @Getter @Setter
    protected BikeState state;

    public BikeDecorator(Bike bike) {
        super(bike);
        this.vehicle = bike;
    }
}
