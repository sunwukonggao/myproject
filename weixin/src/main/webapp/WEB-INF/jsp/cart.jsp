<%--
  Created by IntelliJ IDEA.
  User: gaojc
  Date: 2014/11/27
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>测试</title>
</head>
<body>
<table border="1">
    <c:forEach items="${pageContext.request.cookies}" var="users">
        <tr>
            <td align="center">${users.name }</td>
            <td align="center">${users.value}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
