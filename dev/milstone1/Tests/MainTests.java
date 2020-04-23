import BusinessLogic.StoreTest;
import BusinessLogic.WrotequantitiesTest;
import BusinessLogic.systemTest;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MainTests {

    public static void main(String[] args){
        Result result = JUnitCore.runClasses(StoreTest.class);
        System.out.println("Test run: " + result.getRunCount());
        for (Failure failure : result.getFailures()){
            System.out.println((failure.toString()));
        }

        result = JUnitCore.runClasses(systemTest.class);
        System.out.println("Test run: " + result.getRunCount());
        for (Failure failure : result.getFailures()){
            System.out.println((failure.toString()));
        }
        result = JUnitCore.runClasses(WrotequantitiesTest.class);
        System.out.println("Test run: " + result.getRunCount());
        for (Failure failure : result.getFailures()){
            System.out.println((failure.toString()));
        }

    }
}
