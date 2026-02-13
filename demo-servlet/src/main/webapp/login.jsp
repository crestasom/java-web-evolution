<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login | Java Web Evolution</title>
        <link rel="stylesheet" href="css/modern-ui.css">
    </head>

    <body>
        <div class="container">
            <div class="glass-card">
                <h1>Login</h1>
                <p class="subtitle">Secure access to Java Web Evolution</p>

                <% if (request.getAttribute("error") !=null) { %>
                    <div style="color: #ff4d4d; margin-bottom: 20px; text-align: center;">
                        <%= request.getAttribute("error") %>
                    </div>
                    <% } %>

                        <form action="login" method="post">
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input type="text" id="username" name="username" placeholder="admin or user" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" id="password" name="password" placeholder="admin123 or user123"
                                    required>
                            </div>
                            <button type="submit">Login</button>
                        </form>

                        <div
                            style="margin-top: 20px; font-size: 0.8em; color: var(--text-secondary); text-align: center;">
                            <p>Try <strong>admin</strong> / <strong>admin123</strong> (Admin)</p>
                            <p>Try <strong>user</strong> / <strong>user123</strong> (User)</p>
                        </div>
            </div>
        </div>
        <footer>Powered by Java Servlets & JSP</footer>
    </body>

    </html>