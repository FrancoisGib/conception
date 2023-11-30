package project.vehicles;

import org.junit.jupiter.api.BeforeEach;

public class BikeTest extends VehicleTest {
    @BeforeEach
    public void init() {
        this.vehicle = new Bike(0);
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
