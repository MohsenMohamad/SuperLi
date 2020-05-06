package DataAccesslayer;

import java.util.List;
import java.util.Map;

public class DALContract {

   public int Suplaier_ID;
    public boolean FixeDays;
    public List<Integer> Dayes;
    public boolean leading;
    public Map<Integer, Integer> ItemsID_ItemsIDSupplier;
    public Map<Integer, String> ProductIDSupplier_Name;
    public Map<Integer, Double> productIDSupplier_Price;

    public DALContract(int suplaier_ID, boolean fixeDays,List<Integer> dayes, boolean leading,
                    Map<Integer, String> productIDSupplier_name, Map<Integer, Integer> ItemsID_ItemsIDsupplier,
                    Map<Integer, Double> producttemsIDSupplier_price) {
        Suplaier_ID = suplaier_ID;
        FixeDays = fixeDays;
        Dayes=dayes;
        this.leading = leading;
        ProductIDSupplier_Name =productIDSupplier_name;
        ItemsID_ItemsIDSupplier=ItemsID_ItemsIDsupplier;
        productIDSupplier_Price =producttemsIDSupplier_price;


    }
}
