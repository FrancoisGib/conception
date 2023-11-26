package project.vehicles.scooters.utilities;

import project.vehicles.scooters.Scooter;

public class FrontBasket extends ScooterDecorator {
    public static final String DESCRIPTION = "Front basket";
    
    public FrontBasket(Scooter scooter) {
        super(scooter);
        this.description = DESCRIPTION;
    }
}
