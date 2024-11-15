<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Member</title>
    <style>
        /* Similar styling as login.jsp */
        body { font-family: Arial, sans-serif; background-color: #f2f2f2; }
        .container { width: 300px; padding: 16px; background-color: white; margin: 100px auto; border-radius: 5px; }
        input[type=text], input[type=password], input[type=email] { width: 100%; padding: 12px; margin: 8px 0; box-sizing: border-box; }
        button { background-color: #4CAF50; color: white; padding: 12px; border: none; width: 100%; }
        .error { color: red; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add New User</h2>
        <c:if test="${not empty errorMessage}">
            <p class="error">${errorMessage}</p>
        </c:if>
        <form action="addNewServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required />

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required />

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required />

            <button type="submit">Insert </button>
        </form>
       <p><a href="userList">Back to User List</a></p>
    </div>
</body>
</html>
