package com.appdirect.integration.subscription.dao.marketplace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.appdirect.integration.configuration.AppDAOException;
import com.appdirect.integration.configuration.AppDAOImpl;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Creator;
import com.appdirect.integration.entities.MarketPlace;

@Repository("MarketPlaceDAO")
public class MarketPlaceDAOImpl extends AppDAOImpl implements MarketPlaceDAO {
	
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
	public MarketPlace add(MarketPlace object, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarketPlace update(MarketPlace object, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Integer Id, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MarketPlace getObject(Object obj, AppJdbcTemplate jTemplate) throws Exception {
		try {
		assertNull("Id or PartnerName cannot be null", obj);
		String query = "SELECT [Id],[partnerName],[baseUrl],[description],[startDate],[endDate]  FROM [marketPlace] where ";
		if(obj instanceof Integer) {
			query +=" [Id] = ?";
			return jTemplate.queryForObject(query, new Object[] { (Integer)obj}, getRowMapperForMarketPlace());
		}else if (obj instanceof String) {
			query +=" [partnerName] = ?";
			return jTemplate.queryForObject(query, new Object[] {(String)obj}, getRowMapperForMarketPlace());
		}
		return null;
		}catch(IllegalArgumentException e) {
			throw new Exception("Invalid arguments in marketplace get by id"+ obj.toString());
		}catch(EmptyResultDataAccessException e) {
			return null;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private RowMapper<MarketPlace> getRowMapperForMarketPlace() {
		return new RowMapper<MarketPlace>() {
			@Override
			public MarketPlace mapRow(ResultSet rs, int rowNum) throws SQLException {
				MarketPlace result = new MarketPlace();
				result.setPartner(rs.getString("partnerName"));
				result.setBaseUrl(rs.getString("baseUrl"));
				result.setMarketPlaceId(rs.getInt("Id"));
				return result;
			}		
		};
	}

	@Override
	public List<MarketPlace> getObjectByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
