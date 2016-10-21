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
                <button type="button" class="close" onclick="hideModal()"><span aria-hidden="true">&times;</span></button><h3 class='modal-title'>用户信息</h3>
            </div>
            <div class='modal-body'>
                <input type="hidden" name="id" id="id"/>
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">账号：</label>
                    <div class="col-xs-8">
                        <input type="text" class="form-control" placeholder="" name="account" id="account">
                    </div>
                    <div class="col-xs-1">
                      <span class="text-danger inline m-l-n m-t-sm">*</span>
                    </div>                    
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">姓名：</label>
                    <div class="col-xs-8">
                        <input type="text" class="form-control" placeholder="姓名最多32个字符" maxlength="32" name="name" id="name">
                    </div>
                    <div class="col-xs-1">
                      <span class="text-danger inline m-l-n m-t-sm">*</span>
                    </div>                    
                </div>
                <div class="form-group" id="passwordDiv" style="display: none;">
                    <label class="col-xs-3 control-label" for="">密码：</label>
                    <div class="col-xs-8">
                        <input type="password" class="form-control" placeholder="密码最少6个字符" minlength="6" name="password" id="password">
                    </div>
                    <div class="col-xs-1">
                      <span class="text-danger inline m-l-n m-t-sm">*</span>
                    </div>                    
                </div>
                <div class="form-group" id="repeatPasswordDiv" style="display: none;">
                    <label class="col-xs-3 control-label" for="">重复密码：</label>
                    <div class="col-xs-8">
                        <input type="password" class="form-control" placeholder="必须与密码一致" minlength="6" name="repeatPassword" id="repeatPassword">
                    </div>
                    <div class="col-xs-1">
                      <span class="text-danger inline m-l-n m-t-sm">*</span>
                    </div>                    
                </div>
                
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">所属角色：</label>
                    <div class="col-xs-6">
	                    <input type="text" disabled class="form-control" style="width: 250px;" placeholder=""  id="selectdRoleNames">
	                    <input type="hidden" name = "roleIds" id="selectdRoleIds">
                    </div>
                    <div class="col-xs-2">
                      <button name="button" type="button" class="btn btn-default btn btn-default" onclick="openSelectRole()">选择</button>
                    </div>
                    <div class="col-xs-1">
                      <span class="text-danger inline m-l-n m-t-sm">*</span>
                    </div> 
                </div>
                
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">手机：</label>
                    <div class="col-xs-8">
                        <input type="text" class="form-control" placeholder="手机号码" name="phone" id="phone">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">邮箱：</label>
                    <div class="col-xs-8">
                        <input type="email" maxlength="32" class="form-control" placeholder="例如：example@email.com" name="email" id="email">
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
var scripts="";
$('.page-content-area').ace_ajax('loadScripts', scripts, function(args) {
	  if(args.userId!=null){
		  kled.ajax.get('pages/systems/user/get?userId='+args.userId,function(result){
			  result=$.parseJSON(result);
			  for(var o in result){
                  $("#"+o).val(result[o]);
                  if("roles"==o && result[o].length>0){
                	  var selectdRoleNames="";
                	  var selectdRoleIds="";
                	  for(var i in result[o]){
                		  selectdRoleNames +=","+result[o][i].name;
                		  selectdRoleIds +=","+result[o][i].id;
                	  }
                	  if(selectdRoleIds!=""){
                		  $("#selectdRoleIds").val(selectdRoleIds.substring(1,selectdRoleIds.length));
                		  $("#selectdRoleNames").val(selectdRoleNames.substring(1,selectdRoleNames.length));
                	  }
                	  
                  }
               }
		  });  
	  }else{
		  $("#passwordDiv").css('display','block');
		  $("#repeatPasswordDiv").css('display','block');
	  }
	  
});
    function submitUser(){
     	kled.ajax.post('pages/systems/user/add',$("#userForm").serializeJson(),function(result){
    		result=$.parseJSON(result);
    		if(result.state==0){
    			alert("保存成功");
    			hideModal();
    			search();
    		}if(result.state==1){
    			alert("保存失败");
    		}
    		
    	}); 
    }
    function openSelectRole(){
    	showModalSec("pages/systems/common/selectRole.jsp?selectdRoleIds="+$("#selectdRoleIds").val());
    }
</script>
</html>