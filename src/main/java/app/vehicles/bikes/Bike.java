package app.vehicles.bikes;

import app.vehicles.Vehicle;

public interface Bike extends Vehicle {
    public void setState(BikeState state);
    public BikeState getState();
}