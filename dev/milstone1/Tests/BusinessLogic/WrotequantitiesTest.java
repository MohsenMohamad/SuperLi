package BusinessLogic;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class WrotequantitiesTest {

    @Test
    public void getSale() {
        Map<Integer,String> Contacts_ID = new HashMap<Integer, String>();
        Map<Integer,Integer> Contacts_Number = new HashMap<Integer, Integer>();
        Store store = new Store("Avi@gmail.com");
        store.AddSuplier("Moti",1,"Poalim","branch",1234,"cash",Contacts_ID,Contacts_Number);

        LinkedList<String> days = new LinkedList<String>();
        days.add("Sunday");
        Map<Integer,String> Product_supplierName = new HashMap<Integer, String>();
        Product_supplierName.put(1,"Moti");
        Map<Integer,Double> prices = new HashMap<Integer, Double>();
        prices.put(1,10.0);
        store.AddContract(1,true,days,true,Product_supplierName,prices);

        Map<Integer,Integer> Product_amount = new HashMap<Integer, Integer>();
        Product_amount.put(1,2);
        Map<Integer,Double> product_assumption = new HashMap<Integer, Double>();
        product_assumption.put(1,5.0);
        store.AddWrite(1,Product_amount,product_assumption);

        Supplier sup = store.getList_of_Suplier().get(0);
        Wrotequantities w = sup.getWorte();
        int sale = w.GetSale(1,2);
        assertEquals(sale,5);
    }
}