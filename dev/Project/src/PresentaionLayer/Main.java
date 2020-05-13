package PresentaionLayer;


import BusinessLayer.BLService;
import DTOs.*;


import java.math.BigInteger;
import java.util.*;

public class Main {

    private static final BLService blService = new BLService();
    static Scanner keyboard = new Scanner(System.in);
    static InitializeData init_data = new InitializeData();

    public static void main(String[] argv) {

        boolean terminate = false;

//        init_data.createWorkers();
//        init_data.createShifts();


        while (!terminate) {
            Printer.printMainMenu();
            int choice = getChoice(1, 6);

            switch (choice) {
                case 1:
                    workersView();
                    break;
                case 2:
                    //    shiftsView();
                    break;
                case 3:
                    //        addressesView();
                    break;
                case 4:
                    trucksView();
                    break;
                case 5:
                    deliveriesView();
                    break;
                case 6:
                    terminate = true;
                    break;
            }

//            Printer.border();
        }

    }

    private static void deliveriesView()
    {



    }

    private static void trucksView() {

        List<Truck> trucks = blService.getAllTrucks();
        Printer.printAllTrucks(trucks);

        boolean terminate = false;
        Printer.printTrucksMenuOptions();


        while(!terminate) {

            int choice = getChoice(1, 3);
            switch (choice) {
                case 1:
                    CreateActions.AddTruck();
                    break;
                case 2:

                    System.out.println("Enter the truck's serial number:");
                    String serial_number = keyboard.nextLine();
                    Truck truck = blService.getTruck(serial_number);


                    break;
                case 3:
                    terminate = true;
                    break;
            }
        }

    }

    private static void truckView(Truck truck) {

        boolean go_back = false;
        while (!go_back) {

            Printer.printTruckView(truck);

            int choice = getChoice(1, 3);

            switch (choice) {
                case 1:
                    System.out.println("Are you sure you want to remove this truck ? y/n");
                    boolean confirmation = getConfirmation();
                    if(confirmation)
                    {
                        String result = blService.removeTruck(truck.getSerialNumber());
                        System.out.println(result);
                    }
                    else
                        System.out.println("The deletion was canceled");
                    break;
                case 2:
                    go_back = true;
                    break;
            }
            Printer.border();
        }
    }

    private static void workersView() {

        boolean go_back = false;

        while (!go_back) {

//            Printer.printWorkersView();
            int choice = getChoice(1, 3);

            switch (choice) {
                case 1:
//                    new CreateActions().registerWorker();
                    break;
                case 2:
                    if (blService.getAllWorkers().isEmpty()) {
                        System.out.println("Error : there are no workers!");
                        break;
                    }
                    System.out.println("enter the worker id :");
                    int id = keyboard.nextInt();
//                    Printer.border();
//                    if (!Workers.getInstance().getAllWorkers().containsKey(id)) {
                    System.out.println("Error : invalid id");
//                    } else workerView(id);
                    break;

                case 3:
                    go_back = true;
                    break;


            }

        }


    }
/*
    private static void workerView(int worker_id) {
        Worker w = Workers.getInstance().getWorker(worker_id);

        boolean go_back = false;
        while (!go_back) {

            Printer.PrintWorkerView(worker_id);

            int choice = getChoice(1, 5);

            switch (choice) {
                case 1:
                    Printer.printSchedule(worker_id);
                    break;
                case 2:
                    System.out.println(w.getContract().toString());
                    break;
                case 3:
                    Printer.printWorkingShifts(w);
                    break;
                case 4:
                    new CreateActions().editWorker(worker_id);
                    break;
                case 5:
                    go_back = true;
                    break;
            }
            Printer.border();
        }
    }


    private static void shiftsView() {

        boolean go_back = false;
        while (!go_back) {

           Printer.printShiftsView();
            int choice = getChoice(1, 3);
            switch (choice) {
                case 1:
                    if (History.getInstance().getShifts().isEmpty()) {
                        System.out.println("There are no shifts at the moment.");
                        break;
                    }
                    System.out.println("enter the shift id :");
                    int id = keyboard.nextInt();
                    Printer.border();
                    if (!History.getInstance().isAvailable(id)) {
                        System.out.println("Error : invalid shift_id!");
                    } else shiftView(id);
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
            Printer.printShiftView(shift_id);

            int first_choice = getChoice(1, 2);
            switch (first_choice) {
                case 1:
                    List<Worker> available_workers = Workers.getInstance().getAvailableWorkers(shift.getShiftDate(), shift.getShiftTime());
                    System.out.println(available_workers.toString());
                    System.out.println("1) add a worker to this shift");
                    System.out.println("2) return");
                    int second_choice = getChoice(1, 2);
                    if (second_choice == 2)
                        break;
                    else if (available_workers.isEmpty()) {
                        System.out.println("Error : there are no workers to choose from!");

                    } else {
                        System.out.println("enter the id of the worker you want to add");
                        int worker_id = keyboard.nextInt();
                        Worker w = Workers.getInstance().getWorker(worker_id);
                        System.out.println("which job will "+ w.getName()+" do?");
                        int index =1;
                        for(WorkingType type : w.getType())
                        {
                            System.out.println(index+") "+type);
                            index++;
                        }
                        int type_index = getChoice(1,w.getType().size())-1;
                        shift.addToWorkingTeam(w, w.getType().get(type_index));
                    }
                    break;
                case 2:
                    go_back = true;
                    break;

            }
            Printer.border();
        }

    }

 */

    private static int getChoice(int lower_bound, int upper_bound) {
        for (; ; ) {
            String keyboard_input = keyboard.nextLine();
            int choice_number = -1;
            boolean tooBig = false;
            try {
                BigInteger big_int = new BigInteger(keyboard_input);
                if (big_int.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
                    //    System.out.println("Error : value is too large");
                    tooBig = true;
                }
                choice_number = big_int.intValue();

                if (tooBig || choice_number < lower_bound || choice_number > upper_bound) {
                    System.out.println("Error : number out of bounds!");
                } else return choice_number;
            } catch (NumberFormatException numberFormatException) {
                System.out.println(keyboard_input);
                System.out.println("Error : Enter a numeric input!");
            }

        }
    }

    private static boolean getConfirmation()
    {
        for(;;)
        {
            String keyboard_input = keyboard.nextLine();
            if(keyboard_input.equals("y")|| keyboard_input.equals("Y"))
                return true;
            else if(keyboard_input.equals("n")|| keyboard_input.equals("N"))
                return false;
            else
                System.out.println("Error : Invalid input ! type n to cancel or y to confirm");
        }

    }


}

