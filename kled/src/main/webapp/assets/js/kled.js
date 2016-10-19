/**
 * 
 * 引用 jquery 转换为JSON对象
 * 
 * hexin 2016-10-08
 * 
 */
(function($){  
    $.fn.serializeJson=function(){  
        var serializeObj={};  
        var array=this.serializeArray();  
        var str=this.serialize();  
        $(array).each(function(){  
        	if(this.value.length>0){
        		if(serializeObj[this.name]){  
                    if($.isArray(serializeObj[this.name])){  
                        serializeObj[this.name].push(this.value);  
                    }else{  
                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
                    }  
                }else{  
                    serializeObj[this.name]=this.value;   
                } 
        	}
        });  
        return serializeObj;  
    };  
})(jQuery);