<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<% if(flag){ %>
			    <ul class="nav navbar-nav navbar-right">
			       <%if(avatar){ %>
					     <li><img class="img-circle" style="height:50px;width:50px" src="${avatar}"></li>
					<% }%>
					<li class="dropdown">
				  	  <a class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					  <!--<span class="glyphicon glyphicon-user"></span>-->${nickName}
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
					<!--  
					<p class="navbar-text" style="font-size:18px">${username}</p>
					<li><a href="">通知</a></li>glyphicon glyphicon-off
					<li><a href="<%=basePath%>logout">退出</a></li>	
					-->
				</ul>
				<%}else{ %>
				   <ul class="nav navbar-nav navbar-right">
				    	<li> <a href="<%=basePath%>login">登录</a></li>
						<li><a href="<%=basePath %>registers">注册</a></li>
                   </ul>
				
				<%}%>			
			   
			</div>
	
		</div>
  
  </div>
  <!-- 图片轮播效果 -->
	<div class="carousel slide" id="browser-carousel" data-ride="carousel">
	   <!-- 轮播（Carousel）指标 -->
	   <ol class="carousel-indicators">
	      <li data-target="#browser-carousel" data-slide-to="0" class="active"></li>
	      <li data-target="#browser-carousel" data-slide-to="1"></li>
	      <li data-target="#browser-carousel" data-slide-to="2"></li>
	   </ol> 
	   <!-- 轮播（Carousel）项目 -->
	   <div class="carousel-inner" role="listbox">
	      <div class="item active">
	         <img src="<%=basePath %>resources/Images/111.png" alt="First slide">
	         <div class="carousel-caption" >
	         	<h1>移动应用开发</h1>
				<p>移动应用接入第三方开放平台，可以使应用拥有更多的更多的用户。无需注册，用户登录分享更方便</p>
		         <p><a class="btn btn-lg btn-primary" href="<%=basePath %>change/documentController"
	                          role="button">了解更多</a>
	             </p>
	             <br><br> <br><br> 
	         </div>
	      </div>
	      <div class="item">
	         <img src="<%=basePath %>resources/Images/122.jpg" alt="Second slide">
	         <div class="carousel-caption" >
                    <h1>网站开发</h1>
                    <p>网站接入第三方开放平台，让你的网站支持第三方账号登录降。低用户注册门槛，提高用户留存</p>
                    <p><a class="btn btn-lg btn-primary" href="<%=basePath %>change/documentController"
                          role="button">了解更多</a>
                    </p>
                   <br><br> <br><br> 
             </div>
	      </div>
			<div class="item">
				<img src="<%=basePath %>resources/Images/12.jpg" alt="Third slide">
				<div class="carousel-caption" >
					<h1 style="margin-top:-100px">站内应用开发</h1>
					<p>接入第三方开放平台，可以让你在网站内部进行应用开发。获取更多的用户</p>
					<p><a class="btn btn-lg btn-primary" href="<%=basePath %>change/documentController"
							role="button">了解更多</a>
					</p>
					<br><br> <br><br>
				</div>
			</div>
	   </div>
	   <!-- 轮播导航 左右箭头-->
	   <div class="container">
		   <a class="carousel-control left" href="#browser-carousel" data-slide="prev" role="button">
			 
			 <span class="glyphicon glyphicon-chevron-left center" style="margin-left:100px" aria-hidden="true"></span>
			  <span class="sr-only">Previous</span>
		   </a>
		   <a class="carousel-control right" href="#browser-carousel" data-slide="next">
			  <span class="glyphicon glyphicon-chevron-right center" aria-hidden="true"></span>
			  <span class="sr-only"> Next</span>
		   </a>
	   </div>
	   
	</div>
	<div class="container content" id="profile-content">
		<div class="row summary">
			<div class="col-md-4">
				<img class="img-circle" src="<%=basePath %>resources/Images/3.png" alt="phone">
				<h2>移动应用开发</h2>
				<p>接入第三方开放平台，可以让你的移动应用拥有更多的用户，用户登录分享更加方便，无需注册。</p>
				<p><a class="btn btn-default" href="<%=basePath %>change/documentController">了解跟多</a></p>
			</div>
			<div class="col-md-4">
				<img class="img-circle" src="<%=basePath %>resources/Images/4.png" alt="firefox">
				<h2>网站应用开发</h2>
				<p>接入第三方开放平台，可以让你的网站使用第三方账号登录。可以快速增加你网站的用户群体，同时免去了用户注册的麻烦。</p>
				<p><a class="btn btn-default" href="<%=basePath %>change/documentController">了解更多</a></p>
			</div>
			<div class="col-md-4">
				<img class="img-circle" src="<%=basePath %>resources/Images/5.png" alt="safari">
				<h2>站内应用开发</h2>
				<p>接入第三方开放平台，可以让你的网站使用第三方账号登录。可以快速增加你网站的用户群体，同时免去了用户注册的麻烦。</p>
				<p><a class="btn btn-default" href="<%=basePath %>change/documentController">了解更多</a></p>
			</div>
		</div>
		
	</div>
    <footer class="container-fluid content-footer">
    	<div class="container footer-container">
	        <p><strong>&copy; 2015 <a href="" target="_blank">张城城</a></strong></p>    		
    	</div>
    </footer>
	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://libs.baidu.com/jquery/2.0.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath%>resources/plugin/js/bootstrap.min.js"></script>
  </body>
</html>