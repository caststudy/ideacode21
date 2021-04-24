<%--
  Created by IntelliJ IDEA.
  User: caststudy
  Date: 2021/2/22
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bootstrap 101 Template</title>

  <!-- Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
  <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
  function  del(flda) {
    if(confirm("确定要删除吗?")){
      $.ajax({
        type:"post",
        url:"${pageContext.servletContext.contextPath}/fldAction/ajaxdel",
        data:{flda:flda,test:"@#$"},
        success:function (res) {
          alert(res)
          if(res.indexOf("success")!=-1)
            $("#"+flda).remove();
        }
      });
    }
    
  }
</script>
<body>
<div class="container">
  <div class="row clearfix">
    <div class="col-md-12 column">
      <form action="fldAction" method="post">
        <input type="hidden" name="op" value="select">
        <div class="form-group">
          <label for="fldb">FLDB</label>
          <input type="text" class="form-control" id="fldb"  name="fldb"  placeholder="要搜索字符串">
        </div>
        <div class="form-group">
          <button type="submit" class="btn btn-default">select</button>
          <button type="button" class="btn btn-default" onclick="window.location.href='${pageContext.servletContext.contextPath}/fldAction/insert'">insert</button>
          <button type="button" class="btn btn-default" style="float: right"  onclick="window.location.href='${pageContext.servletContext.contextPath}/member/logout'">logout</button>
        </div>
      </form>
    </div>
    <div class="col-md-12 column">
      <table class="table table-striped">
        <thead>
        <tr>
          <th>FLDA信息</th>
          <th>FLDB信息</th>
          <th>FLDC信息</th>
          <th>FLDD信息</th>
          <th>处理</th>
        </tr>
        <c:forEach items="${sessionScope.lstFld}" var="fld" >
          <tr id="${fld.flda}">
            <td> ${fld.flda}</td>
            <td>${fld.fldb}</td>
            <td>${fld.fldc}</td>
            <td>${fld.fldd}</td>
            <td>
              <a href="${pageContext.servletContext.contextPath}/fldAction/delete?flda=${fld.flda}" onclick="return confirm('确定要删除吗?')">删除</a>
              <a href="javascript:void(0)" onclick="del(${fld.flda})">异步删除</a>
            </td>
          </tr>
        </c:forEach>
        </thead>

      </table>
    </div>
  </div>
</div>
</body>
</html>
