<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理员</title>
</head>
<body>
<div class="container">
    <%@ include file="menu.jsp" %>
    <!-- 搜索框-->
    <div class="row clearfix" id="finddiv" style="margin-top: 20px;">
        <div class="col-md-12 column">
            <form role="form" action="admin" method="post">
                <input type="hidden" name="op" value="select"/>
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="请输入要搜索名称..."/>
                </div>
                <label>
                    <button type="submit" class="btn btn-default" id="find">搜索</button>
                </label>
                <label>
                    <button type="button" class="btn btn-default" id="toadd">增加</button>
                </label>
            </form>
        </div>
    </div>
    <!-- 显示数据-->
    <div class="row clearfix" id="showdiv">
        <div class="col-md-12 column">
            <table class="table">
                <thead>
                <tr>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>权限</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.lstAdmin}" var="v" varStatus="x">
                    <c:choose>
                        <c:when test="${x.index mod 2 ==0}"> <tr > </c:when>
                        <c:otherwise> <tr class="info"></c:otherwise>
                    </c:choose>
                    <td> ${v.name} </td>
                    <td> ******</td>
                    <td> ${v.privi}</td>
                    <td> 删除</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- 增加数据-->
    <div class="row clearfix" style="display: none;margin-top: 20px;" id="adddiv">
        <div class="col-md-12 column">
            <form role="form">
                <div class="form-group">
                    <label for="name" id="ts" style="color: red;"></label>
                    <input type="text" class="form-control" id="name" value="admin" placeholder="请输入名称..."/>
                </div>
                <div class="checkbox">
                    <label><input type="checkbox" name="p"/>管理员维护</label>
                    <label><input type="checkbox" name="p"/>学生管理</label> &nbsp;
                    <label><input type="checkbox" name="p"/>老师管理</label>&nbsp;
                    <label><input type="checkbox" name="p"/>课程管理</label>&nbsp;
                    <label><input type="checkbox" name="p"/>成绩管理</label>&nbsp;
                </div>
                <label>
                    <button type="button" class="btn btn-default" id="insert" disabled>增加</button>
                </label>
                <label>
                    <button type="button" class="btn btn-default" id="cancel">取消</button>
                </label>
            </form>
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

        //校验用户名
        $("#name").keyup(function () {
            var name = $(this).val();
            if (name == "") {
                $("#ts").html("用户名不能为空");
                return;
            }

            $.post({
                url: "${pageContext.servletContext.contextPath}/admin",
                data: {name: name, op: "check"},
                success: function (res) {
                    if (res.indexOf("exist") != -1) {
                        $("#ts").html("账号已被占用");
                        $("#insert").prop("disabled", true);
                    } else {
                        $("#ts").html("账号可用");
                        $("#insert").prop("disabled", false);
                    }
                }
            })
        })   //end of check

        //增加
        $("#insert").click(function () {
            var privi = "";
            $("input[name='p']").each(function () {
                privi = privi + (this.checked ? '1' : '0');
            });
            // console.log(privi);
            var name = $("#name").val();
            // var pass = $("#pass").val();
            if (name == "") {
                $("#ts").html("用户不能为空");
                return;
            }
            //发登录请求
            $.post({
                url: "${pageContext.servletContext.contextPath}/admin",
                data: {name: name, privi: privi, op: "insert"},
                success: function (res) {
                    if (res.indexOf("success") != -1) {
                        $("#ts").html("增加成功");
                        setTimeout("window.location.href='${pageContext.servletContext.contextPath}/admin'", 400);
                    } else {
                        $("#ts").html("增加失败");
                    }
                }
            });

        });  //end of insert


    })

</script>
</body>
</html>
