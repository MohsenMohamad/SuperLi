package BusinessLayer;

import java.time.LocalDate;
import java.util.Map;

public class Order {

    private int ID_Invitation;
    private int ID_Vendor;
    private LocalDate OrderDate;
    private LocalDate ArrivalTime;
    private Map<Integer, Integer> ItemsID_ItemsIDVendor;
    private Map<Integer, Integer> ItemsID_NumberOfItems;
    private double TotalPrice;
    private String Status;

    public Order(int ID_Vendor, int Id, Map<Integer, Integer> itemsID_ItemsIDVendor, Map<Integer, Integer> itemsID_NumberOfItems, Double totalPrice){//List<DALContact> vendorContacts, List<DALContact> leadersContacts) {
        this.ID_Vendor = ID_Vendor;
        this.ID_Invitation=Id;
        OrderDate = LocalDate.now();
        ArrivalTime =null;
        ItemsID_ItemsIDVendor = itemsID_ItemsIDVendor;
        ItemsID_NumberOfItems = itemsID_NumberOfItems;
        TotalPrice = totalPrice;
        Status = "Waiting";
        //VendorContacts = vendorContacts;
        // LeadersContacts = leadersContacts;
    }

    public int getID_Invitation() {
        return ID_Invitation;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getID_Vendor() {
        return ID_Vendor;
    }

    public LocalDate getOrderDate() {
        return OrderDate;
    }

    public LocalDate getArrivalTime() {
        return ArrivalTime;
    }

    public Map<Integer, Integer> getItemsID_ItemsIDVendor() {
        return ItemsID_ItemsIDVendor;
    }

    public Map<Integer, Integer> getItemsID_NumberOfItems() {
        return ItemsID_NumberOfItems;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public String getStatus() {
        return Status;
    }

    public void setArrivedatime(LocalDate now) {
        ArrivalTime=now;
    }
}
