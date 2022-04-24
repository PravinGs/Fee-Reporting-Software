package model;

public class FeeStructure {
    private Long studentId;
    private int tuitionFees;
    private int hostelFees;
    private int transportFees;
    private Boolean scholarship;
    private int scholarShipAmount;
    private int total;
    private int dueAmount;

    public FeeStructure(){}

    public FeeStructure(Long studentId,
                        int tuitionFees,
                        int hostelFees,
                        int transportFees,
                        Boolean scholarship,
                        int scholarShipAmount) {
        this.studentId = studentId;
        this.tuitionFees = tuitionFees;
        this.hostelFees = hostelFees;
        this.transportFees = transportFees;
        this.scholarship = scholarship;
        this.scholarShipAmount = scholarShipAmount;
        this.total = this.hostelFees+this.tuitionFees+this.transportFees-this.scholarShipAmount;
        this.dueAmount = this.total - this.dueAmount;
    }

    public void payment(int amount) {
        this.dueAmount = this.total-amount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(int dueAmount) {
        this.dueAmount = dueAmount;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public int getTuitionFees() {
        return tuitionFees;
    }

    @Override
    public String toString() {
        return "FeeStructure{" +
                "studentId=" + studentId +
                ", tuitionFees=" + tuitionFees +
                ", hostelFees=" + hostelFees +
                ", transportFees=" + transportFees +
                ", scholarship=" + scholarship +
                ", scholarShipAmount=" + scholarShipAmount +
                ", total=" + total +
                ", dueAmount=" + dueAmount +
                '}';
    }

    public void setTuitionFees(int tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public int getHostelFees() {
        return hostelFees;
    }

    public void setHostelFees(int hostelFees) {
        this.hostelFees = hostelFees;
    }

    public int getTransportFees() {
        return transportFees;
    }

    public void setTransportFees(int transportFees) {
        this.transportFees = transportFees;
    }

    public Boolean getScholarship() {
        return scholarship;
    }

    public void setScholarship(Boolean scholarship) {
        this.scholarship = scholarship;
    }

    public int getScholarShipAmount() {
        return scholarShipAmount;
    }

    public void setScholarShipAmount(int scholarShipAmount) {
        this.scholarShipAmount = scholarShipAmount;
    }
}
