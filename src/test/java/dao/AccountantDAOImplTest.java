package dao;

import model.FeeStructure;
import model.Student;
import org.junit.jupiter.api.Test;


class AccountantDAOImplTest {

    private AccountantDAOImpl accountantDAO = new AccountantDAOImpl();
    @Test
    public void addStudent() throws Exception {
        Student student = new Student(210918104036L, "Ram", "CSE", "IV", "ram@yahoo.com");
        accountantDAO.addStudent(student);
    }
    @Test
    public void assignFeesStructure() throws Exception {
        FeeStructure fs = new FeeStructure(210918104036L,50000,40000,0,false,50000);
        accountantDAO.addFeesStructure(fs);
    }
    @Test
    public void deleteStudent()throws Exception {
        accountantDAO.deleteStudent(210918104036L);
    }
    @Test
    public void getAllStudent() throws Exception {
        accountantDAO.getAllStudents();
    }
}