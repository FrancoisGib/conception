package project.stations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Simulation;
import project.mocks.MockObserver;
import project.mocks.MockVehicle;
import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceEmptyException;
import project.stations.spaces.SpaceFullException;
import project.vehicles.State;
import project.vehicles.Vehicle;

public class RentalStationTest {
    private static final int CAPACITY = 20;

    private RentalStation station;

    private List<MockParkingSpace> spaces;

    private MockObserver observer;

    @BeforeEach
    public void init() {
        List<ParkingSpace> constructorSpaces = new ArrayList<>();
        this.spaces = new ArrayList<>();
        for (int i = 0; i < CAPACITY; i++) {
            MockParkingSpace space = new MockParkingSpace();
            this.spaces.add(space);
            constructorSpaces.add(space);
        }
        this.station = new RentalStation(CAPACITY, constructorSpaces);
        this.observer = new MockObserver();
        this.station.attach(this.observer);
    }

    @Test
    public void storeVehicleWhenStationFull() throws StationFullException {
        for (int i = 0; i < CAPACITY; i++)
            station.storeVehicle(new MockVehicle(i));
        assertThrows(StationFullException.class, () -> station.storeVehicle(new MockVehicle(0)));
    }

    @Test
    public void storeVehicleWhenStationEmpty() throws StationFullException {
        for (int i = 0; i < CAPACITY; i++)
            station.storeVehicle(new MockVehicle(i));
        assertFalse(station.isEmpty());
    }

    @Test
    public void rentVehicleWhenStationEmpty() throws StationEmptyException {
        assertThrows(StationEmptyException.class, () -> station.rentVehicle());
    }

    @Test
    public void rentVehicleWhenStationNotEmpty() throws StationEmptyException, StationFullException {
        Vehicle vehicle = new MockVehicle(0);
        station.storeVehicle(vehicle);
        int vehicleLives = vehicle.getLives();
        Vehicle rentedVehicle = station.rentVehicle();
        assertSame(vehicle, rentedVehicle);
        assertSame(vehicleLives - 1, vehicle.getLives());
        assertSame(State.RENTED, rentedVehicle.getState());
    }

    @Test
    public void controlCenterVehicleRentedMethodCalledWhenVehicleRented() throws StationEmptyException, StationFullException {
        station.storeVehicle(new MockVehicle(0));
        station.rentVehicle();
        assertTrue(observer.vehicleRentedCalled);
    }

    @Test
    public void spaceStoreMethodCalledWhenRentVehicle() throws StationFullException {
        station.storeVehicle(new MockVehicle(0));
        assertTrue(spaces.get(0).storeCalled);
    }

    @Test
    public void storeVehicleWhenVehicleOutOfLives() throws StationFullException {
        Vehicle vehicle = new MockVehicle(0);
        vehicle.setLives(0);
        station.storeVehicle(vehicle);
        assertSame(State.OUT_OF_SERVICE, vehicle.getState());
    }

    @Test
    public void storeVehicleWhenVehicleHasLives() throws StationFullException {
        Vehicle vehicle = new MockVehicle(0);
        station.storeVehicle(vehicle);
        assertSame(State.STORED, vehicle.getState());
    }

    @Test
    public void controlCenterOutOfServiceMethodCalledWhenVehicleOutOfLivesStored() throws StationFullException {
        Vehicle vehicle = new MockVehicle(0);
        vehicle.setLives(0);
        station.storeVehicle(vehicle);
        assertTrue(observer.vehicleOutOfServiceCalled);
    }

    @Test
    public void controlCenterVehicleStoredMethodCalledWhenVehicleWithLivesStored() throws StationFullException {
        Vehicle vehicle = new MockVehicle(0);
        station.storeVehicle(vehicle);
        assertTrue(observer.vehicleStoredCalled);
    }

    @Test
    public void spaceRemoveMethodCalledWhenRentVehicle() throws StationEmptyException, StationFullException {
        station.storeVehicle(new MockVehicle(0));
        station.rentVehicle();
        assertTrue(spaces.get(0).removeCalled);
    }

    @Test
    public void isEmptyWhenStationNotEmpty() throws StationFullException {
        station.storeVehicle(new MockVehicle(0));
        assertFalse(station.isEmpty());
    }

    @Test
    public void isEmptyWhenAllVehiclesInStationOutOfService() throws StationFullException {
        Vehicle vehicle = new MockVehicle(0);
        station.storeVehicle(vehicle);
        vehicle.setState(State.OUT_OF_SERVICE);
        assertTrue(station.isEmpty());
    }

    @Test
    public void isFullWhenStationNotFull() {
        assertFalse(station.isFull());
    }

    @Test
    public void isFullWhenStationFull() throws StationFullException {
        for (int i = 0; i < CAPACITY; i++)
            station.storeVehicle(new MockVehicle(i));
        assertTrue(station.isFull()); 
    }

    @Test
    public void testTickUntilStolenWhenJustOneVehicleInStation() throws StationFullException {
        Vehicle vehicle = new MockVehicle(0);
        station.storeVehicle(vehicle);
        for (int i = 0; i < Simulation.TIME_BEFORE_VEHICLE_STOLLEN; i++)
            station.tick();
        assertSame(State.STOLEN, vehicle.getState());
        assertTrue(observer.vehicleStolenCalled);
    }

    @Test
    public void testJustOneTickWhenJustOneVehicleInStation() throws StationFullException {
        Vehicle vehicle = new MockVehicle(0);
        station.storeVehicle(vehicle);
        station.tick();
        assertNotSame(State.STOLEN, vehicle.getState());
        assertFalse(observer.vehicleStolenCalled);
    }

    private class MockParkingSpace extends ParkingSpace {
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