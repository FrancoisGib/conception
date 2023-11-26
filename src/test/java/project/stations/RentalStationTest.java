package project.stations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockObserver;
import project.mocks.MockParkingSpace;
import project.mocks.MockVehicle;
import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceOccupiedException;
import project.vehicles.State;
import project.vehicles.Vehicle;

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
        vehicle.setState(State.RENTED); // else the station would be considered as empty because the vehicle was not rentable
        assertFalse(observer.called);
        station.storeVehicle(vehicle);
        assertTrue(observer.called);
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
