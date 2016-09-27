package com.lpl.kled.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询结果对象
 * 
 * @author hx
 * @param <T>
 */
public class QueryResult<T> {

    private List<T> data;

    private Long    total;

    public Long getTotal() {
        return total;
    }
    
    public QueryResult(){}
    
    public void setTotal(Long total) {
        this.total = total;
    }
    /**
     * 当查询记录数为0时,返回一个空的ArrayList实例而不是NULL，
     * 
     * @return
     */
	public List<T> getData() {
		if (data != null) {
            return data;
        }
        else {
            return new ArrayList<T>();
        }
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
