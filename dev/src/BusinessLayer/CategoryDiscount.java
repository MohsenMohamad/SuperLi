package BusinessLayer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CategoryDiscount extends Discount {

    private Category category;

    public CategoryDiscount(Category cat, Date start, Date end, int perc) {
        super(start,end,perc);
        category = cat;
    }

    @Override
    public boolean validCategoryDiscount(String name) {
        if (name.equals(category.getName())) {
            Date now = new Date();
            Date start = new Date(String.valueOf(getStartDate()));
            Date end = new Date(String.valueOf(getEndDate()));
            if(start.after(now) && now.before(end) )
                return true;
        }
        return false;
    }

    @Override
    public String withDiscount() {
        return category.getName() +" : "+getPercentage()+"% Discount";
    }

    @Override
    public boolean validItemDiscount(String name) {
        return false;
    }
}
