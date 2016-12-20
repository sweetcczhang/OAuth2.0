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
	boolean flag;
	if(flag1!=null){
		flag=true;
	}else{
		flag=false;
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
     
    <link rel="stylesheet" href="<%=basePath %>resources/css/reset.css" />
	<link rel="stylesheet" href="<%=basePath %>resources/css/common.css" />
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  <div class="wrap login_wrap">
			<div class="content">
				
				<div class="logo"></div>
  
  <div class="container content">
		<div class="col-md-6 col-md-offset-3">
		     <br/>
			 <br/>
			<div class="panel panel-default" style="background:#f8f8ff">
				<div class="panel-body">
					<p style="font-size:15px">&nbsp;&nbsp;是否允许<a href="${client.webUrl}">${client.clientName}</a>进行以下操作：</p>
					  <ul style="font-size:15px" >
							   <br/>
							   <li >
							  &nbsp;&nbsp;&nbsp;&nbsp;
							   <label style="font-size:13px">&nbsp;&nbsp;获得您的昵称、性别等信息</label>
							   </li>
							   <br>
							   <li >
							   &nbsp;&nbsp;&nbsp;&nbsp;
							   <label style="font-size:13px">&nbsp;&nbsp;获得您的好有关系</label>
							   </li>
							   <br/>
							   <br/>
							   <form action="" method="post" class="form-horizontal">
							    
								<input  type="hidden" name="authorizeFlag"  value="true">
								
								<div class="form-group">
									<div class="col-md-offset-7 col-md-10">
										<button type="submit" class="btn btn-success">&nbsp;&nbsp;授权&nbsp;&nbsp;</button>&nbsp;&nbsp;&nbsp;&nbsp;
									 	<a href="?authorizeFlag=false" role="button" class="btn btn-success">&nbsp;&nbsp;取消&nbsp;&nbsp;</a>
									</div>
								</div>
							   
							   </form>
							  
						</ul>
					
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