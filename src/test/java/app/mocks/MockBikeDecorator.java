package app.mocks;

import app.persons.visitors.Visitor;
import app.vehicles.bikes.utilities.BikeDecorator;

public class MockBikeDecorator extends BikeDecorator {

    public int cpt = 0;

    public MockBikeDecorator() {
        super(new MockBike());
    }

    public void accept(Visitor visitor) {
        this.cpt++;
        super.accept(visitor);
    }
}
