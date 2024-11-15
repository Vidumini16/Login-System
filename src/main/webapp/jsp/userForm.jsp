<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.example.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>${action} User</title>
    <style>
        /* Basic styling */
        body { font-family: Arial, sans-serif; background-color: #f2f2f2; }
        .container { width: 400px; padding: 16px; background-color: white; margin: 50px auto; border-radius: 5px; }
        input[type=text], input[type=password], input[type=email] { width: 100%; padding: 12px; margin: 8px 0; box-sizing: border-box; }
        button { background-color: #4CAF50; color: white; padding: 12px; border: none; width: 100%; }
        .error { color: red; }
    </style>
</head>
<body>
    <div class="container">
        <h2>${action} User</h2>
        <c:if test="${not empty errorMessage}">
            <p class="error">${errorMessage}</p>
        </c:if>
        <form action="${action == 'Insert' ? 'insertUser' : 'updateUser'}" method="post">
            <c:if test="${action == 'Update'}">
                <input type="hidden" name="id" value="${user.id}" />
            </c:if>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="${user.username != null ? user.username : ''}" required />

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="${user.password != null ? user.password : ''}" required />

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${user.email != null ? user.email : ''}" required />

            <button type="submit">${action} User</button>
        </form>
        <p><a href="userList">Back to User List</a></p>
    </div>
</body>
</html>
