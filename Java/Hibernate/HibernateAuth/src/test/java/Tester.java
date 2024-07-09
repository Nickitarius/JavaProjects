import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Tester {

    public static void main(String[] args) {
        Result results1 = JUnitCore.runClasses(TestsClass.class);
        for (Failure failure : results1.getFailures()) {
            System.out.println("\n---!!!Test failed successfully!!!: " + failure + " ---\n");
        }
        System.out.println("\n---Testing is over---\n");
        System.exit(0);
    }
}
