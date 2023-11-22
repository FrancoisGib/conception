package app.stations;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.mocks.MockParkingSpace;
import app.stations.spaces.ParkingSpace;

public class RentalStationTest {
    protected RentalStation station;

    @BeforeEach
    public void init() {
        List<ParkingSpace> spaces = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            spaces.add(new MockParkingSpace());
        this.station = new RentalStation(0, spaces);
    }

    @Test
    public void storeVehicleWhenStationEmpty() {
    }

    @Test
    public void storeVehicleWhenStationFull() {
    }
}
