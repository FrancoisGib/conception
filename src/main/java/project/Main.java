package project;

public class Main {
    public static void main(String[] args) {

        if (args[0].equals("1"))
            new Simulation().startWithoutTimer();
        else
            new Simulation().start();
    }

}
