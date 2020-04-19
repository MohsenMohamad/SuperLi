package BusinessLayer;

import java.util.LinkedList;

public class Category {

    public String getName() {
        return name;
    }

    public String items() {
        String items = "";
        for (ItemRecord record: itemRecords) {
            items = items + Controller.getController().itemForReport(record) + "\n";
        }
        return items.substring(0,items.length()-2);
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

    enum CategoryRole {MainCategory, SubCategory, SubSubCategory}
    private CategoryRole role;
    private String name;
    private LinkedList<ItemRecord> itemRecords;

    public Category(CategoryRole role, String name){
        this.role = role;
        this.name = name;
        itemRecords = new LinkedList<>();
    }

    public void addItem(ItemRecord itemRecord) {
        itemRecords.add(itemRecord);
    }

}
