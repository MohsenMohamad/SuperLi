package BL;

import java.util.List;

public class WorkerDeal {

    private int worker_id;      // added foreign key
    private String start_date;
    private int bank_address;
    private int salary;
    private List<String> work_conditions;    // ask what is a work condition ?


    public WorkerDeal(String start_date, int salary, List<String> work_conditions) {
        this.start_date = start_date;
        this.salary = salary;
        this.work_conditions = work_conditions;
    }

    public String getStart_date() {
        return start_date;
    }

    public int getSalary() {
        return salary;
    }

    public List<String> getWork_conditions() {
        return work_conditions;
    }

    public int getWorker_id() {
        return worker_id;
    }
}
