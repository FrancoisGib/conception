package project.stations.factories;

import project.vehicles.factories.BikeFactory;

public class BikeStationFactory extends StationFactory {
    public BikeStationFactory() {
        super(new BikeFactory());
    }
}
