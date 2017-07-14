package com.appdirect.integration.subscription.dao.creator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.appdirect.integration.configuration.AppDAOImpl;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Creator;
import com.appdirect.integration.entities.MarketPlace;

@Repository("CreatorDAO")
public class CreatorDAOImpl extends AppDAOImpl implements CreatorDAO {
	
	@Override
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	public Creator add(Creator object, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Creator update(Creator object, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Integer Id, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Creator getObject(Object obj, AppJdbcTemplate jTemplate) throws Exception {
		try {
			assertNull("Id or uuid cannot be null", obj);
			String query = "SELECT [creatorId],[firstName],[lastName],[fullName],[email],[language],"
					+ "[locale],[openId],[uuid],[createdDate],[updateDate]  FROM [Creator] where ";
			if(obj instanceof Integer) {
				query +=" [creatorId] = ?";
				return jTemplate.queryForObject(query, new Object[] { (Integer)obj}, getRowMapperForCreator());
			}else if (obj instanceof String) {
				query +=" [uuid] = ?";
				return jTemplate.queryForObject(query, new Object[] {(String)obj}, getRowMapperForCreator());
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
	
	private RowMapper<Creator> getRowMapperForCreator() {
		return new RowMapper<Creator>() {

			@Override
			public Creator mapRow(ResultSet rs, int rowNum) throws SQLException {
				Creator result = new Creator();
				result.setCreatorId(rs.getInt("creatorId"));
				result.setFirstName(rs.getString("firstName"));
				result.setLastName(rs.getString("lastName"));
				result.setFullName(rs.getString("fullName"));
				result.setEmail(rs.getString("email"));
				result.setLanguage(rs.getString("language"));
				result.setLocale(rs.getString("locale"));
				result.setOpenId(rs.getString("openId"));
				result.setUuid(rs.getString("uuid"));
				return result;
			}
			
		};
	}

	@Override
	public List<Creator> getObjectByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getObjectIdByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	


}
