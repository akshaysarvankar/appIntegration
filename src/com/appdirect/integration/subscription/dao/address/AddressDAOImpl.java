package com.appdirect.integration.subscription.dao.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.appdirect.integration.configuration.AppDAOImpl;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Address;
import com.appdirect.integration.entities.Creator;
import com.appdirect.integration.entities.User;

@Repository("AddressDAO")
public class AddressDAOImpl extends AppDAOImpl implements AddressDAO {
	
	public static final String ADDRESS = "address";
	public static final String PARENTCLASS = "parentClass";
	
	@Override
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception {
		return new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					Map<String, Object> complexObj = (Map<String, Object>) entity;
					Address address = (Address) complexObj.get(ADDRESS);
					Class<?> parentClass = (Class<?>) complexObj.get(PARENTCLASS);
					int idx=1;
					PreparedStatement ps= null;
					if(parentClass.equals(Creator.class)) {
					ps = conn.prepareStatement("INSERT INTO [CreatorAddress]([creatorId],[street1],[street2],[state],[city],[country],[zip]) " + 
							" VALUES(?,?,?,?,?,?,?)");
					}else if(parentClass.equals(User.class)) {
						ps = conn.prepareStatement("INSERT INTO [UserAddress]([userId],[street1],[street2],[state],[city],[country],[zip]) " + 
								" VALUES(?,?,?,?,?,?,?)");
					}else{
					throw new Exception("Unknow parent class while adding address");
					}
					
					if(address.getId()!= null) ps.setInt(idx++,address.getId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(address.getStreet1()!= null) ps.setString(idx++,address.getStreet1());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(address.getStreet2()!= null) ps.setString(idx++,address.getStreet2());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(address.getState()!= null) ps.setString(idx++,address.getState());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(address.getCity()!= null) ps.setString(idx++,address.getCity());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(address.getCountry()!= null) ps.setString(idx++,address.getCountry());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(address.getZip()!= null) ps.setString(idx++,address.getZip());
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
					Map<String, Object> complexObj = (Map<String, Object>) entity;
					Address address = (Address) complexObj.get(ADDRESS);
					Class<?> parentClass = (Class<?>) complexObj.get(PARENTCLASS);
					int idx=1;
					PreparedStatement ps= null;
					if(parentClass.equals(Creator.class)) {
						String query = "UPDATE [CreatorAddress] set [updatedDate]= ?" +
								(address.getStreet1()!= null? ",[street1] = ?": "")+
								(address.getStreet2()!= null? ",[street2] = ?": "")+
								(address.getState()!= null? ",[state] = ?": "")+
								(address.getCity()!= null? ",[city] = ?": "")+
								(address.getCountry()!= null? ",[country] = ?": "")+
								(address.getZip()!= null? ",[zip] = ?": "")+
								" where [creatorId] = ? ";					
					ps = conn.prepareStatement(query);
					}else if(parentClass.equals(User.class)) {

						String query = "UPDATE [UserAddress] set [updatedDate]= ?" +
								(address.getStreet1()!= null? ",[street1] = ?": "")+
								(address.getStreet2()!= null? ",[street2] = ?": "")+
								(address.getState()!= null? ",[state] = ?": "")+
								(address.getCity()!= null? ",[city] = ?": "")+
								(address.getCountry()!= null? ",[country] = ?": "")+
								(address.getZip()!= null? ",[zip] = ?": "")+
								" where [userId] = ? ";					
					ps = conn.prepareStatement(query);
					} else{
					throw new Exception("Unknow parent class while updating address");
					}
					
					ps.setDate(idx++, new java.sql.Date(new Date().getTime()));
					
					if(address.getStreet1()!= null) ps.setString(idx++,address.getStreet1());
					
					if(address.getStreet2()!= null) ps.setString(idx++,address.getStreet2());
					
					if(address.getState()!= null) ps.setString(idx++,address.getState());
					
					if(address.getCity()!= null) ps.setString(idx++,address.getCity());
					
					if(address.getCountry()!= null) ps.setString(idx++,address.getCountry());
					
					if(address.getZip()!= null) ps.setString(idx++,address.getZip());
					
					ps.setInt(idx++,address.getId());
					
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
	public Address add(Address object,Class<?> parentClass, AppJdbcTemplate jTemplate) throws Exception {
		try{
		assertNull("Address cannot be null", object);
		assertNull("Parent class cannot be null", parentClass);
		Map<String, Object> complexObj = new HashMap<String, Object>();
		complexObj.put(ADDRESS, object);
		complexObj.put(PARENTCLASS, parentClass);
		insert(this, complexObj, jTemplate);
		return getObject(object.getId(), parentClass, jTemplate);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Address update(Address object,Class<?> parentClass, AppJdbcTemplate jTemplate) throws Exception {
		try{
		assertNull("Address cannot be null", object);
		assertNull("Parent class cannot be null", parentClass);
		Map<String, Object> complexObj = new HashMap<String, Object>();
		complexObj.put(ADDRESS, object);
		complexObj.put(PARENTCLASS, parentClass);
		update(this, complexObj, jTemplate);
		return getObject(object.getId(), parentClass, jTemplate);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean remove(Integer Id,Class<?> parentClass, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Address getObject(Object obj, Class<?> parentClass, AppJdbcTemplate jTemplate) throws Exception {
		try {
			assertNull("Id cannot be null", obj);
			assertTrue("Id is not integer", !(obj instanceof Integer));
			if(parentClass.equals(Creator.class)) {
				String query = "SELECT [creatorId] as id,[street1],[street2],[state],[country],[zip],[city],[createdDate],[updatedDate] ";
				query += "From [CreatorAddress] where [creatorId] = ?";
				return jTemplate.queryForObject(query, new Object[] { (Integer)obj}, getRowMapperForAddress());	
			}else if (parentClass.equals(User.class)) {
				String query = "SELECT [UserId] as id,[street1],[street2],[state],[country],[zip],[city],[createdDate],[updatedDate] ";
				query += "From [UserAddress] where [userId] = ?";
				return jTemplate.queryForObject(query, new Object[] { (Integer)obj}, getRowMapperForAddress());	
			}
			return null;
			}catch(IllegalArgumentException e) {
				throw new Exception("Invalid arguments in creator get by id"+ obj.toString());
			}catch(EmptyResultDataAccessException e) {
				return null;
			}catch(Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	
	private RowMapper<Address> getRowMapperForAddress() {
		return new RowMapper<Address>() {

			@Override
			public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
				Address result = new Address();
				result.setId(rs.getInt("id"));
				if(rs.getObject("state")!= null) {
				result.setState(rs.getString("state")); 
				}
				if(rs.getObject("street1")!= null) {
					result.setStreet1(rs.getString("street1"));	
				}
				if(rs.getObject("street2")!= null) {
				result.setStreet2(rs.getString("street2"));
				}
				if(rs.getObject("city")!= null) {
					result.setCity(rs.getString("city"));
				}
				if(rs.getObject("country")!= null) {
					result.setCountry(rs.getString("country"));
				}
				if(rs.getObject("zip")!= null) {
					result.setZip(rs.getString("zip"));
				}
				
				return result;
			}
			
		};
	}

	@Override
	public List<Address> getObjectByCondition(String condition,Class<?> parentClass, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getObjectIdByCondition(String condition,Class<?> parentClass, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	


}
