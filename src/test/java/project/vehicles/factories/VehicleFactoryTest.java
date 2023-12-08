package project.vehicles.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.vehicles.Vehicle;

public abstract class VehicleFactoryTest {

    protected VehicleFactory<? extends Vehicle> factory;

    protected abstract VehicleFactory<? extends Vehicle> createFactory();
    
    @BeforeEach
    public void init() {
        this.factory = this.createFactory();
    }

    @Test
    public void createVehicleWorking() {
        Vehicle vehicle = factory.createVehicle();
        assertNotNull(vehicle);
        assertEquals(0, vehicle.getId());
    }
    
    @Test
    public void cptIncrementation() {
        Vehicle firstVehicle = factory.createVehicle();
        assertEquals(0, firstVehicle.getId());
        Vehicle secondVehicle = factory.createVehicle();
        assertEquals(1, secondVehicle.getId());
    }
}
