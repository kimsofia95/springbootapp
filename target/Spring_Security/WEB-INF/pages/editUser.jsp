<%--
  Created by IntelliJ IDEA.
  User: cyclo
  Date: 18.03.2021
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty user.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty user.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty user.name}">
    <c:url value="/admin/add" var="var"/>
</c:if>
<c:if test="${!empty user.name}">
    <c:url value="/admin/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
<c:if test="${!empty user.name}">
    <input type="hidden" name="id" value="${user.id}">
</c:if>
    <label for="name">Name</label>
    <input type="text" name="name" id="name">
    <label for="password">Password</label>
    <input type="text" name="password" id="password">
    <label for="editRoles"></label>
    <select id="editRoles" name="roles" multiple size="${allRoles.size()}" required
                                           class="form-control form-control-sm">
<c:forEach items="${allRoles}" var="role">
    <option value="${role.role}"  ${role.role} selected   >${role.role}</option>
</c:forEach>
    </select>
    <c:if test="${empty user.name}">
        <input type="submit" value="Add new user">
    </c:if>
    <c:if test="${!empty user.name}">
        <input type="submit" value="Edit user">
    </c:if>
</form>
</body>
</html>
