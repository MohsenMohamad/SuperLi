package PL;


import BL.*;
import BL.Shift.ShiftTime;
import BL.WorkPolicy.WorkingType;
import javafx.util.Pair;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
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

            border();
        }

    }

    private static void createWorkers() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            WorkerDeal shadi_contract = new WorkerDeal(Worker.count, dateFormat.parse("30/03/2017"), 28, 11, new LinkedList<>());
            Worker shadi = new Worker("Shadi", createJob(), createSchedule(), shadi_contract);

            WorkerDeal eran_contract = new WorkerDeal(Worker.count, dateFormat.parse("05/11/2016"), 30, 22, new LinkedList<>());
            Worker eran = new Worker("Eran", createJob(), createSchedule(), eran_contract);


            WorkerDeal mohamad_contract = new WorkerDeal(Worker.count, dateFormat.parse("12/06/2018"), 1000, 33, new LinkedList<>());
            Worker mohamad = new Worker("Mohamad", createJob(), createSchedule(), mohamad_contract);

            Workers workers = Workers.getInstance();
            workers.addWorker(shadi);
            workers.addWorker(eran);
            workers.addWorker(mohamad);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

    }

    private static void createShifts() {
        History history = History.getInstance();
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Shift sundayMorning_shift = new Shift(date_format.parse("13/04/2020"), ShiftTime.Morning, Workers.getInstance().getWorker(1), new HashMap<>());
            Shift mondayEvening_shift = new Shift(date_format.parse("14/04/2020"), ShiftTime.Evening, Workers.getInstance().getWorker(2), new HashMap<>());
            history.addShift(sundayMorning_shift);
            history.addShift(mondayEvening_shift);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

    }

    private static Map<Pair<DayOfWeek, ShiftTime>, Boolean> createSchedule() {

        Map<Pair<DayOfWeek, ShiftTime>, Boolean> schedule = new HashMap<>();


        List<Pair<DayOfWeek, ShiftTime>> shifts = new LinkedList<>();
        shifts.add(new Pair<>(DayOfWeek.SUNDAY, ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.SUNDAY, ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.MONDAY, ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.MONDAY, ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.TUESDAY, ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.TUESDAY, ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.WEDNESDAY, ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.WEDNESDAY, ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.THURSDAY, ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.THURSDAY, ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.FRIDAY, ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.FRIDAY, ShiftTime.Evening));
        shifts.add(new Pair<>(DayOfWeek.SATURDAY, ShiftTime.Morning));
        shifts.add(new Pair<>(DayOfWeek.SATURDAY, ShiftTime.Evening));

        for (Pair<DayOfWeek, ShiftTime> pair : shifts) {
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

        boolean go_back = false;

        while (!go_back) {
            System.out.println(ConsoleColors.BLUE_BOLD + Workers.getInstance().toString() + ConsoleColors.RESET + "\n");
            System.out.println("1) Register a worker");
            System.out.println("2) select a worker");
            System.out.println("3) return\n");

            int choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    registerWorker();
                    break;
                case 2:
                    System.out.println("enter the worker id :");
                    int id = keyboard.nextInt();
                    border();
                    workerView(id);
                    break;

                case 3:
                    go_back = true;
                    break;

            }

            //border();
        }


    }

    private static void workerView(int worker_id) {
        Worker w = Workers.getInstance().getWorker(worker_id);
        if (w == null) {
            throw new IllegalArgumentException(); // random
        }

        boolean go_back = false;
        while (!go_back) {

            System.out.println(ConsoleColors.BLUE_BOLD + "Worker name : " + w.getName());
            System.out.println("Worker id : " + w.getId());
            System.out.println("jobs : " + w.getType().toString() + ConsoleColors.RESET + "\n");
            //        border();

            System.out.println("1) print Schedule");
            System.out.println("2) print contract");
            System.out.println("3) print working shifts");
            System.out.println("4) return");

            int choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    printSchedule(worker_id);
                    break;
                case 2:
                    System.out.println(ConsoleColors.CYAN_BOLD + w.getContract().toString() + ConsoleColors.RESET);
                    break;
                case 3:
                    printWorkingShifts(w);
                    break;
                case 4:
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
            System.out.println(ConsoleColors.GREEN_BOLD + shift.toString() + ConsoleColors.RESET);
        }
    }

    // work on this
    private static void printSchedule(int worker_id) {
        Map<Pair<DayOfWeek, ShiftTime>, Boolean> worker_schedule = Workers.getInstance().getAllWorkers().get(worker_id).getSchedule();
        List<Pair<DayOfWeek, ShiftTime>> schedules_date = new LinkedList<>(worker_schedule.keySet());
        Collections.sort(schedules_date, new Comparator<Pair<DayOfWeek, ShiftTime>>() {
            @Override
            public int compare(Pair<DayOfWeek, ShiftTime> o1, Pair<DayOfWeek, ShiftTime> o2) {

                if (o1.getKey().getValue() < o2.getKey().getValue())
                    return -1;

                if (o1.getKey().equals(o2.getKey())) {
                    if (o1.getValue() == ShiftTime.Morning)
                        return -1;
                    return 1;
                }
                return 0;
            }
        });
        for (Pair<DayOfWeek, ShiftTime> p : schedules_date) {
    /*        DayOfWeek d = p.getKey();
            SimpleDateFormat date_format = new SimpleDateFormat("EEE");
            date_format.format(d);

     */
            System.out.println(ConsoleColors.PURPLE_BOLD + p.getKey() + " , " + p.getValue().toString() + " : " + worker_schedule.get(p) + ConsoleColors.RESET);
        }
    }

    private static void border() {
        System.out.println(ConsoleColors.RED_BOLD + "--------------------------------" + ConsoleColors.RESET);
    }

    private static void shiftsView() {
        if (History.getInstance().getShifts().isEmpty()) {
            System.out.println("There are no shifts at the moment.");
            return;
        }

        boolean go_back = false;
        while (!go_back) {

            for (Shift shift : History.getInstance().getShifts()) {
                System.out.println(ConsoleColors.GREEN_BOLD + shift.toString() + ConsoleColors.RESET);
                border();
            }

            System.out.println("1) select a shift");
            System.out.println("2) create a shift");
            System.out.println("3) return");
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("enter the shift id :");
                    int id = keyboard.nextInt();
                    border();
                    shiftView(id);
                    break;
                case 2:
                    createShift();
                    break;
                case 3:
                    go_back = true;
                    break;
            }
        }

    }

    private static void shiftView(int shift_id) {
        Shift shift = History.getInstance().getShifts().get(shift_id);
        if (shift == null) {
            throw new IllegalArgumentException(); // random
        }

        System.out.println(ConsoleColors.GREEN_BOLD + shift.toString() + ConsoleColors.RESET);

        boolean go_back = false;
        while (!go_back) {

            System.out.println("1) print available workers for this shift");
            System.out.println("2) return");

            int first_choice = keyboard.nextInt();

            switch (first_choice) {
                case 1:

                    System.out.println(ConsoleColors.BLUE_BOLD + Workers.getInstance().getAvailableWorkers(shift.getShiftDate(), shift.getShiftTime()).toString() + ConsoleColors.RESET);
                    System.out.println("1) add a worker to this shift");
                    System.out.println("2) return");
                    int second_choice = keyboard.nextInt();
                    if (second_choice == 2)
                        break;
                    else {
                        System.out.println("enter the id of the worker you want to add");
                        int worker_id = keyboard.nextInt();
                        Worker w = Workers.getInstance().getWorker(worker_id);
                        shift.addToWorkingTeam(w, w.getType().get(0));
                    }
                    break;
                case 2:
                    go_back = true;
                    break;

            }
            border();
        }

    }

    private static void createShift() {

        for (; ; ) {
            System.out.println("enter the date using this format dd/mm/yyyy or type EXIT to cancel ...");
            String date_string = keyboard.next();
            if (date_string.equals("EXIT") || date_string.equals("exit"))
                return;

            Date date = null;
            SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");

            try {
                date = date_format.parse(date_string);
                System.out.println("Your shift date will be : " + ConsoleColors.RED_BOLD + new SimpleDateFormat("EEEE").format(date) + " " + date_string + ConsoleColors.RESET);
                System.out.println("Type M for Morning , Type E for Evening");
                ShiftTime shiftTime = null;
                String choice = keyboard.next();

                if (choice.equals("M") || choice.equals("m")) {
                    shiftTime = ShiftTime.Morning;
                } else if (choice.equals("E") || choice.equals("e")) {
                    shiftTime = ShiftTime.Evening;
                }

                System.out.println(ConsoleColors.BLUE_BOLD + Workers.getInstance().toString() + ConsoleColors.RESET);
                System.out.println("enter the id of the worker who you wish to appoint as a boss");
                int boss_id = keyboard.nextInt();
                Worker boss = Workers.getInstance().getWorker(boss_id);
                Map<WorkingType, List<Worker>> working_team = new HashMap<>();

                Shift shift = new Shift(date, shiftTime, boss, working_team);
                boolean success = History.getInstance().addShift(shift);
                if (success)
                    System.out.println("The shift has been created successfully");
                else
                    System.out.println("Error : the shift already exists");
                return;

            } catch (ParseException pe) {
                System.out.println("Error : invalid input");
            }

        }


    }

    private static void registerWorker() {
        System.out.println("Worker name: ");
        String worker_name = keyboard.next();
        List<WorkingType> jobs = Arrays.asList(WorkingType.values());
        boolean stop = false;
        System.out.println("Worker types : ");
        int job_id = 1;
        for (WorkingType type : jobs) {
            System.out.println(ConsoleColors.RED + job_id + ") " + type + ConsoleColors.RESET);
            job_id++;
        }

        List<WorkingType> worker_jobs = new LinkedList<>();
        while (!stop) {
            int workingType_id = keyboard.nextInt();
            worker_jobs.add(jobs.get(workingType_id - 1));
            System.out.println("choose another ? y/n");
            if (keyboard.next().equals("n"))
                stop = true;

        }

        System.out.println("Enter constraints : ");
        keyboard.next();
        System.out.println("Worker salary :");
        int salary = keyboard.nextInt();
        System.out.println("Worker bank address :");
        int address = keyboard.nextInt();
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        Date current_date = new Date();
        WorkerDeal deal = null;
        try {
            deal = new WorkerDeal(11, (date_format.parse(date_format.format(current_date))), address, salary, new LinkedList<String>());
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        Worker worker = new Worker(worker_name, worker_jobs, createSchedule(), deal);
        Workers.getInstance().addWorker(worker);

    }

}
