package dao;

import model.FeeStructure;
import model.Student;

import java.util.List;

public interface AccountantDAO {
    int addStudent(Student student) throws Exception;
    int deleteStudent(Long studentId) throws Exception;
    int updateStudent(Student student) throws Exception;
    void getStudentByID(Long studentId) throws Exception;
    void getAllStudents() throws Exception;

    void addFeesStructure(FeeStructure feeStructure) throws Exception;

    String showFeesStructure(Long studentID) throws Exception;
    void amountPayment(int amount, Long studentID, String desc) throws Exception;
    List<Long> getAllFullyPaidStudents() throws Exception;
    List<Long> getAllPartiallyPaidStudent() throws Exception;
    int checkDue(Long studentID) throws Exception;
    List<FeeStructure> getAllFeesStructures();

}
