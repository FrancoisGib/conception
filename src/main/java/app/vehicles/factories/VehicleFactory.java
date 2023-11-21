package app.vehicles.factories;

import app.vehicles.Vehicle;

public interface VehicleFactory<V extends Vehicle> {
    public V createVehicle(int id);
}
