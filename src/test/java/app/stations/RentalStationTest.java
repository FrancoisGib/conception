package app.stations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.mocks.MockObserver;
import app.mocks.MockParkingSpace;
import app.mocks.MockVehicle;
import app.stations.spaces.ParkingSpace;
import app.stations.spaces.SpaceOccupiedException;
import app.vehicles.Vehicle;

public class RentalStationTest {
    protected static final int CAPACITY = 20;

    protected RentalStation station;

    protected MockObserver observer;

    @BeforeEach
    public void init() {
        List<ParkingSpace> spaces = new ArrayList<>();
        for (int i = 0; i < CAPACITY; i++)
            spaces.add(new MockParkingSpace());
        this.station = new RentalStation(0, spaces);
        this.observer = new MockObserver();
        this.station.attach(this.observer);
    }

    @Test
    public void storeVehicleWhenStationEmpty() throws SpaceOccupiedException, StationFullException {
        assertTrue(station.isEmpty());
        Vehicle vehicle = new MockVehicle();
        assertSame(0, observer.cpt);
        station.storeVehicle(vehicle);
        assertSame(1, observer.cpt);
        assertFalse(station.isEmpty());
        ParkingSpace firstStationSpace = station.getSpaces().get(0);
        assertSame(vehicle, firstStationSpace.getVehicle());
    }

    @Test
    public void storeVehicleWhenStationFull() throws StationFullException, SpaceOccupiedException {
        for (int i = 0; i < CAPACITY; i++)
            station.storeVehicle(new MockVehicle());
        assertThrows(StationFullException.class, () -> station.storeVehicle(new MockVehicle()));
    }
}
