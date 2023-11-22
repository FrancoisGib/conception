package app.stations.factories;

import app.vehicles.factories.BikeFactory;

public class BikeStationFactory extends StationFactory {
    public BikeStationFactory() {
        super(new BikeFactory());
    }
}
