package InterfaceLayer;

import java.util.Map;

public class InterfaceWrotequantities {

    public int Suplaier_ID;
    public Map<Integer, Integer> ItemsID_Amount;
    public Map<Integer, Integer> ItemsID_Assumption;

    public InterfaceWrotequantities(int suplaier_ID, Map<Integer, Integer> itemsID_Amount, Map<Integer, Integer> itemsID_Assumption) {
        Suplaier_ID = suplaier_ID;
        ItemsID_Amount = itemsID_Amount;
        ItemsID_Assumption = itemsID_Assumption;
    }
}
