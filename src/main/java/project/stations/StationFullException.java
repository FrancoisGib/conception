package project.stations;

public class StationFullException extends Exception {
    public StationFullException() {
        super("The station is full");
    }
}