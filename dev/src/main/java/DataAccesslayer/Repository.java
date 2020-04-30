package DataAccesslayer;

import java.util.LinkedList;

class  Repo {

    private static Repo single_instance = null;

    LinkedList<DALOrder> Invetations;
    LinkedList<DALSupplier> Suppliers;
    LinkedList<DALUser> Users;

    private Repo() {
        Invetations = new LinkedList<DALOrder>();
        Suppliers = new LinkedList<DALSupplier>();
        Users = new LinkedList<DALUser>();
    }

    public static Repo getInstance() {
        if (single_instance == null)
            single_instance = new Repo();

        return single_instance;
    }
}

