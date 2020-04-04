package BL;

import java.util.List;

public class Shift
{

    private Worker boss;
    private String date;
    private String shift_time;
    private List<Worker> work_team;

    public String getDate() {
        return date;
    }

    public String getShift_time() {
        return shift_time;
    }

    public List<Worker> getWork_team() {
        return work_team;
    }

    public Shift(Worker boss, String date, String shift_time, List<Worker> work_team) {
        this.boss = boss;
        this.date = date;
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
