package DAL;

import DTOs.*;

import java.sql.*;


public class DAL {
    static String url = "jdbc:sqlite:C:/Users/Mohamad/Desktop/Database/SuperLee.db"; // change this to desired location

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createTables()
    {

    }

    public static void main(String[] argv) {

        connect();
        createTables();

    }

}

