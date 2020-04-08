<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>设置我的资料</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/admin.css" media="all">
</head>
<body>
<div class="layui-fluid">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md12">
      <div class="layui-card">
        <div class="layui-card-header">查看我的信息</div>
        <div class="layui-card-body" pad15>
          
          <div class="layui-form" lay-filter="">
            <div class="layui-form-item">
              <label class="layui-form-label">姓名</label>
              <div class="layui-input-inline">
                <input type="text" id="name" readonly style="background-color: rgba(159,159,159,0.34)" class="layui-input">
              </div>
            </div>
            <div class="layui-form-item">
              <label class="layui-form-label">账号</label>
              <div class="layui-input-inline">
                <input type="text" id="account" readonly style="background-color: rgba(159,159,159,0.39)" class="layui-input">
              </div>
            </div>
            <div class="layui-form-item">
              <label class="layui-form-label">电话</label>
              <div class="layui-input-inline">
                <input type="text" id="phone" readonly style="background-color: rgba(159,159,159,0.39)"  class="layui-input">
              </div>
            </div>
            <div class="layui-form-item">
              <label class="layui-form-label">公司名</label>
              <div class="layui-input-inline">
                <input type="text" id="company" readonly style="background-color: rgba(159,159,159,0.39)" class="layui-input">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/jss/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/jss/jquery.easyui.min.js"></script>
<script>
  $(document).ready(function(){
    $.ajax({
      url:'${pageContext.request.contextPath}/DataUser/meinfo',
      type:'post',
      success: function (data) {
        var result=eval('('+data+')');
        $('#account').val(result.data[0].account)
        $('#name').val(result.data[0].dataUser.name)
        $('#phone').val(result.data[0].dataUser.phone)
        $('#company').val(result.cana)
      }
    })
  })
</script>
</body>
</html>