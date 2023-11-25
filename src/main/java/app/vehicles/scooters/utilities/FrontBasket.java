package app.vehicles.scooters.utilities;

import app.vehicles.scooters.Scooter;

public class FrontBasket extends ScooterDecorator {
    public static final String DESCRIPTION = "Front basket";
    
    public FrontBasket(Scooter scooter) {
        super(scooter);
        this.description = DESCRIPTION;
    }
}
