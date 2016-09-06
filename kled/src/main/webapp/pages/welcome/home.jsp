<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LPL</title>
</head>
<body>
<div class="bg-light lter b-b wrapper-sm">
  <ol class="breadcrumb">
    <li>当前位置：</li>
    <li class="active">操作向导</li>
  </ol>
</div>
<div class='wrapper-md'>    
  	<h4 class="m-b text-primary"><i class="fa fa-star"></i> 入云向导——<span class="text-sm">对接OpenStack</span></h4>
		<div class="row guide">
	    <div class="col-xs-2 guide-item">
			<a onclick="removeTreeStyle()" href="#pages/resources/partitions/datacenters/index">
		        <div class="panel panel-white">
		            <div class="img">
		                <img src="assets/img/guide-dataCenter.png">
		            </div>
		            <h4 class="m-t">创建数据中心</h4>
		            <p class="text-left text-muted">添加数据中心。我们把一组可以网络互通、有机结合的资源视做一个数据中心。这一组资源可以由多个机房内的资源共同组成，也可以由一个机房内的部分资源组成。</p>
		        </div>
			</a>
	    </div>
	    <div class="guide-arr">
	    	<img src="assets/img/arr1.png">
	    </div>
	    <div class="col-xs-2 guide-item">
			<a onclick="removeTreeStyle()" href="#pages/resources/partitions/index">
		        <div class="panel panel-white">
		            <div class="img">
		                <img src="assets/img/guide-partition.png">
		            </div>
		            <h4 class="m-t">创建可用分区</h4>
		            <p class="text-left text-muted">添加数据中心。我们把一组可以网络互通、有机结合的资源视做一个数据中心。这一组资源可以由多个机房内的资源共同组成，也可以由一个机房内的部分资源组成。</p>
		        </div>
			</a>
	    </div>
	    <div class="guide-arr">
	    	<img src="assets/img/arr1.png">
	    </div>
	    <div class="col-xs-2 guide-item">
			<a onclick="doAddResource('iso')" href="#">
		        <div class="panel panel-white">
		            <div class="img">
		                <img src="assets/img/guide-image.png">
		            </div>
		            <h4 class="m-t">添加镜像</h4>
		            <p class="text-left text-muted">添加数据中心。我们把一组可以网络互通、有机结合的资源视做一个数据中心。</p>
		        </div>
			</a>
	    </div>

	</div>
</div>

</body>
</html>