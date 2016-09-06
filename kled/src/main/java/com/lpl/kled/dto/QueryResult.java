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

    private List<T> results;

    private Long    total;

    /**
     * 当查询记录数为0时,返回一个空的ArrayList实例而不是NULL，
     * 
     * @return
     */
    public List<T> getResult() {
        if (results != null) {
            return results;
        }
        else {
            return new ArrayList<T>();
        }
    }

    public void setResult(List<T> results) {
        this.results = results;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
