<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String ctxPath = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ctxPath+"/";
//String basePath = ctxPath+"/";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ctxPath+"/";
System.out.println("basePath:"+basePath);
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
					<li class="active"><a  href="<%=basePath %>change/indexController">&nbsp;&nbsp;&nbsp;主页&nbsp;&nbsp;&nbsp;</a></li>
					<li><a href="<%=basePath %>change/documentController">&nbsp;文档中心&nbsp;</a></li>
					<li class="active"><a href="<%=basePath %>change/managerController">&nbsp;管理中心&nbsp;</a></li>
					<li><a href="<%=basePath %>change/accountCenterController">&nbsp;账号中心&nbsp;</a></li>
				</ul>
				
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
			
			</div>
	
		</div>
  
  </div>
  
  
 <div class="container content">
		<div class="container">
		     <div class="row">
		     <div class="col-md-6 col-md-offset-1">
					<ol class="breadcrumb" style="background-color:white;margin-top:10px">
						<li><a href="<%=basePath %>change/managerController">管理中心</a></li>
						
						<li  class="active">网站资料</li>
					</ol>
			</div>
			</div>
			<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-info" >
			 <div class="panel-heading"><p style="font-size:20px">应用详细资料</p></div>
				<div class="panel-body">
					
					<div class="form-horizontal">
					<div class="form-group">
										
						<label for="label1" class="col-md-2 control-label">应用名称:</label>
						<div class="col-md-7">	
							<label class="control-label">${clientBean.clientName }</label>
						</div>
					</div>
										
					<div class="form-group">
						<label for="label2" class="col-md-2 control-label">应用标识:</label>
						<div class="col-md-7">	
							<label class="control-label">${clientBean.clientId }</label>
						</div>
					</div>
						
										<div class="form-group">
											<label for="label3" class="col-md-2 control-label">应用密钥:</label>
											<div class="col-md-7">	
												<label class="control-label">${clientBean.clientSecret }</label>
											</div>
										</div>
										
										<div class="form-group">
											<label for="label1" class="col-md-2 control-label">应用地址:</label>
											<div class="col-md-7">	
											<label class="control-label">${clientBean.webUrl }</label>
											</div>
										</div>
										
										<div class="form-group">
											<label for="label1" class="col-md-2 control-label">应用回调地址:</label>
											<div class="col-md-7">	
												<label class="control-label">${clientBean.redirectUrl }</label>
											</div>
										</div>
										
										<div class="form-group">
											<label for="label1" class="col-md-2 control-label">创建时间:</label>
											<div class="col-md-7">	
												<label class="control-label">${clientBean.createTime }</label>
											</div>
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