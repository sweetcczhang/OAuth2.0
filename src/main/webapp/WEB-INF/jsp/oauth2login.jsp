<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
						<div class="login_title1">
							欢迎使用sweet-smile账号登录<a href="${client.webUrl}">${client.clientName}</a>
						</div>
						<form action="" method="post">
							 <div class="form_text_ipt">
								<input name="username" type="text" placeholder="手机号/邮箱">
							</div>
							<div class="ececk_warning"><span>数据不能为空</span></div>
							<div class="form_text_ipt">
								<input  name="password" type="password" placeholder="密码">
							</div>
							<div class="ececk_warning"><span>数据不能为空</span></div>
							<div class="form_check_ipt">
								<div class="left check_right">
									<a href="#">忘记密码</a>
								</div>
							</div>
							<div class="form_btn">
							<!--	<button type="button">登录</button>  -->
							<input type="submit" value="登录并授权">
							</div>
							<div class="form_reg_btn">
								<span>还没有帐号？</span><a href="register.html">马上注册</a>
							</div>
							<div class="other_login1">
							
							</div>	
						</form>
						
						
					</div>
				</div>
				
			</div>
		</div>
		<script type="text/javascript" src="<%=basePath %>resources/js/jquery.min.js" ></script>
		<script type="text/javascript" src="<%=basePath %>resources/js/common.js" ></script>
	</body>
</html>