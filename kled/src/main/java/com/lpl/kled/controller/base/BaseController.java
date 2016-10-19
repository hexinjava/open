package com.lpl.kled.controller.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lpl.kled.common.utils.JSONUtils;

public class BaseController {
	//private static Logger logger = Logger.getLogger(BaseController.class);
	private static Gson gson = new Gson();
	protected HashMap<String, Object> getQueryParams(HttpServletRequest request) {
		HashMap<String, Object> result=new HashMap<String, Object>();
		JsonParser jsonParser=new JsonParser();
    	List<Map<String,String>> list= gson.fromJson(jsonParser.parse(request.getParameter("jsonParam")).getAsJsonArray(),
    												 new TypeToken<List<Map<String,String>>>() {}.getType());
    	int start=0;
    	int length=10;
    	for (Map<String, String> map : list) {
    		String name=map.get("name");
    		String value=map.get("value");
    		
    		if("iDisplayStart".equals(name)){
    			start=Integer.parseInt(value);
    			
    		}else if("iDisplayLength".equals(name)){
    			length=Integer.parseInt(value);
    			
    		}
		}
    	result.put("startLimit", start);
    	result.put("endLimit", ((start / length)+1)*length);
		String searchParams=request.getParameter("searchParams");
		
		Map<String, Object> searchParamsMap=JSONUtils.getMapFromJson(searchParams);
		result.putAll(searchParamsMap);
		return result;
	}
	
	public <T> T getPostEntity(HttpServletRequest request,Class<T> clazz){
		return JSONUtils.fromJson(request.getParameter("postBody"), clazz, "yyyy-MM-dd hh:mm:ss");
	}
	
	public String toJson(Object obj){
    	return gson.toJson(obj);
	}
}
