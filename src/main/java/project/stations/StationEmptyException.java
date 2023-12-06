package project.stations;

/**
 * Exception thrown when a station is empty.
 */
public class StationEmptyException extends Exception {
    public StationEmptyException() {
        super("Station empty");
    }
}
