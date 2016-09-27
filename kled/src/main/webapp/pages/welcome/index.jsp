<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>LPL</title>
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
	<div class='app app-header-fixed app-aside-fixed'>
	  <!-- header -->
	  <div class='app-header navbar' id='header' >
	    <!-- navbar header -->
	    <div class='navbar-header bg-dark'>
	      <button class='pull-right visible-xs dk'  >
	        <i class='glyphicon glyphicon-cog'></i>
	      </button>
	      <button class='pull-right visible-xs'>
	        <i class='glyphicon glyphicon-align-justify'></i>
	      </button>
	      <!-- brand -->
	      <a class='navbar-brand text-lt' href='#/'>
	        <span><img alt='.' src='assets/img/csc_logo.png'></span>
	        <span class='hidden-folded m-l-xs' style="margin-left:20px;">Kled</span>
	      </a>
	       <!-- / brand -->
	    </div>
	    <!-- / navbar header -->
	    <!-- navbar collapse -->
	    <div class='collapse pos-rlt navbar-collapse box-shadow bg-white-only'>
	          <!-- buttons -->
	      <div class='nav navbar-nav hidden-xs'>
	        <a class='btn no-shadow navbar-btn' href='#' target='.app' >
	          <i class='fa fa-dedent fa-fw text'></i>
	          <i class='fa fa-indent fa-fw text-active'></i>
	        </a>
	      </div>
	      <!-- / buttons -->
	      <!-- nabar right -->
	      <ul class='nav navbar-nav navbar-right'>
	        <li >
	          <a href="#pages/guide/index">向导页预览</a>
	        </li>
	        <li class="dropdown">
	          <a><i class="fa fa-fw fa-plus visible-xs-inline-block"></i><span></span> <span class="caret"></span></a>
	          <ul class="dropdown-menu" >
	            <li >
	              <a href="#" ><i class="fa fa-fw fa-angle-right text-muted m-r-xs"></i><span ></span></a>
	            </li>
	          </ul>
	        </li>
	        <li class='dropdown' >
	          <a class="dropdown-toggle" ><i class="fa fa-fw fa-warning"></i><span>告警</span> <span class='badge badge-sm up bg-danger' ></span> <span class="caret"></span></a>
	          <ul class="dropdown-menu"  id="warnings-tabs">
	            <li><a href="#pages/systems/warnings/index?showTab=sysTab"><i class="fa fa-fw fa-angle-right text-muted m-r-xs"></i>系统告警：<span class="text-danger" ></span>个</a></li>
	            <li><a href="#pages/systems/warnings/index?showTab=vdcTab"><i class="fa fa-fw fa-angle-right text-muted m-r-xs"></i>VDC配额告警：<span class="text-danger" ></span>个</a></li>
	          </ul>                
	        </li>
	        <li class='dropdown'>
	          <a  href='#pages/systems/notices/index'><i class='fa fa-bell'></i> 通知<span class='badge badge-sm up bg-info'>10</span></a>
	        </li>
	        <li >
	          <a href="#pages/services/shopping_carts/index"><i class="fa fa-shopping-cart"></i> 购物车 <span class='badge badge-sm up bg-danger' >10</span></a>
	        </li>
	        <li class='dropdown'>
	          <a class='dropdown-toggle clear'><i class='fa fa-user'></i><span class='hidden-sm hidden-md' >${currentUser.name }</span><b class='caret'></b></a>
	          <!-- dropdown -->
	          <ul class='dropdown-menu animated fadeInRight w-lg bg-light'>
	            <li>
	              <table class="table text-xs">
	                <tbody >
	                  <tr >
	                    <td><a href="#" onclick="showModal('pages/systems/users/edit_profile.html')" class="btn btn-info btn-xs">修改信息</a></td>
	                    <td><a href="#" onclick="showModal('pages/systems/users/edit_password.html')" class="btn btn-primary btn-xs">修改密码</a></td>
	                  </tr>
	                  <tr>
	                    <td>账号：</td>
	                    <td ></td>
	                  </tr>
	                  <tr>
	                    <td>昵称：</td>
	                    <td ></td>
	                  </tr>
	                  <tr>
	                    <td>角色：</td>
	                    <td ></td>
	                  </tr>
	                  <tr>
	                    <td>组织：</td>
	                    <td ></td>
	                  </tr>
	                  <tr>
	                    <td>手机：</td>
	                    <td ></td>
	                  </tr>
	                  <tr>
	                    <td>邮箱：</td>
	                    <td ></td>
	                  </tr>
	                </tbody>
	              </table>                
	            </li>
	            <li class='divider'>
	            </li>
	            <li>
	              <div class="padder"><a href="api/logoutServlet" class="btn btn-danger btn-xs"><i class='fa fa-power-off'></i> 退出系统</a></div>
	            </li>
	          </ul>
	          <!-- / dropdown -->
	        </li>
	      </ul>
	    <!-- / navbar right -->
	    </div>
	  <!-- / navbar collapse -->
	  </div>
	  <!-- / header -->
	
	  <!-- aside -->
	  <div class='app-aside hidden-xs bg-dark' id='aside'>
	    <div class='aside-wrap'>
	      <div class='navi-wrap'>
	        <div class='navi clearfix'>
	          <ul class='nav navbox'>
	            <li>
	              <a href="#pages/welcome/index"><i class="icon home"></i><span class='text-base'>首页</span></a>
	            </li>
	            <li>
	              <a class='auto'>
	                <span class='pull-right text-muted'><i class='fa fa-fw fa-angle-right text'></i><i class='fa fa-fw fa-angle-down text-active'></i></span>
	                  <i class="icon service"></i>
	                <span class='text-base'>服务</span>
	              </a>
	              <ul class='nav nav-sub dk'>
	                <li class='nav-sub-header'>
	                  <a><span class='text-base'>服务</span></a>
	                </li>
	                <li >
	                  <a href="#pages/services/product_definitions/index"><span>产品定义</span></a>
	                </li>
	                <li>
	                  <a href="#pages/services/service_definitions/index"><span>服务管理</span></a>
	                </li>
	              </ul>
	            </li>
	            <li>
	              <a class='auto'>
	                <span class='pull-right text-muted'><i class='fa fa-fw fa-angle-right text'></i><i class='fa fa-fw fa-angle-down text-active'></i></span>
	                  <i class="icon service"></i>
	                <span class='text-base'>系统</span>
	              </a>
	              <ul class='nav nav-sub dk'>
	                <li class='nav-sub-header'>
	                  <a><span class='text-base'>系统</span></a>
	                </li>
	                <li >
	                  <a href="#pages/systems/user/list"><span>用户管理</span></a>
	                </li>
	                <li>
	                  <a href="#pages/services/service_definitions/index"><span>角色管理</span></a>
	                </li>
	              </ul>
	            </li>
	          </ul>
	        </div>
	      </div>
	    </div>
	  </div> 
	  <!-- / aside -->
	  <!-- content -->
	  <div class='app-content' id='mainContent' >
	    <div class='app-content-body page-content-area' data-ajax-content="true">
	    </div>
	  </div>
	  <!-- / content -->
	  <!-- footer -->
	  <div class='app-footer' id='footer' >
	    <div class='wrapper b-t bg-light'>
	      <span class='pull-right'>5.0.0
	          <a class='m-l-sm text-muted' href='#' ><i class='fa fa-long-arrow-up'></i></a>
	      </span>&copy; 版权所有 2016 LPL
	    </div>
	  </div>
	  <!-- / footer -->
	</div>
	




    <script src='${pageContext.request.contextPath}/assets/js/jquery.min.js'></script>

    <script src="${pageContext.request.contextPath}/assets/js/underscore.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/backbone.js"></script>

<%--<script src="${pageContext.request.contextPath}/assets/js/knockout.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/knockout.bootstrap.modal.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/knockout-datatable.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/knockout.validation.js"></script>
 --%>
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
<%--
    <script src="${pageContext.request.contextPath}/assets/js/api.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/api_test.js"></script> --%>

     <script src='${pageContext.request.contextPath}/assets/js/ajax-pushlet-client.js'></script>
    <script src='${pageContext.request.contextPath}/assets/js/common.js'></script>
    <script src='${pageContext.request.contextPath}/assets/lib/moment/moment.js'></script>
    <script src='${pageContext.request.contextPath}/assets/lib/bootstrap-daterangepicker/daterangepicker.js'></script>
    
    <script src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/dataTables.bootstrap.min.js"></script>

<script type="text/javascript">
   
</script>
</body>
</html>