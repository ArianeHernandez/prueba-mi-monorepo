package com.osmosyscol.datasuite.near.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class ObjectUtils {
	static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);
	
	/**
	 * Makes a deep copy of any Java object that is passed.
	 */
	public static Either<Exception,Object> deepCopy(Object object) {
	   try {
	     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	     ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
	     outputStrm.writeObject(object);
	     ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
	     ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
	     return Either.right(objInputStream.readObject());
	   }
	   catch (Exception e) {
	     return Either.left(e);
	   }
	}
	
	public static String toJson(Object solicitud) {
		try {
			Gson gson = new Gson();
			return gson.toJson(solicitud);
		}
		catch( Exception e ) {
			logger.warn("gson serialization toJson", e );
		}
		return null;
	}	

}