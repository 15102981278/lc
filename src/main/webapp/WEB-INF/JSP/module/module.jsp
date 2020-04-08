<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>模块管理</title>
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
            <label class="layui-form-label">IMEI号</label>
            <div class="layui-input-block">
              <input type="text" name="module_number" id = "module_number" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">平台设备ID</label>
            <div class="layui-input-block">
              <input type="text" name="platform_id" id = "platform_id" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">发货公司</label>
            <div class="layui-input-block">
              <input type="text" name="conam" id = "conam" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>

            <div class="layui-inline">
              <label class="layui-form-label">最近上报</label>
              <div class="layui-input-inline">
                <input type="text" class="layui-input" readonly id="last_time"  readonly="" placeholder="yyyy-MM-dd HH:mm:ss">
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
             <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="add()">
             		<i class="layui-icon">&#xe654;</i></button>
             <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="update()"><i class="layui-icon">&#xe642;</i></button>
             <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="delet()"><i class="layui-icon">&#xe640;</i></button>
             <button class="layui-btn layui-btn-primary layui-btn-sm"  id="upload" >导入表格</button>
             <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="exportall()">导出表格</button>
             <a href="${pageContext.request.contextPath}/Position/export.do" class="layui-btn layui-btn-primary layui-btn-sm">导出模板</a>
        </div>
        
        <table id="test-table-height" lay-filter="test-table-height"></table>
      </div>
    </div>
  </div>
  
  <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
  <script src="${pageContext.request.contextPath}/jss/jquery-1.10.2.js"></script>
  <script src="${pageContext.request.contextPath}/jss/jquery.easyui.min.js"></script>
  <script>
  layui.use(['table', 'layer', 'form', 'element', 'jquery','tree','laydate'], function(){
	  var table = layui.table;
      var laydate =  layui.laydate;
	  table.render({
	       elem: '#test-table-height'
	      ,url: '${pageContext.request.contextPath}/datamodule/selectDaco'
	      ,height:675
	      ,cellMinWidth: 80
	      ,page: true
	      ,limit: 20
	      ,limits:[10,20,30,50,100]
	      ,even: true
	      ,cols: [[
             {field:'modid',title:'序号',width:60, sort: true, fixed: true,type:'numbers'}
            ,{type:'checkbox'}
	        ,{field:'module_number', title: 'IMEI号',  align: 'center'}
            ,{field:'module_sim', title: 'SIM卡号',  align: 'center'}
            ,{field:'platform_id', title: '平台设备ID',  align: 'center'}
            ,{field:'register_time', title: '注册时间',  align: 'center'}
            ,{field:'last_time', title: '最近上报时间',  align: 'center'}
            ,{field:'dqtime', title: '在线状态1',  align: 'center'}
            ,{title: '在线状态2',  align: 'center',
              templet:function(d){
                  stat="<text>"+d.last_time+"</text >";
                return stat;
              }}
            ,{title: '是否首次测试',  align: 'center',
              templet:function(d){
                stat="";
                if(d.test_state == 0){
                  stat=" <button class='layui-btn layui-btn-primary layui-btn-xs'>未测试</button>";
                }else{
                  stat="<button class='layui-btn  layui-btn-xs' style='background-color: rgba(60,139,47,0.69)'>已测试</button>";
                }
                return stat;
              }}
            ,{title: '发货公司',  align: 'center',
              templet:function(obj){
                return obj.dataCompany.coname;
              }}
            ,{field:'username', title: '操作人',  align: 'center'}
            ,{field:'remark', title: '备注',  align: 'center'}
	      ]]
	  });
	  
	//根据条件查询表格数据重新加载
	  var $ = layui.$, active = {
		    reload: function(){
		    //获取查询条件
		      var module_number=document.getElementById("module_number").value;
              var platform_id=document.getElementById("platform_id").value;
              var conam=document.getElementById("conam").value;
              var last_time=document.getElementById("last_time").value;

		      //执行重载
		      table.reload('test-table-height', {
		    	 url : '${pageContext.request.contextPath}/datamodule/selectDaco'
		        ,page: {
		          curr: 1
		        }
		      //根据条件查询
		        ,where: {
                  module_number:module_number,
                  platform_id:platform_id,
                  conam:conam,
                  last_time:last_time
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

    laydate.render({
      elem: '#last_time'
      ,range: true
      ,type: 'datetime'
      ,theme: '#6d8dcc'
    });
  });
  function add(){
    $.ajax({
      url:'${pageContext.request.contextPath}/keyword/passThrough?juau=user_add',
      type:'post',
      success: function (data) {
        var result=eval('('+data+')');
        if(result.code == -1){
          layer.msg('您无此操作权限');
        }else{
          layer.open({
            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            type:2,
            title:"添加用户",
            area: ['22%','45%'],
            offset: '100px',
            content:'${pageContext.request.contextPath}/StyleJump/UserJump?num=UserAdd',
            btn: ['确定','取消'],
            yes: function(index,layero){

              var iframeWindow = window['layui-layer-iframe'+ index]
                      ,submitID = 'LAY-user-front-submit'
                      ,submit = layero.find('iframe').contents().find('#'+ submitID);

              //监听提交
              iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var body = layer.getChildFrame('body', index);
                var name = body.find('#name').val().replace(/\s/g,"");
                var phone = body.find('#phone').val().replace(/\s/g,"");
                var company = body.find('#company').val().replace(/\s/g,"");
                var remark = body.find('#remark').val().replace(/\s/g,"");
                $.ajax({
                  url:'${pageContext.request.contextPath}/DataUser/addDaus',
                  type:'post',
                  data:{
                    "name":name,
                    "phone":phone,
                    "company":company,
                    "remark":remark
                  },
                  success: function (data) {
                    var result=eval('('+data+')');
                    if(result.code == 1){
                      layer.closeAll('iframe');
                      layer.alert('添加成功', {
                        skin: 'layui-layer-molv'
                        ,closeBtn: 0
                        ,icon:1
                        ,end:function(){
                          location.reload();
                        }
                        ,time:800
                      })
                    }else if(result.code == 2){
                      layer.msg('电话号码已存在,请重新输入');
                    }else{
                      layer.alert('添加失败', {
                        skin: 'layui-layer-molv'
                        ,closeBtn: 0
                      })
                    }
                  }
                });
              })
              submit.trigger('click');
            }
          })
        }
      }
    });
  }

  function update(){

    $.ajax({
      url:'${pageContext.request.contextPath}/keyword/passThrough?juau=user_upd',
      type:'post',
      success: function (data) {
        var result = eval('(' + data + ')');
        if (result.code == -1) {
          layer.msg('您无此操作权限');
        } else {
          var table = layui.table
          var checkStatus = table.checkStatus('test-table-height'),
                  data = checkStatus.data;

          if(data.length != 1){
            layer.msg('请选择一条数据');
          }else{
            var uid = data[0].uid
            layer.open({
              //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层 ）4（tips层）
              type:2,
              title:"修改楼层/商户",
              area: ['22%','45%'],
              offset: '100px',
              content:'${pageContext.request.contextPath}/StyleJump/UserJump?num=UserUpd&uid=' + uid,
              btn: ['确定','取消'],
              yes: function(index,layero){


                var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submitID = 'LAY-posupdate-submit'
                        ,submit = layero.find('iframe').contents().find('#'+ submitID);

                //监听提交
                iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                  var body = layer.getChildFrame('body', index);
                  var name = body.find('#name').val().replace(/\s/g,"");
                  var phone = body.find('#phone').val().replace(/\s/g,"");
                  var company = body.find('#company').val().replace(/\s/g,"");
                  var remark = body.find('#remark').val().replace(/\s/g,"");
                  $.ajax({
                    url:'${pageContext.request.contextPath}/DataUser/updateDaus',
                    type:'post',
                    data:{
                      "uid":uid,
                      "name":name,
                      "phone":phone,
                      "company":company,
                      "remark":remark
                    },
                    success: function (data) {
                      var result=eval('('+data+')');
                      if(result.code == 1){
                        layer.closeAll('iframe');
                        layer.alert('修改成功', {
                          skin: 'layui-layer-molv'
                          ,closeBtn: 0
                          ,icon:1
                          ,end:function(){
                            location.reload();
                          }
                          ,time:800
                        })
                      }else if(result.code == 2){
                        layer.msg('电话号码已存在,请重新输入');
                      }else{
                        layer.alert('修改失败', {
                          skin: 'layui-layer-molv'
                          ,closeBtn: 0
                        })
                      }
                    }
                  });
                })
                submit.trigger('click');
              }
            })
          }
        }
      }
    })
  }
  
  function delet(){

          var table = layui.table
          var checkStatus = table.checkStatus('test-table-height'),
                  data = checkStatus.data;
          //判断选中几条数据
          if(data.length < 1){
            layer.msg('请至少选择一条数据');
          }else{
            var strids = "";
            for(var i = 0 ; i < data.length ; i++){

              strids = strids + data[i].uid + ",";
            }
            layer.confirm('确定删除该用户吗？',{title:"警告！",skin:"layui-layer-molv"
              ,icon:0}, function(){
              $.ajax({
                url:'${pageContext.request.contextPath}/DataUser/deletecount',
                type:'post',
                data: {
                  ids:strids
                },
                success: function (data) {
                  var result=eval('('+data+')');

                  if(result.code == 1){
                    layer.alert('删除成功', {
                      skin: 'layui-layer-molv'
                      ,closeBtn: 0
                      ,icon:1
                      ,end:function(){
                        location.reload();
                      }
                      ,time:800
                    })
                  }else if(result.code == -1){
                    layer.msg('您无此操作权限');
                  }else if(result.data.length > 0){
                    var resp ='删除失败,请先删除以下用户对应的账号！'+'<br />';
                    for(var i=0;i<result.data.length;i++){
                      resp = resp +'账号:'+ result.data[i].account+' ～ 姓名:'+result.data[i].dataUser.name +'<br />'
                    }
                    layer.alert(resp, {
                      skin: 'layui-layer-molv'
                      ,closeBtn: 0
                    })
                  }else{
                    layer.alert('删除失败', {
                      skin: 'layui-layer-molv'
                      ,closeBtn: 0
                    })
                  }
                }
              });
            })
          }
  }
  </script>
</body>
</html>
