package project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import project.stations.RentalStation;
import project.stations.spaces.ParkingSpace;
import project.vehicles.State;
import project.vehicles.Vehicle;

public class Displayer {

    /**
     * Displays a message indicating that a vehicle has been rented from a rental
     * station.
     *
     * @param vehicle The rented vehicle.
     * @param station The rental station from which the vehicle has been rented.
     */

    public void vehicleRented(Vehicle vehicle, RentalStation station) {
        System.out.println("The vehicle " + vehicle.getId() + " has been rented from the station " + station.getId());
    }

    /**
     * Displays a message indicating that a vehicle has been stored in a rental
     * station.
     * 
     * @param vehicle The vehicle that has been stored.
     * @param station The rental station where the vehicle has been stored.
     */
    public void vehicleStored(Vehicle vehicle, RentalStation station) {
        System.out.println("The vehicle " + vehicle.getId() + " has been stored in the station " + station.getId());
    }

    /**
     * Displays a message indicating that a vehicle has been stolen.
     *
     * @param vehicle the stolen vehicle
     */
    public void vehicleStolen(Vehicle vehicle) {
        System.out.println("The vehicle " + vehicle.getId() + " has been stolen");
    }

    /**
     * Notifies that a vehicle is out of service and a repairer has been called.
     * 
     * @param vehicle the vehicle that is out of service
     */
    public void vehicleOutOfService(Vehicle vehicle) {
        System.out.println("The vehicle " + vehicle.getId() + " is out of service, a repairer has been called");
    }

    /**
     * Notifies when a vehicle is returned after being considered stolen.
     *
     * @param vehicle The vehicle that has been returned.
     */
    public void vehicleBackFromStolen(Vehicle vehicle) {
        System.out.println("The vehicle " + vehicle.getId() + " who was considered stolen, has been returned");
    }

    /**
     * Notifies that a vehicle has been repaired and is now available.
     *
     * @param vehicle The vehicle that has been repaired.
     */
    public void vehicleRepaired(Vehicle vehicle) {
        System.out.println("The vehicle " + vehicle.getId() + " has been repaired, it is now available");
    }

    /**
     * Displays a message indicating that there is no repairer available.
     */
    public void noRepairerAvailable() {
        System.out.println("There's no repairer available, wait !");
    }

    /**
     * Displays a message indicating that a rental station is full.
     * 
     * @param station the rental station that is full
     */
    public void stationFull(RentalStation station) {
        System.out.println("The station " + station.getId() + " is full, come back later !");
    }

    /**
     * Displays a message indicating that a rental station is empty.
     * 
     * @param station the rental station that is empty
     */
    public void stationEmpty(RentalStation station) {
        System.out.println("The station " + station.getId() + " is empty, come back later !");
    }

    // The colors for printing
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private static final List<String> COLORS = Arrays.asList(ANSI_RED_BACKGROUND, ANSI_GREEN_BACKGROUND,
            ANSI_YELLOW_BACKGROUND, ANSI_BLUE_BACKGROUND, ANSI_PURPLE_BACKGROUND, ANSI_CYAN_BACKGROUND,
            ANSI_WHITE_BACKGROUND);

    
    /**
     * A HashMap that maps Vehicle objects to their corresponding color.
     */
    private HashMap<Vehicle, String> vehiclesColor = new HashMap<Vehicle, String>();

    /**
     * Initializes the colors for the vehicles in the rental stations.
     * Each vehicle is assigned a color from a predefined list of colors.
     *
     * @param stations the list of rental stations
     */
    public void initColors(List<RentalStation> stations) {
        int i = 0;
        for (RentalStation rentalStation : stations) {
            for (ParkingSpace space : rentalStation.getSpaces()) {
                if (!space.isEmpty()) {
                    Vehicle vehicle = space.getVehicle();
                    this.vehiclesColor.put(vehicle, COLORS.get(i % COLORS.size()));
                }
            }
            i++;
        }
    }

    /**
     * Displays the redistribution of vehicles in rental stations.
     * 
     * @param stations the list of rental stations
     */
    public void displayRedistribution(List<RentalStation> stations) {
        String res = "";
        for (RentalStation rentalStation : stations) {
            for (ParkingSpace space : rentalStation.getSpaces()) {
                if (!space.isEmpty()) {
                    Vehicle vehicle = space.getVehicle();
                    res += this.vehiclesColor.get(vehicle) + "  " + ANSI_RESET + " ";
                } else
                    res += "✅ ";
            }
            res += "\n\n";
        }
        System.out.println("\nRedistribution started !\n\n" + res);
    }

    /**
     * Displays the status of rental stations.
     * 
     * @param stations the list of rental stations to display
     */
    public void displayStations(List<RentalStation> stations) {
        for (RentalStation station : stations) {
            String res = "";
            for (ParkingSpace space : station.getSpaces()) {
                if (!space.isEmpty()) {
                    State vehicleState = space.getVehicle().getState();
                    if (vehicleState == State.REPARATION)
                        res += "👨‍🔧";
                    else if (vehicleState == State.OUT_OF_SERVICE)
                        res += " ❌ ";
                    else
                        res += " 🛑 ";
                } else
                    res += " ✅ ";

            }
            System.out.println(res + "\n");
        }
    }
}
