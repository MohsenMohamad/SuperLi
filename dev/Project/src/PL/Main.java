package PL;


import BL.*;
import BL.Shift.Day;
import BL.Shift.ShiftTime;
import BL.WorkPolicy.WorkingType;
import javafx.util.Pair;

import java.util.*;

public class Main {

    static Scanner keyboard = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] argv) {
        boolean terminate = false;
        createWorkers();
        createShifts();

        while (!terminate) {
            printMenu();
            int choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    workersView();
                    break;
                case 2:
                    shiftsView();
                    break;
                case 3:
                    terminate = true;
                    break;
            }

            System.out.println("--------------------------------");
        }

    }

    private static void createWorkers() {
        WorkerDeal shadi_contract = new WorkerDeal("today", 28, new LinkedList<>());
        Worker shadi = new Worker("Shadi", 1, createJob(), createSchedule(), shadi_contract);

        WorkerDeal eran_contract = new WorkerDeal("Tomorrow", 30, new LinkedList<>());
        Worker eran = new Worker("Eran", 2, createJob(), createSchedule(), eran_contract);


        WorkerDeal mohamad_contract = new WorkerDeal("Yesterday", 1000, new LinkedList<>());
        Worker mohamad = new Worker("Mohamad", 3, createJob(), createSchedule(), mohamad_contract);

        Workers workers = Workers.getInstance();
        workers.addWorker(shadi);
        workers.addWorker(eran);
        workers.addWorker(mohamad);
    }

    private static void createShifts() {
        History history = History.getInstance();

        Shift sundayMorning_shift = new Shift(Workers.getInstance().getWorker(1), new Pair<>(Day.Sunday, ShiftTime.Morning), new LinkedList<>());
        Shift mondayEvening_shift = new Shift(Workers.getInstance().getWorker(2), new Pair<>(Day.Monday, ShiftTime.Evening), new LinkedList<>());

        history.addShift(sundayMorning_shift);
        history.addShift(mondayEvening_shift);

    }

    private static Map<Pair<Day, ShiftTime>, Boolean> createSchedule() {

        Map<Pair<Day, ShiftTime>, Boolean> schedule = new HashMap<>();
        List<Pair<Day, ShiftTime>> shifts = new LinkedList<>();
        shifts.add(new Pair<>(Day.Sunday, ShiftTime.Morning));
        shifts.add(new Pair<>(Day.Sunday, ShiftTime.Evening));
        shifts.add(new Pair<>(Day.Monday, ShiftTime.Morning));
        shifts.add(new Pair<>(Day.Monday, ShiftTime.Evening));
        shifts.add(new Pair<>(Day.Tuesday, ShiftTime.Morning));
        shifts.add(new Pair<>(Day.Tuesday, ShiftTime.Evening));
        shifts.add(new Pair<>(Day.Wednesday, ShiftTime.Morning));
        shifts.add(new Pair<>(Day.Wednesday, ShiftTime.Evening));
        shifts.add(new Pair<>(Day.Thursday, ShiftTime.Morning));
        shifts.add(new Pair<>(Day.Thursday, ShiftTime.Evening));
        shifts.add(new Pair<>(Day.Friday, ShiftTime.Morning));
        shifts.add(new Pair<>(Day.Friday, ShiftTime.Evening));
        shifts.add(new Pair<>(Day.Saturday, ShiftTime.Morning));
        shifts.add(new Pair<>(Day.Saturday, ShiftTime.Evening));
        for (Pair<Day, ShiftTime> pair : shifts) {
            schedule.put(pair, random.nextBoolean());
        }
        return schedule;
    }

    private static List<WorkingType> createJob() {
        List<WorkingType> jobs = new LinkedList<>();
        jobs.add(WorkingType.Cashier);
        jobs.add(WorkingType.Cleaning);
        jobs.add(WorkingType.Delivery);

        Collections.shuffle(jobs);

        return jobs.subList(0, random.nextInt(2) + 1);
    }

    private static void printMenu() {
        System.out.println("1) view workers");
        System.out.println("2) view shifts");
        System.out.println("3) exit");
    }

    // options

    private static void workersView() {
        for (Worker worker : Workers.getInstance().getAllWorkers().values()) {
            System.out.println(worker.toString());
        }


        System.out.println("1) select a worker");
        System.out.println("2) return");

        int choice = keyboard.nextInt();

        switch (choice) {
            case 1:
                System.out.println("enter the worker id :");
                int id = keyboard.nextInt();
                workerView(id);
                break;

        }


    }

    private static void workerView(int worker_id) {
        Worker w = Workers.getInstance().getWorker(worker_id);
        if (w == null) {
            throw new IllegalArgumentException(); // random
        }

        System.out.println("Worker name : " + w.getName());
        System.out.println("Worker id : " + w.getId());
        System.out.println("jobs : " + w.getType().toString());
        System.out.println("-------------------------");

        boolean go_back = false;
        while (!go_back) {

            System.out.println("1) print Schedule");
            System.out.println("2) add Worker to shift");
            System.out.println("3) print contract");
            System.out.println("4) print working shifts");
            System.out.println("5) return");

            int choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    printSchedule(worker_id);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println(w.getContract().toString());
                    break;
                case 4:
                    printWorkingShifts(w);
                    break;
                case 5:
                    go_back = true;
                    break;
            }
            border();
        }
    }

    private static void printWorkingShifts(Worker w) {

        if (w.getWorker_shifts().isEmpty())
            System.out.println(w.getName() + " has no working shifts.");
        else for (Shift shift : w.getWorker_shifts()) {
            System.out.println(shift.toString());
        }
    }

    private static void printSchedule(int worker_id) {
        Map<Pair<Day, ShiftTime>, Boolean> worker_schedule = Workers.getInstance().getAllWorkers().get(worker_id).getSchedule();
        for (Pair<Day, ShiftTime> p : worker_schedule.keySet()) {
            System.out.println(p.toString() + " : " + worker_schedule.get(p));
        }
    }

    private static void border() {
        System.out.println("--------------------------------");
    }

    private static void shiftsView() {
        if (History.getInstance().getShifts().isEmpty()) {
            System.out.println("There are no shifts at the moment.");
            return;
        }

        for (Shift shift : History.getInstance().getShifts()) {
            System.out.println(shift.toString());
        }

        boolean go_back = false;
        System.out.println("1) select a shift");
        System.out.println("2) return");

        while (!go_back) {
            int choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("enter the shift id :");
                    int id = keyboard.nextInt();
                    shiftView(id);
                    break;
                case 2:
                    go_back = true;
                    break;
            }
        }

    }

    private static void shiftView(int shift_id)
    {
 /*       Shift s = History.getInstance().getShifts().get(shift_id+1);        //     check here the +1
        if (shift_id == null) {
            throw new IllegalArgumentException(); // random
        }

        System.out.println("Worker name : " + w.getName());
        System.out.println("Worker id : " + w.getId());
        System.out.println("jobs : " + w.getType().toString());
        System.out.println("-------------------------");

        boolean go_back = false;
        while (!go_back) {

            System.out.println("1) print Schedule");
            System.out.println("2) add Worker to shift");
            System.out.println("3) print contract");
            System.out.println("4) print working shifts");
            System.out.println("5) return");

            int choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    printSchedule(worker_id);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println(w.getContract().toString());
                    break;
                case 4:
                    printWorkingShifts(w);
                    break;
                case 5:
                    go_back = true;
                    break;
            }
            border();
        }

  */
    }
}
