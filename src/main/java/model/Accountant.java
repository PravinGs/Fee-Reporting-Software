package model;

import dao.StudentDAOImpl;

public class Accountant {
    private String id;
    private String name;
    private String email;

    private String represent;

    public String getRepresent() {
        return represent;
    }

    @Override
    public String toString() {
        return "Accountant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", represent='" + represent + '\'' +
                '}';
    }

    public Accountant(String id, String name, String email, String represent) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.represent = represent;
    }

    public void setRepresent(String represent) {
        this.represent = represent;
    }



    public Accountant (){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
