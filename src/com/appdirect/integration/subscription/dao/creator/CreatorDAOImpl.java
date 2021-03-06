package com.appdirect.integration.subscription.dao.creator;

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
import com.appdirect.integration.entities.Creator;

@Repository("CreatorDAO")
public class CreatorDAOImpl extends AppDAOImpl implements CreatorDAO {
	
	@Override
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception {
		return new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				try {
					Creator creator = (Creator) entity;
					int idx=1;
					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO [Creator] ([firstName],[lastName],[fullName],[email],[language],[locale],[openId],[uuid])     VALUES" + 
							" (? ,? ,? ,? ,? ,? ,? ,?)", new String[] {"[CreatorId]"});
					if(creator.getFirstName()!= null) ps.setString(idx++,creator.getFirstName());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(creator.getLastName()!= null) ps.setString(idx++,creator.getLastName());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(creator.getFullName()!= null) ps.setString(idx++,creator.getFullName());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(creator.getEmail()!= null) ps.setString(idx++,creator.getEmail());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(creator.getLanguage()!= null) ps.setString(idx++,creator.getLanguage());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(creator.getLocale()!= null) ps.setString(idx++,creator.getLocale());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(creator.getOpenId()!= null) ps.setString(idx++,creator.getOpenId());
					else ps.setNull(idx++, java.sql.Types.VARCHAR);
					
					if(creator.getUuid()!= null) ps.setString(idx++,creator.getUuid());
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
	public Creator add(Creator object, AppJdbcTemplate jTemplate) throws Exception {
		try{
			assertNull("Creator cannot be null", object);
		assertNull("Creator uuid cannot be null", object.getUuid());
		KeyHolder key = insertForPrimaryKey(this, object, jTemplate);
		return getObject(Integer.valueOf(key.getKey().intValue()), jTemplate);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
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
