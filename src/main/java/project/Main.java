package project;

import project.vehicles.Bike;
import project.vehicles.Vehicle;
import project.vehicles.utilities.Backpack;

public class Main {
    public static void main(String[] args) {

        /*if (args[0].equals("1"))
            new Simulation().startWithoutTimer();
        else
            new Simulation().start();
        */
        Vehicle vehicle = new Backpack(new Bike(0));
        System.out.println(vehicle.getDescription());
    }

}
