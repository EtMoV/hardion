package com.hardion.services;

import org.springframework.stereotype.Service;

@Service
public class RouterServiceImpl implements RouterService {

    private String goToRedirect(String road) {
        return "redirect:" + road;
    }

    @Override
    public String goToHomePage() {
        return goToRedirect("home");
    }

    @Override
    public String goToArticlePage() {
        return goToRedirect("article");

    }

    @Override
    public String goToLoginPage() {
        return goToRedirect("login");
    }

}
