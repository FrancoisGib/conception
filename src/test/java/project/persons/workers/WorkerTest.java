package project.persons.workers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockObserver;
import project.mocks.MockVehicle;
import project.vehicles.Bike;
import project.vehicles.Scooter;
import project.vehicles.Vehicle;

public abstract class WorkerTest {
    protected abstract Worker createWorker();

    protected Worker worker;

    protected MockObserver observer;

    @BeforeEach
    public void init() {
        this.worker = this.createWorker();
        this.observer = new MockObserver();
        this.worker.attach(this.observer);
    }

    @Test
    public void isOccupiedWhenIsNot() {
        assertFalse(worker.isOccupied());
    }

    @Test
    public void isOccupiedWhenTrue() {
        MockWorker mockWorker = new MockWorker();
        mockWorker.setVehicle(new MockVehicle());
        assertTrue(mockWorker.isOccupied());
    }

    protected class MockWorker extends Worker {
        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }
        public void visit(Bike bike) {}
        public void visit(Scooter scooter) {}
        public void tick() {} 
    }
}
