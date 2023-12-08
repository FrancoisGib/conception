package project.mocks;

import project.Observer;
import project.stations.RentalStation;

public class MockRentalStation extends RentalStation {
    public boolean attachCalled = false;

    public static final int CAPACITY = 2;

    public MockRentalStation() {
        super(0, CAPACITY);
    }

    public void attach(Observer observer) {
        attachCalled = true;
        super.attach(observer);
    }
}