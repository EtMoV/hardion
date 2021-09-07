package com.hardion.services;

import javax.servlet.http.HttpServletRequest;

import com.hardion.entities.User;

public interface SessionService {
    public void addSessionIdUser(HttpServletRequest request, User user);

    public Integer getSessionIdUser(HttpServletRequest request);

    public void disconnectSession(HttpServletRequest request);
}
