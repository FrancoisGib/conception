package app.redistributions;

import java.util.List;

import app.stations.RentalStation;

public interface Redistribution {
    public void redistribute(List<RentalStation> stations);
}
