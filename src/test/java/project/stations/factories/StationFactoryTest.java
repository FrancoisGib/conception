package project.stations.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.stations.RentalStation;
import project.stations.spaces.ParkingSpace;
import project.vehicles.State;
import project.vehicles.Vehicle;
import project.vehicles.factories.VehicleFactory;

public abstract class StationFactoryTest {
    protected static final int CAPACITY = 10;

    protected static final int ID = 1;

    protected VehicleFactory vehicleFactory;

    protected StationFactory stationFactory;

    protected abstract VehicleFactory createVehicleFactory();

    protected abstract StationFactory createStationFactory();

    @BeforeEach
    public void init() {
        this.vehicleFactory = this.createVehicleFactory();
        this.stationFactory = this.createStationFactory();
    }

    @Test
    public void createStationCheckNumberOfSlots() {
        RentalStation station = stationFactory.createStation(ID, CAPACITY);
        List<ParkingSpace> spaces = station.getSpaces();
        assertEquals(CAPACITY, spaces.size());
        int i = 0;
        for (ParkingSpace space : spaces) {
            Vehicle vehicle = space.getVehicle();
            assertEquals(i + (RentalStation.MAX_CAPACITY * ID), space.getVehicle().getId());
            assertFalse(vehicle.getId() < 0);
            assertEquals(State.STORED, vehicle.getState());
            assertInstanceOf(Vehicle.class, vehicle);
            i++;
        }
    }
}
