<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row nav-top-container">

    <div class="col-lg-1 col-md-1 col-xs-0"></div>
    <div class="col-lg-10 col-md-10 col-xs-12">

        <nav class="navbar navbar-default">

            <div class="container-fluid">

                <%--首页按钮--%>
                <div class="navbar-header">
                    <a class="btn btn-default btn-lg nav-ico btn-nav-home" href="index.html" role="button">
                        <span aria-hidden="true">首页</span>
                    </a>
                </div>

                <%--用户中心按钮--%>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <a class="btn btn-default btn-lg nav-ico btn-nav-home dropdown-toggle"
                           role="button" data-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false">
                            <span aria-hidden="true">个人</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="user.html">用户中心</a></li>
                            <li><a href="logout.html">退出</a></li>
                        </ul>
                    </ul>
                </div>

            </div>
        </nav>

    </div>
    <div class="col-lg-1 col-md-1 col-xs-0"></div>
</div>
<br>
