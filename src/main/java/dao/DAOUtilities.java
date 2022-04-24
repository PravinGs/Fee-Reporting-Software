package dao;

import java.sql.*;



public class DAOUtilities {
    protected static final String CONNECTION_USERNAME = "root";
    protected static final String CONNECTION_PASSWORD = "Pravin@123";
    protected static final String URL = "jdbc:mysql://localhost:3306/bill";
    protected static Connection connection;


    static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Could not register server");
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL,CONNECTION_USERNAME,CONNECTION_PASSWORD);
        }

        if (connection.isClosed()) {
            connection = DriverManager.getConnection(URL,CONNECTION_USERNAME,CONNECTION_PASSWORD);
        }
        return connection;
    }

    public static void main(String[] args) throws Exception {


    }
}
