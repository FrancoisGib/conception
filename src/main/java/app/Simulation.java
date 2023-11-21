package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import app.controlcenter.ControlCenter;
import app.stations.RentalStation;
import app.vehicles.Vehicle;

public abstract class Simulation<V extends Vehicle> {

    public static final int CLIENT_MAX_RENT_LOOP = 60;

    public static final int LOOP_RENT = 1;

    protected static final int LOOP_TIME = 1;

    protected int loop = 0;

    protected ControlCenter<V> controlCenter;

    protected List<RentalStation<V>> stations;

    protected ArrayList<Client<V>> clients = new ArrayList<>();

    public void start() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (loop % LOOP_RENT == 0) {
                    int i = 0;
                    while (i < clients.size()) {
                        Client<V> client = clients.get(i);
                        if (!client.hasVehicle()) {
                            client.rentVehicle(stations.get((int) (Math.random() * stations.size())));
                            i = clients.size(); // end loop
                        }
                        i++;
                    }
                }
                loop++;
                loop();
            }
        }, 1000 * LOOP_TIME, 1000 * LOOP_TIME);
    }

    public void loop() {
        for (Client<V> client : clients) {
            client.tick();
        }
        controlCenter.tick();
    }
}