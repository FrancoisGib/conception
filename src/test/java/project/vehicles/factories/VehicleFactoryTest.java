package project.vehicles.factories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.VehicleFactoryMock;
import project.vehicles.Vehicle;

public abstract class VehicleFactoryTest {
    protected VehicleFactory factory;

    @BeforeEach
    public void init() {
        this.factory = new VehicleFactoryMock();
    }

    @Test
    public void createVehicleWorking() {
        Vehicle vehicle = factory.createVehicle(0);
        assertNotNull(vehicle);
        assertSame(0, vehicle.getId());
    }
}
