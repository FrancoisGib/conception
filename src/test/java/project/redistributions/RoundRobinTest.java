package project.redistributions;

public class RoundRobinTest extends RedistributionTest {
    protected Redistribution createRedistribution() {
        return new RoundRobin();
    }
}
