package BL;

import java.util.LinkedList;
import java.util.List;

public class History {

    private static History history_instance = null;

    private List<Shift> shifts;


    public static History getInstance(){
        if(history_instance == null){
            history_instance = new History();
        }
        return history_instance;
    }

    private History()
    {
        shifts = new LinkedList<>();
    }

    public boolean addShift(Shift shift)
    {
        for(Shift s: shifts)
        {
            if(s.getShiftDate().equals(shift.getShiftDate()) &&  s.getShiftTime() == shift.getShiftTime())
            {
                return false;
            }
        }

        shifts.add(shift);
        return true;
    }

    public List<Shift> getShifts() {
        return shifts;
    }
}
