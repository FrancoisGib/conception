package project.workers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import project.mocks.MockBike;
import project.mocks.MockScooter;
import project.persons.workers.Repairer;
import project.persons.workers.Worker;
import project.vehicles.Bike;
import project.vehicles.State;

public class RepairerTest extends WorkerTest {
    protected Worker createWorker() {
        return new Repairer();
    }

    @Test
    public void checkCallToSetStateWhenVisitBike() {
        MockBike bike = new MockBike();
        assertNotEquals(State.REPARATION, bike.getState());
        worker.visit(bike);
        assertEquals(State.REPARATION, bike.getState());
    }

    @Test
    public void checkCallToSetStateWhenVisitScooter() {
        MockScooter scooter = new MockScooter();
        assertNotEquals(State.REPARATION, scooter.getState());
        worker.visit(scooter);
        assertEquals(State.REPARATION, scooter.getState());
    }

    @Test
    public void tickUntilCptEqualsReparationTime() {
        int reparationTime = Bike.REPARATION_TIME;
        int initialLives = Bike.INITIAL_LIVES;
        Bike bike = new MockBike();
        bike.setLives(0);
        worker.visit(bike);
        assertTrue(worker.isOccupied());
        for (int i = 0; i < reparationTime; i++)
            worker.tick();
        assertEquals(initialLives, bike.getLives());
        assertEquals(State.STORED, bike.getState());
        assertTrue(observer.vehicleRepairedCalled);
    }

    @Test
    public void tickBeforeCptEqualsReparationTime() {
        int reparationTime = Bike.REPARATION_TIME;
        int initialLives = Bike.INITIAL_LIVES;
        Bike bike = new MockBike();
        bike.setLives(0);
        worker.visit(bike);
        assertTrue(worker.isOccupied());
        for (int i = 1; i < reparationTime; i++)
            worker.tick();
        assertNotEquals(initialLives, bike.getLives());
        assertNotEquals(State.STORED, bike.getState());
        assertFalse(observer.vehicleRepairedCalled);
    }
}
