<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <base href="http://localhost:8080/StudentInformationManagement/">
<link rel="icon" type="image/png" href="images/SISicon.png">
    <link rel="stylesheet" href="style.css">
</head>

<body>
<div class="box-form">
    <div class="left">
        <div class="overlay">
            <h1>SIS</h1>
            <p>Student Information System<br>Enjoy your day!!
            </p>
        </div>
    </div>

    <div class="right">
        <h3>LOGIN</h3>
        <form action="Login" method="post">
            <div class="inputs">
                <label for="usertype">User Type:</label>
                <select id="usertype" name="usertype" required>
                    <option value="student">Student</option>
                    <option value="instructor">Instructor</option>
                </select>
            </div>
            <br>
            <div class="inputs">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <br>
            <div class="inputs">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <br><br>
            <div class="remember-me--forget-password">
                <!-- Angular -->
                <label>
                    <input type="checkbox" name="item" checked />
                    <span class="text-checkbox">Remember me</span>
                </label>
                <p>forget password?</p>
            </div>
            <br>
            <button type="submit">Login</button> <!-- Add a type attribute -->
        </form>
    </div>
</div>
</body>
</html>
