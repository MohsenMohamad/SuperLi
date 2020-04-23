import BusinessLayer.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryDiscountTest {

    @Test
    public void validCategoryDiscount() {
        Category category1 = new Category(Category.CategoryRole.MainCategory,"Drinks");
        CategoryDiscount cd1 = new CategoryDiscount(category1,
                new java.sql.Date(2020-1900,4-1,20),
                new java.sql.Date(2020-1900,5-1,20),20);
        assertTrue("valid disacount",cd1.validCategoryDiscount("Drinks"));
    }

    @Test
    public void validItemDiscount() {
        Category category1 = new Category(Category.CategoryRole.MainCategory,"Drinks");
        CategoryDiscount cd1 = new CategoryDiscount(category1,
                new java.sql.Date(2020-1900,4-1,20),
                new java.sql.Date(2020-1900,5-1,20),20);
        assertFalse("valid disacount",cd1.validItemDiscount("Drinks"));
    }
}