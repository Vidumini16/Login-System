package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to login.jsp
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle login logic
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.selectUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) { // In production, use hashed passwords
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("userList");
        } else {
            // Invalid credentials
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
    }
}
