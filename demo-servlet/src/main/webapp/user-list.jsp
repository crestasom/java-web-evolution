<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="java.util.List" %>
        <%@ page import="org.example.model.User" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>User List - Servlet Evolution</title>
                <link rel="stylesheet" href="css/style.css">
                <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap"
                    rel="stylesheet">
            </head>

            <body>
                <div class="container" style="max-width: 800px;">
                    <div class="glass-card">
                        <h1>User Registered</h1>
                        <p class="subtitle">Data retrieved from H2 Database via JDBC</p>

                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% List<User> users = (List<User>) request.getAttribute("users");
                                        if (users != null) {
                                        for (User user : users) {
                                        %>
                                        <tr>
                                            <td style="color: var(--accent-color); font-weight: 600;">#<%= user.getId()
                                                    %>
                                            </td>
                                            <td>
                                                <%= user.getName() %>
                                            </td>
                                            <td>
                                                <%= user.getEmail() %>
                                            </td>
                                        </tr>
                                        <% } } %>
                            </tbody>
                        </table>

                        <div style="margin-top: 2rem;">
                            <a href="users?action=new" class="btn">Add Another User</a>
                            <a href="index.html" class="btn btn-secondary">Home</a>
                        </div>
                    </div>
                </div>
            </body>

            </html>