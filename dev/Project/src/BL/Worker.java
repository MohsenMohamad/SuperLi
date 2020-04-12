package BL;

import javafx.util.Pair;
import java.util.*;
import BL.Shift.ShiftTime;
import BL.WorkPolicy.WorkingType;

public class Worker {

    private static int count = 0;
    private int id=0;
    private String name;
    public List<WorkingType> getType() {
        return type;
    }
    public Map<Pair<Date, ShiftTime>, Boolean> getSchedule() {
        return schedule;
    }
    private List<WorkingType> type;   // may become a list
    private Map<Pair<Date , ShiftTime>, Boolean> schedule;
    private WorkerDeal contract;
    private List<Shift> worker_shifts;

    public Worker(String name,List<WorkingType> type, Map<Pair<Date, ShiftTime>, Boolean> schedule, WorkerDeal contract) {
        this.name = name;
        this.id = count++;
        this.type = type;
        this.schedule = schedule;
        this.contract = contract;
        this.worker_shifts = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable(Date date ,ShiftTime shiftTime)
    {
        return schedule.get(new Pair<>(date , shiftTime));
    }

    public void work(Shift shift)
    {
        schedule.replace(new Pair<>(shift.getShiftDate(),shift.getShiftTime()),false);
        worker_shifts.add(shift);
    }

    // add a method to free a shift


    public List<Shift> getWorker_shifts() {
        return worker_shifts;
    }

/*    public List<Pair<Day , ShiftTime>> availableHours()
    {
        List<Pair<Day,ShiftTime>> available_hours = new LinkedList<>();
        for(Pair<Day , ShiftTime> p : schedule.keySet())
        {
            if(schedule.get(p))
                available_hours.add(p);
        }
        return available_hours;
    }

 */

    @Override
    public String toString()
    {
        return new String(id+" , "+name + " : " + type.toString());
    }

    public WorkerDeal getContract() {
        return contract;
    }
}
