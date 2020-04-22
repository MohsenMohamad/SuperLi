package unitTests;

import BL.Shift;
import BL.WorkPolicy;
import BL.Worker;
import BL.WorkerDeal;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShiftWorkerTest {

    Worker worker1;
    Shift TestShift;

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        TestShift = new Shift(date_format.parse("13/04/2020"), Shift.ShiftTime.Morning , worker1 , new HashMap<>());
        List<WorkPolicy.WorkingType> jobs = new LinkedList<>();
        jobs.add(WorkPolicy.WorkingType.Cashier);
        jobs.add(WorkPolicy.WorkingType.Cleaning);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        WorkerDeal worker_contract=null;
        try {

            worker_contract = new WorkerDeal(315375498, dateFormat.parse("30/03/2017"), 28, "123" , new LinkedList<>());
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

        worker1 = new Worker(315375498,"worker1",jobs,schedule,worker_contract);
    }

    @Test
    public void testIsAvailable1() throws ParseException {
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        Shift shift = new Shift(date_format.parse("13/04/2020"), Shift.ShiftTime.Morning , worker1 , new HashMap<>());
        assertTrue(worker1.isAvailable(shift.getShiftDate(),shift.getShiftTime()));//test1
    }

    @Test
    public void testIsAvailable2() throws ParseException{
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        Shift shift = new Shift(date_format.parse("13/04/2020"), Shift.ShiftTime.Morning , worker1 , new HashMap<>());
        worker1.work(shift);
        assertFalse(worker1.isAvailable(shift.getShiftDate(),shift.getShiftTime()));//test2
    }

    @Test
    public void testIsAvailable3() throws ParseException{
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        assertFalse(worker1.isAvailable(date_format.parse("13/04/2020"), Shift.ShiftTime.Evening));//test3
    }

    @Test
    public void testWork1() throws Exception{
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        Shift shift2 = new Shift(date_format.parse("13/04/2020"), Shift.ShiftTime.Evening , worker1 , new HashMap<>());
        assertFalse(worker1.work(shift2));//test4
    }

    @Test
    public void testWork2() throws Exception{
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        Shift shift1 = new Shift(date_format.parse("13/04/2020"), Shift.ShiftTime.Morning , worker1 , new HashMap<>());
        assertTrue(worker1.work(shift1));//test5
    }

    @Test
    public void testWork3() throws Exception {
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        Shift shift1 = new Shift(date_format.parse("13/04/2020"), Shift.ShiftTime.Morning , worker1 , new HashMap<>());
        worker1.work(shift1);
        assertFalse(worker1.work(shift1));//test6
    }

    @Test
    public void testAddToWorkingTeam1(){
        assertFalse(TestShift.addToWorkingTeam(worker1, WorkPolicy.WorkingType.Delivery));//test7
    }

    @Test
    public void testAddToWorkingTeam2() {
        assertTrue(TestShift.addToWorkingTeam(worker1, WorkPolicy.WorkingType.Cashier));//test8
    }

    @Test
    public void testAddToWorkingTeam3(){
        TestShift.addToWorkingTeam(worker1, WorkPolicy.WorkingType.Cashier);
        assertFalse(TestShift.addToWorkingTeam(worker1, WorkPolicy.WorkingType.Cashier));//test9
    }

    @Test
    public void testAddToTeamIsAvailable(){
        TestShift.addToWorkingTeam(worker1, WorkPolicy.WorkingType.Cashier);
        assertFalse(worker1.isAvailable(TestShift.getShiftDate(),TestShift.getShiftTime()));//test10
    }
}