package com.hardion.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hardion.entities.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    boolean existsByLogin(String login);

    boolean existsByLoginAndPassword(String login, String password);

}