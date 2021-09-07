package com.hardion.services;

import com.hardion.entities.User;

public interface UserService {
    
    public User authorizeUser(String login, String password) throws Exception;

    public User getUser(Integer id) throws Exception;

    public User createUser(String login, String password) throws Exception;

}
