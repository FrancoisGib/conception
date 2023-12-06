package project.vehicles;

/**
 * Represents an electric bike, which is a type of bike.
 */
public class ElectricBike extends Bike {
    public static final String DESCRIPTION = "Electric Bike";

    /**
     * Constructs a new ElectricBike object with the specified ID.
     *
     * @param id the ID of the electric bike
     */
    public ElectricBike(int id) {
        super(id);
        this.description = DESCRIPTION;
    }
}
