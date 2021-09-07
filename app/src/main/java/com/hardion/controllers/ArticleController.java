package com.hardion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

import com.hardion.services.ArticleService;
import com.hardion.services.RouterService;
import com.hardion.services.SessionService;
import com.hardion.services.UserService;
import com.hardion.entities.Article;
import com.hardion.entities.User;
import com.hardion.forms.ArticleForm;

@Controller
public class ArticleController {

	@Autowired
	ArticleService articleService;

	@Autowired
	UserService userService;

	@Autowired
	SessionService sessionService;

	@Autowired
	RouterService routerService;

	@GetMapping("/read/{articleId}")
	public String readArticlePage(Model model, @PathVariable(value = "articleId") String articleId) {
		try {
			Article article = articleService.getArticleById(Integer.parseInt(articleId));
			model.addAttribute("title", article.getTitle());
			model.addAttribute("content", article.getContent());
			return "read";
		} catch (Exception e) {
			System.err.println(e);
			model.addAttribute("errorArticleNotExist", true);
			return routerService.goToHomePage();
		}

	}

	@GetMapping("/article")
	public String articlePage(Model model) {
		model.addAttribute("articleForm", new ArticleForm());

		return "article";
	}

	@PostMapping("/article")
	public String createArticle(@ModelAttribute ArticleForm articleForm, Model model, HttpServletRequest request) {
		try {

			Integer idUser = sessionService.getSessionIdUser(request);

			User user = userService.getUser(idUser);

			articleService.createArticle(articleForm.getTitle(), articleForm.getContent(), user);

			return routerService.goToHomePage();
		} catch (Exception e) {
			System.err.println(e);

			model.addAttribute("errorTitleExist", true);

			return this.articlePage(model);
		}

	}

}
