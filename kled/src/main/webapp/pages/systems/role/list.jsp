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
			<li class="active">角色管理</li>
		</ol>
	</div>
	<!-- hbox layout -->
	<div class="hbox hbox-auto-xs bg-light ">
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
											<a class="btn btn-sm btn-info" onclick="showModal('pages/systems/role/add.jsp')">创建角色</a>
										</div>
										<div class="col-xs-3">
											<div class="input-group">
												<span class="input-group-addon">角色名称</span> <input
													type="text" class="input-sm form-control" id="roleName"
													placeholder="请输入角色名称"
													>
											</div>
							
										</div>
										<div class="col-xs-2">
											<button class="btn btn-sm" type="button" onclick="reset()">重置</button>
											<button class="btn btn-sm btn-default" type="button" onclick="search()">搜索</button>
										</div>
									</div>

									<div class="table-responsive" >
										<table class="table table-striped" id="roleTable">
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
var roleTable
var columns=[{
			    "className":"center",
			    "defaultContent":"",
			    "title":"ID",
			    "data":"id"
			},{
			    "className":"center",
			    "defaultContent":"",
			    "title":"角色名称",
			    "data":"name"
			},{
				"className":"center",
			     "defaultContent":"",
			     "title":"备注",
			     "data":"remark"  
			},{
				"className":"center",
			     "defaultContent":"",
			     "title":"创建时间",
			     "data":"ccreateTime"  
			},{
				"className":"center",
			     "defaultContent":"",
			     "title":"操作",
			     "data":null,
			     "render":function ( data, type, full, meta ) {
			         return '<a href="#" onclick="editRole('+data.id+')">编辑</a> | <a href="#" onclick="removeRole('+data.id+')">删除</a> | <a href="#" onclick="configPower('+data.id+')">配置权限</a>';
			     }
			}];
var url="pages/systems/role/list/data";			
$(document).ready(function() {
    function retrieveData( sSource, aoData, fnCallback ) {
    	var searchParams={}
	    searchParams.name  = $("#roleName").val();
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
    roleTable = $('#roleTable').dataTable(kled.table.table_config(url,retrieveData,columns));
} );
function search(){
	roleTable.fnClearTable();
}

function reset(){
	$("#roleName").val("");
}

function createRole(){
	showModal("pages/systems/role/add.jsp");
}
function editRole(id){
	showModal("pages/systems/role/add.jsp?id="+id);
}

function configPower(id){
	showModal("pages/systems/role/config.jsp?id="+id);
}

function removeRole(id){
	kled.ajax.del('pages/systems/role/del?id='+id,function(result){
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