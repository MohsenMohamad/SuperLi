package PresentationLayer;

import BusinessLayer.InterfaceLayer.Service;

import java.util.Scanner;

public class ActionsMenu {

    private static Service service = new Service();

    public static void printActionsMenu() {
        Scanner myScanner = new Scanner(System.in);
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
            if (input.equals("1")){
                System.out.println("Please enter item name");
                String name = myScanner.nextLine();
                System.out.println(service.getItemAmountsByName(name));
                System.out.println("Please enter new amounts");
                String amounts = myScanner.nextLine();
                System.out.println(service.setNewAmounts(name,amounts));
            }
            else if (input.equals("2")){
                System.out.println("Please enter item name");
                String name = myScanner.nextLine();
                System.out.println(service.getItemAmountsByName(name));
                System.out.println("Please enter amount to move");
                String amount = myScanner.nextLine();
                System.out.println(service.moveToShelf(name,amount));
            }
            else if (input.equals("3")){

            }
            else if (input.equals("4")){

            }
            else if (input.equals("5")){

            }
            else if (input.equals("6")){

            }
            else if (input.equals("7")){

            }
            else if (input.equals("8")){

            }
            else if (input.equals("9")){
                System.out.println("GOODBYE");
                break;
            }
            else
                System.out.println("Please enter a valid number from the menu");
        }
    }
}
