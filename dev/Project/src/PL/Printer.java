package PL;

import BL.WorkPolicy;

public class Printer {

    public static void printAllWorkingTypes()
    {
        int number =1;
        for(WorkPolicy.WorkingType workingType : WorkPolicy.WorkingType.values())
        {
            System.out.println(number+") "+workingType.toString());
            number++;
        }
    }
}
