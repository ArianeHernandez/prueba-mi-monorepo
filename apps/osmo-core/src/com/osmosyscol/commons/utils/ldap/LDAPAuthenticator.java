package com.osmosyscol.commons.utils.ldap;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.axis.utils.StringUtils;

import co.htsoft.commons.lang.P;

import com.osmosyscol.commons.log.SimpleLogger;
import com.unboundid.ldap.sdk.Filter;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;

public class LDAPAuthenticator {
	
	private final LDAPConnectionFactory connectionFactory;
	private final LDAPLookupConfig lookupConfig;
	
	public LDAPAuthenticator(LDAPConnectionFactory connectionFactory,
			LDAPLookupConfig lookupConfig) {
		this.connectionFactory = connectionFactory;
		this.lookupConfig = lookupConfig;
	}
	
	
	public LDAPUser authenticate(String userName, String password){
		return authenticate(userName, password, false, false);
	}
	
	public LDAPUser authenticate(String userName, String password, Boolean sslRequired){
		return authenticate(userName, password, sslRequired, false);
	}
	
	public LDAPUser authenticate(String userName, String password, Boolean sslRequired, Boolean useUserDN){
		try {
			
			LDAPUser user = new LDAPUser(userName);
			P.println(user);
			if (user.getName() == null){
				throw new RuntimeException("El userName debe cumplir con el patron " + LDAPUser.PATTERN);
			}
			
			if (StringUtils.isEmpty(password)){
				throw new RuntimeException("No se envio ningun password");
			}
			
			String userDN = (useUserDN) ? dnFromUsername(user.getName()) : userName;
			
			LDAPConnection conn = connectionFactory.getLDAPConnection(userDN, password, sslRequired);
			conn.close();
			
			Set<String> requiredRoles = lookupConfig.getRequiredRoles();	
			if (requiredRoles != null && !requiredRoles.isEmpty()) {
				Set<String> roles = rolesFromDN(userDN);
				roles = rolesFromDN(userDN);
				Boolean allowed = false;
				for (String requiredRol : requiredRoles){
					if (roles.contains(requiredRol)){
						allowed = true;
					}
				}
				
				if(!allowed){
					throw new RuntimeException("El usuario " + userName + " no tiene el rol requerido");
				}
				
			}
			
			return user;
			
			
		}catch (Exception e){
			SimpleLogger.setError("Error en la conexion al LDAP del usuario " + userName + ":", e);
		}
		
		return null;
	}
	
	private String dnFromUsername(String userName) throws LDAPException, GeneralSecurityException{
		String baseDN = lookupConfig.getUserBaseDN();
		String lookup = String.format("(%s=%s)", lookupConfig.getUserAttribute(), userName);
		
		SearchRequest request = new SearchRequest(baseDN, SearchScope.SUB, lookup);
		
		LDAPConnection conn = connectionFactory.getLDAPConnection();

		try{
			SearchResult result = conn.search(request);
			
			if (result.getEntryCount() == 0){
				throw new LDAPException(ResultCode.INVALID_CREDENTIALS);
			}
			
			return result.getSearchEntries().get(0).getDN();
			
		}finally {
			conn.close();
		}
		
	}
	
	private Set<String> rolesFromDN(String userDN) throws LDAPException, GeneralSecurityException, IOException {
		Set<String> roles =  new LinkedHashSet<String>();

		SearchRequest request = new SearchRequest(lookupConfig.getRoleBaseDN(), SearchScope.SUB, Filter.createEqualityFilter("uniqueMember", userDN));
		
		LDAPConnection conn = connectionFactory.getLDAPConnection();
		
		try {
			SearchResult result = conn.search(request);
			
			for (SearchResultEntry entry : result.getSearchEntries()){
				roles.add(entry.getDN());
			}
			
		}finally {
			conn.close();
		}
		
		return roles;
	}
	
}
