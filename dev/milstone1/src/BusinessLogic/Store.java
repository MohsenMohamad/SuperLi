package BusinessLogic;

import DataAccesslayer.*;
import InterfaceLayer.InterfaceContract;
import InterfaceLayer.InterfaceSupplier;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Store {

    private String email_ID;
    private List<Supplier> list_of_Suplier;
    private List<Order> list_of_Order;
    private int NumOfOrder;
    private int NumOfProduct=0;
    private Transportrans Trans;
    private static Mapper Map;

    public Store(String email) {
    Map=new Mapper();
    list_of_Suplier=new LinkedList<Supplier>();
    LinkedList<DALSupplier> Suppliers=Map.ReadAllSupplier();
    for (DALSupplier s : Suppliers
        ) {
            Supplier sp = new Supplier(s.Name, s.ID, s.Bank, s.Branch, s.BankNumber, s.Payments, s.ContactsID_Name, s.ContactsID_number);
            DALContract DC = Map.ReadContract(s.ID);
            if (DC != null) {
                Contract c = new Contract(DC.Suplaier_ID, DC.FixeDays, DC.Dayes, DC.leading, DC.ProductIDSupplier_Name, DC.ItemsID_ItemsIDSupplier, DC.productIDSupplier_Price);
                sp.setContract(c);
                NumOfProduct += c.getItemsID_ItemsIDSupplier().size();
            }
            DALWrotequantities DW = Map.ReadWorte(s.ID);
            if (DW != null) {
                Wrotequantities w = new Wrotequantities(DW.Suplaier_ID, DW.ItemsID_Amount, DW.ItemsID_Assumption);
                sp.setWorte(w);
            }
            list_of_Suplier.add(sp);
        }
    LinkedList<DALOrder> Orders=Map.ReadAllInvetation();
    list_of_Order=new LinkedList<Order>();
    for (DALOrder Do: Orders
        ) {
            Order or=new Order(Do.ID_Vendor,Do.ID_Inventation,Do.ItemsID_ItemsIDVendor,Do.ItemsID_NumberOfItems,Do.TotalPrice);
            list_of_Order.add(or);
        }
    NumOfOrder=Orders.size();
    }

    public String AddSuplier(String name, int id, String bank, String branch, int bankNumber,
                             String payments, java.util.Map<Integer, String> contacts_id, Map<Integer, Integer> contacts_number) {
        for (Supplier s:list_of_Suplier
             ) {
            if(s.getID()==id){
                return "The supplier already exists in the system";
            }
        }
        Supplier sup=new Supplier(name, id, bank,branch,bankNumber,payments,contacts_id,contacts_number);
        list_of_Suplier.add(sup);
        Map.WriteSupplier(name, id, bank,branch,bankNumber,payments,contacts_id,contacts_number);
        return "Done";
    }

    public String Delete(int id) {
        boolean exist=false;
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==id){
                exist=true;
                list_of_Suplier.remove(s);
            }
        }
        if(!exist){
            return "The supplier is not exists in the system";
        }
        Map.DeleteSupplier(id);
        return "Done";
    }

    public String EditSuplier(String name, int id, String bank, String branch, int bankNumber, String payments, Map<Integer, String> contacts_id_name, Map<Integer, Integer> contacts_number) {
        boolean exist=false;
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==id){
                exist=true;
                s.setName(name);
                s.setBank(bank);
                s.setBranch(branch);
                s.setBankNumber(bankNumber);
                s.setPayments(payments);
                s.setContactsID_Name(contacts_id_name);
                s.setContactsID_number(contacts_number);
            }
        }
        if(!exist){
            return "The supplier is not exists in the system";
        }
        Map.EditSupplier(name, id, bank,branch,bankNumber,payments,contacts_id_name,contacts_number);
        return "Done";
    }

    public String AddContract(int suplaier_id, boolean fixeDays, LinkedList<String> days, boolean leading,
                              Map<Integer, String> productIDVendor_name, Map<Integer, Double> productIDVendor_price) {
      Map<Integer,Integer>  ItemsID_ItemsIDSupplier=new ConcurrentHashMap<Integer, Integer>();
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==suplaier_id){
                productIDVendor_name.forEach((Id,name)->{
                  ItemsID_ItemsIDSupplier.put(NumOfProduct,Id);
                  NumOfProduct++;
                });
                Contract con=new Contract(suplaier_id,fixeDays,days,leading,productIDVendor_name,ItemsID_ItemsIDSupplier,productIDVendor_price);
                s.setContract(con);
                Map.WriteContract(suplaier_id,fixeDays,days,leading,productIDVendor_name,ItemsID_ItemsIDSupplier,productIDVendor_price);
                return "Done";
            }
        }
        return "The supplier is not exists in the system";
    }

    public String AddWrite(int suplaier_id,Map<Integer, Integer> itemsID_amount, Map<Integer, Double> itemsID_assumption) {
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==suplaier_id){
                Wrotequantities W=new Wrotequantities(suplaier_id,itemsID_amount,itemsID_assumption);
                s.setWorte(W);
                Map.WriteWrote(suplaier_id,itemsID_amount,itemsID_assumption);
                return "Done";
            }
        }
        return "The supplier is not exists in the system";
    }

    public String MakeOrder(int id_suplaier, Map<Integer, Integer> ProductIDVendor_numberOfItems) {
        Supplier sup=null;
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==id_suplaier){
              sup=s;
            }
        }
        if(sup!=null&&sup.getContract()!=null) {
            Map<Integer, Integer> ItemsID_ItemsIDSupplier = new ConcurrentHashMap<Integer, Integer>();
            ProductIDVendor_numberOfItems.forEach((Id, num) -> {
                int Id_Product = GetIdProduct(id_suplaier, Id);
                ItemsID_ItemsIDSupplier.put(Id_Product, Id);
            });
            AtomicReference<Double> TotalPrice = new AtomicReference<>((double) 0);
            ProductIDVendor_numberOfItems.forEach((Id, num) -> {
                int Sale = GetSaleProduct(id_suplaier, Id, num);
                Sale = (100 - Sale) / 100;
                double Price = GetPricProduct(id_suplaier, Id);
                TotalPrice.set(TotalPrice.get() + num * Price * Sale);
            });
            Order O = new Order(id_suplaier, NumOfOrder, ItemsID_ItemsIDSupplier, ProductIDVendor_numberOfItems, TotalPrice.get());
            if(!sup.getContract().isLeading()){  // TODO: add
                Trans.Lead(O);
            }
            list_of_Order.add(O);
            NumOfOrder++;
            Map.WriteOrder(id_suplaier, NumOfOrder, LocalDate.now(),null,ItemsID_ItemsIDSupplier, ProductIDVendor_numberOfItems, TotalPrice.get(),"Waiting");
            return "Done";
        }
        return "The supplier is not exists in the system";

    } //todo check security

    private Double GetPricProduct(int id_suplaier, Integer id) {
        double price=1;
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==id_suplaier){
                price=s.getContract().GetPrice(id);
            }
        }
        return price;
    }

    private int GetSaleProduct(int id_suplaier, Integer id, Integer num) {
        int sale=0;
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==id_suplaier){
                if(s.getWorte()!=null){
                    sale=s.getWorte().GetSale(id,num);
                }
            }
        }
        return sale;
    }

    private int GetIdProduct(int id_suplaier, Integer id) {
        int ID_product=1;
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==id_suplaier){
                ID_product=s.getContract().GetIdSup(id);
            }
        }
        return ID_product;
    }

    public String EditContract(int suplaier_id, boolean fixeDays, LinkedList<String> days, boolean leading, Map<Integer, String> productIDVendor_name, Map<Integer, Double> producttemsIDVendor_price) {
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==suplaier_id){
                if(s.getContract()!=null) {
                    s.getContract().setDayes(days);
                    s.getContract().setLeading(leading);
                    s.getContract().setFixeDays(fixeDays);
                    s.getContract().setProductIDVendor_Name(productIDVendor_name);
                    s.getContract().setProductIDVendor_Price(producttemsIDVendor_price);
                    Map.DeleteContract(suplaier_id);
                    Map.WriteContract(suplaier_id, fixeDays, days, leading, productIDVendor_name, s.getContract().getItemsID_ItemsIDSupplier(), producttemsIDVendor_price);
                    return "Done";
                }
                else
                    return "The Supplier haven't a contract";
            }
        }
        return "The supplier is not exists in the system";
    }

    public String EditWrite(int suplaier_id, Map<Integer, Integer> itemsID_amount, Map<Integer, Double> itemsID_assumption) {
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==suplaier_id){
                if(s.getWorte()!=null) {
                    s.getWorte().setItemsID_Amount(itemsID_amount);
                    s.getWorte().setItemsID_Assumption(itemsID_assumption);
                    Map.DeleteWrote(suplaier_id);
                    Map.WriteWrote(suplaier_id,itemsID_amount,itemsID_assumption);
                    return "Done";
                }
                else
                    return "The Supplier haven't a 'Wrote quantities'";
            }
        }
        return "The supplier is not exists in the system";
    }

    public String CheckSuplierExist(int id) {
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==id){
                return "Exist";
            }
        }
        return "Not Exist";
    }

    public String CheckSAgreementExist(int suplaier_id) {
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==suplaier_id) {
                if (s.getContract() != null) {
                    return "Done";
                }
                }
            }
        return "The supplier haven't Agreement";
    }


    public String CheckSWortExist(int suplaier_id) {
        for (Supplier s:list_of_Suplier
        ) {
            if(s.getID()==suplaier_id){
                if(s.getWorte()!=null) {
                    return "Done";
                }
            }
        }
        return "The supplier haven't Agreement";
                }

    public String ChangOrderStatus(int id_order) {
        for (Order O:list_of_Order
             ) {
            if(O.getID_Invitation()==id_order){
                O.setStatus("Arrived");
                O.setArrivedatime(LocalDate.now()); // TODO: add
                return "Done";
            }
        }
        return "The Order is not exist in the system";
    }

    public LinkedList<InterfaceSupplier> GetSupliers() {
    LinkedList<InterfaceSupplier> list=new LinkedList<InterfaceSupplier>();
        for (Supplier s:list_of_Suplier
             ) {
            InterfaceSupplier I=new InterfaceSupplier(s.getName(),s.getID(),s.getBank(),s.getBranch(),s.getBankNumber(),s.getPayments(),s.getContactsID_Name(),s.getContactsID_number());
        list.add(I);
        }
        return list;
    }

    public LinkedList<InterfaceContract> GetContract() {
        LinkedList<InterfaceContract> list=new LinkedList<InterfaceContract>();
        for (Supplier s:list_of_Suplier
             ) {
            Contract c=s.getContract();
            if (c!=null) {
                InterfaceContract I = new InterfaceContract(c.getSuplaier_ID(),c.isFixeDays(),c.getDayes(),c.isLeading(),c.getProductIDVendor_Name(),c.getItemsID_ItemsIDSupplier(),c.getProductIDVendor_Price());
            list.add(I);
            }
        }
        return list;
    }


    //Just for tests!
    public List<Order> getList_of_Order() {
        return this.list_of_Order;
    }

    //Just fot tests!
    public List<Supplier> getList_of_Suplier() {
        return this.list_of_Suplier;
    }
}
