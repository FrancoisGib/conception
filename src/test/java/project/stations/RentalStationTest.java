package project.stations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Simulation;
import project.mocks.MockObserver;
import project.mocks.MockVehicle;
import project.vehicles.State;
import project.vehicles.Vehicle;

public class RentalStationTest {
    private static final int CAPACITY = 20;

    private RentalStation station;

    private MockObserver observer;

    @BeforeEach
    public void init() {
        this.station = new RentalStation(0, CAPACITY);
        this.observer = new MockObserver();
        this.station.attach(this.observer);
    }

    @Test
    public void storeVehicleWhenStationFull() throws StationFullException {
        for (int i = 0; i < CAPACITY; i++)
            station.storeVehicle(new MockVehicle());
        assertThrows(StationFullException.class, () -> station.storeVehicle(new MockVehicle()));
    }

    @Test
    public void storeVehicleWhenStationEmpty() throws StationFullException {
        for (int i = 0; i < CAPACITY; i++)
            station.storeVehicle(new MockVehicle());
        assertFalse(station.isEmpty());
    }

    @Test
    public void rentVehicleWhenStationEmpty() throws StationEmptyException {
        assertThrows(StationEmptyException.class, () -> station.rentVehicle());
    }

    @Test
    public void rentVehicleWhenStationNotEmpty() throws StationEmptyException, StationFullException {
        Vehicle vehicle = new MockVehicle();
        station.storeVehicle(vehicle);
        int vehicleLives = vehicle.getLives();
        Vehicle rentedVehicle = station.rentVehicle();
        assertSame(vehicle, rentedVehicle);
        assertSame(vehicleLives - 1, vehicle.getLives());
        assertSame(State.RENTED, rentedVehicle.getState());
    }

    @Test
    public void controlCenterVehicleRentedMethodCalledWhenVehicleRented() throws StationEmptyException, StationFullException {
        station.storeVehicle(new MockVehicle());
        station.rentVehicle();
        assertTrue(observer.vehicleRentedCalled);
    }

    @Test
    public void spaceStoreMethodCalledWhenRentVehicle() throws StationFullException {
        Vehicle vehicle = new MockVehicle();
        station.storeVehicle(vehicle);
        assertSame(vehicle, station.getSpaces().get(0).getVehicle());
    }

    @Test
    public void storeVehicleWhenVehicleOutOfLives() throws StationFullException {
        Vehicle vehicle = new MockVehicle();
        vehicle.setLives(0);
        station.storeVehicle(vehicle);
        assertSame(State.OUT_OF_SERVICE, vehicle.getState());
    }

    @Test
    public void storeVehicleWhenVehicleHasLives() throws StationFullException {
        Vehicle vehicle = new MockVehicle();
        station.storeVehicle(vehicle);
        assertSame(State.STORED, vehicle.getState());
    }

    @Test
    public void controlCenterOutOfServiceMethodCalledWhenVehicleOutOfLivesStored() throws StationFullException {
        Vehicle vehicle = new MockVehicle();
        vehicle.setLives(0);
        station.storeVehicle(vehicle);
        assertTrue(observer.vehicleOutOfServiceCalled);
    }

    @Test
    public void controlCenterVehicleStoredMethodCalledWhenVehicleWithLivesStored() throws StationFullException {
        Vehicle vehicle = new MockVehicle();
        station.storeVehicle(vehicle);
        assertTrue(observer.vehicleStoredCalled);
    }

    @Test
    public void spaceRemoveMethodCalledWhenRentVehicle() throws StationEmptyException, StationFullException {
        station.storeVehicle(new MockVehicle());
        station.rentVehicle();
        assertNull(station.getSpaces().get(0).getVehicle());
    }

    @Test
    public void isEmptyWhenStationNotEmpty() throws StationFullException {
        station.storeVehicle(new MockVehicle());
        assertFalse(station.isEmpty());
    }

    @Test
    public void isEmptyWhenAllVehiclesInStationOutOfService() throws StationFullException {
        Vehicle vehicle = new MockVehicle();
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
            station.storeVehicle(new MockVehicle());
        assertTrue(station.isFull()); 
    }

    @Test
    public void testTickUntilStolenWhenJustOneVehicleInStation() throws StationFullException {
        Vehicle vehicle = new MockVehicle();
        station.storeVehicle(vehicle);
        for (int i = 0; i < Simulation.TIME_BEFORE_VEHICLE_STOLEN; i++)
            station.tick();
        assertSame(State.STOLEN, vehicle.getState());
        assertTrue(observer.vehicleStolenCalled);
    }

    @Test
    public void testJustOneTickWhenJustOneVehicleInStation() throws StationFullException {
        Vehicle vehicle = new MockVehicle();
        station.storeVehicle(vehicle);
        station.tick();
        assertNotSame(State.STOLEN, vehicle.getState());
        assertFalse(observer.vehicleStolenCalled);
    }
}