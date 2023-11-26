package project.vehicles;

import project.persons.Visitor;

public interface Vehicle extends Rentable {
    public String getDescription();
    public int getId();
    public void setLives(int lives);
    public int getLives();
    public void accept(Visitor visitor);
    public void setState(State state);
    public State getState();
}
