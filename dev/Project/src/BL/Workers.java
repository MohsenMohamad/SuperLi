package BL;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;


// not-thread safe singleton design pattern
public class Workers
{

    private static Workers workers_instance= null;
    private List<Worker> workers_list;

    private Workers()
    {
        workers_list = new LinkedList<>();
    }

    public static Workers getInstance(){
        if(workers_instance == null){
            workers_instance = new Workers();
        }
        return workers_instance;
    }

    public boolean addWorker(Worker worker)
    {
        for(Worker w : workers_list)
        {
            if(worker.getId() == w.getId())
            {
                return false;
            }
        }
        workers_list.add(worker);
        return true;
    }

    public List<Worker> getAvailableWorkers(Day day, ShiftTime time ,WorkingType job)
    {
        List<Worker> available_workers = new LinkedList<>();
        for(Worker w : workers_list)
        {
            if(w.getType().contains(job) && w.isAvailable(new Pair<>(day , time)))
            {
                available_workers.add(w);
            }
        }

        return available_workers;
    }

}
