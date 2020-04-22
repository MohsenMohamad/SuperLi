package PL;

import BL.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateActions {

    static Scanner keyboard = new Scanner(System.in);

    public void createShift() {

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
                Shift.ShiftTime shiftTime = null;
                String choice = keyboard.next();

                if (choice.equals("M") || choice.equals("m")) {
                    shiftTime = Shift.ShiftTime.Morning;
                } else if (choice.equals("E") || choice.equals("e")) {
                    shiftTime = Shift.ShiftTime.Evening;
                }

                System.out.println(ConsoleColors.BLUE_BOLD + Workers.getInstance().toString() + ConsoleColors.RESET);
                System.out.println("enter the id of the worker who you wish to appoint as a boss");
                int boss_id = keyboard.nextInt();
                Worker boss = Workers.getInstance().getWorker(boss_id);
                Map<WorkPolicy.WorkingType, List<Worker>> working_team = new HashMap<>();

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

    public void registerWorker() {
        System.out.println("Worker name: ");
        String worker_name = keyboard.next();
        System.out.println("Worker id: ");
        int worker_id = keyboard.nextInt();
        List<WorkPolicy.WorkingType> jobs = Arrays.asList(WorkPolicy.WorkingType.values());
        boolean stop = false;
        System.out.println("Worker types : ");
        int job_id = 1;
        for (WorkPolicy.WorkingType type : jobs) {
            System.out.println(ConsoleColors.RED + job_id + ") " + type + ConsoleColors.RESET);
            job_id++;
        }

        List<WorkPolicy.WorkingType> worker_jobs = new LinkedList<>();
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
        double salary = keyboard.nextDouble();
        System.out.println("Worker bank address :");
        String address = keyboard.next();
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        Date current_date = new Date();
        WorkerDeal deal = null;
        try {
            deal = new WorkerDeal(worker_id, (date_format.parse(date_format.format(current_date))), salary, address, new LinkedList<String>());
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        Worker worker = new Worker(worker_id, worker_name, worker_jobs, new InitializeData().createSchedule(), deal);
        Workers.getInstance().addWorker(worker);

    }
}
