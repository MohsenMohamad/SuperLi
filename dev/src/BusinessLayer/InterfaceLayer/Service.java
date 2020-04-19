package BusinessLayer.InterfaceLayer;

import BusinessLayer.Controller;
import PresentationLayer.ActionsMenu;

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
