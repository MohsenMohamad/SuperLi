package BusinessLayer;

import java.util.LinkedList;

public class ItemRecord {

    private String name;
    private int minAmount;
    private int storageAmount;
    private int shelfAmount;
    private int totalAmount;
    private int shelfNumber;
    private String manufacture;
    private LinkedList<Item> items;
    private LinkedList<Price> prices; //first is current

    public ItemRecord(String name, int minAmount, int storageAmount, int shelfAmount, int totalAmount, int shelfNumber, String manufacture) {
        this.name = name;
        this.minAmount = minAmount;
        this.storageAmount = storageAmount;
        this.shelfAmount = shelfAmount;
        this.totalAmount = totalAmount;
        this.shelfNumber = shelfNumber;
        this.manufacture = manufacture;
        items = new LinkedList<>();
        prices = new LinkedList<>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void addPrice(Price price){
        prices.addFirst(price);
    }

    public String getAmounts() {
        return "storage amount : "+ storageAmount + "\nshelf amount : "+ shelfAmount +"\ntotal amount : "+totalAmount;
    }

    public void setStorageAmount(int parseInt) {
        storageAmount = parseInt;
    }

    public void setShelfAmount(int parseInt) {
        shelfAmount = parseInt;
    }

    public void setTotalAmount(int parseInt) {
        totalAmount = parseInt;
    }

    public String moveToShelf(int parseInt) {
        if(storageAmount < parseInt)
            return "Not enough in storage";
        storageAmount -= parseInt;
        shelfAmount += parseInt;
        return "New storage amount : "+ storageAmount + "\nNew shelf amount : "+ shelfAmount;
    }
}
