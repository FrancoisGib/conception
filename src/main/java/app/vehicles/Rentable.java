package app.vehicles;

import app.persons.visitors.Visitor;

public interface Rentable {
    public void accept(Visitor visitor);
}
