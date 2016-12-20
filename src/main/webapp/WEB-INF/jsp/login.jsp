<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
    String ctxPath = request.getContextPath();
	//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ctxPath+"/";
	//String basePath = ctxPath+"/";
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ctxPath+"/";
	System.out.println("basePath:"+basePath);
%>
<html>
	<head>
		<meta charset="utf-8">
		<title>登录</title>
		<link rel="stylesheet" href="<%=basePath %>resources/css/reset.css" />
		<link rel="stylesheet" href="<%=basePath %>resources/css/common.css" />
	</head>
	<body>
		<div class="wrap login_wrap">
			<div class="content">
				
				<div class="logo"></div>
				
				<div class="login_box">	
					
					<div class="login_form">
						<div class="login_title">
							登录
						</div>
						<form action="/loginContoller" method="post">
							
							<div class="form_text_ipt">
								<input name="username" type="text" placeholder="邮箱">
							</div>
							<div class="ececk_warning"><span>数据不能为空</span></div>
							<div class="form_text_ipt">
								<input name="password" type="password" placeholder="密码">
							</div>
							<div class="ececk_warning"><span>数据不能为空</span></div>
							<div class="form_check_ipt">
								<div class="left check_left">
									<label><input name="" type="checkbox"> 下次自动登录</label>
								</div>
								<div class="right check_right">
									<a href="#">忘记密码</a>
								</div>
							</div>
							<div class="form_btn">
							<!--  	<button type="button">登录</button> -->
							   <input type="submit" value="登录">
							</div>
							<div class="form_reg_btn">
								<span>还没有帐号？</span><a href="<%=basePath %>registers">马上注册</a>
							</div>
						</form>
						<div class="other_login1">
							<div class="left other_left">
								<span>使用第三方账号登录</span>
							</div>
							
						</div >
						<div class="other_login2">
						    <div class="left other_left" >
								<a href="<%=basePath%>qqconnect"><img src="<%=basePath %>resources/Images/QQ.jpg"></a>
								<a href="<%=basePath%>xweibo"><img src="<%=basePath %>resources/Images/weibo.png"></a>
								<a href="#"><img src="<%=basePath %>resources/Images/weixin.png"></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="<%=basePath %>resources/js/jquery.min.js" ></script>
		<script type="text/javascript" src="<%=basePath %>resources/js/common.js" ></script>
	</body>
</html>
