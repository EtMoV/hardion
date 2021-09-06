package com.hardion.services;

import com.hardion.entities.User;

public interface UserService {
    
    public boolean authorizeUser(String login, String password);

    public User getUser(String login) throws Exception;

    public void createUser(String login, String password) throws Exception;

}
