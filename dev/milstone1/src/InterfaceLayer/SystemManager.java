package InterfaceLayer;

import BusinessLogic.Store;
import BusinessLogic.system;
import BusinessLogic.User;

import java.util.LinkedList;
import java.util.Map;

public class SystemManager {
    private system systemcontroler;
    private User logged_user;
    public Store current_Store; //this field is public only for the unitests

    public SystemManager() {
        logged_user=null;
        current_Store=null;
        systemcontroler=new system();
    }

    public String AddSupplier(String name, int ID, String bank, String branch, int bankNumber,
                              String payments, Map<Integer, String> Contacts_ID,
                              Map<Integer, Integer> Contacts_number) {//  List<DALItem> Items)
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null)
         Done = current_Store.AddSuplier(name, ID,bank, branch,bankNumber, payments, Contacts_ID,Contacts_number);

        return Done;
    }

    public String AddContract(int suplaier_id, boolean fixeDays, LinkedList<String> days,
                              boolean leading, Map<Integer, String> productIDVendor_name,
                              Map<Integer, Double> producttemsIDVendor_price) {
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null)
            Done=current_Store.AddContract(suplaier_id,fixeDays, days,leading, productIDVendor_name,producttemsIDVendor_price);
        return Done;
    }

    public String AddWrite(int suplaier_id, Map<Integer, Integer> itemsID_amount, Map<Integer, Double> itemsID_assumption) {
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null)
            Done=current_Store.AddWrite(suplaier_id, itemsID_amount,itemsID_assumption);
        return Done;
    }

    public String MakeOrder(int id_suplaier, Map<Integer, Integer> itemsIDVendor_numberOfItems) {
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null)
            Done= current_Store.MakeOrder(id_suplaier,itemsIDVendor_numberOfItems);
        return Done;
    }

    public String EditSupplier(String name, int id, String bank, String branch, int bankNumber, String payments, Map<Integer, String> contacts_id, Map<Integer, Integer> contacts_number) {
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null)
             Done = current_Store.EditSuplier(name, id ,bank, branch,bankNumber, payments,contacts_id,contacts_number);
        return Done;
    }
    public String DeleteSupplier(int id) {
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null) {
            Done = current_Store.Delete(id);
       //     Done = logged_user.DeleteSupplier(id);
        }
        return Done;
    }

    public String EditContract(int suplaier_id, boolean fixeDays, LinkedList<String> days, boolean leading, Map<Integer, String> productIDVendor_name, Map<Integer, Double> producttemsIDVendor_price) {
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null) {
            Done = current_Store.EditContract(suplaier_id, fixeDays, days, leading, productIDVendor_name, producttemsIDVendor_price);
        }
        return Done;
    }

    public String EditWrite(int suplaier_id, Map<Integer, Integer> itemsID_amount, Map<Integer, Double> itemsID_assumption) {
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null)
           Done=current_Store.EditWrite(suplaier_id, itemsID_amount,itemsID_assumption);
        return Done;
    }

    public String CheckEmailExist(String email) {
        String Done=systemcontroler.CheckEmailExist(email);
        return Done;
    }

    public String Register(String email, String password) {
        String Done=systemcontroler.Register(email,password);
        return Done;
    }

    public String Login(String email, String password) {
        String Done=systemcontroler.Login(email,password);
        if(Done.equals("Done")){
            logged_user=new User(email,password);
            current_Store=new Store(email);
        }
        return Done;
    }

    public LinkedList<InterfaceContract> GetContract() {
        LinkedList<InterfaceContract> list=new LinkedList<InterfaceContract>();
        if(current_Store!=null)
            list=current_Store.GetContract();
        return list;
    }

    public LinkedList<InterfaceSupplier> GetSupliers() {
        LinkedList<InterfaceSupplier> list=new LinkedList<InterfaceSupplier>();
        if(current_Store!=null)
           list=current_Store.GetSupliers();
        return list;
    }

    public String ChangOrderStatus (int id_order) {
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null)
              Done=current_Store.ChangOrderStatus(id_order);
        return Done;
    }

    public String CheckSuplierExist(int id) {
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null)
            Done=current_Store.CheckSuplierExist(id);
        return Done;
    }

    public String CheckSAgreementExist(int suplaier_id) {
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null)
            Done=current_Store.CheckSAgreementExist(suplaier_id);
        return Done;
    }

    public String CheckSWortExist(int suplaier_id) {
        String Done="you must be logged in before doing any actions";
        if(current_Store!=null)
            Done=current_Store. CheckSWortExist(suplaier_id);
        return Done;
    }

    public String Logout() {
        if(logged_user==null| current_Store==null){
            return "you need to Login before you logout";
        }
        current_Store=null;
        logged_user=null;
        return "login successfully";
    }

    public boolean CheckConected() {
        return current_Store!=null;
    }
}