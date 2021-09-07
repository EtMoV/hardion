package com.hardion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import com.hardion.services.SessionService;

@Controller
public class HeaderController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/disconnect")
    public String homePage(Model model, HttpServletRequest request) {
        sessionService.disconnectSession(request);
        return "redirect:login";
    }

}
