package com.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @author halfOfGame
 * @create 2020-03-19,14:32
 */

@WebServlet(name = "MyServlet_1",urlPatterns = {"/MyServlet_1"})

public class MyServlet_1 extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.getWriter().println("Hello World!!");
    }
}
