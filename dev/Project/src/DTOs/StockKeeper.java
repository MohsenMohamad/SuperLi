package DTOs;

import javafx.util.Pair;

import java.time.DayOfWeek;
import java.util.Map;

public class StockKeeper extends Worker {

    public StockKeeper(int id, String name, WorkPolicy.WorkingType working_type, Map<Pair<DayOfWeek, Shift.ShiftTime>, Boolean> schedule, WorkerDeal contract)
    {
        super(id,name,working_type,schedule,contract);
    }
}
