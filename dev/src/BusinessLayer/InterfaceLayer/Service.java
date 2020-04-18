package BusinessLayer.InterfaceLayer;

import BusinessLayer.Controller;

public class Service {

    private Controller controller;

    public Service (){
        controller = Controller.getController();
    }
    public void initialize() {
        controller.initializeItems();
        controller.initializeCategories();
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
}
