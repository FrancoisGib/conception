package project.visitors;

import org.junit.jupiter.api.BeforeEach;

import project.persons.workers.Repairer;

public class RepairerTest extends VisitorTest {
    MockRepairer visitor;

    @BeforeEach
    public void init() {
        super.init();
        this.visitor = new MockRepairer();
    }

    protected class MockRepairer extends Repairer {
    }
}