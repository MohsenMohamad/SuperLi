package unitTests;

import BL.*;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HistoryWorkersTest {
    History history;
    Workers workers;
    Worker worker1;
    Shift TestShift;
    Worker worker2;

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        TestShift = new Shift(date_format.parse("13/04/2020"), Shift.ShiftTime.Morning , worker1 , new HashMap<>());
        history = History.getInstance();
        workers = Workers.getInstance();
        List<WorkPolicy.WorkingType> jobs = new LinkedList<>();
        jobs.add(WorkPolicy.WorkingType.Cashier);
        jobs.add(WorkPolicy.WorkingType.Cleaning);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        WorkerDeal worker_contract=null;
        try {
            worker_contract = new WorkerDeal(208432474, dateFormat.parse("30/03/2017"), 28, "123" , new LinkedList<>());
        }
        catch(ParseException pe)
        {
            pe.printStackTrace();
        }

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
            if(pair.getValue() == Shift.ShiftTime.Morning)
                schedule.put(pair, true);
            else
                schedule.put(pair, false);
        }

        worker1 = new Worker(208432474,"worker1",jobs,schedule,worker_contract);
        WorkerDeal worker2_contract = new WorkerDeal(111111111, dateFormat.parse("30/03/2017"), 28, "a", new LinkedList<>());
        worker2 = new Worker(111111111,"John", jobs, schedule, worker2_contract);

    }


    @Test
    public void testAddWorker1() throws ParseException {
        assertTrue(workers.addWorker(worker1));//test1
        assertFalse(workers.addWorker(worker1));//test2
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        Shift shift1 = new Shift(date_format.parse("13/04/2020"), Shift.ShiftTime.Morning , worker1 , new HashMap<>());
        List<Worker> tempList = workers.getAvailableWorkers(shift1.getShiftDate(),shift1.getShiftTime());
        assertTrue(tempList.contains(worker1));//test3
        worker1.work(shift1);
        tempList = workers.getAvailableWorkers(shift1.getShiftDate(),shift1.getShiftTime());
        assertFalse(tempList.contains(worker1));//test4
    }

    @Test
    public void testAddWorker2() {
        assertTrue(workers.addWorker(worker2));
        assertFalse(workers.addWorker(worker1));//test5
    }

    @Test
    public void testAddWorker3() throws ParseException{
        workers.addWorker(worker1);
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        Shift shift1 = new Shift(date_format.parse("13/04/2020"), Shift.ShiftTime.Morning , worker1 , new HashMap<>());
        worker1.work(shift1);
        List<Worker> tempList = workers.getAvailableWorkers(shift1.getShiftDate(),shift1.getShiftTime());
        assertFalse(tempList.contains(worker1));//test6
    }

    @Test
    public void testAddShift1(){
        List<Shift> tempList = history.getShifts();
        assertFalse(tempList.contains(TestShift));//test7
    }

    @Test
    public void testAddShift2(){
        assertTrue(history.addShift(TestShift));//test8
    }


    @Test
    public void testAddShift3(){
        history.addShift(TestShift);
        assertFalse(history.addShift(TestShift));//test9

    }
}