package app.mocks;

import app.vehicles.Vehicle;
import app.vehicles.factories.VehicleFactory;

public class VehicleFactoryMock implements VehicleFactory {
    @Override
    public Vehicle createVehicle(int id) {
        return new MockVehicle();
    }
}
