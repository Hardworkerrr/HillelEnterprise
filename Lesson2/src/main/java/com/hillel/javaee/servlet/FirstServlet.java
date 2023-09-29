package com.hillel.javaee.servlet;

import com.hillel.javaee.service.IService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;


@WebServlet("/firstServlet")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IService service = (IService) req.getServletContext().getAttribute("service");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(service.getAllEmailsByUserId(Integer.parseInt(req.getParameter("id"))));
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IService service = (IService) req.getServletContext().getAttribute("service");
        BufferedReader bufferedReader = req.getReader();
        List<String> list = bufferedReader.lines().toList();
        String result = service.addNewCustomer(Integer.parseInt(list.get(0)), list.get(1), Date.valueOf(list.get(2)));
        System.out.println(result);
        bufferedReader.close();
    }
}
