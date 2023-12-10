package project.vehicles;

/**
 * Represents a foldable bike, which is a type of bike that can be folded for easy storage and transportation.
 * Inherits from the Bike class.
 */
public class FoldableBike extends Bike {
    public static final String DESCRIPTION = "Foldable Bike";

    /**
     * Constructs a FoldableBike object with the specified unique identifier.
     *
     * @param id the unique identifier for the foldable bike
     */
    public FoldableBike(int id) {
        super(id);
        this.description = DESCRIPTION;
    }
}
