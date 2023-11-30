package project.vehicles.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.vehicles.Vehicle;

public abstract class VehicleFactoryTest {

    protected VehicleFactory factory;

    protected abstract VehicleFactory createFactory();
    
    @BeforeEach
    public void init() {
        this.factory = this.createFactory();
    }

    @Test
    public void createVehicleWorking() {
        Vehicle vehicle = factory.createVehicle(0);
        assertNotNull(vehicle);
        assertEquals(0, vehicle.getId());
    }
}
