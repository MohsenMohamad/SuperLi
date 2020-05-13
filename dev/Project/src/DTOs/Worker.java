package DTOs;

import javafx.util.Pair;
import DTOs.WorkPolicy.WorkingType;
import java.time.DayOfWeek;
import java.util.*;

import DTOs.Shift.ShiftTime;

public abstract class Worker {

    private int id;
    private String name;
    private WorkingType working_type;
    private Map<Pair<DayOfWeek, ShiftTime>, Boolean> schedule;
    private WorkerDeal contract;
    private List<Shift> worker_shifts;

    public Worker(int id, String name, WorkingType working_type, Map<Pair<DayOfWeek, ShiftTime>, Boolean> schedule, WorkerDeal contract)
    {
        this.name = name;
        this.id =id;
        this.working_type = working_type;
        this.schedule = schedule;
        this.contract = contract;
        this.worker_shifts = new LinkedList<>();
    }

    public boolean isAvailable(Date date, ShiftTime shiftTime) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if(dayOfWeek == 0){
            dayOfWeek = 7;
        }//to make sure that the day index is correct

        Pair<DayOfWeek,ShiftTime> pair = new Pair<>(DayOfWeek.of(dayOfWeek),shiftTime);
        if(schedule.get(pair))
        {
            for(Shift shift : worker_shifts)
            {
                if(shift.getShiftDate() == date && shift.getShiftTime() == shiftTime)
                {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public boolean work(Shift shift) {
        if (isAvailable(shift.getShiftDate(), shift.getShiftTime())) {
            worker_shifts.add(shift);
            return true;
        }

        return false;
    }

    // add a method to free a shift

    public List<Shift> getWorker_shifts() {
        return worker_shifts;
    }

    @Override
    public String toString() {
        return new String(id + " , " + name + " : " + working_type.toString());
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public WorkingType getType() {
        return working_type;
    }

    public Map<Pair<DayOfWeek, ShiftTime>, Boolean> getSchedule() {
        return schedule;
    }

    public WorkerDeal getContract() {
        return contract;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setID(int id)
    {
        this.id = id;
    }
}
