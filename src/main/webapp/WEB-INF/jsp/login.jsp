<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Login</title>
    <jsp:include page="common/head.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/login.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/login.js"></script>
</head>
<body>

<div class="container">
    <div class="login-container">
        <div id="output"></div>
        <div class="avatar">
            <img class="headpic cursor-pointer" src="${pageContext.request.contextPath}/resource/pics/2.png" alt=""
                 width="70%" style="margin-top: 15px;">
        </div>

        <%--登录--%>
        <c:if test="${page==0}">
            <div class="form-box">
                <form action="<c:url value="/checkLogin.html"/>" method="post">
                        <%--以下两条语句是为了防止浏览器自动填充用户名和密码--%>
                    <input type="text" style="display:none">
                    <input type="password" style="display:none">
                    <input name="userName" type="text" placeholder="请输入用户名" autocomplete="off">
                    <input name="password" type="password" placeholder="请输入密码" autocomplete="off">
                    <button class="btn btn-info btn-block login" type="submit">登录</button>
                    <br>
                    <a href="login.html?page=1">点击注册</a>
                </form>
            </div>
        </c:if>

        <%--注册--%>
        <c:if test="${page==1}">
            <div class="form-box">
                <form action="<c:url value="checkRegister.html"/>" method="post">
                    <input type="text" style="display:none">
                    <input type="password" style="display:none">
                    <input name="userName" type="text" placeholder="请输入用户名" autocomplete="off">
                    <input name="password" type="password" placeholder="请输入密码">
                    <input name="confirmPassword" type="password" placeholder="请确认密码" autocomplete="off">
                    <input name="headpic" type="text" value="4" style="display:none">
                    <button class="btn btn-info btn-block login" type="submit">注册</button>
                    <br>
                    <a href="login.html?page=0">点击登录</a>
                </form>
            </div>
        </c:if>

    </div>
</div>

<jsp:include page="common/foot.jsp"/>
</body>
</html>


