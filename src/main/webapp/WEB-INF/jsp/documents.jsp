<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
					<li class="active"><a href="<%=basePath %>change/documentController">&nbsp;文档中心&nbsp;</a></li>
					<li><a href="<%=basePath %>change/managerController">&nbsp;管理中心&nbsp;</a></li>
					<li><a href="<%=basePath %>change/accountCenterController.html">&nbsp;账号中心&nbsp;</a></li>
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
			    <%}else { %>
			    
			     <ul class="nav navbar-nav navbar-right">
					<li><a href="<%=basePath %>login">登录</a></li>
					<li><a href="<%=basePath %>registers">注册</a></li>
				 </ul>
			    
			    <%} %>
			</div>
	
		</div>
  
  </div>
  
<div class="container content">
	<h1 class="page-header">文档</h1> 
    <div class="row">
		<div class="col-md-2" id="sidebar">
			<div class="list-group">
				<a class="list-group-item" href="#Safari" data-toggle="tab">OAuth2.0协议</a>
				<a class="list-group-item" href="#Firefox" data-toggle="tab">网站应用开发</a>
				<a class="list-group-item" href="#Chrome" data-toggle="tab">移动应用开发</a>
				<a class="list-group-item" href="#Opera" data-toggle="tab">接口调用</a>
				<a class="list-group-item" href="#IE" data-toggle="tab">返回码说明</a>
				<a class="list-group-item">资源下载</a>
			</div>
		</div>
		
		<div class="col-md-10">
			<div class="panel panel-default">
				<div class="panel-body">
						<div class="tab-content">
						   
                          <!-- 面板一哈哈哈哈哈哈哈-->
						   <div class="tab-pane fade" id="IE">
								<div class="container">
								<h3 class="page-header">返回码说明</h3>
								<div class="col-md-9">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>返回码</th>
												<th>错误码描述</th>
												<th>说明</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>40001</td>
												<td>invalid credential</td>
												<td>不合法的调用凭证</td>
											</tr>
											<tr>
												<td>40002</td>
												<td>invalid grant_type</td>
												<td>不合法的grant_type</td>
											</tr>
											<tr>
												<td>40003</td>
												<td>invalid openid</td>
												<td>不合法的OpenID</td>
											</tr>
											<tr>
												<td>40004</td>
												<td>invalid media type</td>
												<td>不合法的媒体文件类型</td>
											</tr>
											<tr>
												<td>40013</td>
												<td>invalid appid</td>
												<td>不合法的AppID</td>
											</tr>
											<tr>
												<td>40014</td>
												<td>invalid access_token</td>
												<td>不合法的access_token</td>
											</tr>
											<tr>
												<td>40066</td>
												<td>invalid url</td>
												<td>不合法的url</td>
											</tr>
											<tr>
												<td>41001</td>
												<td>access_token missing</td>
												<td>缺失access_token参数</td>
											</tr>
											<tr>
												<td>41002</td>
												<td>appid missing</td>
												<td>缺失appid参数</td>
											</tr><tr>
												<td>41003</td>
												<td>refresh_token missing</td>
												<td>缺失refresh_token参数</td>
											</tr>
											<tr>
												<td>42001</td>
												<td>access_token expired</td>
												<td>access_token超时</td>
											</tr>
											
										</tbody>
									
									</table>
								</div>
								
								</div>
											
							</div>		
						
						
						
						  <!-- 面板一哈哈哈哈哈哈哈-->
							<div class="tab-pane fade" id="Chrome">
								<div class="container">
									<h4 class="page-header col-md-9" style="margin-top:15px">移动应用开发第三方登录指南</h4>
										<h4 class="col-md-9 word-p">准备工作</h4>
										<p class="col-md-9 word-content">移动应用第三方登录是基于OAuth2.0协议标准构建的OAuth2.0授权登录系统。<br><br>
										   在进行OAuth2.0授权登录接入之前，在授权开放平台注册开发者帐号，并
										   拥有一个已审核通过的移动应用，并获得相应的AppID和AppSecret，申请微信登
										   录且通过审核后，可开始接入流程。</p>
										<h4 class="col-md-9 word-p">授权流程说明</h4>
										<p class="col-md-9 word-content">OAuth2.0授权登录让开放平台用户使用开放平台身份安全登录第三方应用或网站，
										   在开放用户授权登录已接入OAuth2.0的第三方应用后，第三方可以获取到
										   用户的接口调用凭证（access_token），通过access_token可以进行开放平
										   台授权关系接口调用，从而可实现获取开放平台用户基本开放信息和帮助用户实现基础开放功能等。</p>
										   
										<p class="col-md-9 word-content">OAuth2.0授权登录目前支持authorization_code模式，适用于拥有server端的应用授权。
										该模式整体流程为：</p>
										
										<p class="col-md-9 word-content">1. 第三方发起开放平台授权登录请求，开放平台用户允许授权第三方应用后，开放平台会拉起应用或重定向到
										第三方网站，并且带上授权临时票据code参数；
										<br><br>
										2. 通过code参数加上AppID和AppSecret等，通过API换取access_token；
										<br><br>
										3. 通过access_token进行接口调用，获取用户基本数据资源或帮助用户实现基本操作。</p>
										<h4 class="col-md-9 word-p">access_token时序图：</h4>
										<div class="col-md-9">
										   <img src="<%=basePath %>resources/Images/access_token.jpg" style="height:400px;width:750px">
										
										</div>
													
								</div>
							</div>
								<!-- 面板二哈哈哈哈哈哈哈-->		
								<div class="tab-pane fade" id="Firefox">
									<div class="container">
										<h4 class="page-header col-md-9" style="margin-top:15px;color:black">移动应用开发第三方登录指南</h4>
										<h4 class="col-md-9 word-p">准备工作</h4>
										<p class="col-md-9 word-content">网站应用第三方登录是基于OAuth2.0协议标准构建的OAuth2.0授权登录系统。<br><br>
											   在进行OAuth2.在进行OAuth2.0授权登录接入之前，在开放平台注册开发者帐号，
											   并拥有一个已审核通过的网站应用，并获得相应的AppID和AppSecret，申请第三方登录且通过审
											   核后，可开始接入流程。</p>
											   
											   <h4 class="col-md-9 word-p">授权流程说明</h4>
													<p class="col-md-9 word-content">OAuth2.0授权登录让开放平台用户使用开放平台身份安全登录第三方应用或网站，
													   在开放用户授权登录已接入OAuth2.0的第三方应用后，第三方可以获取到
													   用户的接口调用凭证（access_token），通过access_token可以进行开放平
													   台授权关系接口调用，从而可实现获取开放平台用户基本开放信息和帮助用户实现基础开放功能等。</p>
													   
													<p class="col-md-9 word-content">OAuth2.0授权登录目前支持authorization_code模式，适用于拥有server端的应用授权。
													该模式整体流程为：</p>
													
													<p class="col-md-9 word-content">1. 第三方发起开放平台授权登录请求，开放平台用户允许授权第三方应用后，开放平台会拉起应用或重定向到
													第三方网站，并且带上授权临时票据code参数；
													<br><br>
													2. 通过code参数加上AppID和AppSecret等，通过API换取access_token；
													<br><br>
													3. 通过access_token进行接口调用，获取用户基本数据资源或帮助用户实现基本操作。</p>
													<h4 class="col-md-9 word-p">access_token时序图：</h4>
													<div class="col-md-9">
													   <img src="<%=basePath %>resources/Images/access_token.jpg" style="height:400px;width:750px">
													
													</div>
									</div>	
								</div>
					 <!--面板三哈哈哈哈哈哈哈-->			
								<div class="tab-pane fade in active" id="Safari">
									<div class="container">
										  <h3 class="page-header col-md-9" style="margin-top:15px">OAuth2.0协议介绍</h3>
										  <h4 class="col-md-9 word-p">OAuth2.0协议是什么？</h4>
										  <p class="col-md-9 word-content">OAuth 2.0是在2006年底创建的下一代OAuth协议。OAuth 2.0为客户
										  端开发者开发Web应用，桌面端应用程序，移动应用及客厅设备提供特定的授权流程。
										  该规范是IETF OAuth WG工作组下基于OAuth WRAP协议制定的。</p>
										  <hr class="feature-divider col-md-9">
										  <h4 class="col-md-9 word-p">OAuth 2.0 能做什么?</h4>
										  <p class="col-md-9 word-content">OAuth 2.0 授权框架允许第三方应用通过代表拥有编排批准交互
										  的资源所有者和HTTP服务的资源或者通过允许第三方应用程序获得代表自己访问受限的HTTP服务。</p>
										  <hr class="feature-divider col-md-9">
										  <h4 class="col-md-9 word-p">OAuth 2.0有哪些角色?</h4>
										  <p class="col-md-9 word-content">资源拥有者（resource owner）：能够授权访问被保护资源的一个实体。当它指的一个人时，就是称之为终端用户。</p>
										  <p class="col-md-9 word-content">资源服务器（resource server）：管理受保护资源的服务器。当使用访问令牌访问资源时，它决定是否接受该令牌
										  并输出受保护的资源。 </p>
										  <p class="col-md-9 word-content"> 客户端（client）：应用程序本身不存储任何受保护的资源，而是资源所有
										  者授权通过后，使用它的授权访问受保护的资源，然后客户端把响应的数据展示/提交给服务器。</p>
										  <p class="col-md-9 word-content"> 授权服务器（authorization server）：客户端成功验证资源所有者并获取授权后，授权服务器发放访问令牌给客户端。 </p>
										  <hr class="feature-divider col-md-9">
										  <h4 class="col-md-9 word-p">OAuth 2.0授权流程?</h4>	
										  <p class="col-md-9 word-content">1 客户端向资源拥有者发起授权请求，这种授权请求可以直接向资源拥有者发起（如图），也可以间接通过授权服务器作为中介发起。</p>
										  <p class="col-md-9 word-content">2 客户端接收授权许可，这是一个代表资源所有者的授权凭证。授权类型可以OAuth 2.0规范中四种的任意一种，也可以是扩展授权类
										  型。授权类型取决于方法所使用的客户端请求授权和授权服务器所支持的类型。</p>
										  <p class="col-md-9 word-content">3 客户端通过私有证书和授权许可请求授权服务器授权。</p>
										  <p class="col-md-9 word-content">4 授权服务器对客户端进行验证。验证通过后，返回访问令牌。</p>
										  <p class="col-md-9 word-content">5 客户端使用访问令牌向资源服务器请求受保护资源。</p>
										  <p class="col-md-9 word-content">6 资源服务器验证令牌的有效性，验证成功后，下发受保护的资源。</p>
										  <div class="col-md-9">
										  <img style="height:400px;width:600px" src="<%=basePath %>resources/Images/oauth2-process.png">
									  
									  
									  </div>
									</div>
								</div>
								<!--面板四哈哈哈哈哈哈哈哈哈-->
										<div class="tab-pane fade" id="Opera">
												<div class="container">
													<h3 class="page-header col-md-9">授权后接口的调用</h3>
													<h4 class="col-md-9 word-p">通过code获取access_token</h4>
													<p class="col-md-9 word-content">接口说明</p>
													<p class="col-md-9 word-content">通过code获取access_token的接口。</p>
													<p class="col-md-9 word-content">请求说明</p>
													<div class="alert alert-info col-md-9" role="alert">
													<p class="word-content">http请求方式: GET<br><br><a>
																	 https://api.weixin.qq.com/sns/oauth2/access_token?<br>
																	 appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code</a></p>
													
													</div>
													
													<h4 class="col-md-9 word-p">参数说明</h4>
													<div class="col-md-9">
														<table class="table table-hover">
															<thead>
																<tr>
																	<th>参数</th>
																	<th>是否必须</th>
																	<th>说明</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>client_id</td>
																	<td>是</td>
																	<td>应用唯一标识，在开放平台提交应用审核通过后获得</td>
																</tr>
																<tr>
																	<td>client_secret</td>
																	<td>是</td>
																	<td>应用密钥AppSecret，在开放平台提交应用审核通过后获得</td>
																</tr>
																<tr>
																	<td>code</td>
																	<td>是</td>
																	<td>填写第一步获取的code参数</td>
																</tr>
																<tr>
																	<td>grant_type</td>
																	<td>是</td>
																	<td>填authorization_code</td>
																</tr>
															
															</tbody>
														</table>
													
													</div>
													
													<h4 class="col-md-9 word-p">返回说明</h4>
													<p class="col-md-9 word-content">正确的返回说明</p>
													<div class="col-md-9 alert alert-warning" role="alert">
														<p class="word-content">
															{ <br>
															"access_token":"ACCESS_TOKEN",<br> 
															"expires_in":7200, <br>
															"refresh_token":"REFRESH_TOKEN",<br>
															"openid":"OPENID", <br>
															"scope":"SCOPE" <br>
															 }<br>
														</p>
													</div>
													
													<div class="col-md-9">
														<table class="table table-hover">
															<thead>
																<tr>
																	<th>参数</th>
																	<th>说明</th>
																	
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>access_token</td>
																	<td>接口调用凭证</td>
																</tr>
																<tr>
																	<td>expires_in</td>
																	<td>access_token接口调用凭证超时时间，单位（秒）</td>
																</tr>
																<tr>
																	<td>refresh_token</td>
																	<td>用户刷新access_token</td>
																</tr>
																<tr>
																	<td>openid</td>
																	<td>授权用户唯一标识</td>
																</tr>
																<tr>
																	<td>scope</td>
																	<td>用户授权的作用域，使用逗号（,）分隔</td>
																</tr>
															</tbody>
														</table>
													</div>
													
													<h4 class="col-md-9 word-p">错误返回示例</h4>
													<div class="col-md-9 alert alert-warning" role="alert">
														<p class="word-content">
															{
															"error":"invalid code"
															}
														</p>
													</div>
													<h4 class="col-md-9 word-p">获取用户个人信息</h4>
													<h4 class="col-md-9 word-p">请求说明</h4>
													<p class="col-md-9 word-content">
														http请求方式: GET<br>
													   <a> https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID<a>
													</p>
													<h4 class="col-md-9 word-p">返回说明</h4>
													<div class=" col-md-9 alert alert-warning" role="alert">
														<p class="word-content">
														    {       <br>
																"nickname":"NICKNAME",<br>
																"sex":1,<br>
																"province":"PROVINCE",<br>
																"city":"CITY",<br>
																"country":"COUNTRY",<br>				
															}<br>
														   
														</p>
													
													</div>
													
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
  </body>
</html>