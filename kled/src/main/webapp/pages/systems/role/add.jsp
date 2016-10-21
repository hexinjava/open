<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LPL</title>
</head>
<body>
<form id="roleForm" class="form-horizontal" action="" method="post" >
    <div class='modal-dialog modal-md' role='document'>
        <div class='modal-content'>
            <div class='modal-header'>
                <button type="button" class="close" onclick="hideModal()"><span aria-hidden="true">&times;</span></button><h3 class='modal-title'>角色信息</h3>
            </div>
            <div class='modal-body'>
                <input type="hidden" name="id" id="id"/>
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">角色名称：</label>
                    <div class="col-xs-8">
                        <input type="text" class="form-control" placeholder="角色名称最多32个字符" maxlength="32" name="name" id="name">
                    </div>
                    <div class="col-xs-1">
                      <span class="text-danger inline m-l-n m-t-sm">*</span>
                    </div>                    
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" for="">备注：</label>
                    <div class="col-xs-8">
                        <textarea rows="5" cols="54" name="remark" id="remark"></textarea>
                    </div>
                </div>
            </div>
            <div class='modal-footer'>
                <input type="button" name="commit" value="确定" class="btn btn-info" onclick="submitRole()" />
                <button name="button" type="button" class="btn btn-default btn btn-default" onclick="hideModal()">取消</button>
            </div>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
var scripts="";
$('.page-content-area').ace_ajax('loadScripts', scripts, function(args) {
	  if(args.id!=null){
		  kled.ajax.get('pages/systems/role/get?id='+args.id,function(result){
			  result=$.parseJSON(result);
			  for(var o in result){
                  $("#"+o).val(result[o]);
               }
		  });  
	  }
	  
});
    function submitRole(){
     	kled.ajax.post('pages/systems/role/add',$("#roleForm").serializeJson(),function(result){
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
</script>
</html>