package dao;

import model.Accountant;
import model.Admin;

import java.sql.*;
import java.util.List;

public class AdminDAOImpl implements AdminDAO{
    AccountantDAOImpl accountantDAO = new AccountantDAOImpl();

    @Override
    public int saveAdmin(Admin admin) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int success = 0;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "INSERT INTO admin VALUES (?,?,?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getEmail());
            statement.setString(3, admin.getMobile());
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
    public int addAccountant(Accountant accountant) throws Exception {
        return accountantDAO.saveAccountant(accountant);
    }

    @Override
    public int deleteAccountant(String  id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int success = 0;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "DELETE FROM accountant WHERE id = ? ;";
            statement = connection.prepareStatement(sql);
            statement.setString(1,id);
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
    public int updateAccountant(Accountant accountant) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int success = 0;
        try{
            connection = DAOUtilities.getConnection();
            String sql = "UPDATE accountant SET name = ?, email = ?, represent = ? WHERE id = ?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1,accountant.getName());
            statement.setString(2,accountant.getEmail());
            statement.setString(3,accountant.getRepresent());
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
    public Accountant getAccountant(String id) throws Exception {
        return accountantDAO.getAccountant(id);
    }

    @Override
    public List<Accountant> getAllAccountants() throws Exception {
        List<Accountant> list = accountantDAO.getAllAccountants();
        return list;
    }

    @Override
    public List<Accountant> getAccountantsByDepartment(String dept) throws Exception {
        List<Accountant> list = accountantDAO.getAccountantByDepartment(dept);
        return list;
    }

    @Override
    public Boolean login(String name, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        String success = "";
        try {
            connection = DAOUtilities.getConnection();
            String sql = "select id from auth where name = ? AND password = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet res = statement.executeQuery();
            while (res.next())
                success = res.getString("id");
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
            }
        }
        return (success.length() == 0) ? false:true;
    }

    @Override
    public Boolean logout(String name, String password) {
        if (login(name,password))
            return true;
        return false;
    }
}
