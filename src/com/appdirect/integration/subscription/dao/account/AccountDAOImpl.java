package com.appdirect.integration.subscription.dao.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.appdirect.integration.configuration.AppDAOImpl;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Account;
import com.appdirect.integration.entities.AccountStatus;

@Repository("AccountDAO")
public class AccountDAOImpl extends AppDAOImpl implements AccountDAO {
	
	private AccountStatus accStatus = new AccountStatus();
	@Override
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception {
		return new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					Account account = (Account) entity;
					int idx=1;
					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO [Account] ([companyId],[parentAccountId],[statusId] )     VALUES" + 
							" (? ,? ,?)", new String[] {"[AccountId]"});
					
					if(account.getCompanyId()!= null) ps.setInt(idx++,account.getCompanyId());
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(account.getParentAccountId()!= null) ps.setInt(idx++,Integer.valueOf(account.getParentAccountId()));
					else ps.setNull(idx++, java.sql.Types.INTEGER);
					
					if(account.getStatus()!= null) ps.setInt(idx++,accStatus.getKeyByValue(account.getStatus()));
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
					String query = "update Account set [updateDate]=?" 
					+ (account.getCompanyId()!= null? ",[companyId]=?" : "")  
					+ (account.getParentAccountId()!= null? ",[parentAccountId]=?" : "")
					+ (account.getStatus()!= null? ",[statusId]=?" : "")
					+ " where [accountId]=?";
					PreparedStatement ps= conn.prepareStatement(query);
					
					ps.setDate(idx++, new java.sql.Date(new Date().getTime()));
					
					if(account.getCompanyId()!= null) {
						ps.setInt(idx++,account.getCompanyId());
					}
					
					if(account.getParentAccountId()!= null) {
						ps.setInt(idx++,Integer.valueOf(account.getParentAccountId()));
					}
					
					if(account.getStatus()!= null) {
						ps.setInt(idx++,accStatus.getKeyByValue(account.getStatus()));
					}

					ps.setInt(idx++,Integer.valueOf(account.getAccountIdentifier()));
					
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
			String query = "SELECT [accountId],[companyId],[parentAccountId],[statusId], [startDate], [updateDate]  FROM [Account] where [accountId] = ?";
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
				if(rs.getObject("companyId")!= null) {
					result.setCompanyId(rs.getInt("companyId"));
				}
				if(rs.getObject("parentAccountId")!= null) {
					result.setParentAccountId(String.valueOf(rs.getInt("parentAccountId")));
				}
				result.setStatus(accStatus.status.get(rs.getObject("statusId")).toString());
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
	

}
