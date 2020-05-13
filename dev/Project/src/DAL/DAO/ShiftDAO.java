package DAL.DAO;

import DTOs.Shift;

import java.util.LinkedList;
import java.util.List;

public class ShiftDAO
{

    List<Shift> shifts = new LinkedList<>();

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
