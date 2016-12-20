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
     
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  <div class="container content">
      <!-- <h3 class="page-header" style="margin-top:20px">账号中心</h3> -->
		<div class="container">
		<div class="row">
		<div class="col-md-3"></div>
		
		<div class="col-md-7" style="margin-left:-31px">
		 <h3 class="page-header" style="margin-top:10px">开放授权身份管理服务系统</h3>
			<div class="panel panel-default">
				<div class="panel-body">
					
			       
			        <div class="tab-pane fade in active" id="Chrome">
							<h4 class="page-header" style="margin-top:1px">&nbsp;&nbsp;是否允许<a href="${client.webUrl}">${client.clientName}</a>进行以下操作：</h4>
								<div class="form-horizontal">
										 <ul style="font-size:15px" >
							   <br/>
							   <li >
							  &nbsp;&nbsp;&nbsp;&nbsp;
							   <label style="font-size:14px">获得您的昵称、性别等信息</label>
							   </li>
							   <br>
							   <li >
							   &nbsp;&nbsp;&nbsp;&nbsp;
							   <label style="font-size:14px">获得您的好有关系</label>
							   </li>
							   <br/>
							   <br/>
							   <form action="" method="post" class="form-horizontal">
							    
								<input  type="hidden" name="authorizeFlag"  value="true">
								<hr>
								<div class="form-group">
									<div class="col-md-offset-7 col-md-10">
										<button style="font-size:17px" type="submit" class="btn btn-success">&nbsp;&nbsp;授权&nbsp;&nbsp;</button>&nbsp;&nbsp;&nbsp;&nbsp;
									 	<a style="font-size:17px" href="${client.webUrl}" role="button" class="btn btn-success">&nbsp;&nbsp;取消&nbsp;&nbsp;</a>
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