package BusinessLayer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemDiscount extends Discount {

    private ItemRecord itemRecord;

    public ItemDiscount(SimpleDateFormat start, SimpleDateFormat end, int perc, ItemRecord itemRecord) {
        super(start, end, perc);
        this.itemRecord = itemRecord;
    }

    @Override
    public boolean validCategoryDiscount(String name) {
        return false;
    }

    @Override
    public String withDiscount() {
        return getPercentage()+"% discount until "+ getEndDate();
    }

    @Override
    public boolean validItemDiscount(String name) {
        if (name.equals(itemRecord.getName())) {
            Date now = new Date();
            Date start = new Date(String.valueOf(getStartDate()));
            Date end = new Date(String.valueOf(getEndDate()));
            if(start.after(now) && now.before(end) )
                return true;
        }
        return false;
    }
}
