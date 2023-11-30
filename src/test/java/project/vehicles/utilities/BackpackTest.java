package project.vehicles.utilities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import project.persons.workers.Worker;
import project.vehicles.Bike;
import project.vehicles.Scooter;

public class BackpackTest extends VehicleDecoratorTest {
    private MockBike vehicle;

    protected Bike createVehicle() {
        return new MockBike();
    }

    protected Backpack createDecorator() {
        return new Backpack(this.vehicle);
    }

    @Test
    public void vehicleAcceptCalledWhenAcceptVisitor() {
        MockWorker visitor = new MockWorker();
        assertFalse(vehicle.acceptCalled);
        vehicle.accept(visitor);
        assertTrue(vehicle.acceptCalled);
    }

    private class MockBike extends Bike {
        public boolean acceptCalled = false;

        public MockBike() {
            super(0);
        }

        public void accept(Worker worker) {
            this.acceptCalled = true;
            super.accept(worker);
        }
    }

    public class MockWorker extends Worker {
        public void visit(Bike bike) {
        }

        public void visit(Scooter scooter) {
        }

        public void tick() {}
    }
}
