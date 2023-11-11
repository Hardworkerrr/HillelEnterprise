package com.hillel.javaee.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/get")
public class GetRequestsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Сюда будут попадать get запросы со всех либо многих страниц и тут же буду обрабатываться
        if (req.getParameterNames().hasMoreElements()){
            processRequests(req,resp);
        }
    }

    protected void processRequests(HttpServletRequest request, HttpServletResponse response){

    }
}
