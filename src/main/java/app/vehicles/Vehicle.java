package app.vehicles;

public interface Vehicle {
    public String getDescription();
    public int getId();
    public boolean isRentable();
    public int getLives();
    public void setLives(int lives);
}
