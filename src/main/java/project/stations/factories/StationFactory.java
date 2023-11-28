package project.stations.factories;

import java.util.ArrayList;
import java.util.List;

import project.stations.ParkingSpace;
import project.stations.RentalStation;
import project.vehicles.State;
import project.vehicles.Vehicle;
import project.vehicles.factories.VehicleFactory;

public abstract class StationFactory {
    private VehicleFactory factory;

    public StationFactory(VehicleFactory factory) {
        this.factory = factory;
    }

    public RentalStation createStation(int id, int capacity) {
        List<ParkingSpace> spaces = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Vehicle newVehicle = this.factory.createVehicle(i + (id * RentalStation.MAX_CAPACITY));
            newVehicle.setState(State.STORED);
            ParkingSpace space = new ParkingSpace();
            space.store(newVehicle);
            spaces.add(space);
        }
        return new RentalStation(id, spaces);
    }
        
}
