package com.appdirect.integration.subscription.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.appdirect.integration.configuration.AppDAOImpl;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Account;
import com.appdirect.integration.entities.Status.statusCode;
import com.appdirect.integration.entities.User;
import com.appdirect.integration.subscription.dao.account.AccountDAO;

@Repository("UserDAO")
public class UserDAOImpl extends AppDAOImpl implements UserDAO {
	
	@Autowired
	private AccountDAO accountDAO = null;
	
	
	@Override
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception {
		return new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					User user = (User) entity;
					int idx=1;
					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO [User] ([firstName],[lastName],[fullName],[email],[language],[locale],[openId],[uuid],[accountId], [statusId])     VALUES" + 
							" (? ,? ,? ,? ,? ,? ,? ,? ,? ,?)", new String[] {"[UserId]"});
					if(user.getFirstName()!= null) ps.setString(idx++,user.getFirstName());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(user.getLastName()!= null) ps.setString(idx++,user.getLastName());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(user.getFullName()!= null) ps.setString(idx++,user.getFullName());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(user.getEmail()!= null) ps.setString(idx++,user.getEmail());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(user.getLanguage()!= null) ps.setString(idx++,user.getLanguage());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(user.getLocale()!= null) ps.setString(idx++,user.getLocale());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(user.getOpenId()!= null) ps.setString(idx++,user.getOpenId());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(user.getUuid()!= null) ps.setString(idx++,user.getUuid());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					ps.setInt(idx++,user.getAccountId());
					ps.setInt(idx++,user.getStatusId());
					
					
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
					User user = (User) entity;
					int idx=1;
					String query = "UPDATE [User] set [updateDate]= ?" +
							(user.getFirstName()!= null? ",[firstName] = ?": "")+
							(user.getLastName()!= null? ",[lastName] = ?": "")+
							(user.getFullName()!= null? ",[fullName] = ?": "")+
							(user.getEmail()!= null? ",[email] = ?": "")+
							(user.getLanguage()!= null? ",[language] = ?": "")+
							(user.getLocale()!= null? ",[locale] = ?": "")+
							(user.getOpenId()!= null? ",[openId] = ?": "")+
							(user.getStatusId()!= null? ",[statusId] = ?": "") +
							(user.getAccountId()!= null? ",[accountId] = ?": "" )+
							" where [uuid] = ? and [userId]= ?";

					PreparedStatement ps = conn.prepareStatement(query);
					
					ps.setDate(idx++, new java.sql.Date(new Date().getTime()));
					
					if(user.getFirstName()!= null) ps.setString(idx++,user.getFirstName());
					
					if(user.getLastName()!= null) ps.setString(idx++,user.getLastName());
					
					if(user.getFullName()!= null) ps.setString(idx++,user.getFullName());
					
					if(user.getEmail()!= null) ps.setString(idx++,user.getEmail());
					
					if(user.getLanguage()!= null) ps.setString(idx++,user.getLanguage());
					
					if(user.getLocale()!= null) ps.setString(idx++,user.getLocale());
					
					if(user.getOpenId()!= null) ps.setString(idx++,user.getOpenId());
										
					if(user.getStatusId()!= null) ps.setInt(idx++,user.getStatusId());

					if(user.getAccountId()!= null) ps.setInt(idx++,user.getAccountId());
					
					ps.setString(idx++,user.getUuid());
					ps.setInt(idx++,user.getUserId());
					
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
	public User add(User object, AppJdbcTemplate jTemplate) throws Exception {
		try{
		assertNull("User cannot be null", object);
		assertNull("User uuid cannot be null", object.getUuid());
		assertNull("User account identifier cannot be null", object.getAccountId());
		assertNull("User status Id cannot be null", object.getStatusId());
		Account account = accountDAO.getObject(object.getAccountId(), jTemplate);
		if(account!= null) {
			KeyHolder key = insertForPrimaryKey(this, object, jTemplate);
			return getObject(Integer.valueOf(key.getKey().intValue()),object.getAccountId(), jTemplate);
		}else{
			throw new Exception("Account in which user to be added is not found");			
		}
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public User update(User object, AppJdbcTemplate jTemplate) throws Exception {
		try{
			assertNull("User cannot be null", object);
			assertNull("User uuid cannot be null", object.getUuid());
			assertNull("User user identifier cannot be null", object.getUserId());
			update(this, object, jTemplate);
			return getObject(object.getUserId(),object.getAccountId(), jTemplate);
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
	public User getObject(Object obj, Integer accountId, AppJdbcTemplate jTemplate) throws Exception {
		try {
			assertNull("user Id or uuid cannot be null", obj);
			String query = "SELECT [UserId],[firstName],[lastName],[fullName],[email],[language],"
					+ "[locale],[openId],[uuid],[createdDate],[updateDate],[accountId],[statusId]  FROM [User] where ";
			if(obj instanceof Integer) {
				query +=" [UserId] = ? and [accountId]=?";
				return jTemplate.queryForObject(query, new Object[] { (Integer)obj, accountId}, getRowMapperForUser());
			}else if (obj instanceof String) {
				query +=" [uuid] = ? and [accountId]=?";
				return jTemplate.queryForObject(query, new Object[] {(String)obj, accountId}, getRowMapperForUser());
			}
			return null;
			}catch(IllegalArgumentException e) {
				throw new Exception("Invalid arguments in User get by id"+ obj.toString());
			}catch(EmptyResultDataAccessException e) {
				return null;
			}catch(Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	
	@Override
	public List<User> getUsersByAccount(Integer accountId, AppJdbcTemplate jTemplate) throws Exception {
		try {
			assertNull("accountId cannot be null", accountId);
			String query = "SELECT [UserId],[firstName],[lastName],[fullName],[email],[language],"
					+ "[locale],[openId],[uuid],[createdDate],[updateDate],[accountId]  FROM [User] where [accountId]=?";
				return jTemplate.query(query, new Object[] { accountId}, getRowMapperForUser());
			}catch(IllegalArgumentException e) {
				throw new Exception("Invalid arguments in User get by account id"+ accountId);
			}catch(EmptyResultDataAccessException e) {
				return null;
			}catch(Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	
	private RowMapper<User> getRowMapperForUser() {
		return new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User result = new User();
				result.setUserId(rs.getInt("UserId"));
				if(rs.getObject("firstName")!= null) {
					result.setFirstName(rs.getString("firstName"));
				}
				if(rs.getObject("lastName")!= null) {
					result.setLastName(rs.getString("lastName"));
				}
				if(rs.getObject("fullName")!= null) {
					result.setFullName(rs.getString("fullName"));
				}
				if(rs.getObject("email")!= null) {
					result.setEmail(rs.getString("email"));
				}
				if(rs.getObject("language")!= null) {
					result.setLanguage(rs.getString("language"));
				}
				if(rs.getObject("locale")!= null) {
					result.setLocale(rs.getString("locale"));
				}
				if(rs.getObject("openId")!= null) {
					result.setOpenId(rs.getString("openId"));
				}
				if(rs.getObject("uuid")!= null) {
					result.setUuid(rs.getString("uuid"));
				}
				result.setAccountId(rs.getInt("accountId"));
				result.setStatusId(rs.getInt("statusId"));
				return result;
			}
			
		};
	}

	@Override
	public List<User> getObjectByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getObjectIdByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	


}
