<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>公司修改</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
</head>
<body>
  <div class="layui-form" lay-filter="layuiadmin-form-posupdate" id="layuiadmin-form-posupdate" style="padding: 20px 40px 0 0;">
    <div class="layui-form-item">
      <label class="layui-form-label">公司名</label>
      <div class="layui-input-inline">
        <input type="text" name="coid" id="coid" value="${co.coid}" style="display:none"  class="layui-input">
        <input type="text" name="coname" id="coname" value="${co.coname}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">编码</label>
      <div class="layui-input-inline">
        <input type="text" name="conumber" id="conumber" value="${co.conumber}"  autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">备注</label>
      <div class="layui-input-inline">
        <input type="text" name="remark" id="remark" value="${co.remark}" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="LAY-posupdate-submit" id="LAY-posupdate-submit" value="确认">
    </div>
  </div>
  <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
  <script src="${pageContext.request.contextPath}/layuiadmin/modules/user.js"></script>
</body>
</html>