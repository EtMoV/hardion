package com.hardion.services;

import com.hardion.entities.User;
import com.hardion.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean authorizeUser(String login, String password) {
        boolean userExist = userRepository.existsByLoginAndPassword(login, password);

        return userExist;
    }

    @Override
    public void createUser(String login, String password) throws Exception {
        boolean userExist = userRepository.existsByLogin(login);
        if (!userExist) {
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPassword(password);
            userRepository.save(newUser);
        } else {
            throw new Exception("User exist");
        }
    }

}
