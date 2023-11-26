package project.vehicles.factories;

import project.vehicles.Vehicle;

public interface VehicleFactory {
    public Vehicle createVehicle(int id);
}
