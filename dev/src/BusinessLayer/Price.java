package BusinessLayer;

public class Price {

    private int retailPrice;
    private int storePrice;

    public Price(int retail, int store) {
        retailPrice = retail;
        storePrice = store;
    }

    public String toString(){
        return "retail price "+ retailPrice + " store price "+ storePrice;
    }
}
