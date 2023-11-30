package project.vehicles.factories;

public class BikeFactoryTest extends VehicleFactoryTest {
    protected VehicleFactory createFactory() {
        return new BikeFactory();
    }
}
