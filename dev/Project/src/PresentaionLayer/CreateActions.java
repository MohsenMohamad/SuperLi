package PresentaionLayer;

import BusinessLayer.BLService;
import DTOs.*;
import DTOs.WorkPolicy.WorkingType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateActions {

    private static BLService blService = new BLService();
    static Scanner keyboard = new Scanner(System.in);


    public static void registerWorker() {

        System.out.println("Worker id: ");
        int worker_id = getChoice(100000000, 999999999);
        if (blService.getWorker(worker_id) != null) {
            System.out.println("Error : There is a user with the same id in the data base!");
            return;
        }
        System.out.println("Worker name: ");
        String worker_name = keyboard.nextLine();
        List<WorkPolicy.WorkingType> jobs = Arrays.asList(WorkPolicy.WorkingType.values());
        boolean stop = false;
        System.out.println("Working types : ");
        Printer.printAllWorkingTypes();
        WorkPolicy.WorkingType worker_job;
        System.out.println("Choose one of the working types :");
        int workingType_id = getChoice(1, 3);
        worker_job = jobs.get(workingType_id - 1);
        System.out.println("Enter constraints : ");
        keyboard.nextLine();
        System.out.println("Worker salary :");
        double salary = getDoubleChoice(0,Double.MAX_VALUE);
        System.out.println("Worker bank address :");
        String address = keyboard.nextLine();
        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
        Date current_date = new Date();
        WorkerDeal deal = null;
        try {
            deal = new WorkerDeal(worker_id, (date_format.parse(date_format.format(current_date))), salary, address, new LinkedList<String>());
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        Worker worker;
        switch (worker_job)
        {
            case Driver:
                System.out.println("Enter the driver's license:");
                String license = keyboard.nextLine();
                worker = new Driver(worker_id, worker_name, worker_job, new InitializeData().createSchedule(),deal,license);
                break;
            case StockKeeper:
                break;
        }

    }

    public static void AddTruck() {
        System.out.println("Enter the truck's serial number:");
        String serialNumber = keyboard.nextLine();
        System.out.println("Enter the truck's model:");
        String model = keyboard.nextLine();
        System.out.println("Enter the truck's weight:");
        int weight = getChoice(0, Integer.MAX_VALUE);
        System.out.println("Enter the truck's max allowed weight:");
        int maxAllowedWeight = getChoice(0, Integer.MAX_VALUE);

        String result = blService.addTruck(serialNumber, model, weight, maxAllowedWeight);

        System.out.println(result);

    }

/*
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
                System.out.println("Your shift date will be : " + new SimpleDateFormat("EEEE").format(date) + " " + date_string);
                System.out.println("Type M for Morning , Type E for Evening");
                Shift.ShiftTime shiftTime = null;
                String choice = keyboard.next();

                if (choice.equals("M") || choice.equals("m")) {
                    shiftTime = Shift.ShiftTime.Morning;
                } else if (choice.equals("E") || choice.equals("e")) {
                    shiftTime = Shift.ShiftTime.Evening;
                }

                System.out.println(Workers.getInstance().AvilableWorkerstoString(date, shiftTime));
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

    public void editWorker(int worker_id) {
        Worker worker = Workers.getInstance().getWorker(worker_id);
        WorkPolicy.WorkingType[] current_types = WorkPolicy.WorkingType.values();
        boolean go_back = false;
        while (!go_back) {
            System.out.println("1) Edit worker name");
            System.out.println("2) Edit worker id");
            System.out.println("3) Edit worker jobs");
            System.out.println("4) Edit worker bank address");
            System.out.println("5) Edit worker salary");
            System.out.println("6) Return");

            int choice = getChoice(1, 6);
            switch (choice) {
                case 1:
                    System.out.println("Type the new name :");
                    String edited_name = keyboard.next();
                    Workers.getInstance().getWorker(worker_id).setName(edited_name);
                    break;
                case 2:
                    System.out.println("Type the new id :");
                    int edited_id = keyboard.nextInt();
                    Workers.getInstance().getWorker(worker_id).setID(edited_id);
                    break;
                case 3:
                    System.out.println("choose the type you want to add/remove :");
                    System.out.println("to add type the number to remove type -number...");
                    Printer.printAllWorkingTypes();
                    int type_choice = getChoice(-1 * WorkPolicy.WorkingType.values().length, WorkPolicy.WorkingType.values().length);
                    if (type_choice > 0) {
                        WorkPolicy.WorkingType workingType = WorkPolicy.WorkingType.values()[Math.abs(type_choice) - 1];
                        if (worker.getType().contains(workingType))
                            System.out.println("the worker is already signed for this job!");
                        else
                            worker.getType().add(workingType);

                    } else if (type_choice < 0) {
                        WorkPolicy.WorkingType workingType = WorkPolicy.WorkingType.values()[Math.abs(type_choice) - 1];
                        if (!worker.getType().contains(workingType))

                            System.out.println("the worker is not signed for this job");

                        else
                            worker.getType().remove(workingType);
                    } else
                        System.out.println("Error : number out of bounds!");

                    break;
                case 4:
                    System.out.println("Type the new bank address :");
                    String edited_address = keyboard.next();
                    Workers.getInstance().getWorker(worker_id).getContract().setBankAddress(edited_address);
                    break;
                case 5:
                    System.out.println("Type the new salary :");
                    double edited_salary = keyboard.nextDouble();
                    Workers.getInstance().getWorker(worker_id).getContract().setSalary(edited_salary);
                    break;
                case 6:
                    go_back = true;
                    break;


            }
        }

    }



*/

    private static int getChoice(int lower_bound, int upper_bound) {
        for (; ; ) {
            int keyboard_input = keyboard.nextInt();

            if (keyboard_input < lower_bound || keyboard_input > upper_bound) {
                System.out.println("Error : number out of bounds!");
            } else return keyboard_input;
        }
    }

    private static double getDoubleChoice(double lower_bound, double upper_bound) {
        for (; ; ) {
            double keyboard_input = keyboard.nextInt();

            if (keyboard_input < lower_bound || keyboard_input > upper_bound) {
                System.out.println("Error : number out of bounds!");
            } else return keyboard_input;
        }
    }
}


