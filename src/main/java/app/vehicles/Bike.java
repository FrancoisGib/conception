package app.vehicles;

public interface Bike extends Vehicle {
    public void setState(BikeState state);
    public BikeState getState();
    public void setLives(int livesCount);
    public int getLives();
}