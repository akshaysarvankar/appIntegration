package com.appdirect.integration.client;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;


@Service("restClient")
@Scope("request")
public class RestClientImpl implements RestClient{
	
	@Override
	public <T> T getRestObject (String URL) throws Exception {
		OAuthConsumer consumer = new DefaultOAuthConsumer("Dummy", "secret");
		URL url = new URL(URL);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		consumer.sign(request);
		request.connect();
		request.getResponseMessage();
		return null;
			
		
	}

}
