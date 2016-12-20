<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<%
String ctxPath = request.getContextPath();
	//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ctxPath+"/";
	//String basePath = ctxPath+"/";
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ctxPath+"/";
	Object flag1=request.getSession().getAttribute("flag");
	Object flag2=request.getSession().getAttribute("avatar");
	System.out.println("flag1:"+flag1);
	
	boolean flag,avatar;
	
	if(flag1!=null){
		flag=true;
	}else{
		flag=false;
	}
	if(flag2!=null){
		avatar=true;
	}else{
		avatar=false;
	}
	
%>
    
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>OAuth2.0开放授权系统</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>resources/plugin/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath %>resources/css/web.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  <div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapsed-content" aria-expanded="false" >
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
				    <span class="icon-bar"></span>
					<span class="icon-bar"></span>
				
				</button>
			    <a class="navbar-brand" href="#">OAuth2.0开放授权系统</a>
			</div>
			<div class="collapse navbar-collapse col-md-offset-1" id="navbar-collapsed-content">
				<ul class="nav navbar-nav">
					<li><a  href="<%=basePath %>change/indexController">&nbsp;&nbsp;&nbsp;主页&nbsp;&nbsp;&nbsp;</a></li>
					<li><a href="<%=basePath %>change/documentController">&nbsp;文档中心&nbsp;</a></li>
					<li><a href="<%=basePath %>change/managerController">&nbsp;管理中心&nbsp;</a></li>
					<li class="active"><a href="<%=basePath %>change/accountCenterController">&nbsp;账号中心&nbsp;</a></li>
				</ul>
				<%if(flag){ %>
			    <ul class="nav navbar-nav navbar-right">
				 <%if(avatar){ %>
					     <li><img class="img-circle" style="height:50px;width:50px" src="${avatar}"></li>
					<% }%>
				 <li class="dropdown">
				  	  <a class="sdropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					   <span class="glyphicon glyphicon-user"></span>${nickName}
					    <span class="caret"></span>
					  </a>
					  <ul class="dropdown-menu" style="background-color:white">
                        <li><a href="<%=basePath %>change/accountCenterController" ><span class="glyphicon glyphicon-user">
                        </span>用户资料</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="<%=basePath %>userAuthor/show" ><span class="glyphicon glyphicon-cog">
                        </span>授权管理</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="<%=basePath %>logout" ><span class="glyphicon glyphicon-off">
                        </span>退出</a></li>
                	</ul>
				  </li>
		  
				</ul>
				<%}else{ %>
				<ul class="nav navbar-nav navbar-right">
				
					<li><a href="<%=basePath %>login">登录</a></li>
					<li><a href="<%=basePath %>registers">注册</a></li>
		  
				</ul>
				
				<%} %>
			
			</div>
	
		</div>
  
  </div>
  
 <div class="container content">
	<h3 class="page-header" style="margin-top:20px">账号中心</h3>
	<div class="row">
		<div class="col-md-2">
			<div class="panel panel-default" style="height:700px">
				<div class="panel-body">
					<div class="list-group" style="margin:-16px -16px -16px -16px">
						<a class="list-group-item" href="#Chrome" data-toggle="tab">用户资料</a>
						<a class="list-group-item" href="#Safari" data-toggle="tab">开发者资料</a>
						<a class="list-group-item" href="#Firefox" data-toggle="tab">修改用户资料</a>
						<a class="list-group-item" href="<%=basePath %>change/accountCenterController1">修改开发者资料</a>
					</div>
				</div>
			</div>
		
		
		</div>
		
		<div class="col-md-10" style="margin-left:-31px">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="tab-content">
						<div class="tab-pane fade" id="Chrome">
							<h4 class="page-header" style="margin-top:10px">用户资料</h4>
								<div class="form-horizontal">
										<div class="form-group">
										
											<label for="label1" class="col-md-2 control-label">用户名：</label>
											<div class="col-md-7">	
												<label class="control-label">${userBean.username}</label>
											</div>
										</div>
										
										<div class="form-group">
											<label for="label2" class="col-md-2 control-label">昵称：</label>
											<div class="col-md-7">	
												<label class="control-label">${userBean.nickName}</label>
											</div>
										</div>
						
										<div class="form-group">
											<label for="label3" class="col-md-2 control-label">性别：</label>
											<div class="col-md-7">	
												<label class="control-label">${userBean.sex}</label>
											</div>
										</div>
										
										<div class="form-group">
											<label for="label1" class="col-md-2 control-label">地址：</label>
											<div class="col-md-7">	
											<label class="control-label">${userBean.address}</label>
											</div>
										</div>
										
										<hr>
										<div class="form-group">
												<div class="col-md-offset-2 col-md-10">
													<a role="button" type="submit" class="btn btn-success" href="#Firefox" data-toggle="tab">&nbsp;&nbsp修改&nbsp;&nbsp </a>
												</div>
										</div>
									
									
								</div>
				
								<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
								
								
								
						</div>		
							
                   <!-- tab 第二个 哈哈哈                      -->
						<div class="tab-pane fade" id="Safari">
							<h4 class="page-header" style="margin-top:10px">开发者资料</h4>
								<div class="form-horizontal">
										<div class="form-group">
										
											<label for="label1" class="col-md-2 control-label">开发者类型:</label>
											<div class="col-md-7">	
												<label class="control-label">${developerBean.developerType }</label>
											</div>
										</div>
										
										<div class="form-group">
											<label for="label2" class="col-md-2 control-label">公司名称:</label>
											<div class="col-md-7">	
												<label class="control-label">${developerBean.companyName }</label>
											</div>
										</div>
						
										<div class="form-group">
											<label for="label3" class="col-md-2 control-label">公司地址:</label>
											<div class="col-md-7">	
												<label class="control-label">${developerBean.companyAddress }</label>
											</div>
										</div>
										
										<div class="form-group">
											<label for="label1" class="col-md-2 control-label">联系人:</label>
											<div class="col-md-7">	
											<label class="control-label">${developerBean.linkMan }</label>
											</div>
										</div>
										
										<div class="form-group">
											<label for="label1" class="col-md-2 control-label">邮箱:</label>
											<div class="col-md-7">	
												<label class="control-label">${developerBean.email }</label>
											</div>
										</div>
										
										<div class="form-group">
											<label for="label1" class="col-md-2 control-label">手机号码:</label>
											<div class="col-md-7">	
												<label class="control-label">${developerBean.telePhone }</label>
											</div>
										</div>
										
										<div class="form-group">
											<label for="label1" class="col-md-2 control-label">QQ号:</label>
											<div class="col-md-7">	
												<label class="control-label">${developerBean.qq }</label>
											</div>
										</div>
										<hr>
										<div class="form-group">
												<div class="col-md-offset-2 col-md-10">
													<a type="submit" class="btn btn-success" href="#Opera" data-toggle="tab">&nbsp;&nbsp修改&nbsp;&nbsp </a>
												</div>
										</div>
									
									
								</div>
				
								<br><br><br><br><br><br><br><br><br><br><br><br>
								
								
								
						</div>
						
						<!--     tab 第三个 哈哈哈           -->
						
						
						
						<!--   tab 第四个 哈哈哈          -->
						
						<div class="tab-pane fade in active" id="Firefox">
							<h4 class="page-header" style="margin-top:10px">用户资料修改</h4>
								<form:form class="form-horizontal" commandName="userBean" action="updateUserInfo" method="post">
									  <form:hidden path="id" />
									  <form:hidden path="password" />
									  <form:hidden path="uid" />
										
										<div class="form-group">
										
											<label for="label1" class="col-md-2 control-label">用户名：</label>
											<div class="col-md-7">	
												<form:input  class="form-control" id="label1" path="username"/>
											</div>
										</div>
										
										<div class="form-group">
											<label for="label2" class="col-md-2 control-label">昵称：</label>
											<div class="col-md-7">	
												<form:input class="form-control" id="label2" path="nickName"/>
											</div>
										</div>
						
										<div class="form-group">
											<label for="label3" class="col-md-2 control-label">性别：</label>
											<div class="col-md-7">	
												<form:input type="text" class="form-control" id="label3" path="sex"/>
											</div>
										</div>
										
										<div class="form-group">
											<label for="address" class="col-md-2 control-label">地址：</label>
											<div class="col-md-7">	
												<form:input class="form-control" id="address" path="address"/>
											</div>
										</div>
										
										<hr>
										<div class="form-group">
												<div class="col-md-offset-2 col-md-10">
													<button type="submit" class="btn btn-success">&nbsp;&nbsp;提交&nbsp;&nbsp;</button>
												</div>
										</div>
									
									
								</form:form>
				
								<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
								
								
								
						</div>



							
								
				    </div>
					
					
				</div>
			</div>
		
		
		</div>
	
	</div>
 
 
 
 
 
 
 </div>
 
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://libs.baidu.com/jquery/2.0.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>resources/plugin/js/bootstrap.min.js"></script>
	<script >
	$(function () { $("[data-toggle='popover']").popover(); });
	</script>
	
  </body>
  </html>