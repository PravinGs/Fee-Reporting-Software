package dao;

import model.*;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Stimulation {
    private static StudentDAOImpl studentDAO = new StudentDAOImpl();
    private static AccountantDAOImpl accountantDAO = new AccountantDAOImpl();
    private static AdminDAOImpl adminDAO = new AdminDAOImpl();
    private static LogDAOImpl logDAO = new LogDAOImpl();
    static FeeStructure addFeesStructure() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter student id, tuition, hostel, transport, scholar, amt");
        System.out.print("Enter student id: ");
        Long id = in.nextLong();
        System.out.print("\nEnter tuition fees: ");
        Integer tuition = in.nextInt();
        System.out.print("\nEnter Hostel fees: ");
        Integer hostel = in.nextInt();
        System.out.print("\nEnter transport fees: ");
        Integer transport = in.nextInt();
        System.out.print("\nEnter true / false he is getting scholarship: ");
        Boolean scholar = in.nextBoolean();
        System.out.print("\nEnter 0 if he is not getting scholarship else put amount: ");
        Integer amt = in.nextInt();
        FeeStructure structure = new FeeStructure(id,tuition,hostel,transport,scholar,amt);
        return structure;
    }

    static Auth updateAuth(String id)throws Exception{
        Scanner in = new Scanner(System.in);
        System.out.println("Accountant update...");
        Auth auth = adminDAO.getAuthenticator(id);
        if (auth != null) {
            System.out.println("If you don't want to change the existing one just hit enter ");
            System.out.print("Your Existing ID " + auth.getId() + " :\t");
            String accountantId = in.nextLine();
            accountantId = (accountantId.length() == 0) ? auth.getId() : accountantId;
            System.out.println();
            System.out.print("Your Existing Name " + auth.getName() + " :\t");
            String name = in.nextLine();
            name = (name.length() == 0) ? auth.getName() : name;
            System.out.println();
            System.out.print("Your Existing Password " + auth.getPassword() + " :\t");
            String password = in.nextLine();
            password = (password.length() == 0) ? auth.getPassword() : password;
            System.out.println();
            System.out.print("Your Existing email " + auth.getEmail() + " :\t");
            String email = in.nextLine();
            email = (email.length() == 0) ? auth.getEmail() : email;
            System.out.println();
            Auth updatedAuth = new Auth(accountantId, name, password, email);
            return updatedAuth;
        } else {
            return null;
        }
    }
    static Student updateStudent() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Student update...");
        System.out.print("Enter the id of the student for update:\t");
        Long id = in.nextLong();
        in.nextLine();
        System.out.println();
        Student student = studentDAO.getStudent(id);
        if (student != null) {
            System.out.println("If you don't want to change the existing one just hit enter ");
            System.out.print("Your Existing Name " + student.getName() + " :\t");
            String name = in.nextLine();
            name = (name.length() == 0) ? student.getName() : name;
            System.out.println();
            System.out.print("Your Existing Department " + student.getDepartment() + " :\t");
            String department = in.nextLine();
            department = (department.length() == 0) ? student.getDepartment() : department;
            System.out.println();
            System.out.print("Your Existing Year " + student.getYear() + " :\t");
            String year = in.nextLine();
            year = (year.length() == 0) ? student.getYear() : year;
            System.out.println();
            System.out.print("Your Existing email " + student.getEmail() + " :\t");
            String email = in.nextLine();
            email = (email.length() == 0) ? student.getEmail() : email;
            System.out.println();
            Student updatedStudent = new Student(id, name, department, year, email);
            return updatedStudent;
        } else {
            return null;
        }
    }
    static Student addStudent() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Student creation...");
        System.out.print("Enter id:\t");
        Long id = in.nextLong();
        in.nextLine();
        System.out.println();
        System.out.print("Enter name:\t");
        String name = in.nextLine();
        System.out.println();
        System.out.print("Enter Department:\t");
        String department = in.nextLine();
        System.out.println();
        System.out.print("Enter Year:\t");
        String year = in.nextLine();
        System.out.println();
        System.out.print("Enter email:\t");
        String email = in.nextLine();
        System.out.println();
        Student student = new Student(id, name, department,year,email);
        return student;
    }
    static Auth addAuthenticator() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Accountant creation..");
        System.out.print("Enter id ():\t");
        String id = in.nextLine();
        System.out.println();
        System.out.print("Enter name:\t");
        String name = in.nextLine();
        System.out.println();
        System.out.print("Enter password:\t");
        String password = in.nextLine();
        System.out.println();
        System.out.print("Enter email:\t");
        String email = in.nextLine();
        System.out.println();
        Auth auth = new Auth(id, name, password,email);
        return auth;
    }


    static void addAdmin() throws Exception {
        Auth auth = null;
        Scanner in = new Scanner(System.in);
        int i = 1;
        while (i == 1) {
            System.out.print("Enter your id:\t");
            String id = in.nextLine();
            System.out.println();
            System.out.print("Enter your name:\t");
            String name = in.nextLine();
            System.out.println();
            System.out.print("Enter your password:\t");
            String password = in.nextLine();
            System.out.println();
            System.out.print("Enter your email:\t");
            String email = in.nextLine();
            System.out.println();
            auth = new Auth(id, name, password,email);
            int success = adminDAO.addAuthenticators(auth);
            if (success > 0) {
                System.out.println("Admin created successfully");
            }
            System.out.print("You want to add more admin please enter 1 else 0:\t");
            i = in.nextInt();
        }
    }
    static void adminActions() throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.println("\nPlease Enter Admin login credentials ");
        System.out.print("please Enter your ID:\t");
        String adminID = in.nextLine();
        System.out.print("Please Enter your password:\t");
        String password = in.nextLine();
        boolean choice  = adminDAO.login(adminID, password);
        System.out.println();
        Auth admin = adminDAO.getAuthenticator(adminID);
        if (choice == false) {
            System.out.println("Failed to login please check your Id and Password");
            return;
        }

        if (choice == true && !admin.getAdmin()) {
            System.out.println("you are not authorized to access this page");
            return;
        }


        System.out.println("Welcome to this is Admin panel ");
        System.out.println("Please keep this list in your mind for any operation" +
                "\nclick 1 for view any accountant" +
                "\nclick 2 for view all the Accountants" +
                "\nclick 3 for remove any Accountant from the DB" +
                "\nclick 4 add new Accountant" +
                "\nclick 5 to see log book of the college" +
                "\nclick 6 to see all fees schedule "+
                "\nclick 7 to logout from this panel"
        );
        while (choice) {
            int i = in.nextInt();
            switch (i) {
                case 1:
                    in.nextLine();
                    System.out.print("Please enter the id of the accountant:\t");
                    String id = in.nextLine();
                    System.out.println((adminDAO.getAuthenticator(id) != null) ? adminDAO.getAuthenticator(id).toString(): "check your Id no accountant with this id");
                    break;
                case 2:
                    System.out.println("Here are the list of accountants we have");
                    List<Auth> list = adminDAO.getAllAccountants();
                    if (list != null) {
                        for (Auth accountant : list)
                            System.out.println(accountant.toString());
                    }
                    break;
                case 3:
                    System.out.print("Please enter the id of the accountant:\t");
                    in.nextLine();
                    String accountantId = in.nextLine();
                    System.out.println(adminDAO.deleteAccountant(accountantId));
                    break;
                case 4:
                    System.out.println(adminDAO.addAuthenticators(addAuthenticator())  >  0 ? adminDAO.addAuthenticators(addAuthenticator())
                            : "Change Your Name which is already exists.");
                    break;

                case 5:
                    System.out.println("The Transaction details ");
                    List<Log> logs = logDAO.getAllLogs();
                    for (Log log: logs) {
                        System.out.println(log.toString());
                    }
                    break;
                case 6:
                    System.out.println("Fees structure database");
                    List<FeeStructure > list1 = accountantDAO.getAllFeesStructures();
                    if (list1.size() > 0){
                        for (FeeStructure feeStructure: list1) {
                            System.out.println(feeStructure.toString());
                        }
                    }else {
                        System.out.println("No fees structures...");
                    }
                    break;
                case 7:
                    System.out.println("Thanks..");
                    choice = false;
                    break;

            }
        }

    }

    static void accountantActions() throws Exception {
        Scanner in = new Scanner(System.in);


        System.out.println("\nPlease Enter Accountant login credentials ");
        System.out.print("please Enter your ID:\t");
        String accountantID = in.nextLine();
        System.out.print("Please Enter your password:\t");
        String password = in.nextLine();
        boolean choice  = adminDAO.login(accountantID, password);
        System.out.println();

        if (choice == false) {
            System.out.println("Login failed please check your id and password");
            return;
        }

        System.out.println("Welcome to is Accountant panel ");
        System.out.println("Please keep this list in your mind for any operation" +
                "\nclick 1 to view any student" +
                "\nclick 2 to view all the students" +
                "\nclick 3 to remove a student from the DB" +
                "\nclick 4 to add new Student" +
                "\nclick 5 to update student" +
                "\nclick 6 to create feesStructure" +
                "\nclick 7 to check student Due amount" +
                "\nclick 8 to see log details of the student" +
                "\nclick 9 to see fully paid student's list" +
                "\nenter 10 to see partially paid student's list" +
                "\nenter 11 update your details" +
                "\nenter 12 to logout from the accountant panel"

        );
        while (choice) {
            int i = in.nextInt();
            switch (i) {
                case 1:
                    System.out.print("Please enter the id of the student:\t");
                    Long id = in.nextLong();
                    accountantDAO.getStudentByID(id);
                    break;
                case 2:
                    System.out.println("Here are the list of students we have");
                    accountantDAO.getAllStudents();
                    break;
                case 3:
                    System.out.print("\nPlease enter the id of the student:\t");
                    Long studentID = in.nextLong();
                    int result = accountantDAO.deleteStudent(studentID);
                    if (result == 0)
                        System.out.println("There is not student with id "+studentID);
                    else
                        System.out.println("Student id " + studentID + " removed from the database");
                    break;
                case 4:
                    Student student = addStudent();
                    System.out.println(accountantDAO.addStudent(student));
                    break;
                case 5:
                    Student updatedStudent = updateStudent();
                    if (updatedStudent != null)
                        System.out.println(accountantDAO.updateStudent(updatedStudent));
                    else
                        System.out.println("Please check your ID");
                    break;
                case 6:
                    FeeStructure structure = addFeesStructure();
                    accountantDAO.addFeesStructure(structure);
                    break;
                case 7:
                    System.out.print("Enter student id:\t");
                    Long sid = in.nextLong();
                    System.out.println("Your due amount is : " + accountantDAO.checkDue(sid));
                    break;
                case 8:
                    System.out.print("Enter student id for fetching his payment details:\t");
                    Long id1 = in.nextLong();
                    List<Log> logs = logDAO.getLogsByStudentID(id1);
                    if (logs.size() == 0)
                        System.out.println("There is no transactions yet");
                    for (Log log: logs) {
                        System.out.println(log.toString());
                    }
                    break;
                case 9:
                    System.out.println("Fully paid students  list");
                    List<Long> student_id = accountantDAO.getAllFullyPaidStudents();
                    for (Long ids: student_id) {
                        Student s = studentDAO.getStudent(ids);
                        System.out.println(s.toString());
                    }
                    break;
                case 10:
                    System.out.println("Partially paid students  list");
                    List<Long> studentsList = accountantDAO.getAllPartiallyPaidStudent();
                    for (Long studID: studentsList) {
                        Student new_student = studentDAO.getStudent(studID);
                        System.out.println(new_student.toString());
                    }
                    break;
                case 11:
                    Auth accountant = updateAuth(accountantID);
                    adminDAO.updateAccountant(accountant);
                    break;
                case 12:
                    System.out.println("Thank you....");
                    choice = false;
                    break;
            }
        }
    }

    static void studentActions() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("This is Student panel let's do some activities ");
        System.out.println("Please keep this list in your mind for any operation" +
                "\nclick 1 to add student" +
                "\nclick 2 to update student" +
                "\nclick 3 to check student fee_structure" +
                "\nclick 4 to pay due"
        );

        boolean choice = true;
        while (choice) {
            int i = in.nextInt();
            switch (i) {
                case 1:
                    Student student = addStudent();
                    System.out.println(studentDAO.saveStudent(student));
                    break;
                case 2:
                    Student updatedStudent = updateStudent();
                    System.out.println(studentDAO.updateStudent(updatedStudent));
                    break;
                case 3:
                    System.out.print("Enter student id :\t");
                    Long s_id = in.nextLong();
                    studentDAO.showFeesStructure(s_id);
                    break;
                case 4:
                    System.out.print("Enter Amount:\t");
                    int amount = in.nextInt();
                    System.out.print("Enter student id:\t");
                    Long studentID = in.nextLong();
                    System.out.print("Enter which amount you going to pay tuition/hostel/transport:\t");
                    in.nextLine();
                    String desc = in.nextLine();
                    studentDAO.amountPayment(amount,studentID,desc);
                    break;
                case 5:
                    System.out.println("Thank you....");
                    choice = false;
                    break;

            }
        }


    }

    static void adminAuth() {

    }
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        boolean choice = true;
        while (choice) {
            System.out.print("Please Enter 1 you are student?\nEnter 2 you are Accountant?\nEnter 3 you are an Admin \noption:\t");
            int i = in.nextInt();
            if (i == 1)
                studentActions();
            else if (i == 2)
                accountantActions();
            else if (i == 3)
                adminActions();
            else
                choice = false;
        }
    }
}
