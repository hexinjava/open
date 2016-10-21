<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LPL</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/lib/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<style type="text/css">
      .radioBtn {height: 16px;vertical-align: middle;}
      .checkboxBtn {vertical-align: middle;margin-right: 2px;}
      </style>
<body>
<form action="" id="configForm">
    <div class='modal-dialog modal-sm' role='document'>
        <div class='modal-content'>
            <div class='modal-header'>
                <button type="button" class="close" onclick="hideModal()"><span aria-hidden="true">&times;</span></button><h3 class='modal-title'>角色配置权限</h3>
            </div>
            <div class='modal-body'>
                <div id="powerTree" class="ztree"></div>
            </div>
            <div class='modal-footer'>
                <input type="button" name="commit" value="确定" class="btn btn-info" onclick="submitConfig()" />
                <button name="button" type="button" class="btn btn-default btn btn-default" onclick="hideModal()">取消</button>
            </div>
            <input type="hidden" name="ids" id="ids">
            <input type="hidden" name="roleId" id="roleId">
        </div>
    </div>
</form>    
</body>
<script type="text/javascript">
var scripts = [null, '${pageContext.request.contextPath}/assets/lib/zTree/js/jquery.ztree.core.js',
               '${pageContext.request.contextPath}/assets/lib/zTree/js/jquery.ztree.excheck.js',null];



var zTree
$('.page-content-area').ace_ajax('loadScripts', scripts, function(args) {
	var setting = {  
            data:{  
                simpleData:{  
                    enable:true  
                }  
            },  
            check: {    
                enable: true  
            }  

        };  
	
	if(args.id!=null){
		$("#roleId").val(args.id);
		  kled.ajax.get('pages/systems/role/treeData?id='+args.id,function(result){
			  result=$.parseJSON(result);
			  zTree = $.fn.zTree.init($("#powerTree"), setting, result);  
			  zTree.expandAll(true);
		  });  
	}
});
    function submitConfig(){
    	var nodes = new Array();  
        //取得选中的结点  
        nodes = zTree.getCheckedNodes(true);  
        var ids = new Array();  
        for (i = 0; i < nodes.length; i++) {  
        	ids.push(nodes[i].id);
        }  
        $("#ids").val(ids);
        debugger;
     	kled.ajax.post('pages/systems/role/configPower',$("#configForm").serializeJson(),function(result){
    		result=$.parseJSON(result);
    		if(result.state==0){
    			alert("保存成功");
    			hideModal();
    		}if(result.state==1){
    			alert("保存失败");
    		}
    		
    	});
    }
</script>
</html>