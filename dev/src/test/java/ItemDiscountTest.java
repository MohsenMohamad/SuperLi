import BusinessLayer.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemDiscountTest {

    @Test
    public void validItemDiscount() {
        ItemRecord itemRecord1 = new ItemRecord("white bread",3,2,3,5,2,"dganit");
        ItemDiscount id =new ItemDiscount(itemRecord1,
                new java.sql.Date(2020-1900,4-1,20),
                new java.sql.Date(2020-1900,5-1,20),15);
        assertTrue("valid disacount",id.validItemDiscount("white bread"));
    }
}