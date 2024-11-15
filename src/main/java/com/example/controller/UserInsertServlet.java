package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@SuppressWarnings("serial")
@WebServlet("/insertUser")
public class UserInsertServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to userForm.jsp with empty fields for insert
        request.setAttribute("action", "Insert");
        request.getRequestDispatcher("/jsp/userForm.jsp").forward(request, response); // Absolute path
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle user insertion
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User newUser = new User(username, password, email);
        try {
            userDAO.insertUser(newUser);
            response.sendRedirect("userList");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error inserting user");
            request.setAttribute("action", "Insert");
            request.setAttribute("user", newUser); // Set the user attribute
            request.getRequestDispatcher("/jsp/userForm.jsp").forward(request, response); // Absolute path
        }
    }
}