package dao;

import model.FeeStructure;
import model.Student;

import java.util.List;

public interface StudentDAO {
    // get a particular Student with his id
    int saveStudent(Student student) throws Exception;
    int updateStudent(Student student) throws Exception;
    int deleteStudent(Long studentId) throws Exception;
    Student getStudent(Long studentID) throws Exception;
    List<Student> getAllStudent() throws Exception;
    void showFeesStructure(Long studentId) throws Exception;
    int amountPayment(int amount, Long studentId) throws Exception;


}
