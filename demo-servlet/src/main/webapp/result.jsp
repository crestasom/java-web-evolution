<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hello | Java Web Evolution</title>
        <link rel="stylesheet" href="css/modern-ui.css">
    </head>

    <body>
        <div class="container">
            <div class="glass-card">
                <h1>Hello, <span class="result-val">${userName}</span>!</h1>
                <p class="subtitle">This response was handled by a Java Servlet and rendered via JSP expression
                    language.</p>

                <button onclick="window.history.back()">Go Back</button>
            </div>
        </div>
        <footer>Powered by Java Servlets & JSP</footer>
    </body>

    </html>