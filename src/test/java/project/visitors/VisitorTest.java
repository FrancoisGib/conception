package project.visitors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockBike;
import project.mocks.MockScooter;
import project.persons.workers.Worker;
import project.vehicles.Bike;
import project.vehicles.Scooter;

public abstract class VisitorTest {
    MockWorker worker;

    @BeforeEach
    public void init() {
        this.worker = new MockWorker();
    }

    @Test
    public void visitBikeCall() {
        Bike bike = new MockBike();
        assertFalse(worker.bikeCalled);
        bike.accept(worker);
        assertTrue(worker.bikeCalled);
    }

    @Test
    public void visitScooterCall() {
        Scooter scooter = new MockScooter();
        assertFalse(worker.scooterCalled);
        scooter.accept(worker);
        assertTrue(worker.scooterCalled);
    }

    protected class MockWorker extends Worker {
        public boolean bikeCalled = false;
        public boolean scooterCalled = true;

        public void visit(Bike bike) {
            bikeCalled = true;
        }

        public void visit(Scooter scooter) {
            scooterCalled = true;
        }

        public void tick() {
        }
    }
}
