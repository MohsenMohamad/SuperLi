package InterfaceLayer;

import BusinessLayer.BLService;
import DTOs.*;
import javafx.util.Pair;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class ILService{
    private BLService blService;

    public ILService(){
        this.blService = new BLService();
    }

    public void addDriver(int id, String name, List<WorkPolicy.WorkingType> type, Map<Pair<DayOfWeek, Shift.ShiftTime>, Boolean> schedule, WorkerDeal contract, String license){
        blService.addDriver()


        if(this.blService.addDriver(driver)){
            System.out.println("Driver was added successfuly.\n" +
                    "   Driver name : " + name + "\n" +
                    "   Driver ID : " + id + "\n" +
                    "   License : " + license + "\n");
        }
    }
//
//    public void addLocation(String location){
//        DTOLocation loc = new DTOLocation();
//
//        if(this.blService.addLocation(loc)){
//            System.out.println("Location + " + location + " was added successfuly.");
//        }
//    }

    public void addAdress(String location, String contactName, String phoneNumber){
        DTOAdress adress = new DTOAdress(location, contactName, phoneNumber);

        if(this.blService.addAdress(adress)){
            System.out.println("Address was added successfuly.\n" +
                    "   Location : " + location + "\n" +
                    "   Contact name : " + contactName + "\n" +
                    "   Phone number : " + phoneNumber + "\n");
        }
    }

    public void addTruck(String serialNumber,String model, int weight, int maxAllowedWeight){
        DTOTruck truck = new DTOTruck(serialNumber, model, weight, maxAllowedWeight);

        if(this.blService.addTruck(truck)){
            System.out.println("Truck added successfuly.\n" +
                    "   Serial number : " + serialNumber + "\n" +
                    "   Model : " + model + "\n" +
                    "   Weight : " + weight + "\n" +
                    "   Max allowed weight : " + maxAllowedWeight + "\n");
        }
    }

    public void addProduct(String productName, String CN, int weight, int amount, String adress){
        DTOProduct product = new DTOProduct(productName, CN, weight, amount, adress);

        if(this.blService.addProduct(product)){
            System.out.println("Product was added successfult.\n" +
                    "   Product name : " + productName + "\n" +
                    "   Catalog number : " + CN + "\n" +
                    "   Weight : " + weight + "\n" +
                    "   Amount : " + amount + "\n" +
                    "   Address : " + adress + "\n");
        }
    }

    public void printDriversData(){
        System.out.println(this.blService.getDriversData());
    }

    public void printTrucksData(){
        System.out.println(this.blService.getTrucksData());
    }

    public void printAdresses(){
        System.out.println(this.blService.getAdresses());
    }
    
    //
    public void arrangeDelivery(String source, String destinationsAndProducts){

    }
}