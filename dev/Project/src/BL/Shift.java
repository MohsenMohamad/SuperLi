package BL;

import javafx.util.Pair;

import java.util.List;



public class Shift
{
    public enum Day {
        Sunday,
        Monday , Tuesday , Wednesday , Thursday , Friday , Saturday
    }

    public enum ShiftTime {
        Morning,
        Evening
    }

    private Worker boss;
    private Pair<Day,ShiftTime> shift_time;
    private List<Worker> work_team;


    public Pair<Day,ShiftTime> getShift_time() {
        return shift_time;
    }

    public List<Worker> getWork_team() {
        return work_team;
    }

    public Shift(Worker boss, Pair<Day,ShiftTime> shift_time, List<Worker> work_team) {
        this.boss = boss;
        this.shift_time = shift_time;
        this.work_team = work_team;
    }

    public Worker getBoss()
    {
        return boss;
    }

    // in case if the boss can be changed
    public void setBoss(Worker boss) {
        this.boss = boss;
    }
}
