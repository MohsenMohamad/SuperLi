package BusinessLayer;

import java.text.SimpleDateFormat;

public abstract class Discount {

    private SimpleDateFormat startDate;
    private SimpleDateFormat endDate;
    private int percentage;

    public Discount( SimpleDateFormat start, SimpleDateFormat end, int perc){
        startDate = start;
        endDate = end;
        percentage = perc;
    }

    public abstract boolean validCategoryDiscount(String name);

    public abstract String withDiscount();

    public int getPercentage() {
        return percentage;
    }

    public SimpleDateFormat getEndDate() {
        return endDate;
    }

    public SimpleDateFormat getStartDate() {
        return startDate;
    }

    public abstract boolean validItemDiscount(String name);
}
