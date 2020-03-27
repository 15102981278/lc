<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>账号管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/admin.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/modules/layer/zidingy/zidingy.css" media="all">
  <script src="${pageContext.request.contextPath}/jss/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
</head>
<body>
    <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-block">
              <input type="text" name="account" id = "account" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
              <input type="text" name="name" id = "name" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button   class="layui-btn layuiadmin-btn-useradmin" onclick="return false;" data-type="reload" id="selectAcc" >
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      <div class="layui-card-body">
        <div class="layui-btn-group" style=" padding-bottom: 10px;">
             <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="add()"><i class="layui-icon">&#xe654;</i></button>
             <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="update()"><i class="layui-icon">&#xe642;</i></button>
             <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="delet()"><i class="layui-icon">&#xe640;</i></button>
             <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="exportall()">导出表格</button>
        </div>
        
        <table id="test-table-height" lay-filter="test-table-height"></table>
      </div>
    </div>
  </div>
  
  <script>
  layui.use(['table', 'layer', 'form', 'element', 'jquery','tree'],
		    function () {
		        var table = layui.table, layer = layui.layer, form = layui.form,
		            element = layui.element, $ = layui.jquery,
		            tree = layui.tree;
	  
	  table.render({
	       elem: '#test-table-height'
	      ,url: '${pageContext.request.contextPath}/DataAccount/selectDaac'
	      ,height:675
	      ,cellMinWidth: 80
	      ,page: true
	      ,limit: 20
	      ,limits:[10,20,30,50,100]
	      ,even: true
	      ,cols: [[
             {field:'accid',title:'序号',width:70, sort: true, fixed: true,type:'numbers'}
            ,{type:'checkbox', fixed: true}
	        ,{field:'account', title: '账号',  align: 'center'}
            ,{title: '姓名',  align: 'center',
              templet:function(obj){
                return obj.dataUser.name;
             }}
	        ,{field:'authority', title: '身份',  align: 'center',
	        	templet:function(obj){
	        		if(obj.authority == 0){
	        			return"超管";
	        		}else if(obj.authority == 1){
	        			return"管理员";
	        		}else if(obj.authority == 2){
	        			return"用户";
	        		}
        		}}
            ,{field:'establish_time', title: '注册日期',  align: 'center'}
            ,{field:'ip', title: '登录ip',  align: 'center'}
	        ,{field:'logon_time', title: '登录日期',  align: 'center'}
            ,{field:'remark', title: '备注', align: 'center'}
	        ,{title: '权限操作', align: 'center',unresize: true,
	        	templet:function(d){
	        		 var stat = "";
                  stat="<button class='layui-btn layui-btn-sm 'onclick='edit("+ d.accid +")'>编辑</button>";
	        		 if(d.authority == 0){
                       stat="<button class='layui-btn layui-btn-sm layui-btn-disabled'>禁用</button>";
	        		 }
	        		 return stat;
	        	}}
            ,{title: '重置密码', align: 'center',unresize: true,
              templet:function(d){
                var stat = "";
                stat="<button class='layui-btn layui-btn-sm layui-btn-danger'onclick='Reset("+ d.accid +")'>重置</button>";
                if(d.authority == 0){
                  stat="<button class='layui-btn  layui-btn-sm layui-btn-disabled'>禁用</button>";
                }
                return stat;
              }}
	      ]]
	  });

	//根据条件查询表格数据重新加载
	  var $ = layui.$, active = {
		    reload: function(){
		    //获取查询条件
		      var account=document.getElementById("account").value;
		      var u_name=document.getElementById("name").value;
		      //执行重载
		      table.reload('test-table-height', {
		    	 url : '${pageContext.request.contextPath}/DataAccount/selectDaac'
		        ,page: {
		          curr: 1
                 }
		         //根据条件查询
		        ,where: {
		        	account:account,
                      u_name:u_name
		         }
		      });
		   }
		};
	 //点击搜索按钮根据楼层称查询
	  $('#selectAcc').on('click',
	      function(){
	          var type = $(this).data('type');
	          active[type] ? active[type].call(this) : '';
	   });
  });
  
  function add(){
    $.ajax({
      url:'${pageContext.request.contextPath}/keyword/passThrough?juau=acco_add',
      type:'post',
      success: function (data) {
        var result = eval('(' + data + ')');
        if (result.code == -1) {
          layer.msg('您无此操作权限');
        } else {
      layer.open({
          //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
          type:2,
          title:"账号添加",
          area: ['26%','50%'],
          offset: '100px',
          content:'${pageContext.request.contextPath}/StyleJump/AccJump?num=AccAdd',
          btn: ['确定','取消'], 
          yes: function(index,layero){

            var iframeWindow = window['layui-layer-iframe'+ index]
                    ,submitID = 'LAY-user-front-submit'
                    ,submit = layero.find('iframe').contents().find('#'+ submitID);

              //监听提交
              iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
              var body = layer.getChildFrame('body', index);
              var authority = body.find('#authority').val().replace(/\s/g,"");
              var account = body.find('#account').val().replace(/\s/g,"");
              var u_id = body.find('#u_id').val().replace(/\s/g,"");
              var remark = body.find('#remark').val().replace(/\s/g,"");

                $.ajax({
                  url:'${pageContext.request.contextPath}/DataAccount/selectAcuid',
                  type:'post',
                  data:{
                    "account":account
                  },
                  success: function (data) {
                    var result = eval('(' + data + ')');
                    if(result.code > 0){
                      layer.msg('该账号已存在,请重新输入');
                    }else{
                      $.ajax({
                        url:'${pageContext.request.contextPath}/DataAccount/selectAcuid',
                        type:'post',
                        data:{
                          "u_id":u_id
                        },
                        success: function (data) {
                          var result=eval('('+data+')');
                          layer.confirm('该用户已有 '+result.code+' 个账号,确定添加吗？',{title:"提示！",skin:"layui-layer-molv"
                            ,icon:0}, function(index){
                            $.ajax({
                              url:'${pageContext.request.contextPath}/DataAccount/addAcco',
                              type:'post',
                              data:{
                                "authority":authority,
                                "account":account,
                                "u_id":u_id,
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
                                }else{
                                  layer.alert('添加失败', {
                                    skin: 'layui-layer-molv'
                                    ,closeBtn: 0
                                  })
                                }
                              }
                            });
                          })
                        }
                      });
                    }
                  }
                })

            })
            submit.trigger('click');
          }
          	})
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
      var adm = 0;
      for(var i = 0 ; i < data.length ; i++){
        if (data[i].authority == 0){
          adm = 1;
        }
        strids = strids + data[i].accid + ",";
      }
      if(adm == 1){
        layer.msg('您无权删除超级管理员！');
      }else{
        layer.confirm('确定删除账号吗？',{title:"警告！",skin:"layui-layer-molv"
          ,icon:0}, function(){
          $.ajax({
            url:'${pageContext.request.contextPath}/DataAccount/deleteacc',
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
  }

  function update(){

    $.ajax({
      url:'${pageContext.request.contextPath}/keyword/passThrough?juau=acco_upd',
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
            if (data[0].authority == 0){
              layer.msg('您无权修改超级管理员！');
            }else{
              var accid = data[0].accid
              layer.open({
                //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层 ）4（tips层）
                type:2,
                title:"修改楼层/商户",
                area: ['26%','45%'],
                offset: '100px',
                content:'${pageContext.request.contextPath}/StyleJump/AccJump?num=AccUpd&accid=' + accid,
                btn: ['确定','取消'],
                yes: function(index,layero){

                  var iframeWindow = window['layui-layer-iframe'+ index]
                          ,submitID = 'LAY-posupdate-submit'
                          ,submit = layero.find('iframe').contents().find('#'+ submitID);

                  //监听提交
                  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                    var body = layer.getChildFrame('body', index);
                    var authority = body.find('#authority').val().replace(/\s/g,"");
                    var account = body.find('#account').val().replace(/\s/g,"");
                    var u_id = body.find('#u_id').val().replace(/\s/g,"");
                    var remark = body.find('#remark').val().replace(/\s/g,"");

                          $.ajax({
                            url:'${pageContext.request.contextPath}/DataAccount/updateAcco',
                            type:'post',
                            data:{
                              "accid":accid,
                              "authority":authority,
                              "account":account,
                              "u_id":u_id,
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
                                layer.msg('账号已存在,请重新输入');
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
      }
    })
  }


  function exportall(){
    var account = document.getElementById("account").value;
    var name = document.getElementById("name").value;

    $.ajax({
      url:'${pageContext.request.contextPath}/keyword/passThrough?juau=acco_exp',
      type:'post',
      success: function (data) {
        var result = eval('(' + data + ')');
        if (result.code == -1) {
          layer.msg('您无此操作权限');
        }else{
          window.location.href="${pageContext.request.contextPath}/DataAccount/exportaco?account="+account+"&u_name="+name;
        }
      }
    })
  }


  function Reset(d){

    $.ajax({
      url:'${pageContext.request.contextPath}/keyword/passThrough?juau=acco_res',
      type:'post',
      success: function (data) {
        var result = eval('(' + data + ')');
        if (result.code == -1) {
          layer.msg('您无此操作权限');
        }else{
              layer.confirm('确定重置密码吗？',{title:"警告！",skin:"layui-layer-molv"
                ,icon:0}, function(){
                  $.ajax({
                    url:'${pageContext.request.contextPath}/DataAccount/updatePas',
                    type:'post',
                    data:{
                      accid:d
                    },
                    success: function (data) {
                      var result = eval('(' + data + ')');
                      if(result.code == 1){
                        layer.alert('重置成功', {
                          skin: 'layui-layer-molv'
                          ,closeBtn: 0
                          ,icon:1
                          ,time:800
                        })
                      }else{
                        layer.alert('重置失败', {
                          skin: 'layui-layer-molv'
                          ,closeBtn: 0
                        })
                      }
                    }
                  })
              })
        }
      }
    })
  }

    function edit(d){

      $.ajax({
        url:'${pageContext.request.contextPath}/keyword/passThrough?juau=acco_edi',
        type:'post',
        success: function (data){
          var result = eval('(' + data + ')');
          if (result.code == -1){
            layer.msg('您无此操作权限');
          }else{
            layer.open({
              //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
              type:2,
              title:"编辑权限",
              area: ['40%','60%'],
              offset: '100px',
              content:'${pageContext.request.contextPath}/StyleJump/AccJump?num=AccEdit&accid=' + d,
              btn: ['确定','取消'],
              yes: function(index,layero){
                var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submitID = 'LAY-user-front-submit'
                        ,submit = layero.find('iframe').contents().find('#'+ submitID);
                var body = layer.getChildFrame('body', index);
                var fs = body.find('#fs').val().replace(/\s/g,"");
                var is = body.find('#is').val().replace(/\s/g,"");
                if(is == '0'){
                  alert('is : '+is)
                  $.ajax({
                    url:'${pageContext.request.contextPath}/DataAccount/editAuth',
                    type:'post',
                    data:{
                      "accid":d,
                      "ids":fs
                    },
                    success: function (data) {
                      var result=eval('('+data+')');
                      if(result.code == 1){
                        layer.closeAll('iframe');
                        layer.alert('编辑成功', {
                          skin: 'layui-layer-molv'
                          ,closeBtn: 0
                          ,icon:1
                          ,end:function(){
                            location.reload();
                          }
                          ,time:800
                        })
                      }else{
                        layer.alert('编辑失败', {
                          skin: 'layui-layer-molv'
                          ,closeBtn: 0
                        })
                      }
                    }
                  });
                }else{
                  layer.closeAll('iframe');
                  layer.alert('编辑成功', {
                    skin: 'layui-layer-molv'
                    ,closeBtn: 0
                    ,icon:1
                    ,time:800
                  })
                }
              }
            })
          }
        }
      })
    }
  </script>
</body>
</html>
