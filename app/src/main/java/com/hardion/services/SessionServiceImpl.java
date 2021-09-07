package com.hardion.services;

import com.hardion.entities.User;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public void addSessionIdUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute("idUser", user.getId());
    }

    @Override
    public Integer getSessionIdUser(HttpServletRequest request) {
        return (Integer) request.getSession().getAttribute("idUser");
    }

    @Override
    public void disconnectSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}
