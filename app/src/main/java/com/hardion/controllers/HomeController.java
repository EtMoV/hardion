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
import com.hardion.forms.ArticleForm;

@Controller
public class HomeController {

	@Autowired
	ArticleService articleService;

	@Autowired
	UserService userService;

	@GetMapping("/home")
	public String homePage(Model model) {
		List<Article> lastArticles = articleService.getLastArticles();
		model.addAttribute("articles", lastArticles);
		return "home";
	}

	// DEV METHOD -> TO KILL
	@GetMapping("/article")
	public String createArticle(Model model) {
		User user;
		try {
			user = userService.getUser("e");
			articleService.createArticle("test", "content test", user);
		} catch (Exception e) {
			System.err.println(e);
		}
		model.addAttribute("articleForm", new ArticleForm());
		return "article";

	}

}
