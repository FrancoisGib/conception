package app.vehicles.bikes;

public class ElectricBike extends ClassicBike {
    public static final String DESCRIPTION = "Electric Bike";

    public ElectricBike(int id) {
        super(id);
        this.description = DESCRIPTION;
    }
}
