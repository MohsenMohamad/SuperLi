package BusinessLayer;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    private static Controller instance;
    private HashMap<String,ItemRecord> itemRecords;
    private List<Category> categories;
    private static int itemId;

    private Controller(){
        itemRecords = new HashMap<>();
        categories = new LinkedList<>();
        itemId = 0;
    }

    public static Controller getController(){
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void initializeItems() {
        ItemRecord itemRecord1 = new ItemRecord("milk tnova 3%",3,1,3,4,1,"tnova");
        itemRecord1.addItem(new Item(itemId++, new SimpleDateFormat("20200419")));
        itemRecord1.addItem(new Item(itemId++, new SimpleDateFormat("20200419")));
        itemRecord1.addItem(new Item(itemId++, new SimpleDateFormat("20200420")));
        itemRecord1.addItem(new Item(itemId++, new SimpleDateFormat("20200420")));
        itemRecords.put("milk tnova 3%",itemRecord1);

        ItemRecord itemRecord2 = new ItemRecord("white bread",2,2,3,5,2,"dganit");
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200519")));
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200519")));
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200520")));
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200520")));
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200520")));
        itemRecords.put("white bread",itemRecord2);

        ItemRecord itemRecord3 = new ItemRecord("coffee elite",2,0,2,2,3,"elite");
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200820")));
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200820")));
        itemRecords.put("coffee elite",itemRecord3);


    }

    public void initializeCategories() {
    }

    public String getItemAmountsByName(String name) {
        ItemRecord ir = itemRecords.get(name);
        if (ir == null){
            return "No such item in inventory";
        }
        return  ir.getAmounts();
    }

    public String setNewAmounts(String name, String amounts) {
        ItemRecord ir = itemRecords.get(name);
        if(amounts.contains("-"))
            return "Can't set amounts to a negative number";
        String[] split = amounts.split("\\s+");
        try {
            ir.setStorageAmount(Integer.parseInt(split[0]));
            ir.setShelfAmount(Integer.parseInt(split[1]));
            ir.setTotalAmount(Integer.parseInt(split[2]));
            return ir.getAmounts();

        } catch (Exception e) {
            return "Action failed due to invalid input";
        }
    }

    public String moveToShelf(String name, String amount) {
        ItemRecord ir = itemRecords.get(name);
        try {
            return ir.moveToShelf(Integer.parseInt(amount));
        } catch (Exception e) {
            return "Action failed due to invalid input";
        }
    }
}
