package PresentationLayer;

import InterfaceLayer.InterfaceContract;
import InterfaceLayer.InterfaceSupplier;
import InterfaceLayer.SystemManager;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Menu {
     private static SystemManager Sys = new SystemManager();

     public static void main(String[] args) {
         if (args.length>1 && args[1].equals("Arg")) {
           AddArguments();
        }
             boolean con = true;
             System.out.println("Welcome to 'super Le'!\n");
             while (con) {
                 System.out.println("What action would you like to take now?\n" +
                         "please enter the correct number\n"+
                         "1. Register\n"+
                         "2. Login\n"+
                         "3. Add a new supplier\n" +
                         "4. Add an agreement to supplier\n" +
                         "5. Adding \"Quantity Write\" to supplier\n" +
                         "6. Make an order\n" +
                         "7. Display the items in the super\n" +
                         "8. Display sll the supplier's details\n"+
                         "9. Update Order Status\n"+
                         "10. Edit supplier details\n"+
                         "11. Edit supplier's arrangement\n" +
                         "12. Edit \"Write Quantities\" of supplier\n" +
                         "13. Delete supplier\n" +
                         "14. Logout\n"+
                         "15. Exit");
                 Scanner Reder = new Scanner(System.in);
                 int Ask = Reder.nextInt();
                  if(Ask>0&&Ask<16) {
                      switch (Ask) {
                          case 1:
                              Register();
                              break;
                          case 2:
                              Login();
                              break;
                          case 3:
                              Add_Edit_Supplier(1);
                              break;
                          case 4:
                              Add_Edit_Agreement(1);
                              break;
                          case 5:
                              Add_Edit_Write(1);
                              break;
                          case 6:
                              MakeOrder();
                              break;
                          case 7:
                              DisplayItems();
                              break;
                          case 8:
                              DisplaySupplierDetails();
                              break;
                          case 9:
                              UpdateOrderStatus();
                              break;
                          case 10:
                              Add_Edit_Supplier(2);
                              break;
                          case 11:
                              Add_Edit_Agreement(2);
                              break;
                          case 12:
                              Add_Edit_Write(2);
                              break;
                          case 13:
                              DeleteSupplier();
                              break;
                          case 14:
                              Logout();
                              break;
                          case 15:
                              System.out.println("GoodBye!");
                              con = false;
                              break;
                      }
                  }
                  else{
                      System.out.println("Out-of-range selection, pleas try again");
                  }
             }
         }


    private static void AddArguments() {
         Sys.Register("Stor1@gmail.com","S1_superLi");
         Sys.Register("Stor2@gmail.com","S2_superLi");

         Sys.Login("Stor1@gmail.com","S1_superLi");
         Map<Integer,Integer> contactAli1=new ConcurrentHashMap<Integer, Integer>();
         contactAli1.put(2087564,0524536272);
         contactAli1.put(2453214,0523756223);
         Map<Integer,String> contactAli2=new ConcurrentHashMap<Integer, String>();
         contactAli2.put(2087564,"yoni");
         contactAli2.put(2453214,"roi");
         Sys.AddSupplier("Ali",51345,"Mizrahi","007",873645,"EFT",contactAli2,contactAli1);
         Map<Integer,Integer> contactIKEA1=new ConcurrentHashMap<Integer, Integer>();
         contactIKEA1.put(208231,0522136272);
         contactIKEA1.put(4283214,0523546253);
         Map<Integer,String> contactIKEA2=new ConcurrentHashMap<Integer, String>();
         contactIKEA2.put(208231,"Dov");
         contactIKEA2.put(4283214,"Leni");
             Sys.AddSupplier("IKEA",51321,"Ben-Leumi","027",432679,"EFT",contactIKEA2,contactIKEA1);
         Map<Integer,Integer> contacttXiaomi1=new ConcurrentHashMap<Integer, Integer>();
        contacttXiaomi1.put(45337561,05221336272);
         Map<Integer,String> contactXiaomi2=new ConcurrentHashMap<Integer, String>();
         contactXiaomi2.put(45337561,"Or");
         Sys.AddSupplier("Xiaomi",51328,"Leumi","3456",435678,"EFT",contactXiaomi2,contacttXiaomi1);

        Map<Integer,String> ProductAli1 =new ConcurrentHashMap<Integer, String>();
        ProductAli1.put(12313,"pajamas");
        ProductAli1.put(2314567,"slippers");
        Map<Integer,Double> ProductAli2 =new ConcurrentHashMap<Integer,Double>();
        ProductAli2.put(12313,499.9);
        ProductAli2.put(2314567,69.9);
        Sys.AddContract(51345,false,null,true,ProductAli1,ProductAli2);
        Map<Integer,String> ProductIKEA1 =new ConcurrentHashMap<Integer, String>();
        ProductIKEA1.put(143,"table");
        ProductIKEA1.put(5432,"bed");
        ProductIKEA1.put(22,"lamp");
        Map<Integer,Double> ProductIKEA2 =new ConcurrentHashMap<Integer,Double>();
        ProductIKEA2.put(143,499.9);
        ProductIKEA2.put(5432,1399.9);
        ProductIKEA2.put(22,139.9);
        Sys.AddContract(51321,false,null,true,ProductIKEA1,ProductIKEA2);
        Map<Integer,String> ProductXiaomi1 =new ConcurrentHashMap<Integer, String>();
        ProductAli1.put(142356,"smartPhone");
        ProductAli1.put(46288,"headphones");
        ProductAli1.put(4328,"Screen Protector");
        Map<Integer,Double> ProductXiaomi2 =new ConcurrentHashMap<Integer,Double>();
        ProductAli2.put(142356,2499.9);
        ProductAli2.put(46288,29.9);
        ProductAli2.put(4328,79.9);
        Sys.AddContract(51328,false,null,true,ProductXiaomi1,ProductXiaomi2);

        Map<Integer,Integer> WriteAli1=new ConcurrentHashMap<Integer, Integer>();
        WriteAli1.put(12313,200);
        Map<Integer,Double> WriteAli2=new ConcurrentHashMap<Integer, Double>();
        WriteAli2.put(12313,10.0);
        Sys.AddWrite(51328,WriteAli1,WriteAli2);
        Map<Integer,Integer> WriteIKEA1=new ConcurrentHashMap<Integer, Integer>();
        WriteIKEA1.put(5432,150);
        WriteIKEA1.put(22,200);
        Map<Integer,Double> WriteIKEA2=new ConcurrentHashMap<Integer, Double>();
        WriteIKEA2.put(5432,10.0);
        WriteIKEA2.put(22,10.0);
        Sys.AddWrite(51321, WriteIKEA1,WriteIKEA2);
        Map<Integer,Integer> WriteXiaomi1=new ConcurrentHashMap<Integer, Integer>();
        WriteXiaomi1.put(142356,50);
        WriteXiaomi1.put(4328,70);
        Map<Integer,Double> WriteXiaomi2=new ConcurrentHashMap<Integer, Double>();
        WriteXiaomi2.put(142356,10.0);
        WriteXiaomi2.put(4328,10.0);
        Sys.AddWrite(51328,WriteAli1,WriteAli2);

        Sys.Logout();
        Map<Integer,String> contactAli22=new ConcurrentHashMap<Integer, String>();
        contactAli22.put(2087564,"yoni");
        contactAli22.put(2453214,"roi");
        Map<Integer,Integer> contactAli11=new ConcurrentHashMap<Integer, Integer>();
        contactAli11.put(2087564,0524536272);
        contactAli11.put(2453214,0523756223);
        Sys.AddSupplier("Ali",51345,"Mizrahi","007",873645,"EFT",contactAli22,contactAli11);
        Map<Integer,String> contactIKEA22=new ConcurrentHashMap<Integer, String>();
        contactIKEA22.put(208231,"Dov");
        contactIKEA22.put(4283214,"Leni");
        Map<Integer,Integer> contactIKEA11=new ConcurrentHashMap<Integer, Integer>();
        contactIKEA11.put(208231,05221336272);
        contactIKEA11.put(4283214,0523546253);
        Sys.AddSupplier("IKEA",51321,"Ben-Leumi","027",432679,"EFT",contactIKEA22,contactIKEA11);

        Sys.Login("Stor2@gmail.com","S2_superLi");
        Map<Integer,String> ProductAli11 =new ConcurrentHashMap<Integer, String>();
        ProductAli11.put(1213,"blanket");
        ProductAli11.put(43567,"Pillow");
        Map<Integer,Double> ProductAli22 =new ConcurrentHashMap<Integer,Double>();
        ProductAli22.put(1213,89.9);
        ProductAli22.put(43567,1399.9);
        Sys.AddContract(51345,false,null,true,ProductAli11,ProductAli22);
        Map<Integer,String> ProductIKEA11 =new ConcurrentHashMap<Integer, String>();
        ProductIKEA11.put(223,"Armchair");
        ProductIKEA11.put(345,"Desk");
        ProductIKEA11.put(1687,"Chair");
        Map<Integer,Double> ProductIKEA22 =new ConcurrentHashMap<Integer,Double>();
        ProductIKEA22.put(223,499.9);
        ProductIKEA22.put(345,1399.9);
        ProductIKEA22.put(1687,139.9);
        Sys.AddContract(51321,false,null,true,ProductIKEA11,ProductIKEA22);

        Map<Integer,Double> WriteAli22=new ConcurrentHashMap<Integer, Double>();
        WriteAli22.put(1213,13.0);
        Map<Integer,Integer> WriteAli11=new ConcurrentHashMap<Integer, Integer>();
        WriteAli11.put(1213,200);
        Sys.AddWrite(51328,WriteAli11,WriteAli22);
        Map<Integer,Integer> WriteIKEA11=new ConcurrentHashMap<Integer, Integer>();
        WriteIKEA11.put(223,60);
        WriteIKEA11.put(345,90);
        Map<Integer,Double> WriteIKEA22=new ConcurrentHashMap<Integer, Double>();
        WriteIKEA22.put(223,12.0);
        WriteIKEA22.put(345,12.0);
        Sys.AddWrite(51321, WriteIKEA11,WriteIKEA22);

        Sys.Logout();
    }

    private static void Register() {
        Scanner Reder = new Scanner(System.in);
        String email;
        String password;
        System.out.println("Please enter your email");
        email = Reder.next();
        String Ex=Sys.CheckEmailExist(email);
        while(!Ex.equals("Not Exist")){
            System.out.println(Ex);
            System.out.println("Please enter email again");
            email = Reder.next();
            Ex=Sys.CheckEmailExist(email);
        }
        System.out.println("Please enter password");
        password = Reder.next();
        String Done=Sys.Register(email,password);
        if(Done.equals("Done")){
            System.out.println("The registration was successful");
        }
        else
            System.out.println(Done);
    }

    private static void Login() {
        Scanner Reder = new Scanner(System.in);
        String email;
        String password;
        System.out.println("Please enter your email");
        email = Reder.next();
        String Ex = Sys.CheckEmailExist(email);
        boolean continu = true;
        while (!Ex.equals("Exist")) {
            System.out.println(Ex);
            System.out.println("Do you want to return to the menu? y/n");
            String ans=Reder.next();
            if(ans.equals("y")){
                continu=false;
                break;
            }
            System.out.println("enter your email:");
            email = Reder.next();
            Ex = Sys.CheckEmailExist(email);
        }
        while (continu) {
            System.out.println("Please enter password");
            password = Reder.next();
            String Done = Sys.Login(email, password);
            while (!Done.equals("Done")) {
                System.out.println(Ex);
                System.out.println("enter the password again");
                password = Reder.next();
                Done = Sys.Login(email, password);
            }
            continu=false;
        }
            System.out.println(email + " welcome to your super!");

        }

    private static void Add_Edit_Supplier(int status) {
        boolean conect = Sys.CheckConected();
        if (!conect) {
            System.out.println("You need to connect before you take any action");
        }
        if (conect) {
            Scanner Reder = new Scanner(System.in);
            String name;
            int ID;
            int bankNumber;
            String Bank;
            String Branch;
            String payments;
            Map<Integer, String> Contacts_ID = new ConcurrentHashMap<Integer, String>();
            Map<Integer, Integer> Contacts_number = new ConcurrentHashMap<Integer, Integer>();

            System.out.println("Please enter the Supplier's ID");
            ID = Reder.nextInt();

            boolean contiue = true;
            boolean MoreContact = true;

            if (status == 2) {
                String exist = Sys.CheckSuplierExist(ID);
                while (!exist.equals("Done")) {
                    System.out.println(exist);
                    System.out.println("Do you want to continue and enter the supplier Id again? y/n ");
                    String ans = Reder.next();
                    if (ans.equals("y")) {
                        System.out.println("Please enter the Supplier's ID");
                        ID = Reder.nextInt();
                        exist = Sys.CheckSuplierExist(ID);
                    } else {
                        {
                            contiue = false;
                            exist = "Done";
                        }
                    }
                }
            }
            if (contiue) {
                System.out.println("Please enter the Supplier's name");
                name = Reder.next();
                System.out.println("Please enter the Supplier's Bank");
                Bank = Reder.next();
                System.out.println("Please enter the Supplier's Branch's Bank");
                Branch = Reder.next();
                System.out.println("Please enter the Supplier's bankNumber");
                bankNumber = Reder.nextInt();
                System.out.println("Please enter the Supplier's payments");
                payments = Reder.next();

                while (MoreContact) {
                    String ContactName;
                    int ContactId;
                    System.out.println("Please enter the Contact's name");
                    ContactName = Reder.next();
                    System.out.println("Please enter the Contact's Id");
                    ContactId = Reder.nextInt();
                    Contacts_ID.put(ContactId, ContactName);
                    int PhoneNumber;
                    System.out.println("Please enter the Contact's Phone number");
                    PhoneNumber = Reder.nextInt();
                    Contacts_number.put(ContactId, PhoneNumber);
                    String ans;
                    System.out.println("Do you have more contact? y/n");
                    ans = Reder.next();
                    if (ans.equals("n")) {
                        MoreContact = false;
                    }
                }
                String Done;
                if (status == 1) {
                    Done = Sys.AddSupplier(name, ID, Bank, Branch, bankNumber, payments, Contacts_ID, Contacts_number);  //todo make that fonction not void?
                } else {
                    Done = Sys.EditSupplier(name, ID, Bank, Branch, bankNumber, payments, Contacts_ID, Contacts_number);  //todo make that fonction not void?
                }
            }
        }
    }

    private static void Add_Edit_Agreement(int status) {
        boolean conect = Sys.CheckConected();
        if (!conect) {
            System.out.println("You need to connect before you take any action");
        }
        if (conect) {
         Scanner Reder = new Scanner(System.in);
        int suplaier_ID;
        boolean fixeDays = false;
        LinkedList<String> Days = new LinkedList<String>();
        boolean leading = true;
        Map<Integer, String> ProductIDVendor_Name = new ConcurrentHashMap<Integer, String>();
        Map<Integer, Double> ProducttemsIDVendor_Price = new ConcurrentHashMap<Integer, Double>();
        boolean contiue=true;

        System.out.println("Please enter the Supplier's ID");
        suplaier_ID = Reder.nextInt();
        if(status==2){
            String exist=Sys.CheckSAgreementExist(suplaier_ID);
            while (!exist.equals("Done")){
                System.out.println(exist);
                System.out.println("Do you want to continue and enter the supplier Id again? y/n ");
                String ans=Reder.next();
                if(ans.equals("y")){
                    System.out.println("Please enter the Supplier's ID");
                    suplaier_ID =Reder.nextInt();
                    exist=Sys.CheckSAgreementExist(suplaier_ID);
                }
                else{
                    {
                        contiue=false;
                        exist="Done";
                    }
                }
            }
        }
        if (contiue) {

            System.out.println("Does the supplier bring the supply on regular days? y/n");
            String ans = Reder.next();
            if (ans.equals("y")) {
                fixeDays = true;
                System.out.println("Please enter one day that the supply are expected to arrive.");
                String day = Reder.next();
                Days.add(day);
                boolean MoreDay = true;
                while (MoreDay) {
                    System.out.println("Is the supply expected to arrive in more days? y/n");
                    ans = Reder.next();
                    if (ans.equals("n"))
                        MoreDay = false;
                    else {
                        System.out.println("Please enter the extra day.");
                        day = Reder.next();
                        Days.add(day);
                    }
                }
            }
            System.out.println("Does the supplier bring the supplier by himself (or it required for transport services)? y/n");
            ans = Reder.next();
            if (ans.equals("n")) {
                leading = false;
            }

            boolean MoreProduct = true;
            while (MoreProduct) {
                System.out.println("Which product the supplier will supply to the store?\n" + "Please enter its name");
                String Product_Name = Reder.next();
                System.out.println("Please enter his Catalog Number");
                int product_Id = Reder.nextInt();
                System.out.println("Please enter the price");
                double Product_Price = Reder.nextInt();
                ProductIDVendor_Name.put(product_Id, Product_Name);
                ProducttemsIDVendor_Price.put(product_Id, Product_Price);
                System.out.println("Does the supplier provide another product? y/n");
                ans = Reder.next();
                if (ans.equals("n")) {
                    MoreProduct = false;
                }
            }
            String Done;
            switch (status) {
                case 1:
                    Done = Sys.AddContract(suplaier_ID, fixeDays, Days, leading, ProductIDVendor_Name, ProducttemsIDVendor_Price);
                    break;
                case 2:
                    Done = Sys.EditContract(suplaier_ID, fixeDays, Days, leading, ProductIDVendor_Name, ProducttemsIDVendor_Price);
                    break;
            }
        }
        }
     }

    private static void Add_Edit_Write(int status) {
        boolean conect = Sys.CheckConected();
        if (!conect) {
            System.out.println("You need to connect before you take any action");
        }
        if (conect) {
        int Suplaier_ID;
        Map<Integer, Integer> ItemsID_Amount=new ConcurrentHashMap<Integer, Integer>();
        Map<Integer, Double> ItemsID_Assumption =new ConcurrentHashMap<Integer, Double>();
        Scanner Reder = new Scanner(System.in);
        System.out.println("Please enter the Supplier's ID");
        Suplaier_ID = Reder.nextInt();

            boolean contiue=true;
            if (status == 2) {
                String exist = Sys.CheckSWortExist(Suplaier_ID);
                while (!exist.equals("Done")) {
                    System.out.println(exist);
                    System.out.println("Do you want to continue and enter the supplier Id again? y/n ");
                    String ans = Reder.next();
                    if (ans.equals("y")) {
                        System.out.println("Please enter the Supplier's ID");
                        Suplaier_ID = Reder.nextInt();
                        exist = Sys.CheckSWortExist(Suplaier_ID);
                    } else {
                        {
                            contiue = false;
                            exist = "Done";
                        }
                    }
                }
            }
            if (contiue) {
                boolean MoreProduct = true;
                System.out.println("Which product would you like to add to the quantities?");
                while ((MoreProduct)) {
                    int Product_ID;
                    int Product_Amount;
                    double Product_Assumption;
                    System.out.println("Please enter the Product's ID");
                    Product_ID = Reder.nextInt();
                    System.out.println("How many units of this product should be purchased to get the discount?");
                    Product_Amount = Reder.nextInt();
                    System.out.println("What percentage of discount will the product receive?");
                    Product_Assumption = Reder.nextInt();
                    ItemsID_Amount.put(Product_ID, Product_Amount);
                    ItemsID_Assumption.put(Product_ID, Product_Assumption);
                    System.out.println("Would you like to add another product? y/n");
                    String ans = Reder.next();
                    if (ans.equals("n")) {
                        MoreProduct = false;
                    }
                }
                String Done;
                switch (status) {
                    case 1:
                        Done = Sys.AddWrite(Suplaier_ID, ItemsID_Amount, ItemsID_Assumption);
                        break;
                    case 2:
                        Done = Sys.EditWrite(Suplaier_ID, ItemsID_Amount, ItemsID_Assumption);
                        break;
                }
            }
        }
    }

    private static void MakeOrder() {
        boolean conect = Sys.CheckConected();
        if (!conect) {
            System.out.println("You need to connect before you take any action");
        }
        if (conect) {
         int ID_Suplaier;
        Map<Integer, Integer> ItemsIDVendor_NumberOfItems=new ConcurrentHashMap<Integer, Integer>();
        Scanner Reder = new Scanner(System.in);
        System.out.println("Please enter the Supplier's ID you want to order from");
        ID_Suplaier = Reder.nextInt();

            boolean MoreProduct = true;
            System.out.println("Which product would you like to add to the Order?");
            while ((MoreProduct)) {
                int Product_ID;
                int Product_Amount;
                System.out.println("Please enter the Product's ID (According to the supplier)");
                Product_ID = Reder.nextInt();
                System.out.println("How many units of the product would you like to order??");
                Product_Amount = Reder.nextInt();
                ItemsIDVendor_NumberOfItems.put(Product_ID, Product_Amount);
                System.out.println("Would you like to add another product? y/n");
                String ans = Reder.next();
                if (ans.equals("n")) {
                    MoreProduct = false;
                }
            }
            String Done = Sys.MakeOrder(ID_Suplaier, ItemsIDVendor_NumberOfItems);
            System.out.println(Done);
        }

    }

    private static void DisplayItems() {
        boolean conect = Sys.CheckConected();
        if (!conect) {
            System.out.println("You need to connect before you take any action");
        }
        if (conect) {
            LinkedList<InterfaceContract> Contract = Sys.GetContract();
            for (InterfaceContract Con : Contract
            ) {
                for (Map.Entry<Integer, String> e : Con.ProductIDVendor_Name.entrySet()) {
                    int P = e.getKey();
                    String N = e.getValue();
                    for (Map.Entry<Integer, Double> entry : Con.productIDVendor_Price.entrySet()) {
                        int p = entry.getKey();
                        Double price = entry.getValue();
                        if (P == p) {
                            System.out.println("supplier: " + Con.Suplaier_ID);
                            System.out.println("product name: " + N);
                            System.out.println("Price: " + price + "\n");
                        }
                    }
                }
            }
        }
    }

    private static void DisplaySupplierDetails() {
        boolean conect=Sys.CheckConected();
        if(!conect){
            System.out.println("You should login");
        }
        if(conect) {
            LinkedList<InterfaceSupplier> suppliers =Sys.GetSupliers();
            for (InterfaceSupplier Sup : suppliers
            ) {
                System.out.print("name: " + Sup.Name + "\n" +
                        "Id: " + Sup.ID + "\n" +
                        "Payment with: " + Sup.Payments + "\n" +
                        "Bank: " + Sup.Bank + "\n" +
                        "Branch: " + Sup.Branch + "\n" +
                        "Bank number: " + Sup.BankNumber + "\n");
                System.out.println("Contacts:");
                Sup.ContactsID_Name.forEach((ID, name) -> {
                    System.out.println("name: " + name);
                    System.out.println("ID: " + ID);
                    Sup.ContactsID_number.forEach((Id, number) -> {
                        if (ID == Id) {
                            System.out.println("number: " + number + "\n");
                        }
                    });
                });
            }
        }

        }

     private static void UpdateOrderStatus() {
         boolean conect = Sys.CheckConected();
         if (!conect) {
             System.out.println("You need to connect before you take any action");
         }
         if (conect) {
             int ID_Order;
             Scanner Reder = new Scanner(System.in);
             System.out.println("Pleas enter the Order ID that arrived to the store");
             ID_Order = Reder.nextInt();
             String exist = Sys.ChangOrderStatus(ID_Order);
             while (!exist.equals("Done")) {
                 System.out.println(exist);
                 System.out.println("do you want to return the menu? y/n");
                 String ans = Reder.next();
                 if (ans.equals("y"))
                     break;
                 System.out.println("Pleas enter the Order ID again");
                 ID_Order = Reder.nextInt();
                 exist = Sys.ChangOrderStatus(ID_Order);
             }
             if (exist.equals("Done"))
                 System.out.println("the Order status updated!");
         }
     }

    private static void DeleteSupplier() {
        boolean conect = Sys.CheckConected();
        if (!conect) {
            System.out.println("You need to connect before you take any action");
        }
        if (conect) {
            Scanner Reder = new Scanner(System.in);
            int ID;
            System.out.println("Pleas enter the Supplier's ID");
            ID = Reder.nextInt();
            String Done = Sys.DeleteSupplier(ID);

        }
    }

    private static void Logout() {
         String ans=Sys.Logout();
         System.out.println(ans);
    }

}
