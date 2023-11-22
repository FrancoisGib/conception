package app.vehicles.factories;

import app.vehicles.Vehicle;

public interface VehicleFactory {
    public Vehicle createVehicle(int id);
}
