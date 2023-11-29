package project.vehicles;

public class ElectricBike extends Bike {
    public static final String DESCRIPTION = "Electric Bike";

    public ElectricBike(int id) {
        super(id);
        this.description = DESCRIPTION;
    }
}
