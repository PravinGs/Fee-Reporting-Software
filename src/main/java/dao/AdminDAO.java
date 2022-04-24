package dao;

import model.Accountant;
import model.Admin;

import java.util.List;

public interface AdminDAO {
    int saveAdmin(Admin admin) throws Exception;
    int addAccountant(Accountant accountant) throws Exception;
    int deleteAccountant(String id) throws Exception;
    int updateAccountant(Accountant accountant) throws Exception;
    Accountant getAccountant(String id) throws Exception;
    List<Accountant> getAllAccountants() throws Exception;

    List<Accountant> getAccountantsByDepartment(String dept) throws Exception;

    Boolean login(String name, String password);
    Boolean logout(String name, String password);

}
