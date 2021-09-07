package com.hardion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import com.hardion.entities.Article;
import com.hardion.services.ArticleService;
import com.hardion.services.UserService;

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

}
