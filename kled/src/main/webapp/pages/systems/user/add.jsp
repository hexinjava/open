<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LPL</title>
</head>
<body>
<form id="userForm" class="form-horizontal" action="" method="post" >
    <div class='modal-dialog modal-md' role='document'>
        <div class='modal-content'>
            <div class='modal-header'>
                <button type="button" class="close" data-bind="click:$root.editPasswordCancel"><span aria-hidden="true">&times;</span></button><h3 class='modal-title'>个人信息</h3>
            </div>
            <div class='modal-body' data-bind="with:editUser">
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">账号：</label>
                    <div class="col-xs-8">
                        <input type="text" class="form-control" placeholder="" name="account">
                    </div>
                    <div class="col-xs-1">
                      <span class="text-danger inline m-l-n m-t-sm">*</span>
                    </div>                    
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">姓名：</label>
                    <div class="col-xs-8">
                        <input type="text" class="form-control" placeholder="姓名最多32个字符" maxlength="32" name="name">
                    </div>
                    <div class="col-xs-1">
                      <span class="text-danger inline m-l-n m-t-sm">*</span>
                    </div>                    
                </div>
                <!--
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">所属角色：</label>
                    <div class="col-xs-8">
						<select class="form-control m-b" disabled data-bind="options: $root.roleVO().roleName,optionsText:'name'">
              			</select>
	                        <input type="text" disabled class="form-control" placeholder="" data-bind="attr:{title:$root.showRoles($root.roleVO().roleName)},value:$root.showRoles($root.roleVO().roleName)">
                    </div>
                    <div class="col-xs-1">
                      <span class="text-danger inline m-l-n m-t-sm">*</span>
                    </div>                    
                </div>
                -->
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">手机：</label>
                    <div class="col-xs-8">
                        <input type="text" class="form-control" placeholder="手机号码" name="phone">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">邮箱：</label>
                    <div class="col-xs-8">
                        <input type="email" maxlength="32" class="form-control" placeholder="例如：example@email.com" name="email">
                    </div>
                </div>
            </div>
            <div class='modal-footer'>
                <input type="button" name="commit" value="确定" class="btn btn-info" onclick="submitUser()" />
                <button name="button" type="button" class="btn btn-default btn btn-default" onclick="hideModal()">取消</button>
            </div>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
    function submitUser(){
    	var data = $("#userForm").serializeArray();
    	kled.ajax.post('pages/systems/user/add',data,function(result){
    		result=$.parseJSON(result);
    		if(result.state==0){
    			alert("操作成功");
    			hideModal();
    		}
    		
    	});
    }
</script>
</html>