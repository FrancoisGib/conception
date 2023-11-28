package project.stations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockVehicle;
import project.vehicles.Vehicle;

public class ParkingSpaceTest {
    Vehicle vehicle;
    ParkingSpace space;

    @BeforeEach
    public void init() {
        this.vehicle = new MockVehicle(0);
        this.space = new ParkingSpace();
    }

    @Test
    public void storeVehicleInSpaceWorking() {
        assertTrue(space.store(vehicle));
        assertSame(vehicle, space.getVehicle());
    }

    @Test
    public void storeVehicleWhenSpaceOccupied() {
        space.store(vehicle);
        assertFalse(space.store(vehicle));
    }

    @Test
    public void removeVehicleInSpaceWorking() {
        boolean res = space.store(vehicle);
        assertTrue(res);
        Vehicle spaceVehicle = space.remove();
        assertSame(vehicle, spaceVehicle);
    }

    @Test
    public void removeVehicleWhenSpaceEmpty() {
        assertNull(space.remove());
    }

    @Test
    public void isOccupiedWhenEmpty() {
        assertFalse(space.isOccupied());
    }

    @Test
    public void isOccupiedWorking() {
        space.store(vehicle);
        assertTrue(space.isOccupied());
    }
}
