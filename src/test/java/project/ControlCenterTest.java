package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockBike;
import project.mocks.MockRentalStation;
import project.mocks.MockRepairer;
import project.mocks.MockVehicle;
import project.persons.workers.Repairer;
import project.stations.RentalStation;
import project.stations.StationEmptyException;
import project.vehicles.Bike;
import project.vehicles.State;
import project.vehicles.Vehicle;

public class ControlCenterTest {
    private List<MockRentalStation> stations;

    private List<RentalStation> centerStations;

    private List<MockRepairer> repairers;

    private List<Repairer> centerRepairers;

    private ControlCenter controlCenter;

    private MockDisplayer displayer;

    @BeforeEach
    public void init() throws Exception {
        MockRentalStation station1 = new MockRentalStation();
        MockRentalStation station2 = new MockRentalStation();
        this.stations = Arrays.asList(station1, station2);
        this.centerStations = Arrays.asList(station1, station2);
        MockRepairer repairer1 = new MockRepairer();
        MockRepairer repairer2 = new MockRepairer();
        this.repairers = Arrays.asList(repairer1, repairer2);
        this.centerRepairers = Arrays.asList(repairer1, repairer2);
        this.controlCenter = ControlCenter.getInstance(centerStations, centerRepairers);
        this.displayer = new MockDisplayer();
        Field displayerField = ControlCenter.class.getDeclaredField("displayer");
        displayerField.setAccessible(true);
        displayerField.set(controlCenter, this.displayer);
    }

    @AfterEach
    public void resetStations() throws StationEmptyException {
        for (RentalStation station : stations) {
            while (!station.isEmpty())
                station.rentVehicle();
        }
    }

    @Test
    public void launch() throws Exception {
        attachmentOnInitialisation();
        vehicleOutOfServiceAndRepairerAvailable();
        vehicleOutOfServiceAndRepairerNotAvailable();
        vehicleRentedNewRentCreated();
        vehicleStoredSuppressRent();
        vehicleStolenStateChangedAndDisplayerCalled();
        tickWhenStationsFull();
        tickWhenStationsNotFullAndNotEmpty();
        vehicleRepairedDisplayerCalled();
        vehicleBackFromStolenDisplayerCalled();
    }

    public void attachmentOnInitialisation() {
        for (MockRepairer repairer : repairers)
            assertTrue(repairer.attachCalled);
        for (MockRentalStation station : stations) {
            assertTrue(station.attachCalled);
        }
    }

    public void vehicleOutOfServiceAndRepairerAvailable() {
        MockRepairer repairer = repairers.get(0);
        assertFalse(repairer.isOccupied());
        Bike vehicle = new MockBike();
        vehicle.setState(State.OUT_OF_SERVICE);

        controlCenter.vehicleOutOfService(vehicle);

        assertEquals(State.REPARATION, vehicle.getState());
        assertTrue(repairer.isOccupied());
        assertTrue(repairer.acceptBikeCalled);

        vehicle.setState(State.STORED); // just to reset
    }

    public void vehicleOutOfServiceAndRepairerNotAvailable() {
        MockRepairer repairer = repairers.get(1);
        assertFalse(repairer.isOccupied());
        Bike vehicle = new MockBike();
        vehicle.setState(State.OUT_OF_SERVICE);

        controlCenter.vehicleOutOfService(vehicle);

        assertTrue(repairer.isOccupied());
        assertTrue(repairer.acceptBikeCalled);

        vehicle.setState(State.STORED); // just to reset
    }

    @SuppressWarnings("unchecked")
    public void vehicleRentedNewRentCreated() throws Exception {
        Field field = ControlCenter.class.getDeclaredField("rentedVehicles");
        field.setAccessible(true);
        List<Rent> rents = (List<Rent>) field.get(controlCenter);
        assertEquals(0, rents.size());
        RentalStation station = stations.get(0);
        Vehicle vehicle = new MockVehicle();
        station.storeVehicle(vehicle);

        station.rentVehicle();

        assertEquals(1, rents.size());
        assertSame(vehicle, rents.get(0).getVehicle());
    }

    @SuppressWarnings("unchecked")
    public void vehicleStoredSuppressRent() throws Exception {
        Field field = ControlCenter.class.getDeclaredField("rentedVehicles");
        field.setAccessible(true);
        List<Rent> rents = (List<Rent>) field.get(controlCenter);
        RentalStation station = stations.get(0);
        Vehicle vehicle = new MockVehicle();
        station.storeVehicle(vehicle);
        station.rentVehicle();
        assertEquals(2, rents.size()); // Because with the singleton the others test methods impacts the tests.

        station.storeVehicle(vehicle);

        assertEquals(1, rents.size()); // But it work because we just want the size decrement by one.
    }

    public void vehicleStolenStateChangedAndDisplayerCalled() throws Exception {
        Vehicle vehicle = new MockVehicle();
        vehicle.setState(State.RENTED);
        controlCenter.vehicleStolen(vehicle);

        assertEquals(State.STOLEN, vehicle.getState());
        assertTrue(displayer.vehicleStolenCalled);
    }

    @SuppressWarnings("unchecked")
    public void tickWhenStationsFull() throws Exception {
        Field redistributionCounterField = ControlCenter.class.getDeclaredField("redistributionCounter");
        redistributionCounterField.setAccessible(true);
        Map<RentalStation, Integer> redistributionCounter = (Map<RentalStation, Integer>) redistributionCounterField
                .get(controlCenter);

        Field tickBeforeRedistributionField = ControlCenter.class.getDeclaredField("TICK_BEFORE_REDISTRIBUTION");
        tickBeforeRedistributionField.setAccessible(true);
        int tickBeforeRedistribution = (Integer) tickBeforeRedistributionField.get(controlCenter);

        for (RentalStation station : stations)
            station.storeVehicle(new MockVehicle());
        for (int i = 0; i < tickBeforeRedistribution; i++) {
            Iterator<Integer> it = redistributionCounter.values().iterator();
            while (it.hasNext())
                assertEquals(i, it.next());
            controlCenter.tick();
        }
        // cleared
        redistributionCounter.forEach((key, value) -> assertEquals(0, value));
    }

    @SuppressWarnings("unchecked")
    public void tickWhenStationsNotFullAndNotEmpty() throws Exception {
        Field redistributionCounterField = ControlCenter.class.getDeclaredField("redistributionCounter");
        redistributionCounterField.setAccessible(true);
        Map<RentalStation, Integer> redistributionCounter = (Map<RentalStation, Integer>) redistributionCounterField
                .get(controlCenter);

        RentalStation station = stations.get(1);
        assertFalse(station.isEmpty());
        assertFalse(station.isFull());

        controlCenter.tick();

        assertEquals(null, redistributionCounter.get(station)); // null because of the clear at the previous method
    }

    public void vehicleBackFromStolenDisplayerCalled() {
        controlCenter.vehicleBackFromStolen(new MockVehicle());
        assertTrue(displayer.vehicleBackFromStolenCalled);
    }

    public void vehicleRepairedDisplayerCalled() {
        controlCenter.vehicleRepaired(new MockVehicle());
        assertTrue(displayer.vehicleRepairedCalled);
    }

    private class MockDisplayer extends Displayer {
        public boolean vehicleStolenCalled = false;
        public boolean vehicleBackFromStolenCalled = false;
        public boolean vehicleRepairedCalled = false;

        public void vehicleRented(Vehicle vehicle, RentalStation station) {
        }

        public void vehicleStored(Vehicle vehicle, RentalStation station) {
        }

        public void vehicleStolen(Vehicle vehicle) {
            vehicleStolenCalled = true;
        }

        public void vehicleOutOfService(Vehicle vehicle) {
        }

        public void vehicleBackFromStolen(Vehicle vehicle) {
            vehicleBackFromStolenCalled = true;
        }

        public void vehicleRepaired(Vehicle vehicle) {
            vehicleRepairedCalled = true;
        }

        public void displayRedistribution(List<RentalStation> stations) {
        }

        public void displayStations(List<RentalStation> stations) {
        }
    }
}
