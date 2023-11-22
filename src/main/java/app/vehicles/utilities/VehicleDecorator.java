package app.vehicles.utilities;

import app.vehicles.Vehicle;
import lombok.Getter;

public abstract class VehicleDecorator implements Vehicle {
    protected Vehicle vehicle;

    @Getter
    protected int id;

    public VehicleDecorator(Vehicle vehicle) {
        super();
        this.vehicle = vehicle;
    }

    @Override
    public String getDescription() {
        return vehicle.getDescription();
    }

    @Override
    public boolean isRentable() {
        return this.vehicle.isRentable();
    }
}
