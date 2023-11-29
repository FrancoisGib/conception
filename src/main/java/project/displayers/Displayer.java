package project.displayers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import project.stations.RentalStation;
import project.stations.spaces.ParkingSpace;
import project.vehicles.State;
import project.vehicles.Vehicle;

public class Displayer {
    public void vehicleRented(Vehicle vehicle, RentalStation station) {
        System.out.println("The vehicle " + vehicle.getId() + " has been rented from the station " + station.getId());
    }

    public void vehicleStored(Vehicle vehicle, RentalStation station) {
        System.out.println("The vehicle " + vehicle.getId() + " has been stored in the station " + station.getId());
    }

    public void vehicleStollen(Vehicle vehicle) {
        System.out.println("The vehicle " + vehicle.getId() + " has been stolen");
    }

    public void vehicleOutOfService(Vehicle vehicle) {
        System.out.println("The vehicle " + vehicle.getId() + " is out of service, a repairer has been called");
    }

    public void vehicleBackFromStolen(Vehicle vehicle) {
        System.out.println("The vehicle " + vehicle.getId() + " who was considered stolen, has been returned");
    }

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

    private HashMap<Vehicle, String> vehiclesColor = new HashMap<Vehicle, String>();

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
                }
                    
                else
                    res += " ✅ ";
            }
            System.out.println(res + "\n");
        }
    }
}
