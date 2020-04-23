package BusinessLayer.InterfaceLayer;

import BusinessLayer.Controller;
import PresentationLayer.ActionsMenu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Service {

    private Controller controller;

    public Service (){
        controller = Controller.getController();
    }

    public static void sendWarning(String name, int totalAmount, int minAmount) {
        ActionsMenu.printWarning(name,totalAmount,minAmount);
    }

    public void initialize() {
        controller.initializeItems();
        controller.initializeCategories();
        controller.initializeDiscounts();
    }

    public String getItemAmountsByName(String name) {
        return controller.getItemAmountsByName(name);
    }

    public String setNewAmounts(String name, String amounts) {
        return controller.setNewAmounts(name,amounts);
    }

    public String moveToShelf(String name, String amount) {
        return controller.moveToShelf(name,amount);
    }

    public String subtract(String name, String amount) {
        return controller.subtract(name,amount);
    }

    public String setDefectedItem(String  name, String id){
        try {
            int ID = Integer.parseInt(id);
            return controller.setDefectedItem(name, ID);
        }
        catch (Exception e){
            return "Item's ID must be a number";
        }
    }

    public String addNewItemDiscount(String itemName, String percentage, String begDate, String enDate) {
        java.sql.Date beginDate, endDate;
        int perc;
        try{
            beginDate = new java.sql.Date(Integer.parseInt(begDate.substring(6))-1900,Integer.parseInt(begDate.substring(3,4))-1,Integer.parseInt(begDate.substring(0,1)));
            endDate = new java.sql.Date(Integer.parseInt(enDate.substring(6))-1900,Integer.parseInt(enDate.substring(3,4))-1,Integer.parseInt(enDate.substring(0,1)));
            perc = Integer.parseInt(percentage);
        }
        catch (Exception e){
            return "Please enter valid arguments";
        }
        return controller.addItemDiscount(itemName, perc, beginDate, endDate);
    }


    public String addNewCategoryDiscount(String categoryName, String percentage, String begDate, String enDate) {
        java.sql.Date beginDate, endDate;
        int perc;
        try{
            beginDate = new java.sql.Date(Integer.parseInt(begDate.substring(6))-1900,Integer.parseInt(begDate.substring(3,4))-1,Integer.parseInt(begDate.substring(0,1)));
            endDate = new java.sql.Date(Integer.parseInt(enDate.substring(6))-1900,Integer.parseInt(enDate.substring(3,4))-1,Integer.parseInt(enDate.substring(0,1)));
            perc = Integer.parseInt(percentage);
        }
        catch (Exception e){
            return "Please enter valid arguments";
        }
        return controller.addItemDiscount(categoryName, perc, beginDate, endDate);
    }

    public String setNewPrice(String name, String price){
        try {
            int newPrice = Integer.parseInt(price);
            if(newPrice<=0){
                return "Price must greater than 0";
            }
            return controller.setNewPrice(name, newPrice);
        }
        catch (Exception e){
            return "Item's price must be a number";
        }
    }


    public String getInventoryReport(String names) {
        String[] categories = names.split("\\s+");
        String report = "";
        if (categories[0].equals("all")) {
            report = controller.getAllInventoryReport();
        }
        else {
            for (int i = 0; i < categories.length; i++) {
                report = report + controller.getInventoryReport(categories[i]) + "\n";
            }
        }
        return report;
    }
}
