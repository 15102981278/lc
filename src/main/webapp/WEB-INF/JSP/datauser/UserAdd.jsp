<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户添加弹出框</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/admin.css" media="all">
  <script src="${pageContext.request.contextPath}/jss/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
  <style>
    .layui-form-select dl { max-height:150px; }
  </style>
</head>
<body>
  <div class="layui-form" lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 40px 0 0;">
    <div class="layui-form-item">
      <label class="layui-form-label">姓名</label>
      <div class="layui-input-inline">
        <input type="text" name="name" id="name" lay-verify="name"  autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">所属公司</label>
      <div class="layui-input-block">
        <select name="company" id = "company" lay-search lay-filter="company">
          <option value="">请选择所在公司</option>
          <c:forEach items="${co}" var="co">
            <option value="${co.coid}">${co.coname}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">电话</label>
      <div class="layui-input-inline">
        <input type="text" name="phone" id="phone"  lay-verify="phone"  autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">备注</label>
      <div class="layui-input-inline">
        <input type="text" name="remark" id="remark"  autocomplete="off" class="layui-input">
        <input type="hidden" name="coid" id="coid" lay-verify="comp" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
  </div>
  <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>  
  <script src="${pageContext.request.contextPath}/layuiadmin/modules/user.js"></script>  
</body>
<script>
  layui.use(['table', 'layer', 'form', 'element', 'jquery','tree'],
          function () {
            var  form = layui.form;
            form.on('select(company)', function(data){
              console.log(data.elem); //得到select原始DOM对象
              console.log(data.value); //得到被选中的值
              console.log(data.othis); //得到美化后的DOM对象
              $('#coid').val(data.value);
            });

            form.verify({
              name: [
                /^[\S]{2,4}$/
                ,'请正确输入姓名'
              ],
              comp: [
                /\S/
                ,'请选择用户所在公司'
              ]

            });

          })
</script>
</html>