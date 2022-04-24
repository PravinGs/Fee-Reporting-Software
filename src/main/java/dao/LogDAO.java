package dao;


import model.Log;

import java.awt.*;
import java.util.List;

public interface LogDAO {
    void saveLog (Log log) throws Exception;
    List<Log> getAllLogs() throws Exception;
    List<Log> getLogsByStudentID(Long studentID) throws Exception;
}
