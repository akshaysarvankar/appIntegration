package com.appdirect.integration.subscription.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.appdirect.integration.configuration.AppDAOImpl;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.User;

@Repository("UserDAO")
public class UserDAOImpl extends AppDAOImpl implements UserDAO {
	
	@Override
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception {
		return new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					User User = (User) entity;
					int idx=1;
					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO [User] ([firstName],[lastName],[fullName],[email],[language],[locale],[openId],[uuid],[accounhtId])     VALUES" + 
							" (? ,? ,? ,? ,? ,? ,? ,? ,?)", new String[] {"[UserId]"});
					if(User.getFirstName()!= null) ps.setString(idx++,User.getFirstName());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(User.getLastName()!= null) ps.setString(idx++,User.getLastName());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(User.getFullName()!= null) ps.setString(idx++,User.getFullName());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(User.getEmail()!= null) ps.setString(idx++,User.getEmail());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(User.getLanguage()!= null) ps.setString(idx++,User.getLanguage());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(User.getLocale()!= null) ps.setString(idx++,User.getLocale());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(User.getOpenId()!= null) ps.setString(idx++,User.getOpenId());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(User.getUuid()!= null) ps.setString(idx++,User.getUuid());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(User.getAccountId()!= null) ps.setInt(idx++,User.getAccountId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					return ps;
				}catch(Exception e) {
					throw new SQLException(e.getMessage());
				}
			}
		};
	}

	@Override
	public PreparedStatementCreator createUpdateStatement(Object entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
		KeyHolder key = insertForPrimaryKey(this, object, jTemplate);
		return getObject(Integer.valueOf(key.getKey().intValue()), jTemplate);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public User update(User object, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Integer Id, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getObject(Object obj, AppJdbcTemplate jTemplate) throws Exception {
		try {
			assertNull("user Id or uuid cannot be null", obj);
			String query = "SELECT [UserId],[firstName],[lastName],[fullName],[email],[language],"
					+ "[locale],[openId],[uuid],[createdDate],[updateDate],[accountId]  FROM [User] where ";
			if(obj instanceof Integer) {
				query +=" [UserId] = ?";
				return jTemplate.queryForObject(query, new Object[] { (Integer)obj}, getRowMapperForUser());
			}else if (obj instanceof String) {
				query +=" [uuid] = ?";
				return jTemplate.queryForObject(query, new Object[] {(String)obj}, getRowMapperForUser());
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
