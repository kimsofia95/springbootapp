<%--
  Created by IntelliJ IDEA.
  User: cyclo
  Date: 20.03.2021
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
</head>

<body>
<div>
    <table>
        <thead>
        <th>ID</th>
        <th>UserName</th>
        <th>Password</th>
        <th>Roles</th>
        </thead>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role">${role.role} </c:forEach>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <a href="admin/delete/${user.name}">delete</a>
                        <input type="hidden" name="action" value="edit"/>
                        <a href="admin/edit/${user.name}">edit</a>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h2>Add</h2>
    <c:url value="admin/add" var="add"/>
    <a href="${add}">Add new user</a>
    <a href="/logout">Выйти из системы</a>
</div>
</body>
</html>

