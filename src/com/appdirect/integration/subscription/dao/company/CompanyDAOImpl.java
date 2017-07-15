package com.appdirect.integration.subscription.dao.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.appdirect.integration.configuration.AppDAOImpl;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Company;

@Repository("CompanyDAO")
public class CompanyDAOImpl extends AppDAOImpl implements CompanyDAO {
	
	@Override
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception {
		return new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					Company company = (Company) entity;
					int idx=1;
					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO [Company] ([name],[phoneNumber],[email],[country],[website],[uuid],[externalId])     VALUES" + 
							" (? ,? ,? ,? ,? ,? ,?)", new String[] {"[CompanyId]"});
					
					if(company.getName()!= null) ps.setString(idx++,company.getName());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(company.getPhoneNumber()!= null) ps.setString(idx++,company.getEmail());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(company.getEmail()!= null) ps.setString(idx++,company.getEmail());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(company.getCountry()!= null) ps.setString(idx++,company.getCountry());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(company.getWebsite()!= null) ps.setString(idx++,company.getWebsite());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					 					
					if(company.getUuid()!= null) ps.setString(idx++,company.getUuid());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(company.getExternalId()!= null) ps.setString(idx++,company.getExternalId());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatementCreator createDeleteStatement(Object entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company add(Company object, AppJdbcTemplate jTemplate) throws Exception {
		try{
		assertNull("Company cannot be null", object);
		assertNull("Company uuid cannot be null", object.getUuid());
		KeyHolder key = insertForPrimaryKey(this, object, jTemplate);
		return getObject(Integer.valueOf(key.getKey().intValue()), jTemplate);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Company update(Company object, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Integer Id, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Company getObject(Object obj, AppJdbcTemplate jTemplate) throws Exception {
		try {
			assertNull("Id or uuid cannot be null", obj);
			String query = "SELECT [companyId],[name],[phoneNumber],[email],[country],[website],[uuid],"
					+ "[externalId] FROM [Company] where ";
			if(obj instanceof Integer) {
				query +=" [CompanyId] = ?";
				return jTemplate.queryForObject(query, new Object[] { (Integer)obj}, getRowMapperForCompany());
			}else if (obj instanceof String) {
				query +=" [uuid] = ?";
				return jTemplate.queryForObject(query, new Object[] {(String)obj}, getRowMapperForCompany());
			}
			return null;
			}catch(IllegalArgumentException e) {
				throw new Exception("Invalid arguments in Company get by id"+ obj.toString());
			}catch(EmptyResultDataAccessException e) {
				return null;
			}catch(Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	
	private RowMapper<Company> getRowMapperForCompany() {
		return new RowMapper<Company>() {
			@Override
			public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
				Company result = new Company();
				result.setCompanyId(rs.getInt("CompanyId"));
				if(rs.getObject("name")!= null) {
					result.setName(rs.getString("name"));
				}
				if(rs.getObject("phoneNumber")!= null) {
					result.setPhoneNumber(rs.getString("phoneNumber"));
				}
				if(rs.getObject("email")!= null) {
					result.setEmail(rs.getString("email"));
				}
				if(rs.getObject("country")!= null) {
					result.setCountry(rs.getString("country"));
				}
				if(rs.getObject("website")!= null) {
					result.setWebsite(rs.getString("website"));
				}
				if(rs.getObject("uuid")!= null) {
					result.setUuid(rs.getString("uuid"));
				}
				if(rs.getObject("externalId")!= null) {
					result.setExternalId(rs.getString("externalId"));
				}
				return result;
			}
			
		};
	}

	@Override
	public List<Company> getObjectByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getObjectIdByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	


}
