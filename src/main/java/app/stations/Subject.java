package app.stations;

import app.Observer;

public interface Subject {
    public void attach(Observer o);
}
