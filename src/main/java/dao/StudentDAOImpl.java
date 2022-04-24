package dao;

import model.FeeStructure;
import model.Log;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO{
    @Override
    public int amountPayment(int amount, Long studentId) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        FeesStructureDAOImpl fs = new FeesStructureDAOImpl();
        FeeStructure structure = fs.getFeesStructure(studentId);
        if (structure == null){
            return -1;
        }
        int success = 0;
        try {
            if (structure != null) {
                if (structure.getDueAmount() >= amount) {
                    connection = DAOUtilities.getConnection();
                    int updatedAmount = structure.getDueAmount() - amount;
                    String query = "UPDATE FEES_STRUCTURE SET due_amount = " + updatedAmount + " WHERE id = " + studentId + ";";
                    stmt = connection.prepareStatement(query);
                    success = stmt.executeUpdate();
                } else {
                    System.out.println("Your due amount only "+ structure.getDueAmount() + "\nIncas you gave more than this make sure correct yourself and try again.");
                }
            }else{
                Student student = getStudent(studentId);
                if (student != null){
                    System.out.println("No Fees structure has been assigned to this student");
                }
                else{
                    System.out.println("Invalid studentId " + studentId);
                }
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
        return success;
    }

    public void amountPayment(int amount, Long studentID, String desc) throws Exception {
        LogDAOImpl logDAO = new LogDAOImpl();
        int success = amountPayment(amount,studentID);
        if (success > 0) {
            String description = String.valueOf(amount) + " - " + desc;
            Log log = new Log(studentID, description);
            logDAO.saveLog(log);
        } else if (success < 0) {
            System.out.println("You Fees structure not been initialized, So please contact your department Accountant");
        } else {
            System.out.println("Payment failed");
        }
    }

    @Override
    public Student getStudent(Long studentID) throws Exception{
        Connection connection = null;
        Student student = null;
        Statement st = null;
        try {
            connection = DAOUtilities.getConnection();
            st = connection.createStatement();
            String query = "SELECT * FROM STUDENT WHERE student_id =" +studentID+";";
            ResultSet res = st.executeQuery(query);
            while (res.next()) {
                Student s = new Student();
                s.setStudentId(res.getLong("student_id"));
                s.setName(res.getString("name"));
                s.setDepartment(res.getString("department"));
                s.setYear(res.getString("year"));
                s.setEmail(res.getString("email"));
                student = s;
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    @Override
    public int saveStudent(Student student) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = DAOUtilities.getConnection();
            String sql = "INSERT INTO STUDENT VALUES (?,?,?,?,?);";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, student.getStudentId());
            stmt.setString(2,student.getName());
            stmt.setString(3,student.getDepartment());
            stmt.setString(4,student.getYear());
            stmt.setString(5,student.getEmail());
            success = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
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
    public List<Student> getAllStudent() throws Exception {
        Connection connection = null;
        List<Student> list = new ArrayList<>();
        Statement stmt = null;
        try {
            connection = DAOUtilities.getConnection();
            stmt = connection.createStatement();
            String query = "SELECT * FROM student;";
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                Student student = new Student();
                student.setStudentId(res.getLong("student_id"));
                student.setName(res.getString("name"));
                student.setDepartment(res.getString("department"));
                student.setYear(res.getString("year"));
                student.setEmail(res.getString("email"));
                list.add(student);
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
        return list;
    }

    @Override
    public void showFeesStructure(Long studentId) throws Exception {
        FeesStructureDAOImpl fs = new FeesStructureDAOImpl();
        System.out.println(fs.getFeesStructure(studentId).toString());
    }

    @Override
    public int  deleteStudent(Long studentId) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        // for printing the name of the student
        Student st = getStudent(studentId);
        int success = 0;
        try {
            connection = DAOUtilities.getConnection();
            String query = "DELETE  FROM STUDENT WHERE student_id = " + studentId +";";
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
    public int updateStudent(Student student) throws Exception {
        // get the current student
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "UPDATE STUDENT SET name = ?, department = ?, year = ?, email = ? WHERE student_id = ? ;";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,student.getName());
            stmt.setString(2,student.getDepartment());
            stmt.setString(3,student.getYear());
            stmt.setString(4,student.getEmail());
            stmt.setLong(5,student.getStudentId());
            success = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }
}
