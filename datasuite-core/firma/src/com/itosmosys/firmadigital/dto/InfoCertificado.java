package com.itosmosys.firmadigital.dto;

public class InfoCertificado {

	private String gCommonName;
	private String  gOrganizationalUnit;
	private String  gOrganization;
	private String  gCity;
	private String  gState;
	private String gCountry;
	
	
	public InfoCertificado(){
	
	}

	public InfoCertificado( String commonName,
			 String organizationalUnit, String organization,String city,String state, String country) {
		super();
		gCity = city;
		gCommonName = commonName;
		gCountry = country;
		gOrganization = organization;
		gOrganizationalUnit = organizationalUnit;
		gState = state;
	}

	public String getGCommonName() {
		return gCommonName;
	}

	public void setGCommonName(String commonName) {
		gCommonName = commonName;
	}

	public String getGOrganizationalUnit() {
		return gOrganizationalUnit;
	}

	public void setGOrganizationalUnit(String organizationalUnit) {
		gOrganizationalUnit = organizationalUnit;
	}

	public String getGOrganization() {
		return gOrganization;
	}

	public void setGOrganization(String organization) {
		gOrganization = organization;
	}

	public String getGCity() {
		return gCity;
	}

	public void setGCity(String city) {
		gCity = city;
	}

	public String getGState() {
		return gState;
	}

	public void setGState(String state) {
		gState = state;
	}

	public String getGCountry() {
		return gCountry;
	}

	public void setGCountry(String country) {
		gCountry = country;
	}
}
