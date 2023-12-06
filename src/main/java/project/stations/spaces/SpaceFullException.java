package project.stations.spaces;

/**
 * This exception is thrown when a space is full and cannot accommodate any more items.
 */
public class SpaceFullException extends Exception {
    public SpaceFullException() {
        super("Space full");
    }
}
