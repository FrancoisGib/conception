package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockVehicle;
import project.vehicles.State;
import project.vehicles.Vehicle;

public class RentTest {
    private Vehicle vehicle;

    private Rent rent;

    @BeforeEach
    public void init() {
        this.vehicle = new MockVehicle();
        this.rent = new Rent(vehicle);
        this.rent.attach(ControlCenter.getInstance(new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void incrementByOneBeforeStolen() {
        assertNotEquals(State.STOLEN, vehicle.getState());
        rent.increment();
        assertNotEquals(State.STOLEN, vehicle.getState());
    }

    @Test
    public void incrementUntilVehicleStolen() {
        assertNotEquals(State.STOLEN, vehicle.getState());
        for (int i = 0; i < Simulation.TIME_BEFORE_VEHICLE_STOLEN; i++)
            rent.increment();
        assertEquals(State.STOLEN, vehicle.getState());
    }
}
