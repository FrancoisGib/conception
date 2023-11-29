package project.mocks;

import project.vehicles.Bike;
import project.vehicles.factories.BikeFactory;

public class MockBikeFactory extends BikeFactory {
    public int cpt = 0;

    public Bike createVehicle(int id) {
        this.cpt++;
        return new Bike(id);
    }
}
