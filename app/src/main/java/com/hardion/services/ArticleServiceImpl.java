package com.hardion.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.hardion.entities.Article;
import com.hardion.entities.User;
import com.hardion.repositories.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getLastArticles() {
        List<Article> lastArticle = articleRepository.findAllByOrderByDateDesc();
        return lastArticle;
    }

    @Override
    public Article getArticleById(Integer id) throws Exception {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            return article.get();
        } else {
            throw new Exception("Article not found");
        }
    }

    @Override
    public void createArticle(String title, String content, User user) throws Exception {
        boolean articleExist = articleRepository.existsByUserAndTitle(user, title);
        if (!articleExist) {
            Article newArticle = new Article();
            newArticle.setTitle(title);
            newArticle.setContent(content);
            newArticle.setUser(user);
            newArticle.setDate(new Date());
            articleRepository.save(newArticle);
        } else {
            throw new Exception("Article exist");
        }
    }

}
