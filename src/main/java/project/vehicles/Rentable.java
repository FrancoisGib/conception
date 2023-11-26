package project.vehicles;

import project.persons.Visitor;

public interface Rentable {
    public void accept(Visitor visitor);
    public boolean isRentable();
}
