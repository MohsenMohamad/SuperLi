package PL;


import BL.*;
import BL.Shift.ShiftTime;
import BL.WorkPolicy.WorkingType;
import javafx.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;

public class Main {

    static Scanner keyboard = new Scanner(System.in);
    static InitializeData init_data = new InitializeData();

    public static void main(String[] argv) {

        boolean terminate = false;

        init_data.createWorkers();
        init_data.createShifts();


        while (!terminate) {
            printMenu();
            int choice = getChoice(1,3);

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

    private static void printMenu() {
        System.out.println("1) view workers");
        System.out.println("2) view shifts");
        System.out.println("3) exit");
    }

    private static void workersView() {

        boolean go_back = false;

        while (!go_back) {
            System.out.println(ConsoleColors.BLUE_BOLD + Workers.getInstance().toString() + ConsoleColors.RESET + "\n");
            System.out.println("1) Register a worker");
            System.out.println("2) select a worker");
            System.out.println("3) return\n");

            int choice = getChoice(1, 3);

            switch (choice) {
                case 1:
                    new CreateActions().registerWorker();
                    break;
                case 2:
                    if (Workers.getInstance().getAllWorkers().isEmpty()) {
                        System.out.println("Error : there are no workers!");
                        break;
                    }
                    System.out.println("enter the worker id :");
                    int id = keyboard.nextInt();
                    border();
                    if (!Workers.getInstance().getAllWorkers().containsKey(id)) {
                        System.out.println(ConsoleColors.RED_BOLD+"Error : invalid id"+ConsoleColors.RESET);
                    }
                    else workerView(id);
                    break;

                case 3:
                    go_back = true;
                    break;


            }

        }


    }

    private static void workerView(int worker_id) {
        Worker w = Workers.getInstance().getWorker(worker_id);

        boolean go_back = false;
        while (!go_back) {

            System.out.println(ConsoleColors.BLUE_BOLD + "Worker name : " + w.getName());
            System.out.println("Worker id : " + w.getId());
            System.out.println("jobs : " + w.getType().toString() + ConsoleColors.RESET + "\n");

            System.out.println("1) print Schedule");
            System.out.println("2) print contract");
            System.out.println("3) print working shifts");
            System.out.println("4) edit worker info");
            System.out.println("5) return");

            int choice = getChoice(1, 4);

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
                    new CreateActions().editWorker(worker_id);
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

                if ((o1.getKey().getValue()+1)%8 < (o2.getKey().getValue()+1)%8)
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
            System.out.println(ConsoleColors.PURPLE_BOLD + p.getKey() + " , " + p.getValue().toString() + " : " + worker_schedule.get(p) + ConsoleColors.RESET);
        }
    }

    private static void border() {
        System.out.println(ConsoleColors.RED_BOLD + "--------------------------------" + ConsoleColors.RESET);
    }

    private static void shiftsView() {

        boolean go_back = false;
        while (!go_back) {

            for (Shift shift : History.getInstance().getShifts()) {
                System.out.println(ConsoleColors.GREEN_BOLD + shift.toString() + ConsoleColors.RESET);
                border();
            }

            System.out.println("1) select a shift");
            System.out.println("2) create a shift");
            System.out.println("3) return");
            int choice = getChoice(1, 3);
            switch (choice) {
                case 1:
                    if (History.getInstance().getShifts().isEmpty()) {
                        System.out.println("There are no shifts at the moment.");
                        break;
                    }
                    System.out.println("enter the shift id :");
                    int id = keyboard.nextInt();
                    border();
                    if(!History.getInstance().isAvailable(id))
                    {
                        System.out.println(ConsoleColors.RED_BOLD+"Error : invalid shift_id!"+ConsoleColors.RESET);
                    }
                    else shiftView(id);
                    break;
                case 2:
                    new CreateActions().createShift();
                    break;
                case 3:
                    go_back = true;
                    break;
            }
        }

    }

    private static void shiftView(int shift_id) {
        Shift shift = History.getInstance().getShifts().get(shift_id);

        boolean go_back = false;
        while (!go_back) {

            System.out.println(ConsoleColors.GREEN_BOLD + shift.toString() + ConsoleColors.RESET);
            System.out.println("1) print available workers for this shift");
            System.out.println("2) return");

            int first_choice = getChoice(1, 2);

            switch (first_choice) {
                case 1:
                    List<Worker> available_workers = Workers.getInstance().getAvailableWorkers(shift.getShiftDate(), shift.getShiftTime());
                    System.out.println(ConsoleColors.BLUE_BOLD + available_workers.toString() + ConsoleColors.RESET);
                    System.out.println("1) add a worker to this shift");
                    System.out.println("2) return");
                    int second_choice = getChoice(1, 2);
                    if (second_choice == 2)
                        break;
                    else if (available_workers.isEmpty())
                    {
                        System.out.println(ConsoleColors.RED_BOLD + "Error : there are no workers to choose from!" + ConsoleColors.RESET);

                    }
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

    private static int getChoice(int lower_bound, int upper_bound) {
        for (; ; ) {
            int keyboard_input = keyboard.nextInt();

            if (keyboard_input < lower_bound || keyboard_input > upper_bound) {
                System.out.println(ConsoleColors.RED_BOLD + "Error : number out of bounds!" + ConsoleColors.RESET);
            } else return keyboard_input;
        }
    }
}

