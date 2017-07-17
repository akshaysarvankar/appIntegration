package com.appdirect.integration.subscription.dao.event;

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
import com.appdirect.integration.entities.Event;
import com.appdirect.integration.entities.Event.EventType;

@Repository("EventDAO")
public class EventDAOImpl extends AppDAOImpl implements EventDAO {

	private Map<Integer, String> eType = new HashMap<Integer, String>();
	EventDAOImpl(){
		eType.put(EventType.USER_ASSIGNMENT.getId(),EventType.USER_ASSIGNMENT.getCode());
		eType.put(EventType.USER_UNASSIGNMENT.getId(),EventType.USER_UNASSIGNMENT.getCode());
		eType.put(EventType.USER_UPDATED.getId(),EventType.USER_UPDATED.getCode());
	}
	
	@Override
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception {
		return new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					Event Event = (Event) entity;
					int idx=1;
					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO [UserEvent] ([eventTypeId],[completionDate],[creatorId],"
							+ "[marketPlaceId],[accountId],[userId])     VALUES" + 
							" (? ,? ,? ,? , ?, ? )", new String[] {"[eventId]"});
					
					if(Event.getEventTypeId()!= null) ps.setInt(idx++,Event.getEventTypeId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					ps.setDate(idx++, new java.sql.Date(new java.util.Date().getTime()));
					
					if(Event.getCreatorId()!= null) ps.setInt(idx++,Event.getCreatorId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					ps.setInt(idx++,Event.getMarketPlaceId());
					
					ps.setInt(idx++,Event.getAccountId());
					
					ps.setInt(idx++,Event.getUserId());
					
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
					Event Event = (Event) entity;
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
	public Event add(Event object, AppJdbcTemplate jTemplate) throws Exception {
		try{
		assertNull("Event cannot be null", object);
		assertNull("Account id on Event cannot be null", object.getAccountId());
		assertNull("Marketplace id on Event cannot be null", object.getMarketPlaceId());
		assertNull("user identifier on Event cannot be null", object.getUserId());
		KeyHolder key = insertForPrimaryKey(this, object, jTemplate);
		return getEventByEventId(Integer.valueOf(key.getKey().intValue()), jTemplate);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Event update(Event object, AppJdbcTemplate jTemplate) throws Exception {
		try{
		assertNull("Event cannot be null", object);
		assertNull("Event identifier cannot be null", object.getEventId());
		assertNull("Account identifier on Event cannot be null", object.getAccountId());
		assertNull("user identifier on Event cannot be null", object.getUserId());
		update(this, object, jTemplate);
		return getEventByEventId(object.getEventId(), jTemplate);
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
	public Event getEventByEventId(Integer EventId, AppJdbcTemplate jTemplate) throws Exception {
		try {
			assertNull("EventId cannot be null", EventId);
			String query = "SELECT [eventId],[eventTypeId],[startDate],[completionDate],[creatorId],"
					+ "[marketPlaceId],[accountId],[userId]  FROM [UserEvent] where [eventId]=?";
			return jTemplate.queryForObject(query, new Object[] {EventId}, getRowMapperForEvent());
			
			}catch(IllegalArgumentException e) {
				throw new Exception("Invalid arguments in Event get by id"+ EventId);
			}catch(EmptyResultDataAccessException e) {
				return null;
			}catch(Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	
	private RowMapper<Event> getRowMapperForEvent() {
		return new RowMapper<Event>() {

			@Override
			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				Event result = new Event();
				result.setEventId(rs.getInt("eventId"));
				if(rs.getObject("eventTypeId")!= null) {
					result.setEventTypeId(rs.getInt("eventTypeId"));
				}
				result.setStartDate(rs.getDate("startDate"));
				
				if(rs.getObject("completionDate")!= null) {
					result.setCompetionDate(rs.getDate("completionDate"));
				}
				if(rs.getObject("creatorId")!= null) {
					result.setCreatorId(rs.getInt("creatorId"));
				}
				result.setAccountId(rs.getInt("accountId"));
				result.setMarketPlaceId(rs.getInt("marketPlaceId"));
				result.setUserId(rs.getInt("userId"));					
				return result;
			}
			
		};
	}

	@Override
	public List<Event> getObjectByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception {
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
	public List<Event> getEventsByUserId(Object userId, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	


}
