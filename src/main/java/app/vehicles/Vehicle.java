package app.vehicles;

import app.persons.visitors.Visitor;

public interface Vehicle {
    public String getDescription();
    public int getId();
    public void setLives(int lives);
    public int getLives();
    public boolean isRentable();
    public void accept(Visitor visitor);
    public void setState(State state);
    public State getState();
    public void rented();
}
