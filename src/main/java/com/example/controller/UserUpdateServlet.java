package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@SuppressWarnings("serial")
@WebServlet("/updateUser")
public class UserUpdateServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get user ID from request
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("userList");
            return;
        }

        int id = Integer.parseInt(idStr);
        User existingUser = userDAO.selectUser(id);
        if (existingUser == null) {
            response.sendRedirect("userList");
            return;
        }

        request.setAttribute("user", existingUser);
        request.setAttribute("action", "Update");
        request.getRequestDispatcher("jsp/userForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle user update
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // Consider hashing
        String email = request.getParameter("email");

        User user = new User(id, username, password, email, null);
        try {
            userDAO.updateUser(user);
            response.sendRedirect("userList");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating user");
            request.setAttribute("user", user);
            request.setAttribute("action", "Update");
            request.getRequestDispatcher("jsp/userForm.jsp").forward(request, response);
        }
    }
}
