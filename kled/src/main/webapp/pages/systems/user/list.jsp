<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOL</title>
</head>
<body>
<div class="app-content-full">
	<div class="bg-light lter b-b wrapper-sm">
		<ol class="breadcrumb">
			<li>当前位置：</li>
			<li>系统</li>
			<li class="active">用户管理</li>
		</ol>
	</div>
	<!-- hbox layout -->
	<div class="hbox hbox-auto-xs bg-light ">
		<!-- column -->
		<!-- <div class="col w lter b-r">
			<div class="vbox">
				<div class="wrapper b-b">
					<div class="left-title clearfix">
						<span style="font-size: 16px;">服务目录</span> 
						<span data-bind="click: editDir"
							class="glyphicon glyphicon-pencil pull-right service-edit-btn"
							style="color: #9c9c9c; top: 3px;"></span> 
						<span data-bind="click: delDir"
							class="glyphicon glyphicon-minus pull-right service-delete-btn"
							style="color: #9c9c9c; top: 3px;"></span>
						<span data-bind="click: add"
							class="glyphicon glyphicon-plus pull-right service-add-btn"
							style="color: #2aabd2; top: 3px; margin-right: 10px;"></span>
					</div>
				</div>
				<div class="row-row">
					<div class="cell scrollable hover">
						<div class="cell-inner">
							<div id="serverDefinitionsTree" class="ztree"
								style="margin-left: 8px;overflow-x: scroll;height: 655px"></div>
						</div>
					</div>
				</div>
			</div>
		</div> -->
		<!-- /column -->
		<!-- column -->
		<div class="col">
			<div class="vbox">
				<div class="row-row">
					<div class="cell">
						<div class="cell-inner">
							<div class="wrapper-md">
								<div class="panel panel-default b-blue">
									<div class="row wrapper">
										<div class="col-xs-1">
											<a class="btn btn-sm btn-info" onclick="showModal('pages/systems/user/add.jsp')">创建用户</a>
										</div>
										<div class="col-xs-3">
											<div class="input-group">
												<span class="input-group-addon">用户状态</span> 
												<select class="form-control input-sm" id="userState">
													<option value="">全部</option>
													<option value="0">有效</option>
													<option value="1">无效</option>
												</select>
											</div>
										</div>
										<div class="col-xs-3">
											<div class="input-group">
												<span class="input-group-addon">所属角色</span> 
												<select class="form-control input-sm" id="userRole">
													<option value="">全部</option>
												</select>
											</div>
										</div>
										<div class="col-xs-3">
											<div class="input-group">
												<span class="input-group-addon">用户名称</span> <input
													type="text" class="input-sm form-control" id="userName"
													placeholder="请输入用户名称"
													>
											</div>
							
										</div>
										<div class="col-xs-2">
											<button class="btn btn-sm" type="button" onclick="reset()">重置</button>
											<button class="btn btn-sm btn-default" type="button" onclick="search()">搜索</button>
										</div>
									</div>

									<div class="table-responsive" >
										<table class="table table-striped" id="userTable">
										    <thead>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</div>

<script type="text/javascript">
//显示列表
var userTable
var columns=[{
			    "className":"center",
			    "defaultContent":"",
			    "title":"用户名",
			    "data":"name"
			},{
				"className":"center",
			     "defaultContent":"",
			     "title":"登录账号",
			     "data":"account"  
			},{
				"className":"center",
			     "defaultContent":"",
			     "title":"操作",
			     "data":null,
			     "render":function ( data, type, full, meta ) {
			         return '<a href="#" onclick="editUser('+data.id+')">编辑</a> | <a href="#" onclick="removeUser('+data.id+')">删除</a>';
			     }
			}];
var url="pages/systems/user/list/data";			
$(document).ready(function() {
    function retrieveData( sSource, aoData, fnCallback ) {
    	var searchParams={}
	    searchParams.userName  = $("#userName").val();
		searchParams.userState = $("#userState").val();
		//var searchParams="&userName="+$("#userName").val()+"&userState="+$("#userState").val();
        $.ajax( {     
            type: "POST",
            url: sSource,   
            dataType:"json",  
            data: "jsonParam="+JSON.stringify(aoData)+"&searchParams="+JSON.stringify(searchParams),  
            success: function(result) {
               result=$.parseJSON(result);
               var returnData = {};
			       returnData.recordsTotal = result.total;//返回数据全部记录
			       returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
			       returnData.data = result.data;//返回的数据列表
                   fnCallback(returnData); //服务器端返回的对象的returnObject部分是要求的格式     
            }     
        });
    }    
    userTable = $('#userTable').dataTable(kled.table.table_config(url,retrieveData,columns));
} );
function search(){
	userTable.fnClearTable();
}

function reset(){
	$("#userState").val("");
	$("#userRole").val("");
	$("#userName").val("");
}

function createUser(){
	showModal("pages/systems/user/add.jsp");
}
function editUser(userId){
	showModal("pages/systems/user/add.jsp?userId="+userId);
}
function removeUser(userId){
	kled.ajax.del('pages/systems/user/del?userId='+userId,function(result){
		result=$.parseJSON(result);
		if(result.state==0){
			alert("删除成功");
			search();
		}if(result.state==1){
			alert("删除失败");
		}
	});
}
</script>
</body>
</html>