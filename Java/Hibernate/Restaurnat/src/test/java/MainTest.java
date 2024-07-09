import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MainTest {

    public static void main(String[] args) {
        Result results1 = JUnitCore.runClasses(menuTest.class);
        for (Failure failure : results1.getFailures()) {
            System.out.println("\n---!!!Test failed successfully!!!: ---" + failure + "\n");
        }
        System.out.println("\n---Testing is over---\n");
        System.exit(0);
    }
}
