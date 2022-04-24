package dao;

import model.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDAOImpl implements LogDAO{
    @Override
    public void saveLog(Log log) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "INSERT INTO log (student_id, description )VALUES (?,?);";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, log.getStudentID());
            stmt.setString(2,log.getDescription());
            success = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (success > 0) System.out.println("Log updated");
        else System.out.println("Log not changed");
    }
    @Override
    public List<Log> getAllLogs() throws Exception {
        Connection connection = null;
        List<Log> list = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "SELECT * FROM log;";
            statement = connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Log log = null;
                int id = res.getInt("id");
                Long studentID = res.getLong("student_id");
                String desc = res.getString("description");
                String date = res.getString("date");
                log = new Log(id,studentID,desc,date);
                list.add(log);
            }
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
        return list;
    }

    @Override
    public List<Log> getLogsByStudentID(Long studentID) throws Exception {
        List<Log> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DAOUtilities.getConnection();
            String sql = "SELECT * FROM log WHERE student_id = "+studentID+";";
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()) {
                String desc = res.getString("description");
                String date = res.getString("date");
                Log log = new Log(studentID,desc,date);
                list.add(log);
            }
        } catch (SQLException e) {

        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {

            }
        }
        return list;
    }
}
