package BusinessLayer;

import BusinessLayer.InterfaceLayer.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Controller {

    private static Controller instance;
    private HashMap<String,ItemRecord> itemRecords;
    private HashMap<String,Category> categories;
    private LinkedList<Discount> discounts;
    private static int itemId;

    private Controller(){
        itemRecords = new HashMap<>();
        categories = new HashMap<>();
        discounts = new LinkedList<>();
        itemId = 0;
    }

    public static Controller getController(){
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void initializeItems() {
        ItemRecord itemRecord1 = new ItemRecord("milk Tnova 3%",3,1,3,4,1,"tnova");
        itemRecord1.addItem(new Item(itemId++, new SimpleDateFormat("20200419")));
        itemRecord1.addItem(new Item(itemId++, new SimpleDateFormat("20200419")));
        itemRecord1.addItem(new Item(itemId++, new SimpleDateFormat("20200420")));
        itemRecord1.addItem(new Item(itemId++, new SimpleDateFormat("20200420")));
        itemRecords.put("milk Tnova 3%",itemRecord1);

        ItemRecord itemRecord2 = new ItemRecord("white bread",3,2,3,5,2,"dganit");
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200519")));
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200519")));
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200520")));
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200520")));
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200520")));
        itemRecords.put("white bread",itemRecord2);

        ItemRecord itemRecord3 = new ItemRecord("coffee Elite",2,0,2,2,3,"elite");
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200820")));
        itemRecord2.addItem(new Item(itemId++, new SimpleDateFormat("20200820")));
        itemRecords.put("coffee Elite",itemRecord3);

        itemRecord1.addPrice(new Price(80,120));
        itemRecord2.addPrice(new Price(90,130));
        itemRecord3.addPrice(new Price(100,135));

    }

    public void initializeCategories() {
        Category category1 = new Category(Category.CategoryRole.MainCategory,"Dairy");
        Category subCat1 = new Category(Category.CategoryRole.SubCategory,"Milk");
        Category subsubcat1 = new Category(Category.CategoryRole.SubSubCategory,"1 liter");
        addItemToCategory(itemRecords.get("milk Tnova 3%") ,category1);
        categories.put("Dairy",category1);

        Category category2 = new Category(Category.CategoryRole.MainCategory,"Bread and pastry");
        Category subcat2 = new Category(Category.CategoryRole.SubCategory,"Bread");
        Category subsubcat2 = new Category(Category.CategoryRole.SubSubCategory,"750 gr");
        addItemToCategory(itemRecords.get("white bread") ,category2);

        Category category3 = new Category(Category.CategoryRole.MainCategory,"Drinks");
        Category subcat3 = new Category(Category.CategoryRole.SubCategory,"Coffee powder");
        Category subsubcat3 = new Category(Category.CategoryRole.SubSubCategory,"500 gr");
        addItemToCategory(itemRecords.get("coffee Elite") ,category3);
    }

    private void addItemToCategory(ItemRecord itemRecord, Category cat) {
        for (Category category: categories.values()) {
            if(category.getRole().equals(cat
                    .getRole()) && category.getItemRecords().contains(itemRecord))
                return;
        }
        cat.addItem(itemRecord);
    }

    public void initializeDiscounts() {
    }

    public String addItemDiscount(String name, int percentage, Date beginDate, Date endDate){
        if(!(percentage>=1 && percentage<=100)){
            return "Discount percentage must be a number between 1-100";
        }
        for( ItemRecord ir: itemRecords.values()){
            if (ir.getName().equals(name)){         //checks if there is an item record with the given name
                ItemDiscount d = new ItemDiscount(ir, beginDate, endDate, percentage);
                Price p = ir.getCurrPrice();
                int beforeDiscount = p.getStorePrice();
                int afterDiscount = (beforeDiscount/100) * percentage;
                Price discountedPrice = new Price(p.getRetailPrice() , afterDiscount);
                ir.addPrice(discountedPrice);
                discounts.add(d);
                return "The discount was added succesfully";
            }
        }
        return "No such item";
    }


    public String addNewCategoryDiscount(String categoryName, int percentage, Date beginDate, Date endDate){
        if(!(percentage>=1 && percentage<=100)){
            return "Discount percentage must be a number between 1-100";
        }
        for( Category cat: categories.values()){
            if (cat.getName().equals(categoryName)){         //checks if there is a category with the given name
                    CategoryDiscount d = new CategoryDiscount(cat, beginDate, endDate, percentage);
                    for (ItemRecord itemRec: cat.getItemRecords() ){
                        Price p = itemRec.getCurrPrice();
                        int beforeDiscount = p.getStorePrice();
                        int afterDiscount = (beforeDiscount/100) * percentage;
                        Price discountedPrice = new Price(p.getRetailPrice() , afterDiscount);
                        itemRec.addPrice(discountedPrice);
                    }
                discounts.add(d);
                return "The discount was added succesfully";
            }
        }
        return "No such item";
    }

    public static int incrementAndGetItemID(){
        return itemId++;
    }

    public String setDefectedItem(String name, int id){
        for( ItemRecord ir: itemRecords.values()){
            if (ir.getName().equals(name)){         //checks if there is an item record with the given name
                LinkedList<Item> itemsList = ir.getItems();
                for (Item item: itemsList){
                    if(item.getId()==id){
                        item.setDefective(true);
                        return  "Defected item was added";
                    }
                }
                return "No such item ID";
            }
        }
        return "No such item";
    }

    public String setNewPrice(String name, int price){
        for( ItemRecord ir: itemRecords.values()) {
            if (ir.getName().equals(name)) {         //checks if there is an item record with the given name
                Price p = ir.getCurrPrice();
                Price newPr = new Price(p.getRetailPrice() , price);
                ir.addPrice(newPr);
            }
        }
        return "No such item";
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
            int storage = Integer.parseInt(split[0]);
            int shelf = Integer.parseInt(split[1]);
            int total = storage + shelf;
            ir.setStorageAmount(storage);
            ir.setShelfAmount(shelf);
            ir.setTotalAmount(total);
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

    public String subtract(String name, String amount) {
        ItemRecord ir = itemRecords.get(name);
        try {
            return ir.subtractFromShelf(Integer.parseInt(amount));
        } catch (Exception e) {
            return "Action failed due to invalid input";
        }
    }

    public String getInventoryReport(String category) {
        Category category1 = categories.get(category);
        if (category1 == null){
            return "No such category";
        }
        for (Discount discount: discounts) {
            if(discount.validCategoryDiscount(category))
                return discount.withDiscount() + category1.items();
        }
        return category+" : \n"+ category1.items();
    }

    public String itemForReport(ItemRecord record) {
        String itemStr = record.getName() + " : shelf amount " + record.getShelfAmount() + " storage amount "+ record.getStorageAmount()+" ";
        String main = "main category ";
        String sub = "sub category ";
        String subsub = "sub sub category" ;
        for (Category category:categories.values()) {

            if(category.getItemRecords().contains(record)){
                if(category.isMain())
                    main = main + category.getName();
                else if(category.isSub())
                    sub = sub + category.getName();
                else if(category.isSubSub())
                    subsub = subsub + category.getName();
            }
        }
        itemStr += main+" "+sub+" "+subsub+" "+record.getPrices();
        for (Discount discount: discounts) {
            if(discount.validItemDiscount(record.getName()))
                itemStr += discount.withDiscount();
        }
        return itemStr + "\n";

    }

    public String getAllInventoryReport() {
        String report = "";
        for (Category category:categories.values()) {
            report = report + getInventoryReport(category.getName());
        }
        return report;
    }

    public void sendWarning(ItemRecord itemRecord) {
        Service.sendWarning(itemRecord.getName(),itemRecord.getTotalAmount(),itemRecord.getMinAmount());
    }
}
