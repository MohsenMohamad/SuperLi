package BusinessLogic;

import DataAccesslayer.Mapper;

import java.util.List;

public class User {
    private String email;
    private String password;
    //private List<Integer> list_of_Suplier;
    //private List<Integer> list_of_Order;
    private static Mapper Map;

    public User(String email, String password)
    {
        Map = new Mapper();
        this.email = email;
        this.password = password;
       // list_of_Suplier = Map.GetListOfSuplier(email);
        //list_of_Order = Map.GetListOfOrder(email);
    }
}
