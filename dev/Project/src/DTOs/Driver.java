package DTOs;

import javafx.util.Pair;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Driver extends Worker {
    private String license;
    private ConcurrentHashMap<Address, LinkedList<Product>> destinationsAndProducts;

    public Driver(int id, String name, List<WorkPolicy.WorkingType> type, Map<Pair<DayOfWeek, Shift.ShiftTime>, Boolean> schedule, WorkerDeal contract, String license) {
        super(id, name, type, schedule, contract);
        this.destinationsAndProducts = new ConcurrentHashMap<Address, LinkedList<Product>>();
        this.license = license;

    }

    public String getLicense() {
        return license;
    }

    public ConcurrentHashMap<Address, LinkedList<Product>> getDestinationsAndProducts() {
        return destinationsAndProducts;
    }

    public void setDestinationsAndProducts(ConcurrentHashMap<Address, LinkedList<Product>> destinationsAndProducts) {
        this.destinationsAndProducts = destinationsAndProducts;
    }

    public void setLicense(String license) {
        this.license = license;
    }

}