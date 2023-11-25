package app.visitors;

import org.junit.jupiter.api.BeforeEach;

import app.mocks.MockRepairer;

public class RepairerTest extends VisitorTest {
    MockRepairer visitor;

    @BeforeEach
    public void init() {
        super.init();
        this.visitor = new MockRepairer();
    }
}