package app.mocks;

import app.vehicles.factories.VehicleFactory;

public class VehicleFactoryMock implements VehicleFactory {
    public MockVehicle createVehicle(int id) {
        return new MockVehicle();
    }
}
