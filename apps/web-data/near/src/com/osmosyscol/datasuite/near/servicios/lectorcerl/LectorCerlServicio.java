package com.osmosyscol.datasuite.near.servicios.lectorcerl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.Cli;
import com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json.DataIn;
import com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json.DataOut;
import com.osmosyscol.datasuite.near.utils.Either;

import static com.osmosyscol.datasuite.near.utils.ParametrosInicioUtils.$;

public class LectorCerlServicio {
	
	private static LectorCerlServicio _instance = new LectorCerlServicio(); 
	private LectorCerlServicio(){}
	public static LectorCerlServicio getInstance() {
		return _instance;
	}
	
	static final Logger logger = LoggerFactory.getLogger(LectorCerlServicio.class);
	
	final String LECTORCERL_SERVICIO_ENDPOINT = "lectorcerl.endpoint";
	
	protected Either<Exception,DataOut> invocar(DataIn in){
		final String endpointPropertyKey =  LECTORCERL_SERVICIO_ENDPOINT;
		
		final Either<Exception, String> endpoint = $(endpointPropertyKey); 
		if( endpoint.isLeft()) {
			logger.error("parametro no configrado {}", endpointPropertyKey );
			return Either.left( endpoint.left() );
		}
		
		try {
			Cli<DataOut> cli = new Cli<DataOut>();
			Map<String,String> additionalHeaders = null;
			Map<String, String> httpGetIn = toMap(in);
			
			DataOut out = cli.callGet(endpoint.right(), httpGetIn, additionalHeaders, DataOut.class);
			
			return Either.right(out);
		} catch (Exception e) {
			logger.error("invocacion servicio (error) , [key: {}, value: {} ]", endpointPropertyKey, endpoint.right(), e );
			return Either.left(e);
		}
		
	}
	private Map<String, String> toMap(DataIn in) {
		Map<String,String> httpGetIn = new HashMap<String,String>();
		httpGetIn.put("url_s3", in.getUrl_s3() );
		httpGetIn.put("html_s3", in.getHtml_s3() );
		return httpGetIn;
	}
	

}
