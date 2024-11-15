package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@SuppressWarnings("serial")
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to register.jsp
        request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle registration logic
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User existingUser = userDAO.selectUserByUsername(username);
        if (existingUser != null) {
            // Username already exists
            request.setAttribute("errorMessage", "Username already exists");
            request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
        } else {
            User newUser = new User(username, password, email);
            try {
                userDAO.insertUser(newUser);
                response.sendRedirect("login");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error registering user");
                request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
            }
        }
    }
}
