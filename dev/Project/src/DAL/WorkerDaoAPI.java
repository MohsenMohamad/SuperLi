package DAL;

import DTOs.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class WorkerDaoAPI implements DaoAPI<Worker>
{

    private final Workers workers = Workers.getInstance();

    @Override
    public Optional<Worker> get(long id) {
        return Optional.ofNullable(workers.getWorker(id));
    }

    @Override
    public List<Worker> getAll() {
        return new LinkedList<>(workers.getAllWorkers().values());
    }

    @Override
    public void save(Worker worker)
    {
        workers.addWorker(worker);

    }

    @Override
    public void update(Worker worker, String[] params)
    {
        // ?
    }

    @Override
    public void delete(Worker worker)
    {
        workers.removeWorker(worker.getId());
    }
}
