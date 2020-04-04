package BL;

import java.util.LinkedList;
import java.util.List;


// singleton design pattern
public class Workers
{

    private List<Worker> workers;
    private History history;

    public Workers()
    {
        workers = new LinkedList<>();
        history = new History();
    }


    public boolean addWorker(Worker worker)
    {
        for(Worker w : workers)
        {
            if(worker.getId() == w.getId())
            {
                return false;
            }
        }
        workers.add(worker);
        return true;
    }

    public boolean addShift(Shift shift)
    {
        return history.addShift(shift);
    }
}
