package project.vehicles;

public class FoldableBike extends Bike {
    public static final String DESCRIPTION = "Foldable Bike";

    public FoldableBike(int id) {
        super(id);
        this.description = DESCRIPTION;
    }
}
