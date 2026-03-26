package com.osmosyscol.datapi.persistencia.dao.ibatis.core.ext;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holder to get the extractor processors.
 * @author ahurtado
 * pattern=composite
 */
public class CompositeJdbcExtractorProcessor implements INativeJdbcExtractor {
	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(CompositeJdbcExtractorProcessor.class);

	// #endregion 01 -------------------------------------
	
	
	public CompositeJdbcExtractorProcessor() {
		loadOracleConnectionClasses();
	}
	
	/**
	 * extractors holder
	 */
	private List<INativeJdbcExtractor> jdbcExtractors;
	
	/**
	 * Add jdbcExtractor to list
	 * @param jdbcExtractor
	 */
	public void addExtractor( INativeJdbcExtractor jdbcExtractor ) {
		if( null == jdbcExtractors ) {
			jdbcExtractors = new ArrayList<INativeJdbcExtractor>();
		}
		jdbcExtractors.add(jdbcExtractor);
	}
	
	/**
	 * to instantiate set of known jdbcextactors
	 */
	private NativeJdbcExtractorFactory extractorFactory = NativeJdbcExtractorFactory.getInstance();
	
	/**
	 * Add extractor by name using factory
	 * @param jdbcExtractorClassName
	 */
	public void addExtractor( String jdbcExtractorClassName ) {
		INativeJdbcExtractor jdbcExtractor = null;
		try {
			jdbcExtractor = extractorFactory.newExtractor(jdbcExtractorClassName, true);
		} 
		catch (RuntimeException e) {
			logger.debug( String.format("+ extractor class skipping [class=%s,error=%s,cause=%s]", jdbcExtractorClassName, e.getMessage(), e.getCause() ));
		}
		if( null != jdbcExtractor ) {
			addExtractor(jdbcExtractor);
			logger.debug( String.format("+ extractor class adding [class=%s]", jdbcExtractorClassName ));
		}
		
	}
	
	/**
	 * Add a list of extractors
	 * @param jdbcExtractorClassNameArray
	 */
	public void addExtractors( String[] jdbcExtractorClassNameArray ) {
		for(String jdbcExtractorClassName: jdbcExtractorClassNameArray ) {
			addExtractor(jdbcExtractorClassName);
		}
	}
	
	/**
	 * add a set of sorted extractors to process connections
	 */
	public void addDefaultExtractors() {
		addExtractors(NativeJdbcExtractorFactory.DEFAULT_JDBC_EXTRACTORS);
	}
	
	/**
	 * Unwrap the set of connections / process composite pattern
	 */
	public Connection doGetNativeConnection(Connection con) throws Exception{
		Connection connection = null;
		for( INativeJdbcExtractor extractor : jdbcExtractors ) {
			String extractorName = extractor.getClass().getSimpleName();
			logger.debug( String.format( "+ extracting-connection (begin) [using=%s]" , extractorName ) );			
			try {
				connection = extractor.doGetNativeConnection(con);
				logger.debug( String.format( "+ extracting-connection (end) [using=%s]" , extractorName ) );			
				return connection;
			}
			catch( Exception e ) {
				logger.debug( String.format( "+ extracting-connection (error) [using=%s,error=%s,cause=%s]" , extractorName, e.getMessage(), e.getCause() ) );			
			}
		}
		return null;
	}
	
	/**
	 * known names of oracle connections
	 */
	final String[] ORACLECONN_NAMES = {
			"oracle.jdbc.OracleConnection",
			"oracle.jdbc.driver.OracleConnection"
	};
	
	/**
	 * known classes of oracle connections
	 */
	List< Class<?> > oracleConnClasses = new ArrayList< Class<?>>();;
	
	/**
	 * Load oracle connection classes
	 */
	private void loadOracleConnectionClasses() {
		for(String oracleConn:ORACLECONN_NAMES ) {
			try {
				oracleConnClasses.add(CompositeJdbcExtractorProcessor.class.getClassLoader().loadClass(oracleConn));
				logger.debug( String.format( "+ oracle-compat class adding [class=%s]" , oracleConn ) );			
			} 
			catch (ClassNotFoundException e) {
				logger.debug( String.format( "+ oracle-compat class skipping [class=%s,error=%s,cause=%s]" , oracleConn, e.getMessage(), e.getCause() ) );			
			}
		}
		if( oracleConnClasses == null || oracleConnClasses.size() == 0 ) {
			logger.warn( String.format( "+ verify that are oracle drivers are loaded for the classloader. Some fragments of source code are dependant of this type of connection" ) );			
		}
		
	}
	
	/**
	 * Verifies that connection can be casted to an oracle connection
	 * @param con
	 * @return
	 */
	public boolean isOracleCompatible(Connection con) {
		if( null == con ) {
			return false;
		}
		for( Class<?> oraConnClass: oracleConnClasses ) {
			if( oraConnClass.isAssignableFrom(con.getClass())) {
				return true;
			}
		}
		return false;
	}
	
	

}
