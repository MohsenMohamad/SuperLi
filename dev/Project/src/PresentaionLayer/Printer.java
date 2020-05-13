package PresentaionLayer;

import BusinessLayer.BLService;
import DTOs.*;
import javafx.util.Pair;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;

public class Printer {

    static BLService blService = new BLService();

    public static void printAllWorkingTypes() {
        int number = 1;
        for (WorkPolicy.WorkingType workingType : WorkPolicy.WorkingType.values()) {
            System.out.println(number + ") " + workingType.toString());
            number++;
        }
    }

    public static void printMainMenu() {
        System.out.println("1) View Workers");
        System.out.println("2) View Shifts");
        System.out.println("3) View Addresses");
        System.out.println("4) View Trucks");
        System.out.println("5) View Deliveries");
        System.out.println("6) exit");
    }

    // -------------------------------------- Trucks ----------------------------------------  //
    public static void printAllTrucks() {

        List<Truck> trucks_list = blService.getAllTrucks();

        String trucks = "";

        for (Truck t : trucks_list) {
            trucks += "Truck serial number : " + t.getSerialNumber() + "\n" +
                    "Model : " + t.getModel() + "\n" +
                    "Weight : " + t.getWeight() + "\n" +
                    "Max allowed weight : " + t.getMaxAllowedWeight() + "\n";
        }

        System.out.println(trucks);
    }

    public static void printTruckView(Truck truck) {
        String truck_data = "";
        truck_data += "Truck serial number : " + truck.getSerialNumber() + "\n" +
                "Model : " + truck.getModel() + "\n" +
                "Weight : " + truck.getWeight() + "\n" +
                "Max allowed weight : " + truck.getMaxAllowedWeight() + "\n";

        System.out.println(truck_data);

        System.out.println("1) Remove Truck");
        System.out.println("2) Return");
    }

    public static void printTrucksMenuOptions() {
        System.out.println("1) Add a truck");
        System.out.println("2) Select a truck");
        System.out.println("3) Return");
    }

// -------------------------------------- Workers ----------------------------------------  //

    public static void printWorkersView() {
        System.out.println(blService.getAllWorkers().toString() + "\n");

        System.out.println("1) Register a worker");
        System.out.println("2) select a worker");
        System.out.println("3) return\n");
    }

    public static void printWorkingShifts(int worker_id) {


        Worker worker = blService.getWorker(worker_id);

        if (worker.getWorker_shifts().isEmpty())
            System.out.println(worker.getName() + " has no working shifts.");
        else for (Shift shift : worker.getWorker_shifts()) {
            System.out.println(shift.toString());
        }
    }

    public static void PrintWorkerView(int worker_id) {
        Worker w = blService.getWorker(worker_id);
        System.out.println("Worker name : " + w.getName());
        System.out.println("Worker id : " + w.getId());
        System.out.println("jobs : " + w.getType().toString() + "\n");

        System.out.println("1) print Schedule");
        System.out.println("2) print contract");
        System.out.println("3) print working shifts");
        System.out.println("4) edit worker info");
        System.out.println("5) Delete Worker");
        System.out.println("6) return");
    }

    public static void printEditWorkerMenu()
    {
        System.out.println("1) Edit worker name");
        System.out.println("2) Edit worker id");
        System.out.println("3) Edit worker jobs");
        System.out.println("4) Edit worker bank address");
        System.out.println("5) Edit worker salary");
        System.out.println("6) Return");
    }

    public static void printContract(int worker_id) {
        Worker worker = blService.getWorker(worker_id);
        WorkerDeal contract = worker.getContract();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String deal = "worker id : " + worker_id + '\n';
        deal = deal + "start date : " + dateFormat.format(contract.getStart_date()) + '\n';
        deal = deal + "bank address : " + contract.getBankAddress() + '\n';
        deal = deal + "salary : " + contract.getSalary() + '\n';
        if (contract.getWork_conditions() != null) {
            deal = deal + "working conditions : " + contract.getWork_conditions().toString() + '\n';
        }

        System.out.println(deal);
    }

    public static void printAvailableWorkers(Date date, Shift.ShiftTime shiftTime) {
        Map<Integer, Worker> available_map = blService.getAvailableWorkers(date, shiftTime);

        String workers_string = "";
        for (Worker worker : available_map.values()) {
            workers_string = workers_string + worker.toString() + '\n';
        }

        workers_string = workers_string.substring(0, workers_string.length() - 1);
        System.out.println(workers_string);

    }
// -------------------------------------- Shifts ----------------------------------------  //


    public static void printShiftsView() {
        List<Shift> history = blService.getAllShifts();
        if (history == null) {
            // db connection error
        }
        for (Shift shift : history) {
            System.out.println(shift.toString());
            border();
        }

        System.out.println("1) select a shift");
        System.out.println("2) create a shift");
        System.out.println("3) return");
    }

    public static void printShiftView(int shift_id) {
        Shift shift = blService.getShift(shift_id);
        System.out.println(shift.toString());
        System.out.println("1) print available workers for this shift");
        System.out.println("2) return");
    }

// -------------------------------------- Deliveries ----------------------------------------  //

    public static void printAllDeliveries()
    {
        List<Delivery> deliveries_list = blService.getAllDeliveries();

        for(Delivery d : deliveries_list)
        {
            System.out.println(d.getDate());
        }

    }


    public static void printSchedule(int worker_id) {
        Map<Pair<DayOfWeek, Shift.ShiftTime>, Boolean> worker_schedule = blService.getWorker(worker_id).getSchedule();
        List<Pair<DayOfWeek, Shift.ShiftTime>> schedules_date = new LinkedList<>(worker_schedule.keySet());
        Collections.sort(schedules_date, new Comparator<Pair<DayOfWeek, Shift.ShiftTime>>() {
            @Override
            public int compare(Pair<DayOfWeek, Shift.ShiftTime> o1, Pair<DayOfWeek, Shift.ShiftTime> o2) {

                if ((o1.getKey().getValue()+1)%8 < (o2.getKey().getValue()+1)%8)
                    return -1;

                if (o1.getKey().equals(o2.getKey())) {
                    if (o1.getValue() == Shift.ShiftTime.Morning)
                        return -1;
                    return 1;
                }
                return 0;
            }
        });
        for (Pair<DayOfWeek, Shift.ShiftTime> p : schedules_date) {
            System.out.println(p.getKey() + " , " + p.getValue().toString() + " : " + worker_schedule.get(p));
        }
    }


    public static void border() {
        System.out.println("--------------------------------");
    }


}
