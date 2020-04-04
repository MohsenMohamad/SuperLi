package BL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Worker {

    private String name;
    private int id;
    private int bank_address;
    private WorkingType type;
    private ConcurrentHashMap<String, Boolean> job_map;  // may change the ds
    private ConcurrentHashMap<String, Boolean> constraints;
    private ConcurrentHashMap<String, String> time_available;
    private WorkerDeal contract;

    public Worker(String name, int id, int bank_address, ConcurrentHashMap<String, Boolean> job_map, ConcurrentHashMap<String, Boolean> constraints, ConcurrentHashMap<String, String> time_available, WorkerDeal contract) {
        this.name = name;
        this.id = id;
        this.bank_address = bank_address;
        this.job_map = job_map;
        this.constraints = constraints;
        this.time_available = time_available;
        this.contract = contract;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getBank_address() {
        return bank_address;
    }

    public ConcurrentHashMap<String, Boolean> getJob_map() {
        return job_map;
    }

    public ConcurrentHashMap<String, Boolean> getConstraints() {
        return constraints;
    }

    public ConcurrentHashMap<String, String> getTime_available() {
        return time_available;
    }

    public WorkerDeal getContract() {
        return contract;
    }
}
