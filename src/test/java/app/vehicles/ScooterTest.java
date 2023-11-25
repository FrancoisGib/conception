package app.vehicles;

import org.junit.jupiter.api.BeforeEach;

import app.mocks.MockScooter;

public class ScooterTest extends VehicleTest {
    MockScooter vehicle;

    @BeforeEach
    public void init() {
        super.init();
        this.vehicle = new MockScooter();
    }

    /*@Test
    public void setLivesCalledWhenVisit() {
        MockRepairer repairer = new MockRepairer();
        assertFalse(vehicle.setLivesCalled);
        repairer.visit(this.vehicle);
        assertTrue(vehicle.setLivesCalled);
    }*/
}
