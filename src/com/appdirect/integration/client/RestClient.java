package com.appdirect.integration.client;

import java.io.OutputStream;
import java.net.MalformedURLException;

import com.google.common.net.MediaType;

public interface RestClient {

	public <T> T getRestObject(String URL) throws Exception;

}
