package model;

public class Student {
    private Long studentId;
    private String name;
    private String department;
    private String year;
    private String email;

    public Student(){}

    public Student(Long studentId, String name, String department, String year, String email) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.year = year;
        this.email = email;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", section='" + year + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
