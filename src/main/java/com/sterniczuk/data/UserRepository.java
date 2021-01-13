package com.sterniczuk.data;

import com.sterniczuk.User;

public interface UserRepository {

    boolean insertNewUser(User user, String token) throws Exception;

    void activateNewUser(int token);

    User getUser(User user);

    int checkOneDataInDataBase(String table, String  attribute, String value);

}
