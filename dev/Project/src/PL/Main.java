package PL;


import BL.*;
import BL.Shift.ShiftTime;
import BL.WorkPolicy.WorkingType;
import javafx.util.Pair;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        WorkerDeal shadi_contract = new WorkerDeal("today", 28,11, new LinkedList<>());
        Worker shadi = new Worker("Shadi", createJob(), createSchedule(), shadi_contract);

        WorkerDeal eran_contract = new WorkerDeal("Tomorrow", 30,22, new LinkedList<>());
        Worker eran = new Worker("Eran", createJob(), createSchedule(), eran_contract);


        WorkerDeal mohamad_contract = new WorkerDeal("Yesterday", 1000,33, new LinkedList<>());
        Worker mohamad = new Worker("Mohamad", createJob(), createSchedule(), mohamad_contract);

        Workers workers = Workers.getInstance();
        workers.addWorker(shadi);
        workers.addWorker(eran);
        workers.addWorker(mohamad);
    }

    private static void createShifts() {
        History history = History.getInstance();
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Shift sundayMorning_shift = new Shift(date_format.parse("13/04/2020"), ShiftTime.Morning,Workers.getInstance().getWorker(1), new HashMap<>());
            Shift mondayEvening_shift = new Shift(date_format.parse("14/04/2020"), ShiftTime.Evening,Workers.getInstance().getWorker(2), new HashMap<>());
            history.addShift(sundayMorning_shift);
            history.addShift(mondayEvening_shift);
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }

    }

    private static Map<Pair<Date, ShiftTime>, Boolean> createSchedule() {

        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        Map<Pair<Date, ShiftTime>, Boolean> schedule = new HashMap<>();
        List<Pair<Date, ShiftTime>> shifts = new LinkedList<>();
        try {
            shifts.add(new Pair<>(date_format.parse("12/04/2020"), ShiftTime.Morning));
            shifts.add(new Pair<>(date_format.parse("12/04/2020"), ShiftTime.Evening));
            shifts.add(new Pair<>(date_format.parse("13/04/2020"), ShiftTime.Morning));
            shifts.add(new Pair<>(date_format.parse("13/04/2020"), ShiftTime.Evening));
            shifts.add(new Pair<>(date_format.parse("14/04/2020"), ShiftTime.Morning));
            shifts.add(new Pair<>(date_format.parse("14/04/2020"), ShiftTime.Evening));
            shifts.add(new Pair<>(date_format.parse("15/04/2020"), ShiftTime.Morning));
            shifts.add(new Pair<>(date_format.parse("15/04/2020"), ShiftTime.Evening));
            shifts.add(new Pair<>(date_format.parse("16/04/2020"), ShiftTime.Morning));
            shifts.add(new Pair<>(date_format.parse("16/04/2020"), ShiftTime.Evening));
            shifts.add(new Pair<>(date_format.parse("17/04/2020"), ShiftTime.Morning));
            shifts.add(new Pair<>(date_format.parse("17/04/2020"), ShiftTime.Evening));
            shifts.add(new Pair<>(date_format.parse("18/04/2020"), ShiftTime.Morning));
            shifts.add(new Pair<>(date_format.parse("18/04/2020"), ShiftTime.Evening));
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }
        for (Pair<Date, ShiftTime> pair : shifts) {
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

        while(!go_back)
        {
            System.out.println(Workers.getInstance().toString());
            System.out.println("1) select a worker");
            System.out.println("2) return");

            int choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("enter the worker id :");
                    int id = keyboard.nextInt();
                    workerView(id);
                    break;

                case 2:
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

            System.out.println("Worker name : " + w.getName());
            System.out.println("Worker id : " + w.getId());
            System.out.println("jobs : " + w.getType().toString());
            border();

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
                    System.out.println(w.getContract().toString());
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
            System.out.println(shift.toString());
        }
    }

    // work on this
    private static void printSchedule(int worker_id) {
        Map<Pair<Date, ShiftTime>, Boolean> worker_schedule = Workers.getInstance().getAllWorkers().get(worker_id).getSchedule();
        for (Pair<Date, ShiftTime> p : worker_schedule.keySet()) {
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

        boolean go_back = false;
        while (!go_back) {

            for (Shift shift : History.getInstance().getShifts()) {
                System.out.println(shift.toString());
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

    private static void shiftView(int shift_id)
    {
        Shift shift = History.getInstance().getShifts().get(shift_id-1);        //     check here the -1
        if (shift == null) {
            throw new IllegalArgumentException(); // random
        }

        System.out.println(shift.toString());

        boolean go_back = false;
        while (!go_back) {

            System.out.println("1) print available workers for this shift");
            System.out.println("2) return");

            int first_choice = keyboard.nextInt();

            switch (first_choice) {
                case 1:
                    System.out.println(Workers.getInstance().getAvailableWorkers(shift.getShiftDate() , shift.getShiftTime() , WorkingType.Cleaning).toString());
                    System.out.println("1) add a worker to this shift");
                    System.out.println("2) return");
                    int second_choice = keyboard.nextInt();
                    if(second_choice==2)
                        break;
                    else
                    {
                        System.out.println("enter the id of the worker you want to add");
                        int worker_id = keyboard.nextInt();
                        Worker w = Workers.getInstance().getWorker(worker_id);
                        shift.addToWorkingTeam(w,w.getType().get(0));
                    }
                    break;
                case 2:
                    go_back = true;
                    break;

            }
            border();
        }

    }

    private static void createShift()
    {

        for(;;)
        {
            System.out.println("enter the date using this format dd/mm/yyyy or type EXIT to cancel ...");
            String date_string = keyboard.next();
            if(date_string.equals("EXIT") || date_string.equals("exit"))
                return;

            Date date = null;
            SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");

            try
            {
                date = date_format.parse(date_string);
                System.out.println("you choose "+date_string + " which will be "+new SimpleDateFormat("EEEE").format(date));
                System.out.println("Type M for Morning , Type E for Evening");
                ShiftTime shiftTime = null;
                String choice = keyboard.next();

                if (choice.equals("M")|| choice.equals("m"))
                {
                    shiftTime = ShiftTime.Morning;
                }
                else if(choice.equals("E")|| choice.equals("e"))
                {
                    shiftTime = ShiftTime.Evening;
                }

                System.out.println(Workers.getInstance().toString());
                System.out.println("enter the id of the worker who you wish to appoint as a boss");
                int boss_id = keyboard.nextInt();
                Worker boss = Workers.getInstance().getWorker(boss_id);
                Map<WorkingType , List<Worker>> working_team = new HashMap<>();

                Shift shift = new Shift(date , shiftTime , boss , working_team);
                boolean success = History.getInstance().addShift(shift);
                if(success)
                    System.out.println("The shift has been created successfully");
                else
                    System.out.println("Error : the shift already exists");
                return;

            }
            catch (ParseException pe)
            {
                System.out.println("Error : invalid input");
            }

        }



    }
}
