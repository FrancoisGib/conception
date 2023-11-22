package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import app.stations.RentalStation;
import app.stations.factories.BikeStationFactory;
import app.stations.factories.StationFactory;

public class Simulation {

    public static final int CLIENT_MAX_RENT_LOOP = 60;

    public static final int LOOP_RENT = 1;

    protected static final int LOOP_TIME = 1;

    protected ControlCenter controlCenter;

    protected List<RentalStation> stations;

    protected ArrayList<Client> clients = new ArrayList<>();

    public Simulation(int nbStations) {
        for (int i = 0; i < 50; i++)
            this.clients.add(new Client(i));
        List<RentalStation> stations = new ArrayList<>();
        StationFactory stationFactory = new BikeStationFactory();
        for (int i = 0; i < nbStations; i++)
            stations.add(stationFactory.createStation(i));
        this.controlCenter = new ControlCenter(stations);
    }

    public void start() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                int loop = 0;
                if (loop % LOOP_RENT == 0) {
                    int i = 0;
                    while (i < clients.size()) {
                        Client client = clients.get(i);
                        if (!client.hasVehicle()) {
                            client.rentVehicle(stations.get((int) (Math.random() * stations.size())));
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
        for (Client client : clients) {
            client.tick();
        }
        controlCenter.tick();
    }
}