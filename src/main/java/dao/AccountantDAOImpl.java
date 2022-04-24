package dao;

import model.Accountant;
import model.FeeStructure;
import model.Student;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountantDAOImpl implements AccountantDAO{
    private StudentDAOImpl studentService = new StudentDAOImpl();
    private FeesStructureDAOImpl fsService = new FeesStructureDAOImpl();

    @Override
    public int saveAccountant(Accountant accountant) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;
        try {
            connection = DAOUtilities.getConnection();
            String query = "INSERT INTO accountant values (?,?,?,?);";
            stmt = connection.prepareStatement(query);
            stmt.setString(1,accountant.getId());
            stmt.setString(2,accountant.getName());
            stmt.setString(3,accountant.getEmail());
            stmt.setString(4,accountant.getRepresent());
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
    public Accountant getAccountant(String id) throws Exception {
        Connection connection = null;
        Accountant accountant = null;
        PreparedStatement stmt = null;
        try {
            connection = DAOUtilities.getConnection();
            String query = "SELECT * FROM accountant WHERE id = ?;";
            stmt = connection.prepareStatement(query);
            stmt.setString(1,id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                String name = res.getString("name");
                String email = res.getString("email");
                String represent = res.getString("represent");
                accountant = new Accountant(id,name,email,represent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();;
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return accountant;
    }
    @Override
    public int addStudent(Student student) throws Exception {
        return studentService.saveStudent(student);
    }
    @Override
    public void getStudentByID(Long studentId) throws Exception {
        Student s = studentService.getStudent(studentId);
        if (s == null) {
            System.out.println("There is no student with this id "+studentId);
            return;
        } else {
            s = studentService.getStudent(studentId);
            System.out.println(s.toString());
        }
    }
    @Override
    public int deleteStudent(Long studentId) throws Exception {
        int delete = fsService.removeFeeStructure(studentId);
        if (delete > 0)
            return studentService.deleteStudent(studentId);
        return 0;
    }
    @Override
    public int updateStudent(Student student) throws Exception {
        return studentService.updateStudent(student);
    }
    @Override
    public void addFeesStructure(FeeStructure feeStructure) throws Exception {
        Student student = studentService.getStudent(feeStructure.getStudentId());
        FeesStructureDAOImpl fs = new FeesStructureDAOImpl();
        fs.saveFeeStructure(feeStructure,student);
    }
    @Override
    public String showFeesStructure(Long studentID) throws Exception {
        FeesStructureDAOImpl fs = new FeesStructureDAOImpl();
        return fs.getFeesStructure(studentID).toString();
    }
    @Override
    public void amountPayment(int amount, Long studentID, String desc) throws Exception {
        studentService.amountPayment(amount,studentID, desc);
    }
    @Override
    public void getAllStudents() throws Exception {
        List<Student> list = studentService.getAllStudent();
        for (Student student: list) {
            System.out.println(student.toString());
        }

    }
    @Override
    public List<Accountant> getAllAccountants() throws Exception {
        Connection connection = null;
        List<Accountant> list = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "SELECT * FROM accountant;";
            statement = connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Accountant accountant = new Accountant();
                accountant.setId(res.getString("id"));
                accountant.setName(res.getString("name"));
                accountant.setEmail(res.getString("email"));
                accountant.setRepresent(res.getString("represent"));
                list.add(accountant);
            }
        }catch (SQLException e) {
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
    public List<Long> getAllFullyPaidStudents() throws Exception {
        Connection connection = null;
        List<Long> list = new ArrayList<>();
        Statement statement = null;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "SELECT id FROM fees_structure WHERE due_amount = 0;";
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                list.add(res.getLong("id"));
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
    @Override
    public List<Long> getAllPartiallyPaidStudent() throws Exception {
        Connection connection = null;
        List<Long> list = new ArrayList<>();
        Statement statement = null;
        try {
            connection = DAOUtilities.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT id FROM fees_structure WHERE due_amount < total and due_amount > 0;";
            ResultSet res = statement.executeQuery(sql);

            while (res.next()) {
                Long id = res.getLong("id");
                list.add(id);
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
    @Override
    public List<Accountant> getAccountantByDepartment(String dept) throws Exception {
        Connection connection = null;
        List<Accountant> list = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "SELECT * FROM accountant WHERE represent = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1,dept);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String id = res.getString("id");
                String name = res.getString("name");
                String email = res.getString("email");
                Accountant accountant = new Accountant(id,name,email,dept);
                list.add(accountant);
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
    @Override
    public int checkDue(Long studentID) throws Exception {
        int amount = -1;
        Student s = studentService.getStudent(studentID);
        if (s == null)
            return amount;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "SELECT due_amount FROM fees_structure WHERE id = " + studentID +";";
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                amount = res.getInt("due_amount");
            }
        } catch (SQLException e) {

        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e){

            }
        }
        return amount;
    }

    @Override
    public List<FeeStructure> getAllFeesStructures() {
        List<FeeStructure> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "SELECT * FROM fees_structure;";
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                FeeStructure s = new FeeStructure();
                s.setStudentId(res.getLong("id"));
                s.setTuitionFees(res.getInt("tution_fees"));
                s.setHostelFees(res.getInt("hostel_fees"));
                s.setTransportFees(res.getInt("transport_fees"));
                s.setScholarship(res.getBoolean("scholar"));
                s.setScholarShipAmount(res.getInt("sc_amount"));
                s.setTotal(res.getInt("total"));
                s.setDueAmount(res.getInt("due_amount"));
                list.add(s);
            }
        }catch (SQLException e) {
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
}
