<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>权限编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/admin.css" media="all">
    <style>
        .layui-form-select dl { max-height:150px;}
    </style>
    <script src="${pageContext.request.contextPath}/jss/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
</head>
<body>
<div class="layui-form" lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 40px 0 0;">
    <div >
        <ul id="treee" class="ztree" style="width:630px; overflow:auto;"></ul>
        <input type="hidden" name="ac" id="ac" value="${ac}">
        <input type="hidden" name="fs" id="fs">
        <input type="hidden" name="is" id="is">
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
</div>
<script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/layuiadmin/modules/user.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ztrees/css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ztrees/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/ztrees/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ztrees/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ztrees/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ztrees/znod.js"></script>
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
            nodes=treeObj.getCheckedNodes(),
            v="";
        for(var i=0;i<nodes.length;i++){
            v+=nodes[i].id + ",";
            console.log("节点id:"+nodes[i].id+"节点名称"+v); //获取选中节点的值
        }
        $('#fs').val(v);
        $('#is').val(0);
    }
    var zNodes = znod;

    $(document).ready(function(){
        $.fn.zTree.init($("#treee"), setting, zNodes);
        setCheck();
        $("#py").bind("change", setCheck);
        $("#sy").bind("change", setCheck);
        $("#pn").bind("change", setCheck);
        $("#sn").bind("change", setCheck);
    });
</script>
</body>

</html>