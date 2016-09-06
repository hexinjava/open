<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
											<a class="btn btn-sm btn-info" >创建用户</a>
										</div>
										<div class="col-xs-3">
											<div class="input-group">
												<span class="input-group-addon">用户状态</span> 
												<select class="form-control input-sm" data-bind="value: searchParam().state">
													<option value="">全部</option>
													<option value="0">有效</option>
													<option value="1">无效</option>
												</select>
											</div>
										</div>
										<div class="col-xs-3">
											<div class="input-group">
												<span class="input-group-addon">所属角色</span> 
												<select class="form-control input-sm">
													<option value="">全部</option>
												</select>
											</div>
										</div>
										<div class="col-xs-3">
											<div class="input-group">
												<span class="input-group-addon">用户名称</span> <input
													type="text" class="input-sm form-control"
													placeholder="请输入用户名称"
													>
											</div>
										</div>
										<div class="col-xs-2">
											<button class="btn btn-sm" type="button" onclick="reset()">重置</button>
											<button class="btn btn-sm btn-default" type="button" onclick="search()">搜索</button>
										</div>
									</div>

									<div class="table-responsive" data-bind="with : table">
										<table class="table table-striped">
											<thead>
												<th>用户名称</th>
												<th>所属角色</th>
												<th>用户状态</th>
												<th>操作</th>
											</thead>
											<tbody>
												<tr data-bind="visible: showNoData">
													<td colspan="4" class="aligncenter">暂无数据.</td>
												</tr>
												<!-- ko foreach: {data: pagedRows, as: '$row'} -->
												<tr>
													<td ><a data-bind="text: $row.name,click: $root.showDetail"></a></td>
													<td data-bind="text: $row.productName"></td>
													<td>
														<span class="label label-success" data-bind="visible: $row.stateDesc=='已发布',text: $row.stateDesc"></span>
										                <span class="label label-default" data-bind="visible: $row.stateDesc=='未发布',text: $row.stateDesc"></span>
										                <span class="label label-danger" data-bind="visible: $row.stateDesc=='已下线'||$row.stateDesc=='已销毁'||$row.stateDesc=='无效',text: $row.stateDesc"></span>
													</td>
													<td data-bind="text: $row.dirName"></td>
													<td data-bind="text: $row.createUser"></td>
													<td data-bind="text: $row.effectTime"></td>
													<td data-bind="text: $row.expireTime"></td>
													<td><a data-bind="if: $row.state == 0 || $row.state == 2,click: $root.release">发布&nbsp;&nbsp;</a> 
													 <a data-bind="if: $row.state == 0 || $row.state == 2,click: $root.editService">修改&nbsp;&nbsp;</a>
													 <a data-bind="if: $row.state == 0,click: $root.deleteService">删除 &nbsp;&nbsp;</a>
													 <a data-bind="if: $row.state == 1,click: $root.offline">下线&nbsp;&nbsp;</a>
													 <a data-bind="if: $row.state == 2,click: $root.destory">销毁 </a>													
													</td>													
												</tr>
												<!-- /ko -->
											</tbody>
										</table>
									</div>
									<footer class="panel-footer" data-bind="with: table, visible: table.pages() > 0">
									    <ul class="pagination">
								        <li data-bind="css: leftPagerClass, click: gotoPage(1)">
								          <a href="#">首页</a>
								        </li>
								        <li data-bind="css: leftPagerClass, click: prevPage">
								          <a href="#">&laquo;</a>
								        </li>
								        <li >
								          <a href="#"  data-bind="text: currentPage"></a>
								        </li>
								        <li data-bind="css: rightPagerClass, click: nextPage">
								          <a href="#">&raquo;</a>
								        </li>
								        <li data-bind="css: rightPagerClass, click: gotoPage(pages())">
								          <a href="#">末页</a>
								        </li>
								        <li class="page-control-li">
								          <div class="input-group form-group-sm">
								           <input type="text" class="form-control" data-bind="value: targetPage"  />
								           <div class="input-group-btn">
								             <a href="#" class="btn btn-sm btn-default" data-bind="click: gotoTargetPage()">跳转</a>
								           </div>
								          </div>
								          <div class="page-info-div" data-bind="text: recordsText"></div>
								       </li>
								     </ul>
								   </footer>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</div>


</body>
</html>