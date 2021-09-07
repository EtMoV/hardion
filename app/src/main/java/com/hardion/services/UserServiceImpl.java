package com.hardion.services;

import com.hardion.entities.User;
import com.hardion.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User authorizeUser(String login, String password) throws Exception {
        Optional<User> user = userRepository.findByLoginAndPassword(login, password);
        
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public User createUser(String login, String password) throws Exception {
        boolean userExist = userRepository.existsByLogin(login);
        if (!userExist) {
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPassword(password);
            userRepository.save(newUser);
            return newUser;
        } else {
            throw new Exception("User exist");
        }
    }

    @Override
    public User getUser(Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("User not found");
        }
    }

}
