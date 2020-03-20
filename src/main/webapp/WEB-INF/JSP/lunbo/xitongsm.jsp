<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>轮播</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/admin.css" media="all">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/modules/layer/zidingy/zidingy.css" media="all">
  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>  
  <script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
</head>
<body>

  <style>
  /* 为了区分效果 */
  #LAY-demo-carousel div[carousel-item]>*{text-align: center; line-height: 280px; color: #666;}
  #LAY-demo-carousel div[carousel-item]>*:nth-child(2n){background-color: #E2E2E2;}
  #LAY-demo-carousel div[carousel-item]>*:nth-child(2n+1){background-color: #eee;}
  #test-carousel-normal-2 div[carousel-item]>*{line-height: 120px;}
  </style>

  <div class="layui-card layadmin-header">
    <div class="layui-breadcrumb" lay-filter="breadcrumb">
      <a lay-href="">主页</a>
      <a><cite>组件</cite></a>
      <a><cite>轮播</cite></a>
    </div>
  </div>
  
  <div class="layui-fluid" id="LAY-demo-carousel">
    <div class="layui-row ">
	     <div class="layui-carousel" id="test1" lay-filter="carofilter">
	        	<div carousel-item>
		            <div><img src="${pageContext.request.contextPath}/image/xitong1.jpg"></div>
		            <div><img src="${pageContext.request.contextPath}/image/001.jpg"></div>
	        	</div>
	     </div>
    </div>
  </div>
  
  <script>
  layui.config({
    base: '${pageContext.request.contextPath}/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'carousel', 'form'], function(){
    var carousel = layui.carousel
    ,form = layui.form;
  });
  </script>
  
   <script>
     layui.use('carousel', function () {
         var carousel = layui.carousel;
         //***************************建造实例
         var ins=carousel.render({
               elem: '#test1'
             , arrow: 'always'    //始终显示箭头，可选hover,none
             //,anim: 'updown'    //切换动画方式，可选fade,default
             , full: true       //全屏
             , autoplay: true     //自动切换
             , interval: 1000     //自动切换的时间间隔
             , index: 0           //初始化时item索引,默认时0
             , indicator:'inside' //指示器位置，可选outside,none
         });

         //**************************监听轮播切换事件
         carousel.on('change(carofilter)', function (obj) { //test1来源于对应HTML容器的 lay-filter="test1" 属性值
             console.log(obj.index);     //当前条目的索引
             console.log(obj.prevIndex); //上一个条目的索引
             console.log(obj.item);      //当前条目的元素对象
         });

         //重置轮播
         ins.reload({
             elem: '#test1',
             width: '100%', //设置容器宽度
             height: '100%',
             arrow: 'always', //始终显示箭头
             //,anim: 'updown' //切换动画方式
             autoplay: false
         });
     });
 </script>
</body>
</html>