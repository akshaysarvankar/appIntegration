package com.appdirect.integration.subscription.dao.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.appdirect.integration.configuration.AppDAOImpl;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Account;
import com.appdirect.integration.entities.Account.statusCode;

@Repository("AccountDAO")
public class AccountDAOImpl extends AppDAOImpl implements AccountDAO {
	
	private Map<Integer, String> status = new HashMap<Integer, String>();
	AccountDAOImpl(){
		status.put(statusCode.ACTIVE.getId(),statusCode.ACTIVE.getCode());
		status.put(statusCode.INITIALIZED.getId(),statusCode.INITIALIZED.getCode());
		status.put(statusCode.FAILED.getId(),statusCode.FAILED.getCode());
		status.put(statusCode.FREE_TRIAL.getId(),statusCode.FREE_TRIAL.getCode());
		status.put(statusCode.FREE_TRIAL_EXPIRED.getId(),statusCode.FREE_TRIAL_EXPIRED.getCode());
		status.put(statusCode.SUSPENDED.getId(),statusCode.SUSPENDED.getCode());
		status.put(statusCode.CANCELLED.getId(),statusCode.CANCELLED.getCode());
	}
	
	@Override
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception {
		return new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					Account account = (Account) entity;
					int idx=1;
					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO [Account] ([companyId],[userId],[parentAccountId],[statusId] )     VALUES" + 
							" (? ,? ,? ,?)", new String[] {"[AccountId]"});
					
					if(account.getCompanyId()!= null) ps.setInt(idx++,account.getCompanyId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(account.getUserId()!= null) ps.setInt(idx++,account.getUserId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(account.getParentAccountId()!= null) ps.setInt(idx++,Integer.valueOf(account.getParentAccountId()));
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(account.getStatus()!= null) ps.setInt(idx++,getKeyByValue(status, account.getStatus()));
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
		return new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					Account account = (Account) entity;
					int idx=1;
					PreparedStatement ps = conn.prepareStatement(
							"update Account set [companyId]=?,[userId]?,[parentAccountId]=?,[statusId]=?, [updateDate]=? where [accountId] = ?");
					
					if(account.getCompanyId()!= null) ps.setInt(idx++,account.getCompanyId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(account.getUserId()!= null) ps.setInt(idx++,account.getUserId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(account.getParentAccountId()!= null) ps.setInt(idx++,Integer.valueOf(account.getParentAccountId()));
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(account.getStatus()!= null) ps.setInt(idx++,getKeyByValue(status, account.getStatus()));
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					ps.setDate(idx++, new java.sql.Date(new Date().getTime()));
					
					if(account.getAccountIdentifier()!= null) ps.setInt(idx++,Integer.valueOf(account.getAccountIdentifier()));
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
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
	public Account add(Account object, AppJdbcTemplate jTemplate) throws Exception {
		try{
		assertNull("Account cannot be null", object);
		KeyHolder key = insertForPrimaryKey(this, object, jTemplate);
		return getObject(Integer.valueOf(key.getKey().intValue()), jTemplate);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Account update(Account object, AppJdbcTemplate jTemplate) throws Exception {
		try{
		assertNull("Account cannot be null", object);
		assertNull("Account identifier cannot be null", object.getAccountIdentifier());
		try{
			Integer accountId = Integer.valueOf((String) object.getAccountIdentifier());
		}catch(Exception e) {
			throw new Exception("unable to cast account identifier " + object.getAccountIdentifier());
		}
		update(this, object, jTemplate);
		return getObject(object.getAccountIdentifier(), jTemplate);
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
	public Account getObject(Object obj, AppJdbcTemplate jTemplate) throws Exception {
		try {
			assertNull("accountId cannot be null", obj);
			Integer accountId = null;
			if(obj instanceof String) {
				accountId = Integer.valueOf((String) obj);
			}else if(obj instanceof Integer) {
				accountId = (Integer) obj;
			}
			String query = "SELECT [accountId],[companyId],[userId],[parentAccountId],[statusId], [startDate], [updateDate]  FROM [Account] where [accountId] = ?";
			return jTemplate.queryForObject(query, new Object[] {accountId }, getRowMapperForAccount());
			
			}catch(IllegalArgumentException e) {
				throw new Exception("Invalid arguments in Account get by id"+ obj.toString());
			}catch(EmptyResultDataAccessException e) {
				return null;
			}catch(Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	
	private RowMapper<Account> getRowMapperForAccount() {
		return new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account result = new Account();
				result.setAccountIdentifier(String.valueOf(rs.getInt("accountId")));
				if(rs.getObject("userId")!= null) {
					result.setUserId(rs.getInt("userId"));
				}
				if(rs.getObject("companyId")!= null) {
					result.setUserId(rs.getInt("companyId"));
				}
				if(rs.getObject("parentAccountId")!= null) {
					result.setUserId(rs.getInt("companyId"));
				}
				result.setStatus(status.get(rs.getObject("statusId")).toString());
				result.setStartDate(rs.getDate("startDate"));
				if(rs.getObject("updateDate")!= null) {
					result.setUpdateDate(rs.getDate("updateDate"));
				}
				return result;
			}
			
		};
	}

	@Override
	public List<Account> getObjectByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception {
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


	


}
