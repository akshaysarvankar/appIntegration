package com.appdirect.integration.subscription.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.appdirect.integration.configuration.AppDAOImpl;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Order;
import com.appdirect.integration.entities.Order.OrderType;

@Repository("OrderDAO")
public class OrderDAOImpl extends AppDAOImpl implements OrderDAO {

	private Map<Integer, String> oType = new HashMap<Integer, String>();
	OrderDAOImpl(){
		oType.put(OrderType.SUBSCRIPTION_ORDER.getId(),OrderType.SUBSCRIPTION_ORDER.getCode());
		oType.put(OrderType.SUBSCRIPTION_CHANGE.getId(),OrderType.SUBSCRIPTION_CHANGE.getCode());
		oType.put(OrderType.SUBSCRIPTION_CANCEL.getId(),OrderType.SUBSCRIPTION_CANCEL.getCode());
		oType.put(OrderType.SUBSCRIPTION_NOTICE.getId(),OrderType.SUBSCRIPTION_NOTICE.getCode());
		oType.put(OrderType.USER_ASSIGNMENT.getId(),OrderType.USER_ASSIGNMENT.getCode());
		oType.put(OrderType.USER_UNASSIGNMENT.getId(),OrderType.USER_UNASSIGNMENT.getCode());
		oType.put(OrderType.USER_UPDATED.getId(),OrderType.USER_UPDATED.getCode());
	}
	
	@Override
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception {
		return new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					Order order = (Order) entity;
					int idx=1;
					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO [Order] ([orderTypeId],[completionDate],[creatorId],"
							+ "[marketPlaceId],[accountId],[edition],[pricingDuration])     VALUES" + 
							" (? ,? ,? ,? , ?, ? , ?)", new String[] {"[OrderId]"});
					
					if(order.getOrderTypeId()!= null) ps.setInt(idx++,order.getOrderTypeId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					ps.setDate(idx++, new java.sql.Date(new java.util.Date().getTime()));
					
					if(order.getCreatorId()!= null) ps.setInt(idx++,order.getCreatorId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(order.getMarketPlaceId()!= null) ps.setInt(idx++,order.getMarketPlaceId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(order.getAccountId()!= null) ps.setInt(idx++,order.getAccountId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(order.getEditionCode()!= null) ps.setString(idx++,order.getEditionCode());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(order.getPricingDuration()!= null) ps.setString(idx++,order.getPricingDuration());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					return ps;
				}catch(Exception e) {
					throw new SQLException(e.getMessage());
				}
			}
		};
	}

	@Override
	public PreparedStatementCreator createUpdateStatement(Object entity) throws Exception {
		return new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					Order order = (Order) entity;
					int idx=1;
					PreparedStatement ps = conn.prepareStatement(
							"");
					return ps;
				}catch(Exception e) {
					throw new SQLException(e.getMessage());
				}
			}
		};
	}

	@Override
	public PreparedStatementCreator createDeleteStatement(Object entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order add(Order object, AppJdbcTemplate jTemplate) throws Exception {
		try{
		assertNull("Order cannot be null", object);
		assertNull("Account id on Order cannot be null", object.getAccountId());
		assertNull("Marketplace id on Order cannot be null", object.getMarketPlaceId());
		assertNull("Creator id on order cannot be null", object.getCreatorId());
		KeyHolder key = insertForPrimaryKey(this, object, jTemplate);
		return getOrderByOrderId(Integer.valueOf(key.getKey().intValue()), jTemplate);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Order update(Order object, AppJdbcTemplate jTemplate) throws Exception {
		try{
		assertNull("Order cannot be null", object);
		assertNull("Order identifier cannot be null", object.getOrderId());
		assertNull("Account identifier on order cannot be null", object.getAccountId());
		update(this, object, jTemplate);
		return getOrderByOrderId(object.getOrderId(), jTemplate);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean remove(Integer Id, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order getOrderByOrderId(Integer orderId, AppJdbcTemplate jTemplate) throws Exception {
		try {
			assertNull("OrderId cannot be null", orderId);
			String query = "SELECT [orderId],[orderTypeId],[startDate],[completionDate],[creatorId],[marketPlaceId],[accountId],[edition],[pricingDuration]  FROM [Order] where [orderId]=?";
			return jTemplate.queryForObject(query, new Object[] {orderId}, getRowMapperForOrder());
			
			}catch(IllegalArgumentException e) {
				throw new Exception("Invalid arguments in Order get by id"+ orderId);
			}catch(EmptyResultDataAccessException e) {
				return null;
			}catch(Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	
	private RowMapper<Order> getRowMapperForOrder() {
		return new RowMapper<Order>() {

			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order result = new Order();
				result.setOrderId(rs.getInt("OrderId"));
				if(rs.getObject("orderTypeId")!= null) {
					result.setOrderTypeId(rs.getInt("orderTypeId"));
				}
				result.setStartDate(rs.getDate("startDate"));
				
				if(rs.getObject("completionDate")!= null) {
					result.setCompetionDate(rs.getDate("completionDate"));
				}
				result.setCreatorId(rs.getInt("creatorId"));
				result.setAccountId(rs.getInt("accountId"));
				result.setMarketPlaceId(rs.getInt("marketPlaceId"));
				if(rs.getObject("edition")!= null) {
					result.setEditionCode(rs.getString("edition"));
				}
				if(rs.getObject("pricingDuration")!= null) {
					result.setPricingDuration(rs.getString("pricingDuration"));
				}
						
				return result;
			}
			
		};
	}

	@Override
	public List<Order> getObjectByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getObjectIdByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("hiding")
	private <Integer, String> Integer getKeyByValue(Map<Integer, String> map, String value) {
	    for (Entry<Integer, String> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}

	@Override
	public List<Order> getOrdersByAccountId(Object accountId, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	


}
