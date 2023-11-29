package project.mocks;

import java.util.ArrayList;
import java.util.List;

import project.stations.RentalStation;
import project.stations.spaces.ParkingSpace;

public class MockRentalStation extends RentalStation {
    protected List<MockParkingSpace> spaces;

    public MockRentalStation(int id, List<MockParkingSpace> spaces) {
        super(id, new ArrayList<ParkingSpace>());
        this.spaces = spaces;
    }
}
