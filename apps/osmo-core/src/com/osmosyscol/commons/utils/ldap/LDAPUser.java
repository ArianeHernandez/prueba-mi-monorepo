package com.osmosyscol.commons.utils.ldap;

import java.security.Principal;

import com.osmosyscol.commons.utils.StringUtils;

public class LDAPUser implements Principal {

	private String name;
	
	public static final String PATTERN = "[^A-Za-z0-9-_.@]";
	
	public LDAPUser(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
//		return (isSanitizedUsername()) ? name : null;
		return name;
	}
	
	public Boolean isSanitizedUsername() {
		if (name != null){			
			return name.equals(name.replaceAll(PATTERN, ""));
		}
		return false;
	}
	
	public String getCleanUsername(){
		if (name != null && name.contains("@")){
			return StringUtils.left(name, name.indexOf("@"));
		}
		return name;
	}

}
