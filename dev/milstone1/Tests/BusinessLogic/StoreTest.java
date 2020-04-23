package BusinessLogic;

import InterfaceLayer.InterfaceSupplier;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StoreTest {
    //@Before
    public Store preTest()
    {
        Map<Integer,String> Contacts_ID = new HashMap<Integer, String>();
        Map<Integer,Integer> Contacts_Number = new HashMap<Integer, Integer>();
        Store store = new Store("Avi@gmail.com");
        store.AddSuplier("Moti",1,"Poalim","branch",1234,"cash",Contacts_ID,Contacts_Number);

        LinkedList<String> days = new LinkedList<String>();
        days.add("Sunday");
        Map<Integer,String> Product_supplierName = new HashMap<Integer, String>();
        Product_supplierName.put(1,"Shoes");
        Map<Integer,Double> prices = new HashMap<Integer, Double>();
        prices.put(1,10.0);

        store.AddContract(1,true,days,true,Product_supplierName,prices);
        Map<Integer,Integer> products = new HashMap<Integer, Integer>();
        products.put(1,5);
        store.MakeOrder(1,products);
        return store;
    }


    @Test
    public void addSuplier() {
        Map<Integer,String> Contacts_ID = new HashMap<Integer, String>();
        Map<Integer,Integer> Contacts_Number = new HashMap<Integer, Integer>();
        Store store = new Store("Avi@gmail.com");
        String output = store.AddSuplier("Moti",1,"Poalim","branch",1234,"cash",Contacts_ID,Contacts_Number);
        int size = store.getList_of_Suplier().size();
        assertEquals((output.equals("Done")&&size==1),true);
    }

    @Test
    public void delete() {
        Map<Integer,String> Contacts_ID = new HashMap<Integer, String>();
        Map<Integer,Integer> Contacts_Number = new HashMap<Integer, Integer>();
        Store store = new Store("Avi@gmail.com");
        store.AddSuplier("Moti",1,"Poalim","branch",1234,"cash",Contacts_ID,Contacts_Number);
        String output = store.Delete(1);
        int size = store.getList_of_Suplier().size();
        assertEquals((output.equals("Done")&&size==0),true);
    }

    @Test
    public void makeOrder() {
        Order output = preTest().getList_of_Order().get(0);
        boolean temp = (output != null);
        assertEquals(temp,true);
    }

    @Test
    public void changOrderStatus() {
        Store store = preTest();
        String output = store.ChangOrderStatus(0);
        assertEquals(output, "Done");
    }

    @Test
    public void EditWrite() {
        Store store = preTest();

        Map<Integer,Integer> Product_amount = new HashMap<Integer, Integer>();
        Product_amount.put(1,2);
        Map<Integer,Double> product_assumption = new HashMap<Integer, Double>();
        product_assumption.put(1,5.0);
        store.AddWrite(1,Product_amount,product_assumption);

        Map<Integer,Integer> other_amount = new HashMap<Integer, Integer>();
        other_amount.put(1,10);
        String output = store.EditWrite(1,other_amount,product_assumption);
        assertEquals(output,"Done");
    }

    @Test
    public void EditContract() {
        Store store = preTest();
        LinkedList<String> days = new LinkedList<String>();
        days.add("Sunday");
        days.add("Monday");
        Map<Integer,String> Product_supplierName = new HashMap<Integer, String>();
        Product_supplierName.put(1,"Moti");
        Map<Integer,Double> prices = new HashMap<Integer, Double>();
        prices.put(1,10.0);
        String output = store.EditContract(1,true,days,true,Product_supplierName,prices);
        Supplier sup = store.getList_of_Suplier().get(0);
        Contract con = sup.getContract();
        boolean flag = false;
        if(con.getDayes().size()==2)
            flag = true;
        assertEquals((flag&&(output=="Done")),true);

    }
}