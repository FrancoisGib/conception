package project.stations.factories;

import java.util.ArrayList;
import java.util.List;

import project.stations.RentalStation;
import project.stations.spaces.ParkingSpace;
import project.stations.spaces.SpaceFullException;
import project.vehicles.State;
import project.vehicles.Vehicle;
import project.vehicles.factories.VehicleFactory;

public abstract class StationFactory {
    protected VehicleFactory factory;

    public StationFactory(VehicleFactory factory) {
        this.factory = factory;
    }

    public RentalStation createStation(int id, int capacity) {
        List<ParkingSpace> spaces = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Vehicle newVehicle = this.factory.createVehicle(i + (id * RentalStation.MAX_CAPACITY));
            newVehicle.setState(State.STORED);
            ParkingSpace space = new ParkingSpace();
            try {
                space.store(newVehicle);
            } catch (SpaceFullException e) {
            }
            spaces.add(space);
        }
        return new RentalStation(id, spaces);
    }

}
