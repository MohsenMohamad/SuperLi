package PresentationLayer;

import BusinessLayer.InterfaceLayer.Service;

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
                    System.out.println(service.getItemAmountsByName(name));
                    System.out.println("Please enter new storage and shelf amounts");
                    String amounts = myScanner.nextLine();
                    System.out.println(service.setNewAmounts(name, amounts));
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
                case "5":

                    break;
                case "6":

                    break;
                case "7":

                    break;
                case "8":

                    break;
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
