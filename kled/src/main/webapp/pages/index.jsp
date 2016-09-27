<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>LPL</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1' name='viewport'>
	<link href='${pageContext.request.contextPath}/assets/css/bootstrap.min.css' rel='stylesheet' type='text/css'>
	<link href='${pageContext.request.contextPath}/assets/css/animate.min.css' rel='stylesheet' type='text/css'>
	<link href='${pageContext.request.contextPath}/assets/css/font-awesome.min.css' rel='stylesheet' type='text/css'>
	<link href='${pageContext.request.contextPath}/assets/css/simple-line-icons.css' rel='stylesheet' type='text/css'>
	<link href="${pageContext.request.contextPath}/assets/lib/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
	<link href='${pageContext.request.contextPath}/assets/css/font.css' rel='stylesheet' type='text/css'>
	<link href='${pageContext.request.contextPath}/assets/css/angulr.css' rel='stylesheet' type='text/css'>
	<link href='${pageContext.request.contextPath}/assets/css/knockout-datatable.css' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" media="all" href="${pageContext.request.contextPath}/assets/css/app.css" />
</head>
<body>
	<div class="login-container">
		<div class="login-content">
			<h2 class="text-white m-b-xx2">Kled平台</h2>
			<div class="row login-content-bg">
				<div class="col-xs-6">
				</div>
				<div class="col-xs-6">
					<div class="login-form">
					    <h4 class="text-center text-white m-b">用户登录</h4>
				        <form name="form" class="form-validation" id="login_form" action="login">
				            <div class="list-group list-group-sm">
				                <div class="list-group-item">
								    <div class="input-group">
								  	    <span class="input-group-addon no-border no-bg"><i class="fa fa-user"></i></span>
								  	    <input type="text" placeholder="用户名" class="form-control no-border" name="userName">
								    </div>			                
				                </div>
				                <div class="list-group-item">
								    <div class="input-group">
								     	<span class="input-group-addon no-border no-bg"><i class="fa fa-lock"></i></span>
				                    	<input type="password" placeholder="密码" class="form-control no-border" name="passWord">
								    </div>				                
				                </div>
				                <div class="list-group-item">
								    <div class="input-group">
								      	<span class="input-group-addon no-border no-bg"><i class="fa fa-map-marker"></i></span>
							            <select class="form-control no-border">
							              <option value="">本地用户</option>
							            </select>
								    </div>
				                </div>
				            </div>
				            <div class="m-b" id="showCode" style="display: block">
				            	<input type="text" placeholder="" class="form-control no-border inline w-md v-middle">
				            	<img src="assets/img/creatCode.jpg" width="80" height="34" />
				            </div>
				            <button type="submit" class="btn btn-block btn-info">登录</button>
				            
				            <img alt="" src="${pageContext.request.contextPath}/getSystemLoginQR" style="width:50%;height:50%;">
				            <!-- <div class="text-danger wrapper text-center">密码错误，剩余2次尝试机会</div> -->
				        </form>
					</div>
					
				</div>
			</div>
		</div>
		<div class="login-footer wrapper text-center">
		    <small class="text-muted">&copy; 版权所有 2016 kled</small>
		</div>
	</div>
	
	
    <script src='${pageContext.request.contextPath}/assets/js/jquery.min.js'></script>

   <%--  <script src="${pageContext.request.contextPath}/assets/js/underscore.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/backbone.js"></script>

    <script src="${pageContext.request.contextPath}/assets/js/knockout.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/knockout.bootstrap.modal.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/knockout-datatable.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/knockout.validation.js"></script>

    <script src='${pageContext.request.contextPath}/assets/js/bootstrap.min.js'></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-drag.js"></script>

    <script src='${pageContext.request.contextPath}/assets/js/ui-load.js'></script>
    <script src='${pageContext.request.contextPath}/assets/js/ui-jp.config.js'></script>
    <script src='${pageContext.request.contextPath}/assets/js/ui-jp.js'></script>
    <script src='${pageContext.request.contextPath}/assets/js/ui-nav.js'></script>
    <script src='${pageContext.request.contextPath}/assets/js/ui-toggle.js'></script>

    <script src="${pageContext.request.contextPath}/assets/js/ace.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/ace.ajax-content.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/app.ajax-content.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/request.js"></script>


    <script src='${pageContext.request.contextPath}/assets/js/ajax-pushlet-client.js'></script>
    <script src='${pageContext.request.contextPath}/assets/js/common.js'></script>
    <script src='${pageContext.request.contextPath}/assets/lib/moment/moment.js'></script>
    <script src='${pageContext.request.contextPath}/assets/lib/bootstrap-daterangepicker/daterangepicker.js'></script> --%>
    
	
</html>