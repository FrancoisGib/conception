package app;

import app.vehicles.Vehicle;
import app.vehicles.bikes.ClassicBike;
import app.vehicles.bikes.utilities.Backpack;

public class Main {
    public static void main(String [] args) {
        //new Simulation(4).start();
        Vehicle bike = new Backpack(new ClassicBike(0));
        System.out.println(bike.getDescription());
    }
}
