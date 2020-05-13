package BusinessLayer;

import DAL.DAL;
import DTOs.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BLService {
    private final DAL dal;


    public BLService() {
        this.dal = new DAL();
    }

// ---------------------------------------------  Workers -------------------------------------------//

    public Map<Integer, Worker> getAllWorkers() {
        return null;
    }
// ---------------------------------------------  Trucks -------------------------------------------//
    public List<Truck> getAllTrucks()
    {
        return null;
    }

    public String addTruck(String serialNumber, String model, int weight, int maxAllowedWeight){

        String result;

        // added successfully

        result = "Truck has been added successfully!";

        return result;

    }

    public Truck getTruck(String serial_number)
    {

       return null;
    }

    public String removeTruck(String serialNumber)
    {
        String result;

        // removed successfully

        result = "Truck has been removed successfully!";

        return result;

    }





/*
        public boolean removeWorker(int id)
    {
        return (workers_map.remove(id)!=null);
    }

        public void addLog(String log){
        this.logs.add(log);
    }


     public List<Pair<Day , ShiftTime>> availableHours()
    {
        List<Pair<Day,ShiftTime>> available_hours = new LinkedList<>();
        for(Pair<Day , ShiftTime> p : schedule.keySet())
        {
            if(schedule.get(p))
                available_hours.add(p);
        }
        return available_hours;
    }

     public boolean addToWorkingTeam(Worker worker, WorkPolicy.WorkingType workingType) {

        if (!worker.isAvailable(shift_date, shift_time) || !worker.getType().contains(workingType))
            return false;

        worker.work(this);
        if (!work_team.containsKey(workingType))
            work_team.put(workingType, new LinkedList<>());

        work_team.get(workingType).add(worker);
        return true;
    }


    public boolean isAvailable(Date date, Shift.ShiftTime shiftTime,String license) {
        return (this.isAvailable(date , shiftTime) && this.license==license);
    }


    public boolean addAdress(Address address){
        return false;
    }

     public boolean addShift(Shift shift)
    {
        for(Shift s: shifts)
        {
            if(s.getShiftDate().equals(shift.getShiftDate()) &&  s.getShiftTime() == shift.getShiftTime())
            {
                return false;
            }
        }

        shifts.add(shift);
        return true;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public Boolean isAvailable(int shift_id)
    {
        for(Shift shift : shifts)
        {
            if(shift.getShiftId() == shift_id)
                return true;
        }

        return false;
    }

    public boolean addDriver(Driver driver){
        this.drivers.add(driver);
        return true;
    }


//    public boolean addLocation(DTOLocation location){
//        return this.data.addLocation(new Location(location.getLocation()));
//    }

    public boolean addAddress(Address address) {

        return false;

    }

    public boolean addProduct(Product product) {

        return false;
    }

    public Delivery arrangeDelivery(String source, String destinationsAndProducts) {
        Delivery delivery;
        Delivery tmpDelivery = new Delivery();
        LinkedList<Address> destinations = new LinkedList<Address>();
        ConcurrentHashMap<Address, LinkedList<Product>> DAP = new ConcurrentHashMap<Address, LinkedList<Product>>();
        int totalWeight = 0;

        Address s = this.dao.getAddress(source);

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


    public boolean addProoduct(Product product, String adress){
        for(Address a : this.addresses){
            if((a.getLocation().getLocation().compareTo(adress) == 0)){
                boolean flag = false;

                for(Product p : this.adressesAndProducts.get(adress))
                    if(p.getName().compareTo(product.getName()) == 0){
                        p.setAmount(p.getAmount() + product.getAmount());
                        flag = true;
                    }

                if(!flag)
                    this.adressesAndProducts.get(adress).add(product);
            }
        }

        return false;
    }

    public boolean addProduct(Product product, String adress) {
        boolean flag = false;

        for(Address a : this.addresses){
            if(a.getLocation().getLocation().compareTo(adress) == 0)
                flag = true;
        }

        if(!flag)
            return false;

        if(this.adressesAndProducts.get(adress) == null){
            LinkedList<Product> products = new LinkedList<>();
            this.adressesAndProducts.put(adress, products);
        }

        flag = false;

        for(Product p : this.adressesAndProducts.get(adress)){
            if(p.getCN().compareTo(product.getCN()) == 0) {
                flag = true;
                p.setAmount(p.getAmount() + product.getAmount());
            }
        }

        if(!flag)
            this.adressesAndProducts.get(adress).add(new Product(product.getName(), product.getCN(), product.getWeight(), product.getAmount()));

        return true;
    }

    public Address getAdress(String adress) {
        for(Address a : this.addresses){
            if(a.getLocation().compareTo(adress) == 0){
                return a;
            }
        }

        return null;
    }

    public Driver getDriverByLicense(int totalWeight) {
        for(Driver driver : this.drivers){
            if(isAllowed(driver.getLicense(), totalWeight) & driver.isAvailable())
                return driver;
        }

        return null;
    }

    private boolean isAllowed(String license, int weight){
        return weight < 12 |
                (weight < 15 & license.compareTo("C") != 0) |
                license.compareTo("C15") == 0;
    }

    public void addDelivery(Delivery delivery) {
        this.deliveries.add(delivery);
    }

    public boolean addWorker(Worker worker) {
        if (workers_map.containsKey(worker.getId()))
            return false;

        workers_map.put(worker.getId(),worker);
        return true;
    }

    public List<Worker> getAvailableWorkers(Date date, ShiftTime time, WorkingType job) {
        List<Worker> available_workers = new LinkedList<>();
        for (Worker w : workers_map.values()) {
            if (w.getType().contains(job) && w.isAvailable(date, time)) {
                available_workers.add(w);
            }
        }

        return available_workers;
    }

    public List<Worker> getAvailableWorkers(Date date, ShiftTime time)
    {
        List<Worker> available_workers = new LinkedList<>();
        for (Worker w : workers_map.values()) {
            if (w.isAvailable(date, time)) {
                available_workers.add(w);
            }
        }

        return available_workers;
    }

    public Worker getWorker(int worker_id)
    {
        if(!workers_map.containsKey(worker_id))
            return null;
        return workers_map.get(worker_id);
    }

    @Override
    public String toString()
    {
        String workers_string="";
        for (Worker worker : Workers.getInstance().getAllWorkers().values()) {
            workers_string = workers_string+worker.toString()+'\n';
        }
        workers_string = workers_string.substring(0,workers_string.length()-1);
        return workers_string;
    }
    public String AvilableWorkerstoString(Date date, ShiftTime shiftTime)
    {
        String workers_string="";
        for (Worker worker : Workers.getInstance().getAllWorkers().values()) {
            if(worker.isAvailable(date,shiftTime)){
                workers_string = workers_string+worker.toString()+'\n';
            }
        }
        workers_string = workers_string.substring(0,workers_string.length()-1);
        return workers_string;
    }


 */


}
