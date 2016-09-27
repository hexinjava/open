package com.lpl.kled.dto;

/**
 * 提交结果对象
 * 
 * @author hx
 * @param <T>
 */
public class SubmitResult {

    private String message;//存放返回页面错误提示信息

    private int    state;//存放返回页面错误编码

    public SubmitResult(){}
    
    public SubmitResult(String message){
    	this.message=message;
    }
    
    public SubmitResult(String message,int state){
    	this.message=message;
    	this.state=state;
    }
    public SubmitResult(int state){
    	this.state=state;
    }
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
