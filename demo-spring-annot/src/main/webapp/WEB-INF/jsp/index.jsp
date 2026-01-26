<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Spring MVC Annotation Demo | Java Web Evolution</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modern-ui.css">
    </head>

    <body>
        <div class="container">
            <div class="glass-card">
                <h1>Welcome.</h1>
                <p class="subtitle">Phase 3: Spring MVC (Annotation Config c. 2007)</p>

                <form action="hello" method="post">
                    <div class="form-group">
                        <label for="name">What is your name?</label>
                        <input type="text" id="name" name="name" placeholder="Enter name..." required>
                    </div>
                    <button type="submit">Continue</button>
                </form>
                <hr style="border: 0; border-top: 1px solid var(--card-border); margin: 20px 0;">
                <a href="${pageContext.request.contextPath}/user/form" class="btn"
                    style="text-decoration: none; display: block; text-align: center;">Go to User Registration Demo</a>
            </div>
        </div>
        <footer>Powered by Spring MVC (Annotation)</footer>
    </body>

    </html>