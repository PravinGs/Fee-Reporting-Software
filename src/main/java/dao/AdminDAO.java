package dao;

import model.Auth;

import java.util.List;

public interface AdminDAO {
    int addAuthenticators(Auth auth) throws Exception;
    int deleteAccountant(String authID) throws Exception;
    int updateAccountant(Auth auth) throws Exception;
    Auth getAuthenticator(String authID) throws Exception;
    List<Auth> getAllAccountants() throws Exception;
    Boolean login(String id, String password);
    Boolean logout(String id, String password);

}
