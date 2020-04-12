package BL;

import javafx.util.Pair;
import BL.Shift.Day;
import BL.Shift.ShiftTime;
import BL.WorkPolicy.WorkingType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


// not-thread safe singleton design pattern
public class Workers {

    private static Workers workers_instance = null;
    private Map<Integer, Worker> workers_map;

    private Workers() {
        workers_map = new HashMap<>();
    }

    public static Workers getInstance() {
        if (workers_instance == null) {
            workers_instance = new Workers();
        }
        return workers_instance;
    }

    public boolean addWorker(Worker worker) {
        if (workers_map.containsKey(worker.getId()))
            return false;

        workers_map.put(worker.getId(),worker);
        return true;
    }

    public List<Worker> getAvailableWorkers(Day day, ShiftTime time, WorkingType job) {
        List<Worker> available_workers = new LinkedList<>();
        for (Worker w : workers_map.values()) {
            if (w.getType().contains(job) && w.isAvailable(new Pair<>(day, time))) {
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

    public Map<Integer,Worker> getAllWorkers() {
        return workers_map;
    }
}
