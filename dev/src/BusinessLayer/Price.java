package BusinessLayer;

public class Price {

    private int retailPrice;
    private int storePrice;

    public Price(int retail, int store) {
        retailPrice = retail;
        storePrice = store;
    }

    public int getStorePrice() {
        return storePrice;
    }
    public int getRetailPrice(){
        return retailPrice;
    }

    public String toString(){
        return "retail price "+ retailPrice + " store price "+ storePrice;
    }
}
