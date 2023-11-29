package project.stations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockStationFactory;
import project.mocks.MockVehicleFactory;
import project.stations.spaces.ParkingSpace;
import project.vehicles.State;
import project.vehicles.Vehicle;

public abstract class StationFactoryTest {
    protected MockVehicleFactory vehicleFactory;

    protected MockStationFactory factory;

    protected int id;

    protected int capacity;

    protected RentalStation station;

    @BeforeEach
    public void init() {
        this.vehicleFactory = new MockVehicleFactory();
        this.factory = new MockStationFactory(this.vehicleFactory);
        this.id = 1;
        this.capacity = 10;
    }

    @Test
    public void checkStationCapacityAtInitialisation() {
        this.station = factory.createStation(id, capacity);
        assertSame(capacity, station.getCapacity());
    }

    @Test
    public void checkAllVehiclesIdAndStateAndCheckCreateVehiclesCalls() {
        assertSame(0, vehicleFactory.cpt);
        assertFalse(factory.called);
        this.station = factory.createStation(1, capacity);
        Iterator<ParkingSpace> it = station.getSpaces().iterator();
        int i = 0;
        while (it.hasNext()) {
            Vehicle vehicle = it.next().getVehicle();
            assertSame(i + RentalStation.MAX_CAPACITY * id, vehicle.getId());
            assertSame(State.STORED, vehicle.getState());
            i++;
        }
        assertSame(i, vehicleFactory.cpt);
        assertTrue(factory.called);
    }
}
