package project.vehicles;

import org.junit.jupiter.api.BeforeEach;


public class ScooterTest extends VehicleTest {

    @BeforeEach
    public void init() {
        this.vehicle = new Scooter(0);
    }

    /*@Test
    public void setLivesCalledWhenVisit() {
        MockRepairer repairer = new MockRepairer();
        assertFalse(vehicle.setLivesCalled);
        repairer.visit(this.vehicle);
        assertTrue(vehicle.setLivesCalled);
    }*/
}
