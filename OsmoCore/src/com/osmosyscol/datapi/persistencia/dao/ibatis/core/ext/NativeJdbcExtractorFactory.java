package com.osmosyscol.datapi.persistencia.dao.ibatis.core.ext;

/**
 * 
 * @author ahurtado
 * pattern=factorymethod,singleton
 */
public class NativeJdbcExtractorFactory {
	
	// #region 02 singleton declaration ---------------------
	// http://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
	
	private static class Holder {
		static final NativeJdbcExtractorFactory INSTANCE = new NativeJdbcExtractorFactory();
	}
	public static NativeJdbcExtractorFactory getInstance() {
        return Holder.INSTANCE;		
	}
	private NativeJdbcExtractorFactory() {
	}
	// #endregion 02 -------------------------------------
	
	/**
	 * Default set of jdbcextractors to process with weblogic
	 */
	public static final String[] DEFAULT_JDBC_EXTRACTORS= {
		JDCNativeJdbcExtractor.class.getName(),
		WlasNativeJdbcExtractor.class.getName(),
		CommonsDbcpNativeJdbcExtractor.class.getName(), // needs a special configuration flag
		BypassNativeJdbcExtractor.class.getName()
	};

	
	/**
	 * Creates an extractor by classname
	 * @param className
	 * @param propagateError if required, throw the errors, else return null
	 * @return
	 * @throws RuntimeException
	 */
	public INativeJdbcExtractor newExtractor( String className, boolean propagateError ) throws RuntimeException {
		
		Class<?> clazz = null;
		
		// load the referenced class
		try {
			
			clazz = getClass().getClassLoader().loadClass(className);
			
			// clazz = Class.forName(extractorClassName);
		} 
		catch (ClassNotFoundException e) {
			handleException(className, e, propagateError);
			return null;
		}
		
		INativeJdbcExtractor nativeJdbcExtractor = null;
		
		
		// prevent assign to non subclasses of INativeJdbcExtarctor interface

		if( !INativeJdbcExtractor.class.isAssignableFrom(clazz) ) {
			handleException(className, new RuntimeException("class not of compatible type"), propagateError);
			return null;
		}
		
		// instantiate 
		try {
			nativeJdbcExtractor = clazz.asSubclass(INativeJdbcExtractor.class).newInstance();
		} 
		catch (InstantiationException e) {
			handleException(className, e, propagateError);
			return null;
		} 
		catch (IllegalAccessException e) {
			handleException(className, e, propagateError);
			return null;
		}
		
		return nativeJdbcExtractor;
	}
	
	
	public <T> T newObject( Class<T> superclass, String subclassName, boolean propagateError ) throws RuntimeException {
		
		Class<?> clazz = null;
		
		// load the referenced class
		try {
			
			clazz = superclass.getClassLoader().loadClass(subclassName);
			
			// clazz = Class.forName(extractorClassName);
		} 
		catch (ClassNotFoundException e) {
			handleException(subclassName, e, propagateError);
			return null;
		}
		
		T t = null;
		
		
		// prevent assign to non subclasses of INativeJdbcExtarctor interface

		if( !superclass.isAssignableFrom(clazz) ) {
			handleException(subclassName, new RuntimeException("class not of compatible type"), propagateError);
			return null;
		}
		
		// instantiate 
		try {
			t = clazz.asSubclass(superclass).newInstance();
		} 
		catch (InstantiationException e) {
			handleException(subclassName, e, propagateError);
			return null;
		} 
		catch (IllegalAccessException e) {
			handleException(subclassName, e, propagateError);
			return null;
		}
		
		return t;
	}

	/**
	 * Control exceptions if flag active
	 * @param extractorClassName
	 * @param e
	 * @param propagateError
	 */
	private static void handleException(String extractorClassName,	Exception e, boolean propagateError) {
		if(propagateError) {
			throw new RuntimeException( String.format("+ instantiation of extractor %s error: %s", extractorClassName, e.getCause() ),  e);
		}
	}

}
