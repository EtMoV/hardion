package com.hardion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import com.hardion.entities.Article;
import com.hardion.services.ArticleService;
import com.hardion.services.UserService;
import com.hardion.entities.User;

@Controller
public class HomeController {

	@Autowired
	ArticleService articleService;

	@Autowired
	UserService userService;

	@GetMapping("/home")
	public String homePage(Model model) {
		List<Article> lastArticles = articleService.getLastArticles();

		for (int i = 0; i < lastArticles.size(); i++) {
			Article article = lastArticles.get(i);
			System.out.println(article.getTitle() + " " + article.getUser().getLogin() + " " + article.getContent());
		}
		return "home";
	}

	// DEV METHOD -> TO KILL
	@PostMapping("/article")
	public void createArticle() {
		User user;
		try {
			user = userService.getUser("e");
			articleService.createArticle("test", "content test", user);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
