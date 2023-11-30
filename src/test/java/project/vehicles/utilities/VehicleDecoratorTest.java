package project.vehicles.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.mocks.MockVehicle;
import project.persons.workers.Worker;
import project.vehicles.Bike;
import project.vehicles.Scooter;
import project.vehicles.State;
import project.vehicles.Vehicle;

public abstract class VehicleDecoratorTest {
    protected static final String SEPARATOR = " / ";

    protected Vehicle vehicle;

    protected String baseDescription;

    protected int initialLives;

    protected VehicleDecorator decorator;

    protected abstract Vehicle createVehicle();

    protected abstract VehicleDecorator createDecorator();

    @BeforeEach
    public void init() {
        this.vehicle = this.createVehicle();
        this.decorator = this.createDecorator();
    };

    @Test
    public void canBeRented() {
        decorator.setState(State.STORED);
        decorator.setLives(1);
        assertTrue(decorator.isRentable());
    }

    @Test
    public void cannotBeRentedBecauseLivesLessThanOne() {;
        decorator.setState(State.STORED);
        decorator.setLives(0);
        assertFalse(decorator.isRentable());
    }

    @Test
    public void vehicleAcceptCalledWhenAcceptVisitor() {
        MockVehicle mockVehicle = new MockVehicle(0);
        VehicleDecorator newDecorator = new MockVehicleDecorator(mockVehicle);
        assertFalse(mockVehicle.acceptCalled);
        newDecorator.accept(new MockWorker());
        assertTrue(mockVehicle.acceptCalled);
    }

    @Test
    public void getDescriptionWithVehicleDescription() {
        MockVehicle mockVehicle = new MockVehicle(0);
        VehicleDecorator newDecorator = new MockVehicleDecorator(mockVehicle);
        assertFalse(mockVehicle.getDescriptionCalled);
        assertEquals(mockVehicle.getDescription() + SEPARATOR + MockVehicleDecorator.DESCRIPTION, newDecorator.getDescription());
        assertTrue(mockVehicle.getDescriptionCalled);
    }

    protected class MockVehicleDecorator extends VehicleDecorator {
        public static final String DESCRIPTION = "Mock Vehicle Decorator";

        public boolean acceptCalled = false;

        public MockVehicleDecorator(Vehicle vehicle) {
            super(vehicle);
            this.description = DESCRIPTION;
        }

        public void accept(Worker worker) {
            acceptCalled = true;
            super.accept(worker);
        }
    }

    protected class MockWorker extends Worker {
        public void tick() {}
        public void visit(Bike bike) {}
        public void visit(Scooter scooter) {}
    }
}
