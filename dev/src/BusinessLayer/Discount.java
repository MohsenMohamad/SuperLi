package BusinessLayer;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Discount {

    private Date startDate;
    private Date endDate;
    private int percentage;

    public Discount(Date start, Date end, int perc){
        startDate = start;
        endDate = end;
        percentage = perc;
    }

    public abstract boolean validCategoryDiscount(String name);

    public abstract String withDiscount();

    public int getPercentage() {
        return percentage;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public abstract boolean validItemDiscount(String name);
}
