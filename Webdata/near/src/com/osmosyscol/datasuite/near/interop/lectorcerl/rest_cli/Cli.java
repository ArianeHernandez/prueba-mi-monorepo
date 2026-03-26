package com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import co.htsoft.commons.net.CallPage;

public class Cli<T> {
	
	private static final String ACCEPT = "Accept";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";
	private static final String WHEN_EMPTYRESPONSE = "Empty Response";
	
	static final Logger logger = LoggerFactory.getLogger(Cli.class);
	

	private Gson gson = new Gson();
	
	//Map<String, List<Object>> params = new HashMap<>();
	
	public T callGet(String endpoint, Map<String, String> httpGetParams , Map<String, String> additionalHeaders, Class<T> outputType ) throws Exception {
		CallPage call = new CallPage();
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(CONTENT_TYPE, APPLICATION_JSON);
		headers.put(ACCEPT, APPLICATION_JSON);
		if( null != additionalHeaders ) {
			headers.putAll(additionalHeaders);
		}
		
		//httpGetParams.stream
		
		String transformedEndpoint = endpoint;
		
		if(null != httpGetParams ) {
			Map<String, List<Object>> _innerHttpGetParams = new HashMap<String,List<Object>>();
			
			for (Map.Entry<String, String> entry : httpGetParams.entrySet()) {
				CallPage.addParam(_innerHttpGetParams, entry.getKey(), entry.getValue());		
			}
			transformedEndpoint = endpoint + "?" + CallPage.join(_innerHttpGetParams);
		} 
		
		String response = call.callGet(transformedEndpoint , headers);
		
		if( logger.isDebugEnabled() ) {
			logger.debug("service with endpoint={} response: {}", transformedEndpoint, response);
		}
		
		if (StringUtils.isEmpty(response)) {
			throw new Exception(WHEN_EMPTYRESPONSE);
		}
		
		return gson.fromJson(response, outputType );
		
	}

}
