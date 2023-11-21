package app.stations.spaces;

public class SpaceOccupiedException extends Exception {
    public SpaceOccupiedException() {
        super("A vehicle is already on that space");
    }
}
