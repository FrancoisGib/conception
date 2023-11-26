package project.stations.spaces;

public class SpaceEmptyException extends Exception {
    public SpaceEmptyException() {
        super("The space is empty");
    }
}
