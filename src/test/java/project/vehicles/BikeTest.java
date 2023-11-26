package project.vehicles;

import org.junit.jupiter.api.BeforeEach;

import project.mocks.MockBike;

public class BikeTest extends VehicleTest {
    MockBike vehicle;

    @BeforeEach
    public void init() {
        super.init();
        this.vehicle = new MockBike();
        this.vehicle.setState(State.RENTED);
    }

    /*@Test
    public void setLivesCalledWhenVisit() {
        MockRepairer repairer = new MockRepairer();
        assertFalse(vehicle.setLivesCalled);
        repairer.visit(this.vehicle);
        assertTrue(vehicle.setLivesCalled);
    }*/
}
