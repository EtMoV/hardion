package com.hardion.controllers;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.hardion.entities.User;
import com.hardion.forms.LoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hardion.services.SessionService;
import com.hardion.services.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService authService;

	@Autowired
	SessionService sessionService;

	String[] sentences = { "Share your opinion", "Denounce anything here", "See what other people think",
			"Find other people like you", "Tell anything to the world", "You can have your ideas",
			"Spread your ideology", "Find your ideology" };

	String getRandomSentence() {
		int rnd = new Random().nextInt(sentences.length);

		return sentences[rnd];
	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("randomSentence", getRandomSentence());
		model.addAttribute("loginForm", new LoginForm());

		return "login";
	}

	@PostMapping("/login")
	public String loginSubmit(@ModelAttribute LoginForm loginForm, Model model, HttpServletRequest request) {
		User userAuth;
		try {
			userAuth = authService.authorizeUser(loginForm.getLogin(), loginForm.getPassword());

			sessionService.addSessionIdUser(request, userAuth);

			return goToHome();

		} catch (Exception e) {
			System.err.println(e);

			model.addAttribute("errorBadAuth", true);

			return this.loginPage(model);
		}
	}

	@PostMapping("/create")
	public String createSubmit(@ModelAttribute LoginForm loginForm, Model model, HttpServletRequest request) {
		try {
			User newUser = authService.createUser(loginForm.getLogin(), loginForm.getPassword());

			sessionService.addSessionIdUser(request, newUser);

			return goToHome();
		} catch (Exception e) {
			System.out.println(e);

			model.addAttribute("errorUserExist", true);

			return this.loginPage(model);
		}
	}

	private String goToHome() {
		return "redirect:home";
	}
}
