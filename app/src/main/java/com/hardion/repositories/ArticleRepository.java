package com.hardion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.hardion.entities.Article;
import com.hardion.entities.User;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    boolean existsByUserAndTitle(User user, String title);

    List<Article> findAllByOrderByDateDesc();
}