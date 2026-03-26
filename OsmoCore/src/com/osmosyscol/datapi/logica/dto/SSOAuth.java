package com.osmosyscol.datapi.logica.dto;

public class SSOAuth {
	
	private Integer id_aplicacion;
	private String seed;
	private String sso_token;
	
	public SSOAuth(){
	}
	
	public SSOAuth(Integer id_aplicacion, String seed, String sso_token){
		setId_aplicacion(id_aplicacion);
		setSeed(seed);
		setSso_token(sso_token);	
	}
	
	public Integer getId_aplicacion() {
		return id_aplicacion;
	}

	public void setId_aplicacion(Integer id_aplicacion) {
		this.id_aplicacion = id_aplicacion;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public String getSso_token() {
		return sso_token;
	}

	public void setSso_token(String sso_token) {
		this.sso_token = sso_token;
	}

}
