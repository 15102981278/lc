<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>账号修改弹出框</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
  <style>
    .layui-form-select dl { max-height:150px; }
  </style>
</head>
<body>
  <div class="layui-form" lay-filter="layuiadmin-form-accupdate" id="layuiadmin-form-accupdate" style="padding: 20px 40px 0 0;">
    <div class="layui-form-item">
      <label class="layui-form-label">身份</label>
      <div class="layui-input-block">
        <select name="authority" id = "authority">
          <option value="2">用户</option>
          <option value="1">管理员</option>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">用户</label>
      <div class="layui-input-block">
        <select name="name" id = "name" lay-search lay-filter="remark1">
          <option value="">请选择对应用户</option>
          <c:forEach items="${dau}" var="dau">
            <option value="${dau.uid},${dau.phone}">${dau.name}～${dau.phone}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">账号</label>
      <div class="layui-input-inline">
        <input type="text"   name="account" id="account" value="${dac.account}" placeholder="第一个账号默认为电话号码"  lay-verify="required" autocomplete="off" class="layui-input">
        <input type="hidden" name="u_id" id="u_id" lay-verify="u_id" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">备注</label>
      <div class="layui-input-inline">
        <input type="text" name="remark" id="remark" value="${dac.remark}" autocomplete="off" class="layui-input">
        <input type="text" name="classtw" id="classtw" value="${dac.authority}" style="display:none"  class="layui-input">
        <input type="text" name="classth" id="classth" value="${dac.u_id}" style="display:none"  class="layui-input">
        <input type="text" name="classfo" id="classfo" value="${pho.phone}" style="display:none"  class="layui-input">
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

              var classtw=document.getElementById("classtw").value;
              var classth=document.getElementById("classth").value;
              var classfo=document.getElementById("classfo").value;
              $("#authority").val(classtw);
              $("#u_id").val(classth);
              $("#name").val(classth+','+classfo);


              form.render('select','layuiadmin-form-accupdate');

              form.on('select(remark1)', function(data){
                var str = data.value.split(",");
                $('#u_id').val(str[0]);
                $('#account').val(str[1]);
              });
            })
  </script>
  <script type="text/javascript">
    layui.use(['layer', 'form', 'element'], function() {
      var form = layui.form;
      form.verify({
        u_id: [
          /\S/
          ,'请选择对应用户'
        ]

      });
    });
  </script>
</body>
</html>