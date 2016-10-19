<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOL</title>
</head>
<body>

 <div class='modal-dialog modal-md' role='document'>
        <div class='modal-content'>
            <div class='modal-header'>
                <button type="button" class="close" ><span aria-hidden="true">&times;</span></button><h3 class='modal-title'>角色信息</h3>
            </div>
            <div class='modal-body' >
                <table class="table table-striped" id="selectRoleTable" style="width: 100%">
				    <thead>
				        <tr>
                        <th>
                            <input type="checkbox" class="checkall" />
                        </th>
                        <th >
                                                                             角色名称
                        </th>
                    </tr>
					</thead>
					<tbody>
					</tbody>
				</table>
            </div>
            <div class='modal-footer'>
                <input type="button" name="commit" value="确定" class="btn btn-info" onclick="selectRoleSubmit()" />
                <button name="button" type="button" class="btn btn-default btn btn-default" onclick="hideModalSec()">取消</button>
            </div>
        </div>
    </div>

<script type="text/javascript">
//显示列表
var selectRoleTable
$('.page-content-area').ace_ajax('loadScripts', scripts, function(args) {
	var columns=[{
				    "className": "dt-body-center",
				    "data": "id",
				    "type": "checkbox",
				    "render": function ( data, type, row ) {
	                              return '<input type="checkbox" class="checkchild" value="'+data+'">';
	                          }
				},{
				    "className":"center",
				    "defaultContent":"",
				    "title":"角色名称",
				    "data":"name"
				}];
	var url="pages/systems/role/list/data";			
	$(document).ready(function() {
	    function retrieveData( sSource, aoData, fnCallback ) {
	    	var searchParams={}
		    /* searchParams.userName  = $("#userName").val();
			searchParams.userState = $("#userState").val(); */
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
	    selectRoleTable = $('#selectRoleTable').dataTable(kled.table.table_config(url,retrieveData,columns));
	    
	    /* 给选中的行增加 selected 样式 */
	    $('#selectRoleTable tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	        if($(this).hasClass('selected')){
	        	$(this).find('td:first input')[0].checked=true;
	        }else{
	        	$(this).find('td:first input')[0].checked=false;
	        }
	    } ); 
	 
	    /*全选*/
	    $(".checkall").click(function () {
	        var check = $(this).prop("checked");
	        $(".checkchild").prop("checked", check);
	        
	        $("#selectRoleTable tbody tr").toggleClass('selected');
	    });
	} );
});
/*确认选择*/
function selectRoleSubmit(){
    var anSelected = kled.table.fnGetSelected(selectRoleTable);
    var resultName="";
    var resultId="";
    if(anSelected.length>0){
    	for(var i=0;i<anSelected.length;i++){
    		var sdata=selectRoleTable.fnGetData(anSelected[i]);
    		resultName+=","+sdata.name;
    		resultId+=","+sdata.id;
    	}
    }else{
    	alert("最少选择一个角色");
    	return;
    }
    $("#selectdRoleNames").val(resultName.substring(1,resultName.length));
    $("#selectdRoleIds").val(resultId.substring(1,resultId.length));
    hideModalSec();
}


</script>
</body>
</html>