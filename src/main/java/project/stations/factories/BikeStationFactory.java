package project.stations.factories;

import project.vehicles.factories.BikeFactory;

/**
 * A factory class for creating bike stations.
 * Extends the StationFactory class.
 */
public class BikeStationFactory extends StationFactory {
    public BikeStationFactory() {
        super(new BikeFactory());
    }
}
