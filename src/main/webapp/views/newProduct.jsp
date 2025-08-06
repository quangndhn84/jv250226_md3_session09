<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/6/2025
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>
</head>
<body>
<style>
    .error {
        color: red;
    }
</style>
<h3>Create a product</h3>
<form:form modelAttribute="productDto" action="${pageContext.request.contextPath}/productController/create"
           method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td><form:label path="productId">Product ID</form:label></td>
            <td><form:input path="productId"/></td>
            <td><form:errors path="productId" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="productName">Product Name</form:label></td>
            <td><form:input path="productName"/></td>
            <td><form:errors path="productName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="price">Price</form:label></td>
            <td><form:input type="number" path="price"/></td>
            <td><form:errors path="price" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="file">Image</form:label></td>
            <td><form:input type="file" path="file"/></td>
            <td><form:errors path="file" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="created">Created</form:label></td>
            <td><input type="date" id="created" name="created"/></td>
            <td><form:errors path="created" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="catalog.catalogId">Catalog</form:label></td>
            <td>
                <form:select path="catalog.catalogId">
                    <c:forEach items="${listCategories}" var="catalog">
                        <form:option value="${catalog.catalogId}" label="${catalog.catalogName}"/>
                    </c:forEach>
                </form:select>
            </td>
            <td><form:errors path="created" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="Create"/>
            </td>
        </tr>
    </table>

</form:form>
</body>
</html>
