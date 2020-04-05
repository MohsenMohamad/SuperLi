package BL;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Worker {

    private String name;
    private int id;

    public List<WorkingType> getType() {
        return type;
    }

    public Map<Pair<Day, ShiftTime>, Boolean> getSchedule() {
        return schedule;
    }

    private List<WorkingType> type;   // may become a list
    private Map<Pair<Day , ShiftTime>, Boolean> schedule;
    private WorkerDeal contract;
    private List<Shift> worker_shifts;

    public Worker(String name, int id, List<WorkingType> type, Map<Pair<Day, ShiftTime>, Boolean> schedule, WorkerDeal contract) {
        this.name = name;
        this.id = id;
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

    public boolean isAvailable(Pair<Day,ShiftTime> shift)
    {
        return schedule.get(shift);
    }

    public void work(Shift shift)
    {
        schedule.replace(shift.getShift_time(),false);
        worker_shifts.add(shift);
    }

    // add a method to free a shift


    public List<Shift> getWorker_shifts() {
        return worker_shifts;
    }

    public List<Pair<Day , ShiftTime>> availableHours()
    {
        List<Pair<Day,ShiftTime>> available_hours = new LinkedList<>();
        for(Pair<Day , ShiftTime> p : schedule.keySet())
        {
            if(schedule.get(p))
                available_hours.add(p);
        }
        return available_hours;
    }


    public WorkerDeal getContract() {
        return contract;
    }
}
