package com.sterniczuk.data;

import com.sterniczuk.User;

import java.sql.SQLException;

public interface UserRepository {

    boolean insertNewUser(User user, String token) throws Exception;

    void activateNewUser(int token);

    User getUser(String NIP) throws SQLException;

    int checkOneDataInDataBase(String table, String  attribute, String value);

}
