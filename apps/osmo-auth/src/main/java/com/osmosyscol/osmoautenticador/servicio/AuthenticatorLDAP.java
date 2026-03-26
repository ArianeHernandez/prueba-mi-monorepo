package com.osmosyscol.osmoautenticador.servicio;

import java.util.Map;

import javax.naming.directory.DirContext;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.seguridad.ldap.ConfiguracionLDAP;
import com.osmosyscol.osmoautenticador.seguridad.ldap.ContextLDAP;
import com.osmosyscol.osmoautenticador.seguridad.ldap.InitLDAPConfig;

public class AuthenticatorLDAP {

	private static AuthenticatorLDAP authenticatorLDAP;

	private AuthenticatorLDAP() {
	}

	public static AuthenticatorLDAP getInstance() {
		if (authenticatorLDAP == null) {
			authenticatorLDAP = new AuthenticatorLDAP();
		}
		return authenticatorLDAP;
	}

	public Boolean authenticate(String user, String password, String configuracion) {

		ContextLDAP contextLDAP = new ContextLDAP();
		Map<String, ConfiguracionLDAP> conldap = null;
		try {
			conldap = InitLDAPConfig.cargarConfiguraciones();
		} catch (Exception e) {
			SimpleLogger.setError("No se ha podido cargar la configuracion del Ldap", e);
		}
		ConfiguracionLDAP configuracionLDAP = conldap.get(configuracion);

		if (configuracionLDAP != null) {
			DirContext ldapContext = contextLDAP.autenticar(user, password, configuracionLDAP);
			if (ldapContext != null) {

				String nombre = contextLDAP.getNombreCompleto(user, ldapContext, configuracionLDAP);
				System.out.println("El Usuario " + nombre + " ha sido Autenticado");
				return true;
			}
		}
		return false;
	}

}
