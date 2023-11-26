package project.stations;

public class StationEmptyException extends Exception {
    public StationEmptyException() {
        super("The station is empty");
    }
}