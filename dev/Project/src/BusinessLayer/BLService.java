package BusinessLayer;

import DAL.DAL;

public class BLService {
    private DAL dal;
    private Data data;

    public BLService() {
        this.dal = new DAL();
        this.data = new Data();
    }
/*
    public boolean addDriver(Driver driver) {

    }

//    public boolean addLocation(DTOLocation location){
//        return this.data.addLocation(new Location(location.getLocation()));
//    }

    public boolean addAddress(Address address) {

    }

    public boolean addTruck(Truck truck) {
    }

    public boolean addProduct(Product product) {
    }

    public Delivery arrangeDelivery(String source, String destinationsAndProducts) {
        Delivery delivery;
        Delivery tmpDelivery = new Delivery();
        LinkedList<Address> destinations = new LinkedList<Address>();
        ConcurrentHashMap<Address, LinkedList<Product>> DAP = new ConcurrentHashMap<Address, LinkedList<Product>>();
        int totalWeight = 0;

    //    Address s = this.dao.getAddress(source);

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

 */


}
