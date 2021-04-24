<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>成绩信息</title>
</head>
<body>
<div class="container">
    <%@ include file="menu.jsp" %>
    <!-- 搜索框-->
    <div class="row clearfix" id="finddiv" style="margin-top: 20px;">
        <div class="col-md-12 column">
            <form role="form" action="sc" method="post">
                <input type="hidden" name="op" value="select"/>
                <div class="form-group">
                    <input type="text" class="form-control" name="sname" placeholder="请输入要搜索名称..."/>
                </div>

                <label>
                    <button type="submit" class="btn btn-default" id="find">搜索</button>
                </label>
                <!--
                <label>
                    <button type="button" class="btn btn-default" id="toadd">增加</button>
                </label>
  -->
            </form>
        </div>
    </div>
    <!-- 显示数据-->
    <div class="row clearfix" id="showdiv">
        <div class="col-md-12 column">
            <table class="table">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>课程名</th>
                    <th>成绩</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.lstSc}" var="v" varStatus="x">
                    <c:choose>
                        <c:when test="${x.index mod 2 ==0}"> <tr > </c:when>
                        <c:otherwise> <tr class="info"></c:otherwise>
                    </c:choose>
                    <td> ${v.sid}</td>
                    <td>${v.sname} </td>
                    <td>${v.cname}</td>
                    <td>${v.score}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- 增加数据-->
    <div class="row clearfix" style="display: none;margin-top: 20px;" id="adddiv">
        <div class="col-md-12 column">


        </div>
    </div>
</div>
<script>
    $(function () {
        //到增加部分
        $("#toadd").click(function () {
            $("#adddiv").css("display", "block");
            $("#showdiv").css("display", "none");
            $("#finddiv").css("display", "none");
        });

        //到搜索，显示部分
        $("#cancel").click(function () {
            $("#adddiv").css("display", "none");
            $("#showdiv").css("display", "block");
            $("#finddiv").css("display", "block");
        });


    })

</script>
</body>
</html>
