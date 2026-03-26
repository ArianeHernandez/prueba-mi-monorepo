package com.osmosyscol.persistencia.dao.ibatis.core;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.sqlmap.client.SqlMapClient;

public class DaoManagerDTO {

	private Integer id;
	private String nombre;
	private DaoManager daoManager;
	private SqlMapClient sqlMapClient;
	private String url;
	private String driver;
	private String test;
	private String user;
	private String password;

	public DaoManagerDTO() {
	}

	public DaoManagerDTO(Integer id, String nombre, String url, String driver, DaoManager daoManager, SqlMapClient sqlMapClient, String user, String password) {
		super();
		this.id = id;
		this.url = url;
		this.driver = driver;
		this.nombre = nombre;
		this.daoManager = daoManager;
		this.sqlMapClient = sqlMapClient;
		this.user = user;
		this.password = password;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DaoManager getDaoManager() {
		return daoManager;
	}

	public void setDaoManager(DaoManager daoManager) {
		this.daoManager = daoManager;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
