<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/30/2025
  Time: 8:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category</title>
</head>
<body>
<h3>List Category</h3>
<table border="1">
    <thead>
    <tr>
        <th>No</th>
        <th>Catalog Id</th>
        <th>Catalog Name</th>
        <th>Description</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listCategory}" var="catalog" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${catalog.catalogId}</td>
            <td>${catalog.catalogName}</td>
            <td>${catalog.description}</td>
            <td>${catalog.status?"Active":"Inactive"}</td>
            <td>
                <a href="<%=request.getContextPath()%>/categoryController/initUpdate?catalogId=${catalog.catalogId}">Update</a>
                <a href="<%=request.getContextPath()%>/categoryController/delete?catalogId=${catalog.catalogId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<%=request.getContextPath()%>/categoryController/initCreate">Create new catalog...</a>
</body>
</html>
