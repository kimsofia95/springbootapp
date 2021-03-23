<%--
  Created by IntelliJ IDEA.
  User: cyclo
  Date: 20.03.2021
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
</head>

<body>
<div>
    <form method="POST" action="/login">
        <h2>Вход в систему</h2>
        <div>
            <input name="j_username" type="text" placeholder="Username"
                   autofocus="true"/>
            <input name="j_password" type="password" placeholder="Password"/>
            <button type="submit">Log In</button>
        </div>
    </form>
</div>

</body>
</html>
