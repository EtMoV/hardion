package com.hardion.services;

public interface AuthService {
    
    public boolean authorizeUser(String login, String password);

    public void createUser(String login, String password) throws Exception;

}
