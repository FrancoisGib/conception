package project;

/**
 * The Timer interface represents a timer that can be used to measure time intervals.
 * Implementing classes should provide the functionality to tick the timer.
 */
public interface Timer {
    /**
     * Performs a tick operation on the timer.
     * This method is used to update the timer's state or perform any necessary actions.
     */
    public void tick();
}
