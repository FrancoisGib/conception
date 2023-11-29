package project.stations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockVehicle;
import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceEmptyException;
import project.stations.spaces.SpaceFullException;
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
    public void storeVehicleInSpaceWorking() throws SpaceFullException {
        assertNull(space.getVehicle());
        space.store(vehicle);
        assertSame(vehicle, space.getVehicle());
    }

    @Test
    public void storeVehicleWhenSpaceOccupied() throws SpaceFullException {
        assertNull(space.getVehicle());
        space.store(vehicle);
        assertThrows(SpaceFullException.class, (() -> space.store(vehicle)));
    }

    @Test
    public void removeVehicleInSpaceWorking() throws SpaceFullException, SpaceEmptyException {
        space.store(vehicle);
        assertSame(vehicle, space.getVehicle());
        Vehicle spaceVehicle = space.remove();
        assertSame(vehicle, spaceVehicle);
    }

    @Test
    public void removeVehicleWhenSpaceEmpty() throws SpaceEmptyException {
        assertThrows(SpaceEmptyException.class, () -> space.remove());
    }

    @Test
    public void isEmptyWorking() {
        assertTrue(space.isEmpty());
    }

    @Test
    public void isEmptyWhenFull() throws SpaceFullException {
        space.store(vehicle);
        assertFalse(space.isEmpty());
    }
}
