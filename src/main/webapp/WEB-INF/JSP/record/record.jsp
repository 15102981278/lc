<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>日志管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/admin.css" media="all">
</head>
<body>
    <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">操作人</label>
            <div class="layui-input-block">
              <input type="text" name="recname" id = "recname" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">操作账号</label>
            <div class="layui-input-block">
              <input type="text" name="recaccount" id = "recaccount" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button   class="layui-btn layuiadmin-btn-useradmin" onclick="return false;" data-type="reload" id="selectPos" >
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      <div class="layui-card-body">
        <div class="layui-btn-group" style=" padding-bottom: 10px;">
             <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="exportrec()">导出表格</button>
        </div>
        
        <table id="test-table-height" lay-filter="test-table-height"></table>
      </div>
    </div>
  </div>
  
  <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
  <script src="${pageContext.request.contextPath}/jss/jquery-1.10.2.js"></script>
  <script src="${pageContext.request.contextPath}/jss/jquery.easyui.min.js"></script>
  <script>
  layui.use('table', function(){ 
	  var table = layui.table;
	  table.render({
	       elem: '#test-table-height'
	      ,url: '${pageContext.request.contextPath}/DataRecord/selectDare'
	      ,height:675
	      ,cellMinWidth: 80
	      ,page: true
	      ,limit: 20
	      ,limits:[15,20,30,50,100]
	      /*,skin:'nob'*/
	      ,even: true
	      ,cols: [[
             {field:'recid',title:'序号',width:170, sort: true, fixed: true,type:'numbers'}
            ,{type:'checkbox'}
	        ,{field:'recname', title: '操作人',  align: 'center'}
            ,{field:'recaccount', title: '操作账号',  align: 'center'}
            ,{field:'recmodule', title: '操作模块',  align: 'center'}
            ,{field:'recip', title: '登录IP',  align: 'center'}
            ,{field:'rectime', title: '操作时间',  align: 'center'}
            ,{field:'recoperation', title: '动作',  align: 'center'}
	      ]]
	  });
	  
	//根据条件查询表格数据重新加载
	  var $ = layui.$, active = {
		    reload: function(){
		    //获取查询条件
		      var recname=document.getElementById("recname").value;
              var recaccount=document.getElementById("recaccount").value;
		      //执行重载
		      table.reload('test-table-height', {
		    	 url : '${pageContext.request.contextPath}/DataRecord/selectDare'
		        ,page: {
		          curr: 1
		        }
		      //根据条件查询
		        ,where: {
                    recname:recname,
                    recaccount:recaccount
		        }
		      });
		   }
		};
	 //点击搜索按钮查询
	  $('#selectPos').on('click',
	      function(){
	          var type = $(this).data('type');
	          active[type] ? active[type].call(this) : '';
	   });
  });

  function exportrec(){
    var recaccount = document.getElementById("recaccount").value;
    var recname = document.getElementById("recname").value;

   $.ajax({
      url:'${pageContext.request.contextPath}/keyword/passThrough?juau=reco_exp',
      type:'post',
      success: function (data) {
        var result = eval('(' + data + ')');
        if (result.code == -1) {
          layer.msg('您无此操作权限');
        }else{
          window.location.href="${pageContext.request.contextPath}/DataRecord/exportrec?recaccount="+recaccount+"&recname="+recname;
        }
      }
    })
  }
  </script>
</body>
</html>
