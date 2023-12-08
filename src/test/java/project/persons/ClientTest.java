package project.persons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Displayer;
import project.mocks.MockObserver;
import project.mocks.MockRentalStation;
import project.mocks.MockVehicle;
import project.stations.RentalStation;
import project.stations.StationEmptyException;
import project.stations.StationFullException;
import project.vehicles.State;
import project.vehicles.Vehicle;

public class ClientTest {
    private Client client;

    private MockDisplayer displayer;

    private RentalStation station;

    @BeforeEach
    public void init() {
        this.displayer = new MockDisplayer();
        this.client = new MockClient(displayer);
        this.station = new MockRentalStation();
        this.station.attach(new MockObserver());
    }

    @Test
    public void rentVehicleWorking() throws StationFullException {
        station.storeVehicle(new MockVehicle());
        assertFalse(client.hasVehicle());
        client.rentVehicle(station);
        assertTrue(client.hasVehicle());
    }

    @Test
    public void rentVehicleWhenStationEmpty() {
        assertFalse(client.hasVehicle());
        client.rentVehicle(station);
        assertFalse(client.hasVehicle());
        assertTrue(displayer.stationEmptyCalled);
    }

    @Test
    public void storeVehicleWorking() throws StationFullException {
        station.storeVehicle(new MockVehicle());
        client.rentVehicle(station);
        assertTrue(client.hasVehicle());
        client.tick();
        assertFalse(client.hasVehicle());
    }

    @Test
    public void storeVehicleWhenStationFull() throws StationFullException {
        station.storeVehicle(new MockVehicle());
        client.rentVehicle(station);
        for (int i = 0; i < MockRentalStation.CAPACITY; i++) {
            station.storeVehicle(new MockVehicle());
        }
        client.tick();
        assertTrue(client.hasVehicle());
        assertTrue(displayer.stationFullCalled);
    }

    @Test
    public void storeVehicleWhenStationFullAndWait() throws StationFullException, StationEmptyException {
        Vehicle vehicle = new MockVehicle();
        vehicle.setLives(2);
        station.storeVehicle(vehicle);
        client.rentVehicle(station);
        for (int i = 0; i < MockRentalStation.CAPACITY; i++) {
            station.storeVehicle(new MockVehicle());
        }
        client.tick();
        assertEquals(State.RENTED, vehicle.getState());
        client.tick();
        assertEquals(State.RENTED, vehicle.getState());
        station.rentVehicle();
        client.tick();
        assertEquals(State.STORED, vehicle.getState());
    }

    private class MockClient extends Client {
        public MockClient(Displayer displayer) {
            super(0, 1);
            this.displayer = displayer;
        }
    }

    private class MockDisplayer extends Displayer {
        public boolean stationEmptyCalled = false;
        public boolean stationFullCalled = false;

        @Override
        public void stationEmpty(RentalStation station) {
            stationEmptyCalled = true;
        }

        @Override
        public void stationFull(RentalStation station) {
            stationFullCalled = true;
        }
    }
}
