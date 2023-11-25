package app.vehicles;

public abstract class VehicleDecorator implements Vehicle {
    protected Vehicle vehicle;

    protected String description;

    protected int id;

    public VehicleDecorator(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getId() {
        return this.vehicle.getId();
    }

    public String getDescription() {
        return this.vehicle.getDescription() + " / " + this.description;
    }

    public void setLives(int lives) {
        this.vehicle.setLives(lives);
    }

    public int getLives() {
        return this.vehicle.getLives();
    }

    public void setState(State state) {
        this.vehicle.setState(state);
    }

    public State getState() {
        return this.vehicle.getState();
    }

    public boolean isRentable() {
        return this.vehicle.getState() == State.STORED && this.vehicle.getLives() > 0;
    }

    public void rented() {
        this.vehicle.setLives(this.vehicle.getLives()-1);
        this.vehicle.setState(State.RENTED);
    }

}
