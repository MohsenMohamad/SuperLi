package DataAccesslayer;

import java.util.Map;

public class DALWrotequantities {

    public int Suplaier_ID;
    public Map<Integer, Integer> ItemsID_Amount;
    public Map<Integer, Double> ItemsID_Assumption;

    public DALWrotequantities(int suplaier_ID,  Map<Integer, Integer> itemsID_Amount, Map<Integer, Double> itemsID_Assumption) {
        Suplaier_ID = suplaier_ID;
        ItemsID_Amount = itemsID_Amount;
        ItemsID_Assumption = itemsID_Assumption;
    }
}
