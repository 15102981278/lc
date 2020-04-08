<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/admin.css" media="all">
    </head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6">
            <div class="layui-card layui-form" lay-filter="component-form-element">
                <div class="layui-card-header" style="background-color: rgba(145,218,252,0.41)">参数选择</div>
                <div class="layui-card-body layui-row layui-col-space10" style="height: 30%">
                    <div class="layui-form-item" style="width: 50%">
                        <label class="layui-form-label">注册地址</label>
                        <div class="layui-input-block">
                            <select name="url" id = "url" lay-filter="url">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item" style="width: 50%">
                        <label class="layui-form-label">平台APP</label>
                        <div class="layui-input-block">
                            <select name="appid" id = "appid" lay-filter="appid" >

                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item" style="width: 50%">
                        <label class="layui-form-label">客户公司</label>
                        <div class="layui-input-block">
                            <select name="coname" id = "coname" lay-filter="coname">
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">号段范围</label>
                            <div class="layui-input-inline" style="width: 30%;">
                                <input type="text" id="price_min" name="price_min" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" placeholder="    起始值         ≤" autocomplete="off" class="layui-input" >
                            </div>
                            <div class="layui-form-mid">-</div>
                            <div class="layui-input-inline" style="width: 30%;">
                                <input type="text" id="price_max" name="price_max" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" placeholder="    ≤         结束值" autocomplete="off" class="layui-input" >
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-card layui-form" lay-filter="component-form-element" style="height: 55%">
                <div class="layui-card-header" style="background-color: rgba(145,218,252,0.41)">模块扫码</div>
                <label class="layui-form-label" style="padding-top: 10%;padding-left: 40%;font-size: 20px">扫码定位</label>
                <div class="layui-card-body layui-row layui-col-space10" style="padding-left: 15%; padding-top: 20%">
                    <input type="text"  name="imei" id="imei" autocomplete="off"  style="height: 9%; border-style: double;width: 80%">
                </div>
                <button style="display: none"  class="layui-btn" lay-submit lay-filter="setm" ></button>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header" style="background-color: rgba(145,218,252,0.41)">数据响应</div>
                <div class="layui-card-body layui-row layui-col-space10">
                    <div class="layui-col-md12">
                        <textarea  readonly name="respo" id="respo"  class="layui-textarea" style="height: 85%"></textarea>
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
    layui.use(['table', 'layer', 'form', 'element', 'jquery','tree','laydate'],
        function () {
            var table = layui.table, layer = layui.layer, form = layui.form,
                element = layui.element, $ = layui.jquery,
                tree = layui.tree, laydate = layui.laydate;
            var text = '';

            $.ajax({
                url:'${pageContext.request.contextPath}/DataCompany/selectall',
                type:'post',
                success: function (data) {
                    var result=eval('('+data+')').data;
                    var tanum = document.getElementById("coname");
                    for (var i=0;i<result.length;i++){
                        var option = document.createElement("option");
                        //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                        option.setAttribute("value",result[i].coid); // 给option的value添加值
                        option.innerText=result[i].coname;     // 打印option对应的纯文本
                        tanum.appendChild(option);           //给select添加option子标签
                        form.render("select");
                    }
                }
            })

            $.ajax({
                url:'${pageContext.request.contextPath}/platformApp/selectPlarform',
                type:'post',
                success: function (data) {
                    var result=eval('('+data+')').data;
                    var tam = document.getElementById("url");
                    for (var i=0;i<result.length;i++){
                        var option = document.createElement("option");
                        //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                        option.setAttribute("value",result[i].rpurl); // 给option的value添加值
                        option.innerText=result[i].rpname;     // 打印option对应的纯文本
                        tam.appendChild(option);        //给select添加option子标签
                        form.render("select");
                    }
                }
            })



            function getappid(dataaa) {

                $('#appid').empty();
                form.render("select");
                $.ajax({
                    url:'${pageContext.request.contextPath}/platformApp/selectPlarformApp',
                    type:'post',
                    success: function (data) {
                        var result=eval('('+data+')').data;
                        var tam = document.getElementById("appid");
                        for (var i=0;i<result.length;i++){
                            if(dataaa == result[i].registerPlatform.rpurl){
                                var option = document.createElement("option");
                                option.setAttribute("value",result[i].paapp);
                                option.innerText=result[i].paname;
                                tam.appendChild(option);
                                form.render("select");
                            }
                        }
                    }
                })
            };

            form.on('select(url)', function(data){
                getappid(data.value);
            });

            $(document).keyup(function(event){
                if(event.keyCode ==13){
                    document.getElementById('respo').scrollTop = document.getElementById('respo').scrollHeight
                    var ti=new Date();
                    var time = ti.toLocaleString();

                    var btn_login=document.getElementById("setm");
                    var url=document.getElementById("url").value;
                    var appid=document.getElementById("appid").value;
                    var coname=document.getElementById("coname").value;
                    var imei=document.getElementById("imei").value.replace(/\s/g,"")

                    var min=parseInt(document.getElementById("price_min").value)
                    var max=parseInt(document.getElementById("price_max").value)
                    if(isNaN(min)){
                        min = 0;
                    }
                    if(isNaN(max)){
                        max = 0;
                    }

                    $("#imei").val("");
                    var imtw =  imei.slice(0,15)

                    if(url == ''){
                        layer.msg('请选择注册地址');
                    }else if(imei == ''){
                        layer.msg('请输入IMEI号');
                    }else if(imei.length < 15){
                        layer.msg('IMEI号: '+imei+' 长度错误');
                        text =text + "IMEI号:"+imei+"  时间:"+time+"   状态: 失败"+"   失败原因:IMEI号长度错误\n"
                        $("#respo").val(text);
                    }else if(isNaN(imtw)){
                        layer.msg('IMEI号: '+imtw+' 错误');
                        text =text + "IMEI号:"+imtw+"  时间:"+time+"   状态: 失败"+"   失败原因:IMEI号错误\n"
                        $("#respo").val(text);
                    } else if(min==0 && max != 0) {
                        if(imtw > max ){
                            layer.msg('IMEI号: '+imtw+' 超出范围');
                            text =text + "IMEI号:"+imtw+"  时间:"+time+"   状态: 失败"+"   失败原因:IMEI号超出范围\n"
                            $("#respo").val(text);
                        }else{
                            zhix(imtw,url,appid,coname,time);
                        }
                    }else if(min!=0 && max ==0) {
                        if(imtw < min ){
                            layer.msg('IMEI号: '+imtw+' 小于范围');
                            text =text + "IMEI号:"+imtw+"  时间:"+time+"   状态: 失败"+"   失败原因:IMEI号小于范围\n"
                            $("#respo").val(text);
                        }else{
                            zhix(imtw,url,appid,coname,time);
                        }
                    }else if(min!=0 && max!=0) {
                        if(min >= max){
                            layer.msg('号段输入错误,请检查'+min + '--' +max);
                        }else if(imtw < min || imtw >max){
                            layer.msg('IMEI号不在范围内范围');
                            text =text + "IMEI号:"+imtw+"   时间:"+time+"   状态: 失败"+"   失败原因:IMEI号不在范围内范围\n"
                            $("#respo").val(text);
                        }else{
                            zhix(imtw,url,appid,coname,time);
                        }
                    }else {
                        zhix(imtw,url,appid,coname,time);
                    }
                    btn_login.focus();
                    btn_login.click()
                }
            });
            
            function zhix(imei,url,appid,coname,time) {
                layer.msg(imei + '--' +url+ '--' +appid+ '--' +coname+ '--' +time);
                $.ajax({

                           url:'${pageContext.request.contextPath}/scann/modulescann',
                           type:'post',
                           data:{
                               "imei":imei,
                               "url":url,
                               "appid":appid,
                               "company":coname
                           },
                           success: function (data) {
                               var result = eval('(' + data + ')');
                               if(result.code == -1){
                                   text =text + "IMEI号:"+imei+"   时间:"+time+"   状态: 失败"+"   失败原因:平台APP已失效\n"
                                   $("#respo").val(text);
                               }else if(result.code == 0){
                                   text =text + "IMEI号:"+imei+"   时间:"+time+"   状态: 失败"+"   失败原因:该IMEI号已注册\n"
                                   $("#respo").val(text);
                               }else if(result.code == 1){
                                   text =text + "IMEI号:"+imei+"   时间:"+time+"   状态: 成功\n"
                                   $("#respo").val(text);
                               }else{
                                   text =text + "IMEI号:"+imei+"   时间:"+time+"   状态: 失败"+"   失败原因:注册成功,但未添加到本系统\n"
                                   $("#respo").val(text);
                               }
                           }
                       })
            }
        })
</script>

</body>
</html>
