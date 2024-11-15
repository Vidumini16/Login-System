package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.UserDAO;
import com.example.model.User;

/**
 * Servlet implementation class AddNewServlet
 */
@WebServlet("/addNewServlet")
public class AddNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private UserDAO userDAO;

	    public void init() {
	        userDAO = new UserDAO();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // Forward to register.jsp
	        request.getRequestDispatcher("jsp/addNew.jsp").forward(request, response);
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
	            request.getRequestDispatcher("jsp/addNew.jsp").forward(request, response);
	        } else {
	            User newUser = new User(username, password, email);
	            try {
	                userDAO.insertUser(newUser);
	                response.sendRedirect("userList");
	            } catch (SQLException e) {
	                e.printStackTrace();
	                request.setAttribute("errorMessage", "Error registering user");
	                request.getRequestDispatcher("jsp/addNew.jsp").forward(request, response);
	            }
	        }
	    }
}
