package BusinessLayer;

import java.util.LinkedList;

public class Category {
    enum CategoryRole {MainCategory, SubCategory, SubSubCategory}
    private CategoryRole role;
    private String name;
    private LinkedList<ItemRecord> itemRecords;

    public Category(CategoryRole role, String name){
        this.role = role;
        this.name = name;
        itemRecords = new LinkedList<>();
    }
    public String getName() {
        return name;
    }

    public String items() {
        String items = "";
        for (ItemRecord record: itemRecords) {
            items = items + Controller.getController().itemForReport(record) + "\n";
        }
        if(items.length() > 2)
            return items.substring(0,items.length()-2);
        else
            return items;
    }

    public LinkedList<ItemRecord> getItemRecords() {
        return itemRecords;
    }

    public boolean isMain() {
        return role .equals(CategoryRole.MainCategory);
    }

    public boolean isSub() {
        return role.equals(CategoryRole.SubCategory);
    }

    public boolean isSubSub(){
        return role.equals(CategoryRole.SubSubCategory);
    }

    public CategoryRole getRole() {
        return role;
    }

    public void addItem(ItemRecord itemRecord) {
        itemRecords.add(itemRecord);
    }

}
