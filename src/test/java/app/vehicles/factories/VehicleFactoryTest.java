package app.vehicles.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.mocks.VehicleFactoryMock;

public abstract class VehicleFactoryTest {
    protected VehicleFactory factory;

    @BeforeEach
    public void init() {
        this.factory = new VehicleFactoryMock();
    }

    @Test
    @DisplayName("")
    public void createVehicleWorking() {

    }
}
