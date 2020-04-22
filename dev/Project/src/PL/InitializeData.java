package PL;

import BL.*;
import javafx.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;

public class InitializeData {


    public InitializeData()
    {

    }

    public void createWorkers() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            WorkerDeal john_contract = new WorkerDeal(Worker.count, dateFormat.parse("30/03/2017"), 28, "a", new LinkedList<>());
            Worker john = new Worker(111111111,"John", createJob(), createSchedule(), john_contract);

            WorkerDeal steve_contract = new WorkerDeal(Worker.count, dateFormat.parse("05/11/2016"), 30, "b", new LinkedList<>());
            Worker steve = new Worker(222222222,"Steve", createJob(), createSchedule(), steve_contract);


            WorkerDeal james_contract = new WorkerDeal(Worker.count, dateFormat.parse("12/06/2018"), 1000, "c", new LinkedList<>());
            Worker james = new Worker(333333333,"James", createJob(), createSchedule(), james_contract);

            WorkerDeal moshe_contract = new WorkerDeal(Worker.count, dateFormat.parse("22/11/2015"), 1000, "c", new LinkedList<>());
            Worker moshe = new Worker(444444444,"Moshe", createJob(), createSchedule(), moshe_contract);


            WorkerDeal asd_contract = new WorkerDeal(Worker.count, dateFormat.parse("01/01/2012"), 1000, "c", new LinkedList<>());
            Worker asd = new Worker(555555555,"Asd", createJob(), createSchedule(), asd_contract);

            WorkerDeal iris_contract = new WorkerDeal(Worker.count, dateFormat.parse("17/02/2010"), 1000, "c", new LinkedList<>());
            Worker iris = new Worker(666666666,"Iris", createJob(), createSchedule(), iris_contract);


            Workers workers = Workers.getInstance();
            workers.addWorker(john);
            workers.addWorker(steve);
            workers.addWorker(james);
            workers.addWorker(moshe);
            workers.addWorker(asd);
            workers.addWorker(iris);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

    }

    public void createShifts() {
        History history = History.getInstance();
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Shift sundayMorning_shift = new Shift(date_format.parse("13/04/2020"), Shift.ShiftTime.Morning, Workers.getInstance().getWorker(1), new HashMap<>());
            Shift mondayEvening_shift = new Shift(date_format.parse("14/04/2020"), Shift.ShiftTime.Evening, Workers.getInstance().getWorker(2), new HashMap<>());
            history.addShift(sundayMorning_shift);
            history.addShift(mondayEvening_shift);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

    }

    public Map<Pair<DayOfWeek, Shift.ShiftTime>, Boolean> createSchedule() {

        Map<Pair<DayOfWeek, Shift.ShiftTime>, Boolean> schedule = new HashMap<>();


        List<Pair<DayOfWeek, Shift.ShiftTime>> shifts = new LinkedList<>();
        shifts.add(new Pair<>(DayOfWeek.SUNDAY, Shift.ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.SUNDAY, Shift.ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.MONDAY, Shift.ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.MONDAY, Shift.ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.TUESDAY, Shift.ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.TUESDAY, Shift.ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.WEDNESDAY, Shift.ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.WEDNESDAY, Shift.ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.THURSDAY, Shift.ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.THURSDAY, Shift.ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.FRIDAY, Shift.ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.FRIDAY, Shift.ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.SATURDAY, Shift.ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.SATURDAY, Shift.ShiftTime.Evening));

        for (Pair<DayOfWeek, Shift.ShiftTime> pair : shifts) {
            schedule.put(pair, true);
        }
        return schedule;
    }

    public List<WorkPolicy.WorkingType> createJob() {
        List<WorkPolicy.WorkingType> jobs = new LinkedList<>();
        jobs.add(WorkPolicy.WorkingType.Cashier);
        jobs.add(WorkPolicy.WorkingType.Cleaning);
        jobs.add(WorkPolicy.WorkingType.Delivery);

        return jobs;
    }
}
