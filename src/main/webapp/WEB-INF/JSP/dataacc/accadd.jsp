<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>账号添加弹出框</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/admin.css" media="all">
  <script src="${pageContext.request.contextPath}/jss/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
</head>
<body>
  <div class="layui-form" lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 40px 0 0;">

    <div class="layui-form-item">
      <label class="layui-form-label">权限</label>
      <div class="layui-input-block">
        <select name="authority" id = "authority" lay-filter="remark2">
          <option value="1">管理员</option>
          <option value="2">用户</option>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">用户信息</label>
      <div class="layui-input-block">
        <select name="name" id = "name" lay-search>
          <c:forEach items="${dau}" var="dau">
            <option value="${dau.name}">${dau.name}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">账号</label>
      <div class="layui-input-inline">
        <input type="text" name="account" id="account"  autocomplete="off" class="layui-input">
      </div>
    </div>


    <div class="layui-form-item">
      <label class="layui-form-label">备注</label>
      <div class="layui-input-inline">
        <input type="text" name="remark" id="remark"  autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
  </div>
  <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>  
  <script src="${pageContext.request.contextPath}/layuiadmin/modules/user.js"></script>  
</body>
<script type="text/javascript">
  layui.use(['layer', 'form', 'element'], function() {
    var form = layui.form;
    form.verify({
            name: [
                     /^[\S]{2,4}$/
                   ,'请正确输入姓名'
                   ]

    });
  });
</script>
</html>