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
  <title>Bootstrap 101 Template</title>

  <!-- Bootstrap -->
  <link href="bootstrap.min.css" rel="stylesheet">
  <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
  <script src="jquery.min.js"></script>
  <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
  <script src="bootstrap.min.js"></script>
  <script>

    var  s=[false,false,false];  //定义一个数组
    //根据数据的有效性，设置提交按钮是否可用
    function setBtnStatus(){
      $("#btn").prop("disabled",false);
      for(var i=0;i<s.length;i++){
        if(!s[i]) $("#btn").prop("disabled",true);
      }
    }

    <!--  文档加载好执行如下代码 -->
    $(function () {

              //设置keyup事件
              $("#flda").keyup(function(){

                $("#ts").html("");
                var flda=$(this).val();

                s[0]=true;
                if(flda==""|| isNaN(flda) || flda.indexOf(".")!=-1) {
                  s[0]=false;
                  $("#ts").html("FLDA信息为有效整数");
                }

                if(s[0]){
                  //数据有效，发起异步请求，校验数据库是否存在
                  $.ajax({
                    type:"post",
                    url:"fldAction",
                    data:{op:"check",flda:flda},
                    success:function(res){
                      if(res.indexOf("exist")!=-1){
                        s[0]=false;
                        $("#ts").html("编号已存在");
                      }else{
                        $("#ts").html("编号可用");
                      }

                    }
                  })
                }
                setBtnStatus();
              });

              $("#fldb").keyup(function(){
                $("#ts").html("")
                var fldb=$(this).val();
                s[1]=true;
                if(fld=="") {b
                  s[1]=false;
                  $("#ts").html("FLDB信息不能为空");
                }
                setBtnStatus();
              });

              $("#fldc").keyup(function(){
                $("#ts").html("")
                var fldc=$(this).val();
                s[2]=true;
                if(fldc==""|| isNaN(fldc) ) {
                  s[2]=false;
                  $("#ts").html("FLDC信息为有效浮点数");
                }
                setBtnStatus();
              });

              $("#frm").submit(function () {
                $("#ts").html("")
                var fldd=$("#fldd").val();
                if(fldd=="") { $("#ts").html("请选择日期")
                  return false;
                }
              });

              $("#flda").focus();
              setBtnStatus();

            }
    );

  </script>

<body>
<div class="container">
  <div class="row clearfix">
    <div class="col-md-12 column">
      <form action="fldAction" method="post"  id="frm">
        <input type="hidden" name="op" value="insert">

        <div class="form-group">
          <label for="flda">FLDA</label>
          <input type="text" class="form-control" id="flda"  name="flda"  value="${param.flda}" placeholder="整形主键">
        </div>
        <div class="form-group">
          <label for="fldb">FLDB</label>
          <input type="text" class="form-control" id="fldb"  name="fldb"  value="${param.flda}" placeholder="字符串">
        </div>
        <div class="form-group">
          <label for="fldc">FLDC</label>
          <input type="text" class="form-control" id="fldc"  name="fldc" value="${param.flda}" placeholder="浮点格式">
        </div>
        <div class="form-group">
          <label for="fldd">FLDD</label>
          <input type="date" class="form-control" id="fldd"  name="fldd" >
        </div>
        <div class="form-group">
          <button type="submit"  id="btn"  class="btn btn-default">insert</button>
        </div>

        <div class="alert alert-danger"   id="ts">${requestScope.error}</div>

      </form>
    </div>
  </div>
</div>
</body>
</html>
