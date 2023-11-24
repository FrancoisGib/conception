package app.vehicles;

public abstract class VehicleDecorator extends ClassicVehicle {
    protected Vehicle vehicle;

    public VehicleDecorator(Vehicle vehicle) {
        super(vehicle.getId());
        this.vehicle = vehicle;
    }

    @Override
    public String getDescription() {
        return this.vehicle.getDescription() + this.description;
    }
}
