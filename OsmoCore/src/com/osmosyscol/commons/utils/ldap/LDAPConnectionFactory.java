package com.osmosyscol.commons.utils.ldap;

import java.security.GeneralSecurityException;

import javax.net.SocketFactory;

import com.unboundid.ldap.sdk.BindResult;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPConnectionOptions;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.util.ssl.HostNameSSLSocketVerifier;
import com.unboundid.util.ssl.SSLUtil;
import com.unboundid.util.ssl.TrustStoreTrustManager;

public class LDAPConnectionFactory {
	
	private final String host;
	private final Integer port;
	private final String userDN;
	private final String passwd;
	private final String trustStorePath;
	private final String trustStorePassword;
	private final String trustStoreType;
	
	
	public LDAPConnectionFactory(String host, Integer port, String userDN, String passwd){
		this.host = host;
		this.port = port;
		this.userDN = userDN;
		this.passwd = passwd;
		this.trustStorePath = null;
		this.trustStorePassword = null;
		this.trustStoreType = null;
	}
	
	public LDAPConnectionFactory(String host, Integer port, String userDN,
			String passwd, String trustStorePath, String trustStorePassword,
			String trustStoreType) {
		this.host = host;
		this.port = port;
		this.userDN = userDN;
		this.passwd = passwd;
		this.trustStorePath = trustStorePath;
		this.trustStorePassword = trustStorePassword;
		this.trustStoreType = trustStoreType;
	}
	
	public LDAPConnection getLDAPConnection() throws LDAPException, GeneralSecurityException {
		return getLDAPConnection(userDN, passwd);
	}
	
	public LDAPConnection getLDAPConnection(Boolean use_ssl) throws LDAPException, GeneralSecurityException {
		return getLDAPConnection(userDN, passwd, use_ssl);
	}
	
	public LDAPConnection getLDAPConnection(String userDN, String passwd) throws LDAPException, GeneralSecurityException {
		return getLDAPConnection(userDN, passwd, false);
	}
	
	public LDAPConnection getLDAPConnection(String userDN, String passwd, Boolean use_ssl) throws LDAPException, GeneralSecurityException {	

		LDAPConnection ldapConnection = null;
		
		if (use_ssl){
			
			LDAPConnectionOptions options = new LDAPConnectionOptions();
			
			TrustStoreTrustManager trust = new TrustStoreTrustManager(trustStorePath, trustStorePassword.toCharArray(), trustStoreType, false);
			options.setSSLSocketVerifier(new HostNameSSLSocketVerifier(false));
			
			SSLUtil sslUtil = new SSLUtil(trust);
			SocketFactory socketFactory = new LDAPSocketFactory(sslUtil.createSSLSocketFactory("TLSv1.2"));
			
			ldapConnection = new LDAPConnection(socketFactory, options);
			ldapConnection.connect(host, port);
			
		}else {
			ldapConnection = new LDAPConnection(host, port);
		}
		
		BindResult bindResult = ldapConnection.bind(userDN, passwd);
		
		if (!(ResultCode.SUCCESS.equals(bindResult.getResultCode()))){
			//Error de autenticacion
			throw new LDAPException(bindResult.getResultCode());
		}
		
		return ldapConnection;		
	}

}
