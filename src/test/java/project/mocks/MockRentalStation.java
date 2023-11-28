package project.mocks;

import java.util.ArrayList;
import java.util.List;

import project.stations.ParkingSpace;
import project.stations.RentalStation;

public class MockRentalStation extends RentalStation {
    protected List<MockParkingSpace> spaces;

    public MockRentalStation(int id, List<MockParkingSpace> spaces) {
        super(id, new ArrayList<ParkingSpace>());
        this.spaces = spaces;
    }
}
