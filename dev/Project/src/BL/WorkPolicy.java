package BL;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

// Worker type is enum


    enum WorkingType {
        Cashier,
        Delivery,
        Cleaning,
    }


public class WorkPolicy {

    private List<Pair<WorkingType,WorkingType>> policy; // if the map contains these two then the worker can get this job

    public boolean canWork(WorkingType w1 , WorkingType w2)
    {
        return false;
    }

}
