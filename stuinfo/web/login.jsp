<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix" style="margin-top:10%;">
        <div class="col-md-12 column">

            <form role="form">

                <div class="form-group">
                    <label for="name" id="ts" style="color: red;"></label>
                    <input type="text" class="form-control" id="name" value="admin" placeholder="请输入名称..."/>
                </div>

                <div class="form-group">
                    <label for="pass"></label>
                    <input type="password" class="form-control" id="pass" value="admin123@#$" placeholder="请输入密码..."/>

                </div>
                <div class="form-group">
                    <label for="rand">
                        <input type="text" id="rand" class="form-control" placeholder="请输入验证码..."/></label>
                    <label> <img src="${pageContext.servletContext.contextPath}/idcode" id="randtp"></label>

                </div>

                <button type="button" class="btn btn-default" id="login">登录</button>

            </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        //设置登录点击事件执行代码
        $("#randtp").click(function () {
            $(this).prop("src", "${pageContext.servletContext.contextPath}/idcode?t=" + new Date().getMilliseconds());
        });


        $("#login").click(function () {
            //校验验证码
            var b = true;
            var inrand = $("#rand").val();
            $.post({
                async: false,
                url: "${pageContext.servletContext.contextPath}/idcode?t1=" + new Date().getMilliseconds(),
                success: function (rand) {
                    console.log(rand);
                    console.log(inrand != rand);
                    if (inrand != rand) {
                        $("#ts").html("请输入有效验证码");
                        b = false;
                        console.log(b)
                    }
                }
            });

            if (!b) return;
            //校验用户和密码
            var name = $("#name").val();
            var pass = $("#pass").val();


            if (name == "" || pass == "") {
                $("#ts").html("用户或密码不能为空");
                return;
            }

            //发登录请求
            $.post({
                url: "${pageContext.servletContext.contextPath}/admin",
                data: {name: name, pass: pass, op: "login"},
                success: function (res) {
                    if (res.indexOf("success") != -1) {
                        $("#ts").html("登录成功");
                        setTimeout("window.location.href='admin.jsp'", 600);
                    } else {
                        $("#ts").html("无效的用户或密码");
                    }

                }
            });

        });


    })

</script>
</body>
</html>
