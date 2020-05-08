package BusinessLayer;

import DAL.DAO;
import DAL.DaoAPI;
import DTOs.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class BLService {
    private DAO dao;

    public BLService() {
        this.dao = new DAO();
    }

    public boolean addDriver(Driver driver) {
        return this.dao.addDriver(new Driver(driver.getName(), driver.getId(), driver.getLicense()));
    }

//    public boolean addLocation(DTOLocation location){
//        return this.data.addLocation(new Location(location.getLocation()));
//    }

    public boolean addAddress(Address address) {
        Location location = new Location(address.getLocation().getLocation());

        return this.data.addAdress(new Address(location, adress.getContactName(), adress.getPhoneNumber()));
    }

    public boolean addTruck(DTOTruck truck) {
        return this.data.addTruck(new Truck(truck.getSerialNumber(), truck.getModel(), truck.getWeight(), truck.getMaxAllowedWeight()));
    }

    public boolean addProduct(DTOProduct product) {
        return this.data.addProduct(new Product(product.getName(), product.getCN(), product.getWeight(), product.getAmount()), product.getAdress());
    }

    public DTODelivery arrangeDelivery(String source, String destinationsAndProducts) {
        DTODelivery delivery;
        Delivery tmpDelivery = new Delivery();
        LinkedList<Address> destinations = new LinkedList<Address>();
        ConcurrentHashMap<Address, LinkedList<Product>> DAP = new ConcurrentHashMap<Address, LinkedList<Product>>();
        int totalWeight = 0;

        Address s = this.data.getAdress(source);

        if (s == null)
            return null;

        tmpDelivery.setSource(this.data.getAdress(source));


        while (destinationsAndProducts.compareTo("") != 0) {
            Address destination = this.data.getAdress(destinationsAndProducts.substring(0, destinationsAndProducts.indexOf(",")));

            if (destination == null) {
                return null;
            }

            if (DAP.get(destination) == null) {
                LinkedList<Product> products = new LinkedList<Product>();
                DAP.put(destination, products);
            }

            Product tmpProduct = new Product(destinationsAndProducts.substring(0, destinationsAndProducts.indexOf(",")),
                    destinationsAndProducts.substring(0, destinationsAndProducts.indexOf(",")),
                    Integer.parseInt(destinationsAndProducts.substring(0, destinationsAndProducts.indexOf(","))),
                    Integer.parseInt(destinationsAndProducts.substring(0, destinationsAndProducts.indexOf(","))));
            DAP.get(destination).add(tmpProduct);
            totalWeight += tmpProduct.getWeight();
        }
//
//        String license;
//
//        if(totalWeight < 12)
//            license = "C";
//        else if (totalWeight < 15)
//            license = "C12";
//        else
//            license = "C15";

        Driver driver = this.data.getDriverByLicense(totalWeight);

        if (driver == null)
            return null;

//        driver.setDestinationsAndProducts(DAP);
//        tmpDelivery.setDriver(driver);
//
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//            Date date = new Date();
//
//
        tmpDelivery.setDate(new Date());
        tmpDelivery.setLaunchTime(formatter.format(new Date()));

        this.data.addDelivery(tmpDelivery);

        return new DTODelivery(tmpDelivery);
    }

    public String getDriversData() {
        String drivers = "";

        for(Driver d : this.data.getDrivers()){
            drivers += "Driver name : " + d.getName() + "\n" +
                    "Drivers ID : " + d.getId() + "\n" +
                    "Available : " + (d.isAvailable() ? "yes" : "no") + "\n" +
                    "License : " + d.getLicense() + "\n";
        }

        return drivers;
    }

    public String getTrucksData() {
        String trucks = "";

        for(Truck t : this.data.getTrucks()) {
            trucks += "Truck serial number : " + t.getSerialNumber() + "\n" +
                    "Model : " + t.getModel() + "\n" +
                    "Weight : " + t.getWeight() + "\n" +
                    "Max allowed weight : " + t.getMaxAllowedWeight() + "\n";
        }

        return trucks;
    }

    public String getAdresses() {
        String adresses = "";

        for(Address a : this.data.getAddresses()) {
            adresses += "Address's location : " + a.getLocation().getLocation() + "\n" +
                    "Contact name : " + a.getContactName() + "\n" +
                    "Phone number : " + a.getPhoneNumber() + "\n";
        }

        return adresses;
    }


}
