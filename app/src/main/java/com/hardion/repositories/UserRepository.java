package com.hardion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.hardion.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByLogin(String login);

    boolean existsByLoginAndPassword(String login, String password);

    Optional<User> findByLogin(String login);
}