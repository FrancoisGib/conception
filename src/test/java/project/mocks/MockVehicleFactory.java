package project.mocks;

import project.vehicles.factories.VehicleFactory;

public class MockVehicleFactory implements VehicleFactory {
    public int cpt = 0;

    public MockVehicle createVehicle(int id) {
        this.cpt++;
        return new MockVehicle(id);
    }
}
