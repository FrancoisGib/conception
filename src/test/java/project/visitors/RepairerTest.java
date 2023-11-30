package project.visitors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockBike;
import project.mocks.MockScooter;
import project.persons.workers.Repairer;
import project.vehicles.Bike;
import project.vehicles.Scooter;

public class RepairerTest {
    protected MockRepairer repairer;

    @BeforeEach
    public void init() {
        this.repairer = new MockRepairer();
    }

    @Test
    public void visitBikeCall() {
        Bike bike = new MockBike();
        assertFalse(repairer.acceptBikeCalled);
        bike.accept(repairer);
        assertTrue(repairer.acceptBikeCalled);
    }

    @Test
    public void visitScooterCall() {
        Scooter scooter = new MockScooter();
        assertFalse(repairer.acceptScooterCalled);
        scooter.accept(repairer);
        assertTrue(repairer.acceptScooterCalled);
    }


    private class MockRepairer extends Repairer {
        public boolean acceptBikeCalled = false;
        public boolean acceptScooterCalled = false;

        public void visit(Bike bike) {
            acceptBikeCalled = true;
            super.visit(bike);
        }

        public void visit(Scooter scooter) {
            acceptScooterCalled = true;
            super.visit(scooter);
        }
    }
}