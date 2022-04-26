package dao;

import model.FeeStructure;
import model.Student;

import java.sql.*;

public class FeesStructureDAOImpl implements FeesStructureDAO{
    @Override
    public void saveFeeStructure(FeeStructure feeStructure, Student student) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            if (student != null) {
                connection = DAOUtilities.getConnection();
                String query = "INSERT INTO FEES_STRUCTURE VALUES (?,?,?,?,?,?,?,?);";
                stmt = connection.prepareStatement(query);
                stmt.setLong(1, student.getStudentId());
                stmt.setInt(2, feeStructure.getTuitionFees());
                stmt.setInt(3, feeStructure.getHostelFees());
                stmt.setInt(4, feeStructure.getTransportFees());
                stmt.setBoolean(5, feeStructure.getScholarship());
                stmt.setInt(6, feeStructure.getScholarShipAmount());
                stmt.setInt(7, feeStructure.getTotal());
                stmt.setInt(8, feeStructure.getDueAmount());
                success = stmt.executeUpdate();
            }else {
                System.out.println("First create student...");
                return;
            }
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
        if (success == 0) throw new Exception("Failed to insert student Fees structure: "+ feeStructure);
        else System.out.println("Fees structure updated for " + student.getName());
    }

    @Override
    public int removeFeeStructure(Long studentID) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;
        try {
            connection = DAOUtilities.getConnection();
            String query = "DELETE FROM fees_structure WHERE id = " +studentID +";";
            stmt = connection.prepareStatement(query);
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
        return success;
    }


    @Override
    public FeeStructure getFeesStructure(Long studentId) throws Exception {
        Connection connection = null;
        Statement st = null;
        FeeStructure f = null;

        try {
            connection = DAOUtilities.getConnection();
            String query = "SELECT * FROM FEES_STRUCTURE WHERE id = " + studentId +";";
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                FeeStructure fs = new FeeStructure();
                fs.setStudentId(studentId);
                fs.setHostelFees(rs.getInt("hostel_fees"));
                fs.setTuitionFees(rs.getInt("tution_fees"));
                fs.setTransportFees(rs.getInt("transport_fees"));
                fs.setScholarship(rs.getBoolean("scholar"));
                fs.setScholarShipAmount(rs.getInt("sc_amount"));
                fs.setTotal(rs.getInt("total"));
                fs.setDueAmount(rs.getInt("due_amount"));
                f = fs;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return f;
    }
}
