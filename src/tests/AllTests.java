import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        // package1
        TIVisitorTest.class,
        TIVisitedTest.class,
        TIVisitorManagerTest.class

        })
public final class AllTests {
} // AllTests