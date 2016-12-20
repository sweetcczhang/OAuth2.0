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
					<li><a href="<%=basePath %>change/indexController">&nbsp;&nbsp;&nbsp;主页&nbsp;&nbsp;&nbsp;</a></li>
					<li><a href="<%=basePath %>change/documentController">&nbsp;文档中心&nbsp;</a></li>
					<li class="active"><a href="<%=basePath %>change/managerController">&nbsp;管理中心&nbsp;</a></li>
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
			<div class="row">
			<div class="col-md-2">
			<img src="<%=basePath %>resources/Images/touxiang.jpg" alt="" class="img-thumbnail" style="width:100px;height:100px">
			</div>
			<div class="col-md-2">
		
				<p style="margin-left:-80px;margin-top:10px">开发者：${username }</p>
				<p style="margin-left:-80px">类型：${developerBean.developerType }</p>
				<p style="margin-left:-80px">资料完善度</p>
			</div>
		</div>
		 <hr class="feature-divider">
		<ul class="nav nav-tabs" id="feature-tab">
		  <li role="presentation" class="active"><a href="#moveapp" data-toggle="tab">网站应用</a></li>
		  <li role="presentation" ><a href="#Chrome" data-toggle="tab">移动应用</a></li>
		  <li role="presentation"><a href="#moveapp2" data-toggle="tab">站内应用</a></li>
		</ul>
		<br>
		
		<div class="tab-content">
			<div class="tab-pane fade" id="Chrome">
				<a href="<%=basePath %>appInfo/create_app" role="button" class="btn btn-success" title="BootStrap简介" 
				    data-container="body" data-toggle="popover"
					data-trigger="hover" data-placement="right" data-content="简洁、直观、强悍的前端开发框架，让web开发更迅速、简单。">
					创建移动应用</a>
			
				<div class="panel panel-info" style="margin-top:30px;height:600px">
					<div class="panel-heading"><span style="font-size:20px">应用列表</span></div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>应用名称</th>
									<th>应用ID</th>
									<th>应用地址</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
									
							</thead>
							<tbody>
							<c:forEach items="${appInfoList }"  var="appInfo">
								<tr>
								<td>${appInfo.appName }</td>
								<td>${appInfo.appId }</td>
								<td>${appInfo.appUrl }</td>
								<td>${appInfo.createTime }</td>
								<td>
								    <li class="dropdown">
								  	      <a class="sdropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									      <span class="glyphicon glyphicon-cog"></span>
									      <span class="caret"></span>
									    </a>
									  <ul class="dropdown-menu" style="background-color:white;width:10px">
					                        <li><a href="<%=basePath %>appInfo/${appInfo.id}/update">修改</a></li>
					                        <li role="separator" class="divider"></li>
					                        <li><a href="<%=basePath %>appInfo/${appInfo.id}/delete" >删除</a></li>
					                        <li role="separator" class="divider"></li>
					                        <li><a href="<%=basePath %>appInfo/${appInfo.id}/show" >详情</a></li>
	                	             </ul>
				                  </li>
								
								<!-- 
								<a href="<%=basePath %>appInfo/${appInfo.id}/update" role="button" class="btn btn-default">修改</a>
								<a href="<%=basePath %>appInfo/${appInfo.id}/delete" role="button" class="btn btn-default">删除</a>
								<a href="<%=basePath %>appInfo/${appInfo.id}/show" role="button" class="btn btn-default">详情</a>
								 -->
								</td>
								
								</tr>
								
						    </c:forEach>
							</tbody>
			  
						</table>
					</div>
				</div>
			
			</div>
			
			
			<div class="tab-pane fade  in active" id="moveapp">
				<a href="<%=basePath %>application/create_web" role="button" class="btn btn-success" title="BootStrap简介" 
				    data-container="body" data-toggle="popover"
					data-trigger="hover" data-placement="right" data-content="简洁、直观、强悍的前端开发框架，让web开发更迅速、简单。">
					创建网站应用</a>
			
				<div class="panel panel-info" style="margin-top:30px;height:600px">
					<div class="panel-heading"><span style="font-size:20px">应用列表</span></div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									
									<th>应用名称</th>
									<th>应用ID</th>
									<th>应用地址</th>
									<th>创建时间</th>
									<th>操作</th>
									</tr>
							</thead>
							<tbody>
							<c:forEach items="${clientList}" var="client">
								<tr>
								<td>${client.clientName }</td>
								<td>${client.clientId }</td>
								<td>${client.webUrl}</td>
								<td>${client.createTime}</td>
								<td>
								<li class="dropdown">
								  	      <a class="sdropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									      <span class="glyphicon glyphicon-cog"></span>
									      <span class="caret"></span>
									    </a>
										<ul class="dropdown-menu" style="background-color:white;width:10px">
							                     <li><a href="<%=basePath %>application/${client.id}/update">修改</a></li>
							                     <li role="separator" class="divider"></li>
							                     <li><a href="<%=basePath %>application/${client.id}/delete" >删除</a></li>
							                     <li role="separator" class="divider"></li>
							                     <li><a href="<%=basePath %>application/${client.id}/show" >详情</a></li>
			                	        </ul>
	                	        </li>
								<!--  
								<a href="<%=basePath %>application/${client.id}/update" role="button" class="btn btn-default">修改</a>
								<a href="<%=basePath %>application/${client.id}/delete" role="button" class="btn btn-default">删除</a>
								<a href="<%=basePath %>application/${client.id}/show" role="button" class="btn btn-default">详情</a>
								-->
								</td>
								</tr>
						   </c:forEach>
							</tbody>
			  
						</table>
					</div>
				</div>
			
			</div>
			
			
			<div class="tab-pane fade" id="moveapp2">
				<a href="<%=basePath %>innerInfo/create_inner" role="button" class="btn btn-success" title="BootStrap简介"
				    data-container="body" data-toggle="popover"
					data-trigger="hover" data-placement="right" data-content="简洁、直观、强悍的前端开发框架，让web开发更迅速、简单。">
					创建站内应用</a>
			
				<div class="panel panel-info" style="margin-top:30px;height:600px">
					<div class="panel-heading"><span style="font-size:20px">应用列表</span></div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>应用名称</th>
									<th>应用ID</th>
									<th>网站地址</th>
									<th>创建时间</th>
									<th>操作</th>
									</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${innerInfoList }" var="innerInfo">
									<tr>
									
									<td>${innerInfo.appName}</td>
									<td>${innerInfo.appId}</td>
									<td>${innerInfo.appUrl}</td>
									<td>${innerInfo.createTime}</td>
									<td>
									
									<li class="dropdown">
								  	      <a class="sdropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									      <span class="glyphicon glyphicon-cog"></span>
									      <span class="caret"></span>
									    </a>
										<ul class="dropdown-menu" style="background-color:white;width:10px">
							                     <li><a href="<%=basePath %>innerInfo/${innerInfo.id}/update">修改</a></li>
							                     <li role="separator" class="divider"></li>
							                     <li><a href="<%=basePath %>innerInfo/${innerInfo.id}/delete" >删除</a></li>
							                     <li role="separator" class="divider"></li>
							                     <li><a href="<%=basePath %>innerInfo/${innerInfo.id}/delete" role="button" >详情</a></li>
			                	        </ul>
	                	            </li>
									<!--  
									<a href="<%=basePath %>innerInfo/${innerInfo.id}/update" role="button" class="btn btn-success">修改</a>
									<a href="<%=basePath %>innerInfo/${innerInfo.id}/delete" role="button" class="btn btn-success">删除</a>
									<a href="<%=basePath %>innerInfo/${innerInfo.id}/show" role="button" class="btn btn-success">详情</a>
									-->
									</td>
									</tr>
								</c:forEach>
							</tbody>
			  
						</table>
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