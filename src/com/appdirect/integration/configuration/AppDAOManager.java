package com.appdirect.integration.configuration;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public interface AppDAOManager extends InitializingBean, DisposableBean {

	/** 
     * This is the method to be used to initialize
     * database resources ie. connection.
  */
  public void setJdbcTemplate(AppJdbcTemplate template);
}

