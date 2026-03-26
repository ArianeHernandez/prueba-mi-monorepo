package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;
import java.util.Map;

public interface SQLDao {


	public void insertSQL(String exesql);
	
	public Integer updateSQL(String updatesql);
	
	public Integer deleteSQL(String deletesql);
	
	public List<Map<String, Object>> selectSQL(String selectsql);
	
	public Map<String, Object> selectSQLRegistro(String selectsql);
	
	public List<Map<String, Object>> selectSQLPaginado(String selectsql, Integer numpagina, Integer tamanoPagina);
	
	public Integer selectSQLNumber(String selectSQL);
	
	public List<Integer> selectSQLNumberList(String selectSQL);
		
	public String selectSQLString(String selectSQL);
	
}
