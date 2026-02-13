<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add User - Spring Annotation Evolution</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <div class="glass-card">
                <h1>Add New User</h1>
                <p class="subtitle">Spring MVC Annotation Persistence Demo (Spring Data JPA)</p>

                <form action="${pageContext.request.contextPath}/user/save" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div class="form-group">
                        <label for="name">Full Name</label>
                        <input type="text" id="name" name="name" placeholder="John Doe" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email Address</label>
                        <input type="email" id="email" name="email" placeholder="john@example.com" required>
                    </div>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" placeholder="jdoe" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="role">Role</label>
                        <select id="role" name="role" class="form-control"
                            style="width: 100%; padding: 10px; border-radius: 8px; border: 1px solid var(--card-border); background: var(--card-bg); color: var(--text-primary);">
                            <option value="ROLE_USER">User</option>
                            <option value="ROLE_ADMIN">Admin</option>
                        </select>
                    </div>
                    <button type="submit">Save User</button>
                </form>
                <a href="${pageContext.request.contextPath}/user/list" class="btn btn-secondary">Back to List</a>
            </div>
        </div>
    </body>

    </html>