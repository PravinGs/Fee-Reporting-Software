package dao;

import model.FeeStructure;
import model.Student;

public interface FeesStructureDAO {
    void saveFeeStructure(FeeStructure feeStructure, Student student) throws Exception;
    int removeFeeStructure(Long studentID) throws Exception;
    FeeStructure getFeesStructure(Long studentId) throws Exception;
}
