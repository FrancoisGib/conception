package project.stations;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
    private ParkingSpace space;
    private MockVehicle vehicle;

    @BeforeEach
    public void init() {
        this.space = new ParkingSpace();
        this.vehicle = new MockVehicle(0);
    }

    @Test
    public void storeWhenSpaceEmpty() throws SpaceFullException {
        assertTrue(space.isEmpty());
        space.store(vehicle);
        assertSame(vehicle, space.getVehicle());
        assertFalse(space.isEmpty());
    }

    @Test
    public void storeWhenSpaceFull() throws SpaceFullException {
        assertTrue(space.isEmpty());
        space.store(vehicle);
        assertThrows(SpaceFullException.class, () -> space.store(vehicle));
    }

    @Test
    public void removeVehicleWhenSpaceFull() throws SpaceEmptyException, SpaceFullException {
        assertTrue(space.isEmpty());
        space.store(vehicle);
        Vehicle spaceVehicle = space.remove();
        assertSame(vehicle, spaceVehicle);
    }

    @Test
    public void removeVehicleWhenSpaceEmpty() throws SpaceEmptyException {
        assertTrue(space.isEmpty());
        assertThrows(SpaceEmptyException.class, () -> space.remove());
    }

    @Test
    public void isEmpty() throws SpaceFullException {
        assertTrue(space.isEmpty());
        space.store(vehicle);
        assertFalse(space.isEmpty());
    }
}
