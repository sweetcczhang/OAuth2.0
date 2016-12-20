<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
					<li><a href="<%=basePath %>change/managerController">&nbsp;管理中心&nbsp;</a></li>
					<li><a href="<%=basePath %>change/accountCenterController">&nbsp;账号中心&nbsp;</a></li>
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
		<div class="container">
		    <h3 class="page-header">开发者资料注册</h3>
			
			<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-info" >
			 <div class="panel-heading"><p style="font-size:20px">基本资料填写</p></div>
				<div class="panel-body">
					
							
					<form:form class="form-horizontal" action="save_developer" commandName="developerBean" method="post">
						<form:hidden path="userId"/>
						<div class="form-group">
						
							<label  class="col-md-2 control-label">开发者类型</label>
							<div class="col-md-7">	
								<label class="radio-inline">
								<form:radiobutton   path="developerType" value="公司"/>&nbsp;公司
								</label>
								<label class="radio-inline">
								<form:radiobutton   path="developerType" value="个人"/>&nbsp;个人
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="label2" class="col-md-2 control-label">公司名称</label>
							<div class="col-md-7">	
							<form:input rows="3" class="form-control" id="label2" path="companyName"/> 
							</div>
						</div>
						<br>
						<div class="form-group">
							<label for="label3" class="col-md-2 control-label">公司地址</label>
							<div class="col-md-7">	
							<form:input  class="form-control" id="label3" path="companyAddress"/>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label for="linkMan" class="col-md-2 control-label">联系人</label>
							<div class="col-md-7">	
							<form:input  class="form-control" id="linkMan" path="linkMan"/>
							<form:errors path="linkMan"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label for="email" class="col-md-2 control-label">邮箱</label>
							<div class="col-md-7">	
							<form:input class="form-control" id="email" path="email"/>
							<form:errors path="email"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label for="telePhone" class="col-md-2 control-label">联系电话</label>
							<div class="col-md-7">	
							<form:input class="form-control" id="telePhone" path="telePhone"/>
							<form:errors path="telePhone"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label for="qqNumber" class="col-md-2 control-label">QQ号</label>
							<div class="col-md-7">	
							 <form:input  class="form-control" id="qqNumber" path="qq"/>
							 <form:errors path="qq"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group">
								<div class="col-md-offset-2 col-md-10">
									<button type="submit" class="btn btn-success">&nbsp;&nbsp提交&nbsp;&nbsp</button>
								</div>
						</div>
					
					
					</form:form>
					
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