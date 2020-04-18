package PresentationLayer;
import BusinessLayer.InterfaceLayer.Service;

import java.util.Scanner;

public class InitializeMenu {

    private static Service service = new Service();

    public static void printInitializeMenu(){
        Scanner myScanner = new Scanner(System.in);
        while(true) {
            System.out.println("Please choose an action : \n 1.Initialize system");
            if(!myScanner.nextLine().equals("1"))
                System.out.println("Please enter a valid number from the menu");
            else
                break;
        }
        service.initialize();
    }
}
