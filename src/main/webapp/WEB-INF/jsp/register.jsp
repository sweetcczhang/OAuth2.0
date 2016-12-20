<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
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
		<title>注册</title>
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
							注册
						</div>
						<form:form  commandName="userBean" method="post">
							<form:hidden path="id"/>
							<form:hidden path="uid"/>
							<div class="form_text_ipt">
								<form:input path="username"  placeholder="邮箱"/>
								<form:errors style="color:red;" path="username"></form:errors>
							</div>
							<div class="ececk_warning">
							 <form:errors path="username"></form:errors>
							<span>数据不能为空</span>
							</div>
							
							<div class="form_text_ipt">
								<form:input path="nickName"  placeholder="昵称"/>
							</div>
							<div class="ececk_warning"><span>数据不能为空</span></div>
							
							<div class="form_text_ipt">
								<form:input path="sex"  placeholder="性别"/>
								<form:errors style="color:red;" path="sex"></form:errors>
							</div>
							<div class="ececk_warning">
							<span>数据不能为空</span>
							</div>
							
							<div class="form_text_ipt">
								<form:input path="address"  placeholder="地址"/>
							</div>
							<div class="ececk_warning"><span>数据不能为空</span></div>
							
							<div class="form_text_ipt">
								<form:password path="password" placeholder="密码"/>
								<form:errors style="color:red;" path="password"></form:errors>
							</div>
							<div class="ececk_warning"><span>数据不能为空</span></div>
							<br>
							<div class="form_btn">
								<!--  	<button type="button">登录</button> -->
							   <input type="submit" value="注册">
							</div>
							<div class="form_reg_btn">
								<span>已有帐号？</span><a href="<%=basePath %>login">马上登录</a>
							</div>
						</form:form>
						<div class="other_login1">
							<div class="left other_left">
								<span>使用第三方账号登录</span>
							</div>
						</div>
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