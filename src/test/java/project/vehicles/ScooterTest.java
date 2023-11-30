package project.vehicles;

public class ScooterTest extends VehicleTest {
    protected Vehicle createVehicle() {
        return new Scooter(0);
    }
}
