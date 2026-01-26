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
                    <div class="form-group">
                        <label for="name">Full Name</label>
                        <input type="text" id="name" name="name" placeholder="John Doe" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email Address</label>
                        <input type="email" id="email" name="email" placeholder="john@example.com" required>
                    </div>
                    <button type="submit">Save User</button>
                </form>
                <a href="${pageContext.request.contextPath}/user/list" class="btn btn-secondary">Back to List</a>
            </div>
        </div>
    </body>

    </html>