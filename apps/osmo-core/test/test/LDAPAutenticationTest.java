package test;

import co.htsoft.commons.lang.P;

import com.osmosyscol.commons.utils.ldap.LDAPAuthenticator;
import com.osmosyscol.commons.utils.ldap.LDAPConnectionFactory;
import com.osmosyscol.commons.utils.ldap.LDAPLookupConfig;

public class LDAPAutenticationTest {
	public static void main(String[] args) throws Throwable {
		
		String host = "3.223.4.140";
		Integer port = 8636;
		
		String userDN = "SMARTCLDAP@corficolombiana.net";
		String passwd = "Pagos2020";
		
		
		LDAPConnectionFactory connectionFactory = new LDAPConnectionFactory(host, port, userDN, passwd);
		
		String userBaseDN = "OU=FC_01_SI,OU=FC_01_20,OU=Bogota,OU=FID,OU=Group_Corficolombiana_Users,DC=corficolombiana,DC=net";
		String roleBaseDN = null;
		LDAPLookupConfig lookupConfig = new LDAPLookupConfig(userBaseDN, "uid", null, roleBaseDN );
		
		LDAPAuthenticator authenticator = new LDAPAuthenticator(connectionFactory, lookupConfig);
		
		Boolean sslRequired = false;
		Boolean sAMAccount = false;
		String user = "FC0120LAD@corficolombiana.net";
		String password = "Feb2020*";
		P.println(authenticator.authenticate(user, password, sslRequired, sAMAccount) );

		System.exit(0);
		
	}
}
