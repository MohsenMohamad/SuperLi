package DAL.DAO;

import DAL.DAL;
import DTOs.History;
import DTOs.Shift;

import java.util.List;

public class ShiftDAO
{

    List<Shift> shifts = History.getInstance().getShifts();

    public List<Shift> getAll()
    {
        return shifts;
    }

    public void save(Shift shift)
    {
        shifts.add(shift);
    }

    public void update(Shift shift, String[] params)
    {
        //

    }

    public void delete(Shift shift)
    {
        shifts.remove(shift);
    }
}
