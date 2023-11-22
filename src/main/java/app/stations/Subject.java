package app.stations;

import app.controlcenter.Observer;

public interface Subject {
    public void attach(Observer o);
}
