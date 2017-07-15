package com.appdirect.integration.subscription.dao.order;

import java.util.List;

import com.appdirect.integration.configuration.AppDAO;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Order;


public interface  OrderDAO extends AppDAO {
	

	/**
	 * @param object
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Order add(Order object, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param object
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Order update(Order object, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param Id
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public boolean remove(Integer Id, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param Id
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Order getOrderByOrderId(Integer orderId, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param accountId
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Order> getOrdersByAccountId(Object accountId, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param condition
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Order> getObjectByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param condition
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getObjectIdByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception;
	
	
}
