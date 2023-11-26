package project.visitors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockBike;
import project.mocks.MockScooter;
import project.mocks.MockVisitor;
import project.vehicles.bikes.Bike;
import project.vehicles.scooters.Scooter;

public abstract class VisitorTest {
    MockVisitor visitor;

    @BeforeEach
    public void init() {
        this.visitor = new MockVisitor();
    }

    @Test
    public void visitBikeCall() {
        Bike bike = new MockBike();
        assertFalse(visitor.called);
        bike.accept(visitor);
        assertTrue(visitor.called);
    }

    @Test
    public void visitScooterCall() {
        Scooter scooter = new MockScooter();
        assertFalse(visitor.called);
        scooter.accept(visitor);
        assertTrue(visitor.called);
    }
}
