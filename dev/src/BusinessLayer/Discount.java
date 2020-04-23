package BusinessLayer;

import java.util.Date;

public abstract class Discount {

    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private int percentage;

    public Discount(java.sql.Date start, java.sql.Date end, int perc){
        startDate = start;
        endDate = end;
        percentage = perc;
    }

    public abstract boolean validCategoryDiscount(String name);

    public abstract String withDiscount();

    public int getPercentage() {
        return percentage;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public abstract boolean validItemDiscount(String name);
}
