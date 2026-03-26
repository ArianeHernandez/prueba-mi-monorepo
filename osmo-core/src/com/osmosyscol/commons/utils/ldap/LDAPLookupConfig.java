package com.osmosyscol.commons.utils.ldap;

import java.util.Set;

public class LDAPLookupConfig {
	
	private String userBaseDN;
	private String userAttribute = "uid";
	public Set<String> requiredRoles;
	public String roleBaseDN = "";
	
	public LDAPLookupConfig(String userBaseDN, String userAttribute,
			Set<String> requiredRoles, String roleBaseDN) {
		super();
		this.userBaseDN = userBaseDN;
		this.userAttribute = userAttribute;
		this.requiredRoles = requiredRoles;
		this.roleBaseDN = roleBaseDN;
	}
	
	public LDAPLookupConfig() {}

	public String getUserBaseDN() {
		return userBaseDN;
	}

	public void setUserBaseDN(String userBaseDN) {
		this.userBaseDN = userBaseDN;
	}

	public String getUserAttribute() {
		return userAttribute;
	}

	public void setUserAttribute(String userAttribute) {
		this.userAttribute = userAttribute;
	}

	public Set<String> getRequiredRoles() {
		return requiredRoles;
	}

	public void setRequiredRoles(Set<String> requiredRoles) {
		this.requiredRoles = requiredRoles;
	}

	public String getRoleBaseDN() {
		return roleBaseDN;
	}

	public void setRoleBaseDN(String roleBaseDN) {
		this.roleBaseDN = roleBaseDN;
	}
	
}
