package dao;

import model.Accountant;
import model.FeeStructure;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class Test {

    static void payment(int amount, Long studentId, String desc) throws Exception{
        StudentDAOImpl s = new StudentDAOImpl();
        s.amountPayment(amount,studentId,desc);
    }
    static void getStudentFee(Long studentId) throws Exception{
        StudentDAOImpl s = new StudentDAOImpl();
        s.showFeesStructure(studentId);
    }
    static void getStudentFromID(Long studId) throws Exception{
        StudentDAOImpl s = new StudentDAOImpl();
        System.out.println(s.getStudent(studId).toString());
//        s.getStudent(studId).toString();
    }
    static void studentTest() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id name dept year email");
        Long id = in.nextLong();
        String name = in.next();
        String dept = in.next();
        String sec = in.next();
        String email = in.next();

        Student student1 = new Student(id,name,dept,sec,email);
        AccountantDAOImpl repository = new AccountantDAOImpl();
        repository.addStudent(student1);
    }
    static void addFeeStructure() throws Exception {
        AccountantDAOImpl repository = new AccountantDAOImpl();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter student id, tuition, hostel, transport, scholar, amt");
        Long id = in.nextLong();
        Integer tuition = in.nextInt();
        Integer hostel = in.nextInt();
        Integer transport = in.nextInt();
        Boolean scholar = in.nextBoolean();
        Integer amt = in.nextInt();

        FeeStructure structure = new FeeStructure(id,tuition,hostel,transport,scholar,amt);
        repository.addFeesStructure(structure);

    }
    static void deleteStudent(Long studentId) throws Exception {
        AccountantDAOImpl repository = new AccountantDAOImpl();
        repository.deleteStudent(studentId);
    }
    static void viewStudent(Long studentID) throws Exception {
        AccountantDAOImpl repository = new AccountantDAOImpl();
        repository.getStudentByID(studentID);
    }
    static void editStudent () throws Exception{
        AccountantDAOImpl repository = new AccountantDAOImpl();

        Scanner in = new Scanner(System.in);
        System.out.println("Enter ID of the student");
        Long studentID = in.nextLong();
        in.nextLine();
        System.out.println("Enter new name of the student");
        String name = in.nextLine();
        System.out.println("Enter new department of the student");
        String dept = in.nextLine();
        System.out.println("Enter new year of the student");
        String sec = in.nextLine();
        System.out.println("Enter new email of the student");
        String email = in.nextLine();

        Student student1 = new Student(studentID,name,dept,sec,email);

        repository.updateStudent(student1);
    }
    static void payment() throws Exception {
        AccountantDAOImpl repository = new AccountantDAOImpl();
        repository.amountPayment(10000,210918104034L, "Tuition");
    }
    static void printAllStudents() throws Exception{
        StudentDAOImpl dao = new StudentDAOImpl();
        List<Student> list = dao.getAllStudent();
        for (Student student : list) {
            System.out.println(student.toString());
        }
    }
    static void addAccountant() throws Exception {
        AccountantDAOImpl repository = new AccountantDAOImpl();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id, name, email, studentID");
        String id = in.nextLine();
        String name = in.nextLine();
        String email = in.nextLine();
        String represent = in.nextLine();
        Accountant accountant = new Accountant(id, name, email,represent);
        repository.saveAccountant(accountant);
    }
    static void getAccountant () throws Exception {
        AccountantDAOImpl repository = new AccountantDAOImpl();
        System.out.println(repository.getAccountant("acc-cse-01").toString());
    }
    static void login() throws Exception{
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        System.out.println(adminDAO.login("admin", "Admin@123"));
    }
    static void logout() {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        System.out.println(adminDAO.logout("dmin", "Admin@123"));
    }
    public static void main(String[] args) throws Exception {
        logout();
    }
}
