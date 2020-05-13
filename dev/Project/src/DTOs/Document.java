package DTOs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Document{
    private int TruckWeight;
    private List<String> logs;
    private Map<String, List<Product>> deliveryGoods;

    public Document(){
    }

    public void setLogs(LinkedList<String> logs) {
        this.logs = logs;
    }

    public List<String> getLogs() {
        return logs;
    }

    public int getTruckWeight() {
        return TruckWeight;
    }

    public void setTruckWeight(int truckWeight) {
        TruckWeight = truckWeight;
    }

}