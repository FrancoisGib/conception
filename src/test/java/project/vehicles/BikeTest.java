package project.vehicles;

public class BikeTest extends VehicleTest {
    protected Vehicle createVehicle() {
        return new Bike(0);
    }
}
