package app.vehicles.utilities;

import app.vehicles.Vehicle;
import lombok.Getter;

public abstract class VehicleDecorator<V extends Vehicle> implements Vehicle {
    protected V vehicle;

    @Getter
    protected int id;

    public VehicleDecorator(V vehicle) {
        super();
        this.vehicle = vehicle;
    }

    @Override
    public String getDescription() {
        return vehicle.getDescription();
    }
}
