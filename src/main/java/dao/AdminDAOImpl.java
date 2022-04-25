package dao;

import model.Auth;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO{
    private static AccountantDAOImpl accountantDAO = new AccountantDAOImpl();
    @Override
    public int addAuthenticators(Auth auth) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int success = 0;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "INSERT INTO auth VALUES (?,?,?,?,?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, auth.getId());
            statement.setString(2, auth.getName());
            statement.setString(3, auth.getPassword());
            statement.setString(4, auth.getEmail());
            if (auth.getId().startsWith("admin")) {
                statement.setBoolean(5,true);
            } else {
                statement.setBoolean(5,false);
            }
            success = statement.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
            }
        }
        return success;
    }
    public Auth getAuthenticator(String authID) {
        Connection connection = null;
        PreparedStatement statement = null;
        Auth auth = null;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "SELECT * FROM auth WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, authID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                auth = new Auth();
                auth.setId(authID);
                auth.setName(rs.getString("name"));
                auth.setPassword(rs.getString("password"));
                auth.setEmail(rs.getString("email"));
                auth.setAdmin(rs.getBoolean("is_admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement!= null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return auth;
    }
    @Override
    public int deleteAccountant(String accountantID) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int success = 0;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "DELETE FROM auth WHERE id = ? ;";
            statement = connection.prepareStatement(sql);
            statement.setString(1,accountantID);
            success = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }
    @Override
    public int updateAccountant(Auth accountant) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int success = 0;
        try{
            connection = DAOUtilities.getConnection();
            String sql = "UPDATE auth SET name = ?, password = ?, email = ? WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1,accountant.getName());
            statement.setString(2,accountant.getPassword());
            statement.setString(3,accountant.getEmail());
            statement.setString(4,accountant.getId());
            success = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }
    @Override
    public List<Auth> getAllAccountants() throws Exception {
        Connection connection = null;
        List<Auth> list = new ArrayList<>();
        Statement statement = null;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "SELECT * FROM auth WHERE is_admin = 0";
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                Auth auth = new Auth();
                auth.setId(res.getString("id"));
                auth.setName(res.getString("name"));
                auth.setPassword(res.getString("password"));
                auth.setEmail(res.getString("email"));
                auth.setAdmin(res.getBoolean("is_admin"));
                list.add(auth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return list;
    }
    @Override
    public Boolean login(String id, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        String name = "";
        try {
            connection = DAOUtilities.getConnection();
            String sql = "select name from auth where id = ? AND password = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, password);
            ResultSet res = statement.executeQuery();
            while (res.next())
                name = res.getString("name");
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
            }
        }
        return (name.length() == 0) ? false:true;
    }

    @Override
    public Boolean logout(String id, String password) {
        if (login(id,password))
            return true;
        return false;
    }
}
