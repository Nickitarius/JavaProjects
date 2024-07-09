import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MainTest {

    public static void main(String[] args) {
        Result results = JUnitCore.runClasses(SequenceTest.class);
        for (Failure failure : results.getFailures()) {
            System.out.println("Test failed successfully: " + failure);
        }

    }
}
