<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户修改弹出框</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
  <style>
    .layui-form-select dl { max-height:150px; }
  </style>
</head>
<body>
  <div class="layui-form" lay-filter="layuiadmin-form-posupdate" id="layuiadmin-form-posupdate" style="padding: 20px 40px 0 0;">
    <div class="layui-form-item">
      <label class="layui-form-label">姓名</label>
      <div class="layui-input-inline">
        <input type="text" name="uid" id="uid" value="${us.uid}" style="display:none"  class="layui-input">
        <input type="text" name="name" id="name" value="${us.name}" lay-verify="name"  autocomplete="off" class="layui-input">
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
        <input type="text" name="phone" id="phone" value="${us.phone}"  lay-verify="phone"  autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">备注</label>
      <div class="layui-input-inline">
        <input type="text" name="remark" id="remark" value="${us.remark}" autocomplete="off" class="layui-input">
        <input type="text" name="classon" id="classon" value="${us.company}" style="display:none"  class="layui-input">
        <input type="text" name="classtw" id="classtw"  lay-verify="coid" style="display:none"  class="layui-input">
      </div>
    </div>
    <div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="LAY-posupdate-submit" id="LAY-posupdate-submit" value="确认">
    </div>
  </div>
  <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
  <script src="${pageContext.request.contextPath}/layuiadmin/modules/user.js"></script>
  <script>
    layui.use(['table', 'layer', 'form', 'element', 'jquery','tree','laydate'],
            function () {
              var table = layui.table, layer = layui.layer, form = layui.form,
                      element = layui.element, $ = layui.jquery,
                      tree = layui.tree, laydate = layui.laydate;

              var classon=document.getElementById("classon").value;

              $("#company").val(classon);
              $("#classtw").val(classon);

              form.render('select','layuiadmin-form-posupdate');

              form.on('select(company)', function(data){
                $('#classtw').val(data.value);
              });
            })
  </script>
  <script type="text/javascript">
    layui.use(['layer', 'form', 'element'], function() {
      var form = layui.form;
      form.verify({
        name: [
          /^[\S]{2,4}$/
          ,'请正确输入姓名'
        ],
        coid: [
          /\S/
          ,'请选择用户公司'
        ]


      });
    });
  </script>
</body>
</html>