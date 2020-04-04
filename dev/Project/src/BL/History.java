package BL;

import java.util.LinkedList;
import java.util.List;

public class History {

    private List<Shift> shifts;


    public History()
    {
        shifts = new LinkedList<>();
    }

    public boolean addShift(Shift shift)
    {
        for(Shift s: shifts)
        {
            if(shift.getDate() == s.getDate() && shift.getShift_time() == s.getShift_time())
            {
                return false;
            }
        }

        shifts.add(shift);
        return true;
    }

}
