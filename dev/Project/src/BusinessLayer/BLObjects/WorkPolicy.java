package BusinessLayer.BLObjects;

import javafx.util.Pair;

import java.util.List;

// Worker type is enum
// also add work conditions and work constraints as enums ?



public class WorkPolicy {

    public enum WorkingType {
        Cashier,
        Delivery,
        Cleaning,
    }
    private List<Pair<WorkingType,WorkingType>> policy; // if the map contains these two then the worker can get this job

    public boolean canWork(WorkingType w1 , WorkingType w2)
    {
        return false;
    }

}
