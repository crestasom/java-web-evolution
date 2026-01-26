<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>User List - Spring XML Evolution</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap"
                rel="stylesheet">
        </head>

        <body>
            <div class="container" style="max-width: 800px;">
                <div class="glass-card">
                    <h1>User Registered</h1>
                    <p class="subtitle">Data retrieved via Spring JdbcTemplate</p>

                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td style="color: var(--accent-color); font-weight: 600;">#${user.id}</td>
                                    <td>${user.name}</td>
                                    <td>${user.email}</td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty users}">
                                <tr>
                                    <td colspan="3" style="text-align: center; color: var(--text-secondary);">No users
                                        found.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>

                    <div style="margin-top: 2rem;">
                        <a href="${pageContext.request.contextPath}/user/form" class="btn">Add Another User</a>
                        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Home</a>
                    </div>
                </div>
            </div>
        </body>

        </html>