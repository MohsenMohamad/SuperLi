package PresentationLayer;

import BusinessLayer.InterfaceLayer.Service;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ActionsMenu {

    private static Service service = new Service();

    public static void printActionsMenu() {
        Scanner myScanner = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("Please choose an action : \n" +
                    "1.Change item amount \n" +
                    "2.Move from storage to shelf \n" +
                    "3.Subtract from shelf \n" +
                    "4.Print inventory report\n" +
                    "5.Enter defected item\n" +
                    "6.Print defective report\n" +
                    "7.Enter new discount\n" +
                    "8.Enter new price\n"+
                    "9.Exit");
            String input = myScanner.nextLine();
            switch (input) {
                case "1": {
                    System.out.println("Please enter item name");
                    String name = myScanner.nextLine();
                    String amount = service.getItemAmountsByName(name);
                    System.out.println(amount);
                    if(!amount.equals("No such item in inventory")) {
                        System.out.println("Please enter new storage and shelf amounts");
                        String amounts = myScanner.nextLine();
                        System.out.println(service.setNewAmounts(name, amounts));
                    }
                    break;
                }
                case "2": {
                    System.out.println("Please enter item name");
                    String name = myScanner.nextLine();
                    System.out.println(service.getItemAmountsByName(name));
                    System.out.println("Please enter amount to move");
                    String amount = myScanner.nextLine();
                    System.out.println(service.moveToShelf(name, amount));
                    break;
                }
                case "3": {
                    System.out.println("Please enter item name");
                    String name = myScanner.nextLine();
                    System.out.println(service.getItemAmountsByName(name));
                    System.out.println("Please enter amount to subtract");
                    String amount = myScanner.nextLine();
                    System.out.println(service.subtract(name, amount));
                    break;
                }
                case "4": {
                    System.out.println("Please enter categories or 'all'");
                    String names = myScanner.nextLine();
                    System.out.println(service.getInventoryReport(names));
                    break;
                }
                case "5": {
                    System.out.println("Please enter defected item's name");
                    String name = myScanner.nextLine();
                    System.out.println("Please enter defected item's ID");
                    String id = myScanner.nextLine();
                    System.out.println(service.setDefectedItem(name, id));
                    break;
                }

                case "6": {
                    System.out.println("Enter report's beginning date in the following format(dd/MM/yyyy)");
                    String begDate = myScanner.nextLine();
                    System.out.println("Enter report's end date in the following format(dd/MM/yyyy)");
                    String endDate = myScanner.nextLine();
                    System.out.println(service.printDefectedReport(begDate, endDate));

                    break;
                }
                case "7": {
                    System.out.println("1. Item discount \n" + "2. Category discount\n");
                    String discountType = myScanner.nextLine();
                    if (discountType.equals("1")) {                   //case item discount
                        System.out.println("Enter item name:");
                        String itemName = myScanner.nextLine();
                        System.out.println("Enter discount percentage:");
                        String percentage = myScanner.nextLine();
                        System.out.println("Enter beginning date in the following format(dd/MM/yyyy)");
                        String begDate = myScanner.nextLine();
                        System.out.println("Enter end date in the following format(dd/MM/yyyy)");
                        String endDate = myScanner.nextLine();

                        System.out.println(service.addNewItemDiscount(itemName, percentage, begDate, endDate));
                    } else if (discountType.equals("2")) {                      //case category discount
                        System.out.println("Enter category name:");
                        String categoryName = myScanner.nextLine();
                        System.out.println("Enter discount percentage:");
                        String percentage = myScanner.nextLine();
                        System.out.println("Enter beginning date in the following format(dd/MM/yyyy)");
                        String begDate = myScanner.nextLine();
                        System.out.println("Enter end date in the following format(dd/MM/yyyy)");
                        String endDate = myScanner.nextLine();

                        System.out.println(service.addNewCategoryDiscount(categoryName, percentage, begDate, endDate));
                    }
                    else {
                        System.out.println("Please enter valid discount type");
                    }
                    break;
                }

                case "8": {
                    System.out.println("Please enter item name:");
                    String name = myScanner.nextLine();
                    System.out.println("Enter new price:");
                    String price = myScanner.nextLine();
                    System.out.println(service.setNewPrice(name, price));
                    break;
                }

                case "9":
                    System.out.println("GOODBYE");
                    break label;
                default:
                    System.out.println("Please enter a valid number from the menu");
                    break;
            }
        }
    }

    public static void printWarning(String name, int totalAmount, int minAmount) {
        System.out.println("Warning : the item's '"+name+"' total amount is ("+totalAmount+") BELOW the minimum required ("+minAmount+")");
    }
}
