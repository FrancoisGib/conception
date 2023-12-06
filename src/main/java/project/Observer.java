package project;

import project.stations.RentalStation;
import project.vehicles.Vehicle;

/**
 * The Observer interface represents an observer in the Observer pattern.
 * It provides methods to handle events related to vehicle rental, storage, service, theft, and repair.
 */
public interface Observer {
    /**
     * This method is called when a vehicle is rented from a rental station.
     * 
     * @param vehicle The rented vehicle.
     * @param station The rental station from which the vehicle is rented.
     */
    public void vehicleRented(Vehicle vehicle, RentalStation station);

    /**
     * This method is called when a vehicle is stored in a rental station.
     * 
     * @param vehicle The stored vehicle.
     * @param station The rental station in which the vehicle is stored.
     */
    public void vehicleStored(Vehicle vehicle, RentalStation station);

    /**
     * This method is called when a vehicle goes out of service.
     * 
     * @param vehicle The vehicle that goes out of service.
     */
    public void vehicleOutOfService(Vehicle vehicle);

    /**
     * This method is called when a vehicle is stolen.
     * 
     * @param vehicle The stolen vehicle.
     */
    public void vehicleStolen(Vehicle vehicle);

    /**
     * This method is called when a stolen vehicle is recovered.
     * 
     * @param vehicle The recovered vehicle.
     */
    public void vehicleBackFromStolen(Vehicle vehicle);

    /**
     * This method is called when a vehicle is repaired.
     * 
     * @param vehicle The repaired vehicle.
     */
    public void vehicleRepaired(Vehicle vehicle);
}
