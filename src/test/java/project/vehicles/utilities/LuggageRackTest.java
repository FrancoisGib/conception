package project.vehicles.utilities;

import project.mocks.MockBike;
import project.persons.workers.Worker;
import project.vehicles.Bike;
import project.vehicles.Scooter;

public class LuggageRackTest extends VehicleDecoratorTest {

    protected MockBike createVehicle() {
        return new MockBike();
    }

    protected VehicleDecorator createDecorator() {
        return new LuggageRack(new MockBike());
    }

    public class MockWorker extends Worker {
        public void visit(Bike bike) {}
        public void visit(Scooter scooter) {}
        public void tick() {}
    }
}