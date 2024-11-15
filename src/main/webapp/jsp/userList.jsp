<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <style>
        /* Basic table styling */
        body { font-family: Arial, sans-serif; background-color: #f2f2f2; }
        .container { width: 80%; margin: 50px auto; background-color: white; padding: 20px; border-radius: 5px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        a { text-decoration: none; color: #4CAF50; }
        .logout { float: right; }
    </style>
</head>
<body>
    <div class="container">
        <h2>User List</h2>
        <a href="addNewServlet">Add New User</a>
        <a href="logout" class="logout">Logout</a>
        <table>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Created At</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.createdAt}</td>
                    <td>
                        <a href="updateUser?id=${user.id}">Edit</a> | 
                        <a href="deleteUser?id=${user.id}" onclick="return confirm('Are you sure?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>