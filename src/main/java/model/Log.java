package model;

public class Log {
    private int id;
    private Long studentID;
    private String description;
    private String date;
    public Log(){}

    public Log(int id, Long studentID, String description, String date) {
        this.id = id;
        this.studentID = studentID;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Log(Long studentID, String description, String date) {
        this.studentID = studentID;
        this.description = description;
        this.date = date;
    }

    public Log(Long studentID, String description) {
        this.studentID = studentID;
        this.description = description;

    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Log{" +
                "studentID=" + studentID +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
