package project.stations;

/**
 * This exception is thrown when a station is full and cannot accept any more items.
 */
public class StationFullException extends Exception {
    public StationFullException() {
        super("Station full");
    }
}
