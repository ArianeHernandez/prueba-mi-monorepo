package com.osmosyscol.osmoautenticador.seguridad.ldap;

import java.util.Hashtable;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;

public class ContextLDAP {

	private String validarUsuario(String pUser) {

		pUser = StringUtils.trimToEmpty(pUser);

		if (!pUser.matches("[\\w\\s]*")) {
			throw new IllegalArgumentException("Nombre de usuario no permitido.");
		}

		return pUser;
	}

	private String validarClave(String pPassword) {

		pPassword = StringUtils.trimToEmpty(pPassword);

		if (!pPassword.matches("[\\w]*")) {
			throw new IllegalArgumentException("Valor de contrasena no permitido.");
		}

		return pPassword;
	}

	public DirContext autenticar(String pUser, String pPassword, ConfiguracionLDAP configuracionLDAP) {

		try {

			pUser = validarUsuario(pUser);
			pPassword = validarClave(pPassword);

			if (pUser.length() != 0 && pPassword.length() != 0) {
				Hashtable<String, String> ldapEnv = new Hashtable<String, String>(11);
				ldapEnv.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
				ldapEnv.put("com.sun.jndi.ldap.read.timeout", "20000");
				ldapEnv.put("java.naming.provider.url", "ldap://" + configuracionLDAP.getServerIP() + ":" + configuracionLDAP.getPuerto());
				ldapEnv.put("java.naming.security.authentication", "simple");
				ldapEnv.put("java.naming.security.principal", "uid=" + pUser + "," + configuracionLDAP.getBaseName());
				ldapEnv.put("java.naming.security.credentials", pPassword);
				DirContext ldapContext = new InitialDirContext(ldapEnv);
				return ldapContext;
			}

		} catch (Exception e) {
			SimpleLogger.setError("El usuario: '" + pUser + "' o password son erroneos");
		}

		return null;
	}

	public synchronized String getDocumento(String pUsername, DirContext ldapContext, ConfiguracionLDAP configuracionLDAP) {

		try {

			String DNIALU = "";

			pUsername = validarUsuario(pUsername);

			String attrIDs[] = { configuracionLDAP.getDocumento() };
			Attributes answer = ldapContext.getAttributes("uid=" + pUsername + "," + configuracionLDAP.getBaseName(), attrIDs);
			for (NamingEnumeration<? extends Attribute> ae = answer.getAll(); ae.hasMore();) {
				Attribute attr = (Attribute) ae.next();
				for (NamingEnumeration<?> e = attr.getAll(); e.hasMore();) {
					DNIALU = (String) e.next();
				}
			}

			return DNIALU;

		} catch (Exception e) {
			SimpleLogger.setError("No se puede obtener el documento del usuario: '" + pUsername + "'");
			return "";
		}

	}

	public synchronized String getNombreCompleto(String pUsername, DirContext ldapContext, ConfiguracionLDAP configuracionLDAP) {

		try {

			String DNIALU = "";

			pUsername = validarUsuario(pUsername);

			String attrNombreCompleto[] = { configuracionLDAP.getNombreCompleto() };
			Attributes answer = ldapContext.getAttributes("uid=" + pUsername + "," + configuracionLDAP.getBaseName(), attrNombreCompleto);
			for (NamingEnumeration<? extends Attribute> ae = answer.getAll(); ae.hasMore();) {
				Attribute attr = (Attribute) ae.next();
				for (NamingEnumeration<?> e = attr.getAll(); e.hasMore();) {
					DNIALU = (String) e.next();
				}
			}

			return DNIALU;

		} catch (Exception e) {
			SimpleLogger.setError("No se puede obtener el nombre completo del usuario: '" + pUsername + "'");

			return "";
		}
	}

}
