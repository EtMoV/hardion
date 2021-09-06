package com.hardion.services;

import java.util.List;

import com.hardion.entities.Article;
import com.hardion.entities.User;

public interface ArticleService {
    public List<Article> getLastArticles();
    
    public Article getArticleById(Integer id) throws Exception;

    public void createArticle(String title, String content, User user) throws Exception;

}
