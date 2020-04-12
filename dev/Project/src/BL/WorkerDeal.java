package BL;

import java.util.List;

public class WorkerDeal {

    private static int count=0;
    private int worker_id=0;
    private String start_date;
    private int bank_address;
    private int salary;
    private List<String> work_conditions;    // ask what is a work condition ?


    public WorkerDeal(String start_date, int salary,int bank_address, List<String> work_conditions) {
        this.start_date = start_date;
        this.salary = salary;
        this.work_conditions = work_conditions;
        this.bank_address = bank_address;
        this.worker_id = count++;
    }


    public String toString()
    {
        String deal = "worker id : "+worker_id+'\n';
        deal = deal + "start date : "+start_date+'\n';
        deal = deal + "bank address : "+bank_address+'\n';
        deal = deal+ "salary : "+salary+'\n';
        if(work_conditions!=null)
        {
            deal = deal + "working conditions : "+work_conditions.toString()+'\n';
        }
        return deal;
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
