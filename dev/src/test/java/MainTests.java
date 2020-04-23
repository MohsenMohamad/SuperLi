import org.junit.runner.*;
import org.junit.runner.notification.Failure;

public class MainTests {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ControllerTest.class);
        System.out.println("test run : "+result.getRunCount());
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        result = JUnitCore.runClasses(CategoryDiscountTest.class);
        System.out.println("test run : "+result.getRunCount());
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        result = JUnitCore.runClasses(ItemDiscountTest.class);
        System.out.println("test run : "+result.getRunCount());
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        result = JUnitCore.runClasses(ItemRecordTest.class);
        System.out.println("test run : "+result.getRunCount());
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}
