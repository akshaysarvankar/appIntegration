package com.appdirect.integration.configuration;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class AppJdbcTemplate extends JdbcTemplate implements DisposableBean {
	
	private PlatformTransactionManager pTransation = null;
	
	private TransactionStatus txStatus= null;
	
	public PlatformTransactionManager initTransaction() {
		if(this.getDataSource()!= null && pTransation== null) {
			pTransation= new DataSourceTransactionManager(this.getDataSource());
			TransactionDefinition txDef = new DefaultTransactionDefinition();
			txStatus = pTransation.getTransaction(txDef);
		}
		return pTransation;
	}

	@Override
	public void destroy() throws Exception {
		if(pTransation!= null && txStatus.hasSavepoint()) {
			pTransation.rollback(txStatus);
		}
		pTransation = null;
		((AppDataSource)this.getDataSource()).destroy();
		
	}
	
	public void commit() {
		pTransation.commit(txStatus);
	}
	
	public void rollback() {
		pTransation.rollback(txStatus);
	}

}
