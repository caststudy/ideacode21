<%--
  Created by IntelliJ IDEA.
  User: caststudy
  Date: 2021/2/22
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script>

        <!--  文档加载好执行如下代码 -->
        $(
            function () {
                //设置click事件,点击登录
                $("#btn").click(
                    function () {
                        $("#ts").html("")
                        var memid = $("#memid").val().trim();
                        var mempass = $("#mempass").val().trim();

                        if (memid == "" || mempass == "") {
                            $("#ts").html("用户和密码不能为空");
                            return;
                        }

                        $.ajax({
                            type: "post",
                            url: "${pageContext.servletContext.contextPath}/member/login",
                            data: { memid: memid, mempass: mempass},
                            success: function (res) {
                                if (res.indexOf("success") != -1) {
                                    $("#ts").html("登录成功,将转到首页......");
                                    setTimeout("window.location.href='${pageContext.servletContext.contextPath}/fldAction'", 1000);
                                } else {
                                    $("#ts").html("无效的用户和密码");
                                }
                            }
                        })
                    });
                $("#memid").focus();
            }
        );

    </script>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

            <div class="form-group">
                <label for="memid">用户ID</label>
                <input type="text" class="form-control" id="memid" name="memid" value="13888888888" placeholder="用户id......">
            </div>
            <div class="form-group">
                <label for="mempass">用户密码</label>
                <input type="password" class="form-control" id="mempass" name="mempass" value="888888" placeholder="用户密码......">
            </div>

            <div class="form-group">
                <button type="button" id="btn" class="btn btn-default">login</button>
            </div>

            <div class="alert alert-danger" id="ts"></div>


        </div>
    </div>
</div>
</body>
</html>
