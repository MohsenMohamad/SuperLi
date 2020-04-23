import BusinessLayer.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemRecordTest {

    @Test
    public void moveToShelf() {
        ItemRecord ir = new ItemRecord("milk Tnova 3%",3,1,3,4,1,"tnova");
        ir.addItem(new Item(1, new java.sql.Date(2020-1900,4-1,19)));
        ir.addItem(new Item(2, new java.sql.Date(2020-1900,4-1,19)));
        ir.addItem(new Item(3, new java.sql.Date(2020-1900,4-1,20)));
        ir.addItem(new Item(4, new java.sql.Date(2020-1900,4-1,20)));
        ir.moveToShelf(1);
        assertEquals(4,ir.getShelfAmount());
        ir.moveToShelf(1);
        assertNotEquals(5,ir.getShelfAmount());
    }

    @Test
    public void subtractFromShelf() {
        ItemRecord ir = new ItemRecord("milk Tnova 3%",0,1,3,4,1,"tnova");
        ir.addItem(new Item(1, new java.sql.Date(2020-1900,4-1,19)));
        ir.addItem(new Item(2, new java.sql.Date(2020-1900,4-1,19)));
        ir.addItem(new Item(3, new java.sql.Date(2020-1900,4-1,20)));
        ir.addItem(new Item(4, new java.sql.Date(2020-1900,4-1,20)));
        ir.subtractFromShelf(3);
        assertEquals(0,ir.getShelfAmount());
        ir.subtractFromShelf(1);
        assertNotEquals(-1,ir.getShelfAmount());
    }
}