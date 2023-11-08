package com.hillel.javaee.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = {"/account",
        "/login",
        "/register",
        "/shop",
        "/test",
        "/welcome"})
public class PageRepresentationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] URL = req.getRequestURL().toString().split("/");
        req.getRequestDispatcher(URL[URL.length - 1] + ".jsp").forward(req, resp);
    }

}
