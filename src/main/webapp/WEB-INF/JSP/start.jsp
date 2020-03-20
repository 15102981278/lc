<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>首页</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/admin.css" media="all">
</head>
<body class="layui-layout-body">
  
  <div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
      <div class="layui-header">
        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
          <li class="layui-nav-item layadmin-flexible" lay-unselect>
            <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
              <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;" layadmin-event="refresh" title="刷新">
              <i class="layui-icon layui-icon-refresh-3"></i>
            </a>
          </li>
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right" >

         <li class="layui-nav-item"lay-unselect>
          	<a id="city"></a>
          </li>
          <li class="layui-nav-item"lay-unselect>
          	<a id="dat"></a>
          </li>
          <li class="layui-nav-item"lay-unselect>
          	<a id="tianqi"></a>
          </li>
          <li class="layui-nav-item"lay-unselect>
          	<a id="maximum"></a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a id="minimum"></a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="theme">
              <i class="layui-icon layui-icon-theme"></i>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="fullscreen">
              <i class="layui-icon layui-icon-screen-full"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
              <cite>用户</cite>
            </a>
            <dl class="layui-nav-child">
              <dd><a lay-href="${pageContext.request.contextPath}/powerJump/powerJump.do?num=balanceMe">基本资料</a></dd>
              <dd><a lay-href="${pageContext.request.contextPath}/powerJump/powerJump.do?num=updatePass">修改密码</a></dd>
              <dd  style="text-align: center;"   onclick="LogoutF()"><a>退出</a></dd>
            </dl>
          </li>
        </ul>
      </div>
      <!-- 侧边菜单 -->
      <div class="layui-side layui-side-menu">
        <div class="layui-side-scroll">
          <div class="layui-logo" lay-href="home/console.html">
            <span>银河电力数据管理系统</span>
          </div>
          
          <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
            <li data-name="home" class="layui-nav-item layui-nav-itemed" id="01">
              <a href="javascript:;" lay-direction="0101">
                <i class="layui-icon layui-icon-home"></i>
                <cite>主页</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="console" class="layui-this" id="011">
                  <a lay-href="${pageContext.request.contextPath}/powerJump/powerJump?num=lunb">公司风采</a>
                </dd>
                <dd data-name="console"  id="012">
                  <a lay-href="${pageContext.request.contextPath}/powerJump/powerJump?num=xitongsm">系统说明</a>
                </dd>
              </dl>
            </li>

            <li data-name="template" class="layui-nav-item" id="02">
              <a href="javascript:;" lay-direction="2">
                <i class="layui-icon layui-icon-template"></i>
                <cite>扫码管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd id="021"><a lay-href="${pageContext.request.contextPath}/PowerJump/authority?num=websocat">模块扫码</a></dd>
                <dd id="022"><a lay-href="${pageContext.request.contextPath}/powerJump/powerJump.do?num=tubiao">测试扫码</a></dd>
              </dl>
            </li>

            <li data-name="app" class="layui-nav-item" id="03">
              <a href="javascript:;"  lay-direction="2">
                <i class="layui-icon layui-icon-app"></i>
                <cite>系统查询</cite>
              </a>
              <dl class="layui-nav-child">
                <dd id="031">
                  <a lay-href="${pageContext.request.contextPath}/powerJump/powerJump.do?num=equpay">计量信息</a>
                </dd>
              </dl>
            </li>
            <li data-name="user" class="layui-nav-item" id="04">
              <a href="javascript:;"  lay-direction="2">
                <i class="layui-icon layui-icon-user"></i>
                <cite>设备管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd id="041"><a lay-href="${pageContext.request.contextPath}/powerJump/powerJump.do?num=perinformation">模块管理</a></dd>
                <dd id="042"><a lay-href="${pageContext.request.contextPath}/powerJump/powerJump.do?num=balance">表管理</a></dd>
              </dl>
            </li>
            <li data-name="set" class="layui-nav-item" id="05">
              <a href="javascript:;" lay-direction="2">
                <i class="layui-icon layui-icon-set"></i>
                <cite>基础设置</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <dl class="layui-nav-child">
                    <dd id="051"><a lay-href="${pageContext.request.contextPath}/powerJump/powerJump?num=account">账号管理</a></dd>
                    <dd id="052"><a lay-href="${pageContext.request.contextPath}/powerJump/powerJump?num=operator">用户管理</a></dd>
                    <dd id="053"><a lay-href="${pageContext.request.contextPath}/powerJump/powerJump?num=record">日志管理</a></dd>
                  </dl>
                </dd>
              </dl>
            </li>
          </ul>
        </div>
      </div>

      <!-- 页面标签 -->
      <div class="layadmin-pagetabs" id="LAY_app_tabs">
        <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-down">
          <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;"></a>
              <dl class="layui-nav-child layui-anim-fadein">
                <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
          <ul class="layui-tab-title" id="LAY_app_tabsheader">
            <li lay-id="${pageContext.request.contextPath}/home/console.jsp" lay-attr="home/console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
          </ul>
        </div>
      </div>
      
      <!-- 主体内容 -->
      <div class="layui-body" id="LAY_app_body">
        <div class="layadmin-tabsbody-item layui-show">
          <iframe src="${pageContext.request.contextPath}/powerJump/powerJump?num=lunb" frameborder="0" class="layadmin-iframe"></iframe>
        </div>
      </div>
      
      <!-- 辅助元素，一般用于移动设备下遮罩 -->
      <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
  </div>

  <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
  <script src="${pageContext.request.contextPath}/jss/jquery-1.10.2.js"></script>
  <script src="${pageContext.request.contextPath}/jss/jquery.easyui.min.js"></script>
  <script>
  layui.config({
    base: '${pageContext.request.contextPath}//layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use('index');
  </script>
  <script>

 /*window.onload = function(){
   var sUserAgent = navigator.userAgent.toLowerCase();
   var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
   var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
   var bIsMidp = sUserAgent.match(/midp/i) == "midp";
   var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
   var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
   var bIsAndroid = sUserAgent.match(/android/i) == "android";
   var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
   var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";

   if (!(bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM)) {
     myfun();
   }

 }*/

  function LogoutF(){
       location.href = '${pageContext.request.contextPath}/';
  }

 $(document).ready(function(){
    var data='${jum}';
    var our = data.split(",");
    for(var j=0; j<our.length-1; j++){
      $("#"+our[j]).toggle();
    }
 })

 document.onkeydown = function(){
   var e = window.event || arguments[0];
   if (e.keyCode == 123) {
     return false;
   }
 }

 document.oncontextmenu = function() {
   return false;
 }
  </script>
</body>
</html>

