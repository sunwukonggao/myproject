<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>商品交易平台</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/static/css/index.css">
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=application.getContextPath()%>/static/js/login.js"></script>
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/packaged/jquery.noty.packaged.min.js"></script>
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/themes/relax.js"></script>
    <script type="text/javascript"
            src="<%=application.getContextPath()%>/static/js/layouts/center.js"></script>
    <script src="<%=application.getContextPath()%>/static/bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/static/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/static/stylesheets/theme.css">
    <link rel="stylesheet" href="<%=application.getContextPath()%>/static/font-awesome/css/font-awesome.css">
</head>
<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <ul class="nav pull-right"></ul>
            <a class="brand" href="index"><span class="first">商品交易</span> <span class="second">平台</span></a>
        </div>
    </div>
</div>
<body>
<form id="formBt" action="">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="dialog span4">
                <div class="block">
                    <div class="block-heading">用户登录</div>
                    <div class="block-body">
                        <form>
                            <label>用户名</label>
                            <input pe="text" id="name" name="user_id" class="span12"/>
                            <label>密码</label>
                            <input id="password" name="passwd" type="password" class="span12"/>
                            <input type="button" id="Submitbtn" class="btn btn-primary pull-right" value="提交"/>
                            <label class="remember-me"><input type="checkbox">记住我</label>

                            <div class="clearfix"></div>
                        </form>
                    </div>
                </div>
                <p><a href="reset-password.html">忘记密码?</a></p>
            </div>
        </div>
    </div>
</form>
</body>
</html>
