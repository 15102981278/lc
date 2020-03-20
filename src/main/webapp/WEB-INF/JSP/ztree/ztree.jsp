<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <HTML>
    <HEAD>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/ztrees/css/demo.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/ztrees/css/zTreeStyle/zTreeStyle.css" type="text/css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/ztrees/js/jquery-1.4.4.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/ztrees/js/jquery.ztree.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/ztrees/js/jquery.ztree.excheck.js"></script>
        <script type="text/javascript">

            var setting = {
                check: {
                    enable: true
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback:{
                    beforeCheck:true,
                    onCheck:onCheck
                }
            };

           window.onload = function(){

                 var arr =$("#ac").val().split(",");
                 var zTree = $.fn.zTree.getZTreeObj("treee");
                 for(var j =0; j < arr.length; j++ ) {
                    console.log(arr[j]);
                   var zTree = $.fn.zTree.getZTreeObj("treee");
                   zTree.checkNode(zTree.getNodeByParam("id",arr[j]),true);
                }
            }

            $(document).ready(function(){
                $.fn.zTree.init($("#treee"), setting, zNodes);
            });

            function onCheck(e,treeId,treeNode){
                var treeObj=$.fn.zTree.getZTreeObj("treee"),
                    nodes=treeObj.getCheckedNodes(true),
                    v="";
                for(var i=0;i<nodes.length;i++){
                    v+=nodes[i].name + ",";
                    console.log("节点id:"+nodes[i].id+"节点名称"+v); //获取选中节点的值
                }
            }

            var zNodes =[
                { id:'02', pId:'0', name:"扫码管理", open:true},
                    { id:'021', pId:'02', name:"模块扫码", open:true},
                        { id:'0211', pId:'021', name:"开始操作"},
                    { id:'022', pId:'02', name:"测试扫码", open:true},
                        { id:'0221', pId:'022', name:"开始操作"},
                { id:'03', pId:'0', name:"系统查询",open:true},
                    { id:'031', pId:'03', name:"计量信息", open:true},
                        { id:'0311', pId:'031', name:"查询操作"},
                        { id:'0312', pId:'031', name:"修改操作"},
                        { id:'0313', pId:'031', name:"删除操作"},
                { id:'04', pId:'0', name:"设备管理", open:true},
                    { id:'041', pId:'04', name:"模块管理", open:true},
                        { id:'0411', pId:'041', name:"查询操作"},
                        { id:'0412', pId:'041', name:"修改操作"},
                        { id:'0413', pId:'041', name:"添加操作"},
                        { id:'0414', pId:'041', name:"删除操作"},
                    { id:'042', pId:'04', name:"表管理", open:true},
                        { id:'0421', pId:'042', name:"查询操作"},
                        { id:'0422', pId:'042', name:"修改操作"},
                        { id:'0423', pId:'042', name:"添加操作"},
                        { id:'0424', pId:'042', name:"删除操作"},
                { id:'05', pId:'0', name:"基础设置", open:true},
                    { id:'051', pId:'05', name:"账号管理", open:true},
                        { id:'0511', pId:'052', name:"查询操作"},
                        { id:'0512', pId:'051', name:"修改操作"},
                        { id:'0513', pId:'051', name:"添加操作"},
                        { id:'0514', pId:'051', name:"删除操作"},
                        { id:'0515', pId:'051', name:"重置操作"},
                        { id:'0516', pId:'051', name:"权限操作"},
                        { id:'0517', pId:'051', name:"导出操作"},
                    { id:'052', pId:'05', name:"用户管理", open:true},
                        { id:'0521', pId:'052', name:"查询操作"},
                        { id:'0522', pId:'052', name:"修改操作"},
                        { id:'0523', pId:'052', name:"添加操作"},
                        { id:'0524', pId:'052', name:"删除操作"},
                    { id:'053', pId:'05', name:"日志管理", open:true},
                        { id:'0531', pId:'053', name:"查询操作"},
                        { id:'0532', pId:'053', name:"导出操作"}
            ];

            $(document).ready(function(){
                $.fn.zTree.init($("#treee"), setting, zNodes);
                setCheck();
                $("#py").bind("change", setCheck);
                $("#sy").bind("change", setCheck);
                $("#pn").bind("change", setCheck);
                $("#sn").bind("change", setCheck);
            });
        </script>
    </HEAD>

<BODY>
<div class="content_wrap">
    <div >
        <ul id="treee" class="ztree" style="width:630px; overflow:auto;"></ul>
        <input type="hidden" name="ac" id="ac" value="${ac}">
    </div>
</div>
</BODY>
</HTML>