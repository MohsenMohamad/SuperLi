import BusinessLayer.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {

    Controller c = Controller.getController();

    @Test
    public void setDefectedItem() {
        ItemRecord ir = new ItemRecord("coca-cola",2,0,2,2,3,"coca-cola");
        ir.addItem(new Item(9, new java.sql.Date(2020-1900,8-1,20)));
        c.addItemRecord(ir);
        String result = c.setDefectedItem("coca-cola", 9);
        assertEquals("Defected item was added", result);
        assertTrue("success", c.isDefective("coca-cola", 9));
        String result2 = c.setDefectedItem("cocacola", 11);
        assertNotEquals("Defected item was added", result2);
    }

    @Test
    public void addItemDiscount() {
        c.initializeItems();
        c.addItemDiscount("white bread", 20, new java.sql.Date(2020 - 1900, 4 - 1, 20),
                new java.sql.Date(2020 - 1900, 5 - 1, 20));
        assertEquals(20, c.getItemDiscount("white bread"));
    }

    @Test
    public void addNewCategoryDiscount() {
        c.initializeCategories();
        c.addNewCategoryDiscount("Drinks", 20, new java.sql.Date(2020 - 1900, 4 - 1, 20),
                new java.sql.Date(2020 - 1900, 5 - 1, 20));
        assertEquals(20, c.getCategoryDiscount("Drinks"));
    }

    @Test
    public void setNewPrice() {
        String result1 = c.setNewPrice("coffee Elite", 12, 6);
        assertEquals("added successfully", result1);
        int priceResult = c.getPrice("coffee Elite");
        assertEquals(12, priceResult);
        String result2 = c.setNewPrice("coffee Elite", 10, 6);
        priceResult = c.getPrice("coffee Elite");
        assertEquals(10, priceResult);

    }

    @Test
    public void setNewAmounts() {
        c.setNewAmounts("white bread", "20 10");
        int shelf = c.getShelfAmount("white bread");
        int storage = c.getStorageAmount("white bread");
        int total = c.getTotalAmount("white bread");
        assertEquals(10, shelf);
        assertEquals(20, storage);
        assertEquals(30, total);
    }
}