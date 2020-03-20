<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>登录页面</title>

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form-elements.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" />

		<!-- Javascript -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.backstretch.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
        <script>
            $(function(){
                if (top != window){
                    top.location.href = window.location.href;
                }
            })
        </script>

         <script type="text/javascript">
            function submitt() {
            	
                var scode = $("#form-username").val().replace(/\s+/g,"");
                var pass = $("#form-password").val().replace(/\s+/g,"");
				
                $.ajax({
                    type: "POST",
                    url:"${pageContext.request.contextPath}/LoginJumpController/login",
                    data:"scode="+scode+"&pass="+pass,
                    success: function(data) {
                        if (data == 0) {
                            $("#nameerr").show();
                            $("#nameerr").html("用户名或者密码不正确！");
                        }else if (data == 2){
                            $("#nameerr").show();
                            $("#nameerr").html("请联系管理员完善个人信息！");
                        } else {
                            location.href = "${pageContext.request.contextPath}/LoginJumpController/authority";
                        }
                    }
                });
            }
        </script>
        
    </head>
    <body>
        <!-- Top content -->
        <div class="top-content">
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>银河电力数据管理系统</strong></h1>
                            <div class="description">
                            	<p>
	                            	感谢使用本系统. 
	                            	更多了解请往 <a href="http://com309847.shop.hao315.tv/"><strong>银河电力官网</strong></a>, 期待您的光临!
                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 150px">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        <div id="nameerr" style="color: red"></div>
                        
                            <div class="form-bottom">
                            
			                    <form role="form" action="" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username"></label>
			                        	<input type="text" name="form-username" placeholder="账号" class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password"></label>
			                        	<input type="password" name="form-password" placeholder="密码" class="form-password form-control" id="form-password">
			                        </div>
			                        <button type="button"  onclick="submitt()" class="btn">登录</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>

</html>