package project.vehicles.factories;

import project.vehicles.Bike;

public class BikeFactoryTest extends VehicleFactoryTest {
    protected VehicleFactory<Bike> createFactory() {
        return new BikeFactory();
    }
}
