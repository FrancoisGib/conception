package project.redistributions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockObserver;
import project.mocks.MockVehicle;
import project.stations.RentalStation;
import project.stations.StationEmptyException;
import project.stations.StationFullException;
import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceEmptyException;
import project.stations.spaces.SpaceFullException;
import project.vehicles.Vehicle;

public abstract class RedistributionTest {
    protected static final int STATIONS_CAPACITY = 5;

    protected Redistribution redistribution;

    protected abstract Redistribution createRedistribution();

    protected MockRentalStation station;

    @BeforeEach
    public void init() {
        this.redistribution = this.createRedistribution();
        this.station = new MockRentalStation();
    }

    @Test
    public void getFirstFreeSlotWhenStationFull() throws StationEmptyException, StationFullException {
        for (int i = 0; i < STATIONS_CAPACITY; i++)
            station.storeVehicle(new MockVehicle(i));
        assertThrows(StationEmptyException.class, () -> redistribution.getFirstFreeSpace(station));
    }

    @Test
    public void getFirstFreeSlotWhenStationNotFull() throws StationEmptyException, StationFullException {
        ParkingSpace space = station.getSpaces().get(0);
        ParkingSpace firstFreeSpace = redistribution.getFirstFreeSpace(station);
        assertSame(space, firstFreeSpace);
    }

    @Test
    public void getAllVehiclesWhenStationsEmpty() {
        RentalStation station = new MockRentalStation();
        assertEquals(0, redistribution.getAllVehicles(Arrays.asList(station)).size());
    }

    @Test
    public void getAllVehiclesWhenStationsNotEmpty() throws StationFullException {
        Vehicle vehicle = new MockVehicle(0);
        station.storeVehicle(vehicle);
        List<Vehicle> vehicles = redistribution.getAllVehicles(Arrays.asList(station));
        assertEquals(1, vehicles.size());
        assertSame(vehicle, vehicles.get(0));
    }

    @Test
    public void noVehicleLostDuringRedistribution() throws StationFullException {
        RentalStation anotherStation = new MockRentalStation();
        List<Vehicle> vehiclesBeforeRedistribution = new ArrayList<>();
        List<RentalStation> stations = Arrays.asList(station, anotherStation);
        for (int i = 0; i < STATIONS_CAPACITY; i++) {
            Vehicle vehicle1 = new MockVehicle(i);
            Vehicle vehicle2 = new MockVehicle(i + STATIONS_CAPACITY);
            vehiclesBeforeRedistribution.addAll(Arrays.asList(vehicle1, vehicle2));
            station.storeVehicle(vehicle1);
            anotherStation.storeVehicle(vehicle2);
        }
        redistribution.redistribute(stations);
        List<Vehicle> vehiclesAfterRedistribution = redistribution.getAllVehicles(stations);
        for (Vehicle vehicle : vehiclesAfterRedistribution) {
            assertTrue(vehiclesBeforeRedistribution.contains(vehicle));
        }
    }

    @Test
    public void checkStoreCallInParkingSpacesWhenRedistribution() throws SpaceFullException {
        List<MockParkingSpace> spaces = station.getSpaces().stream().map((space) -> (MockParkingSpace)space).collect(Collectors.toList());
        for (MockParkingSpace space : spaces) {
            space.store(new MockVehicle(0));
            space.storeCalled = false;
        }
        redistribution.redistribute(Arrays.asList(station));
        for (MockParkingSpace space : spaces)
            assertTrue(space.storeCalled);
    }

    protected List<ParkingSpace> initSpaces() {
        List<ParkingSpace> spaces = new ArrayList<>();
        for (int i = 0; i < STATIONS_CAPACITY; i++)
            spaces.add(new MockParkingSpace());
        return spaces;
    }

    protected class MockRentalStation extends RentalStation {
        public MockRentalStation() {
            super(1, initSpaces());
            this.attach(new MockObserver());
        }
    }

    protected class MockParkingSpace extends ParkingSpace {
        public boolean storeCalled = false;
        public boolean removeCalled = false;

        public void store(Vehicle vehicle) throws SpaceFullException {
            storeCalled = true;
            super.store(vehicle);
        }

        public Vehicle remove() throws SpaceEmptyException {
            removeCalled = true;
            return super.remove();
        }
    }
}
