<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

<!-- 显示图片 -->
<div class="row clearfix" style="margin-top: 20px;">
    <div class="col-md-12 column">
        显示图片 。。。。
    </div>
</div>
<!-- 显示菜单 -->
<div class="row clearfix" style="margin-top: 20px;">
    <div class="col-md-12 column">
        <ul class="nav nav-tabs">
            <li role="presentation" class="menu" id="admin"><a href="admin.jsp">管理员信息</a></li>
            <li role="presentation" class="menu" id="student"><a href="student.jsp">学生信息</a></li>
            <li role="presentation" class="menu" id="teacher"><a href="teacher.jsp">老师信息</a></li>
            <li role="presentation" class="menu" id="course"><a href="course.jsp">课程信息</a></li>
            <li role="presentation" class="menu" id="sc"><a href="sc.jsp">成绩信息</a></li>
            <li role="presentation" style="float: right"><a
                    href="${pageContext.servletContext.contextPath}/admin?op=logout">注销</a></li>

            <script>
                var path = window.location.pathname;
                path = path.substring(1, path.length - 4);
                console.log(path)
                $(".menu").prop("class", '');
                $("#" + path).prop("class", 'active');

            </script>
        </ul>
    </div>
</div>