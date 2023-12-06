package project;

public class Main {
    /**
     * The entry point of the program.
     * 
     * @param args The command line arguments passed to the program.
     */
    public static void main(String[] args) {

        if (args[0].equals("1"))
            new Simulation().startWithoutTimer();
        else
            new Simulation().start();
    }

}
