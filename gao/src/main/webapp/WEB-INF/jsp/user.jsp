<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <title>Spring 3.0 MVC Series</title>
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/jquery.jqGrid.min.js"></script>
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/i18n/grid.locale-cn.js"></script>
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/jqGridGroup.js"></script>
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/jquery-ui.js"></script>
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/jquery.noty.js"></script>
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/themes/default.js"></script>
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/layouts/center.js"></script>
    <link rel="stylesheet" type="text/css"
          href="<%=application.getContextPath()%>/static/css/ui.jqgrid.css">
    <link rel="stylesheet" type="text/css"
          href="<%=application.getContextPath()%>/static/css/ui.multiselect.css">
    <link rel="stylesheet" type="text/css"
          href="<%=application.getContextPath()%>/static/css/jquery-ui.css">
</head>
<body>
<table border="1">
    <tr>
        <th width="50px">ID</th>
        <th width="100px">用户名</th>
        <th width="200px">用户id</th>
        <th width="200px">身份证</th>
        <th width="200px">生日</th>
    </tr>
    <c:forEach items="${user}" var="user">
        <tr>
            <td align="center">${user.id }</td>
            <td align="center">${user.name }</td>
            <td align="center">${user.user_id }</td>
            <td align="center">${user.idcard }</td>
            <td align="center">${user.birth }</td>
        </tr>
    </c:forEach>
</table>
<div>
    <table id="gridTable"></table>
    <div id="gridPager"></div>
</div>
</body>
</html>