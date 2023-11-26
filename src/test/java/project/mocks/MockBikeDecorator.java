package project.mocks;

import project.persons.Visitor;
import project.vehicles.bikes.utilities.BikeDecorator;

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
