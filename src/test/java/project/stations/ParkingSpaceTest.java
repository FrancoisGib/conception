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
import project.stations.spaces.SpaceOccupiedException;
import project.vehicles.Vehicle;

public class ParkingSpaceTest {
    Vehicle vehicle;
    ParkingSpace space;

    @BeforeEach
    public void init() {
        this.vehicle = new MockVehicle();
        this.space = new ParkingSpace();
    }

    @Test
    public void storeVehicleInSpaceWorking() throws SpaceOccupiedException {
        space.store(vehicle);
        assertSame(vehicle, space.getVehicle());
    }

    @Test
    public void storeVehicleWhenSpaceOccupied() throws SpaceOccupiedException {
        space.store(vehicle);
        assertThrows(SpaceOccupiedException.class, () -> space.store(vehicle));
    }

    @Test
    public void removeVehicleInSpaceWorking() throws SpaceEmptyException, SpaceOccupiedException {
        space.store(vehicle);
        assertSame(vehicle, space.remove());
    }

    @Test
    public void removeVehicleWhenSpaceEmpty() throws SpaceEmptyException {
        assertThrows(SpaceEmptyException.class, () -> space.remove());
    }

    @Test
    public void isOccupiedWhenEmpty() {
        assertFalse(space.isOccupied());
    }

    @Test
    public void isOccupiedWorking() throws SpaceOccupiedException {
        space.store(vehicle);
        assertTrue(space.isOccupied());
    }
}
