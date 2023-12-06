package project.vehicles;

/**
 * Represents a foldable bike, which is a type of bike that can be folded for easy storage and transportation.
 * Inherits from the Bike class.
 */
public class FoldableBike extends Bike {
    public static final String DESCRIPTION = "Foldable Bike";

    public FoldableBike(int id) {
        super(id);
        this.description = DESCRIPTION;
    }
}
