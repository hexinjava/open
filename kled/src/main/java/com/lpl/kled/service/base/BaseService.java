package com.lpl.kled.service.base;


import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lpl.kled.dto.QueryResult;

/**
 * 操作通用接口
 * @ClassName: Dao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author hexin 
 * @date 2016年9月5日 上午11:57:10 
 * 
 * @param <T>
 */
@Repository("BaseService")
public interface BaseService<T> {
	/**
	 * 获取分页查询数据
	 * @Title: getPaginationData 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param  params
	 * @return QueryResult<T>    返回类型 
	 * @throws
	 */
	public QueryResult<T> getPaginationData(Map<String, Object> params);
	/**
	 * 根据id查询T信息
	 * @Title: getById 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param  id
	 * @return T    返回类型 
	 * @throws
	 */
	public T getById(Long id);
	/**
	 * 根据id删除信息
	 * @Title: delById 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param  id
	 * @return boolean    返回类型 
	 * @throws
	 */
	public boolean delete(Long id);
	/**
	 * 创建T信息
	 * @Title: create 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param  entity
	 * @return boolean    返回类型 
	 * @throws
	 */
	public boolean create(T entity);
	/**
	 * 更新T信息
	 * @Title: create 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param  entity
	 * @throws
	 */
	public boolean update(T entity);
	
	public boolean createOrUpdate(T entity);
}
