package project.persons;

/**
 * The Thief class represents a thief in the project.
 * It extends the Client class and inherits its properties and methods.
 */
public class Thief extends Client {
    /**
     * Constructs a Thief object with the specified ID.
     *
     * @param id the ID of the thief
     */
    public Thief(int id) {
        super(id, 0);
    }

    /**
     * This method is called on each tick of the simulation.
     * It is empty in the Thief class, as the thief does not have any specific behavior.
     */
    @Override
    public void tick() {
    }
}
