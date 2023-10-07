package com.hillel.javaee.servlet;

import com.hillel.javaee.service.IService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.util.List;


@WebServlet(
        urlPatterns = "/firstServlet",
        initParams = @WebInitParam(name = "name", value = "Oleg"))
public class FirstServlet extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println(config.getInitParameter("name"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        IService service = (IService) req.getServletContext().getAttribute("service");
        if (req.getParameter("id") != null) {
            if (req.getCookies() != null) {
                resp.sendRedirect("http://localhost:8080/L2/thirdServlet");
                return;
            }
        }
        req.getRequestDispatcher("/secondServlet").forward(req, resp);
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
