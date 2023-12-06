package project.stations.spaces;

/**
 * Exception thrown when a space is empty.
 */
public class SpaceEmptyException extends Exception {
    /**
     * Constructs a new SpaceEmptyException with the default message "Space empty".
     */
    public SpaceEmptyException() {
        super("Space empty");
    }
}
