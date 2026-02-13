<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Login | Spring XML Demo</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modern-ui.css">
        </head>

        <body>
            <div class="container">
                <div class="glass-card">
                    <h1>Login</h1>
                    <p class="subtitle">Phase 2: Spring MVC (XML-based) Security</p>

                    <c:if test="${not empty param.error}">
                        <div style="color: #ff4d4d; margin-bottom: 20px; text-align: center;">
                            Invalid username or password.
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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

                    <div style="margin-top: 20px; font-size: 0.8em; color: var(--text-secondary); text-align: center;">
                        <p>Try <strong>admin</strong> / <strong>admin123</strong></p>
                        <p>Try <strong>user</strong> / <strong>user123</strong></p>
                    </div>
                </div>
            </div>
            <footer>Powered by Spring Framework</footer>
        </body>

        </html>