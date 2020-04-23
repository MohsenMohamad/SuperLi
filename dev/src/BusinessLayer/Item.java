package BusinessLayer;

import java.text.SimpleDateFormat;

public class Item {

    private int id;
    private SimpleDateFormat expirationDate;
    private boolean defective;

    public Item(int id, SimpleDateFormat expirationDate){
        this.id = id;
        this.expirationDate = expirationDate;
        defective = false;
    }

    public Item(int id){
        this.id = id;
        defective = false;
    }

    public int getId() {
        return id;
    }

    public void setDefective(boolean isDefective) {
        defective = isDefective;
    }
}
