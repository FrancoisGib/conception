package project.vehicles.bikes;

public class FoldableBike extends ClassicBike {
    public static final String DESCRIPTION = "Foldable Bike";

    public FoldableBike(int id) {
        super(id);
        this.description = DESCRIPTION;
    }
}
