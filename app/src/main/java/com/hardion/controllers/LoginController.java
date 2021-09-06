package com.hardion.controllers;

import java.util.Random;

import com.hardion.forms.LoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.hardion.services.AuthService;

@Controller
public class LoginController {

	String[] sentences = { "Share your opinion", "Denounce anything here", "See what other people think",
			"Find other people like you", "Tell anything to the world", "You can have your ideas",
			"Spread your ideology", "Find your ideology" };

	@Autowired
	AuthService authService;

	String getRandomSentence() {
		int rnd = new Random().nextInt(sentences.length);

		return sentences[rnd];
	}

	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("randomSentence", getRandomSentence());
		model.addAttribute("loginForm", new LoginForm());

		return "login";
	}

	@PostMapping("/login")
	public String loginSubmit(@ModelAttribute LoginForm loginForm, Model model) {
		boolean isAuthGood = authService.authorizeUser(loginForm.getLogin(), loginForm.getPassword());

		if (isAuthGood) {
			return "home";
		} else {
			model.addAttribute("errorBadAuth", true);
			return this.loginForm(model);
		}

	}

	@PostMapping("/create")
	public String createSubmit(@ModelAttribute LoginForm loginForm, Model model) {
		try {
			authService.createUser(loginForm.getLogin(), loginForm.getPassword());

			return "home";
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("errorUserExist", true);
			return this.loginForm(model);
		}
	}

}
