package com.osmosyscol.datasuite.modelatos.logica.clientes;

import java.net.URL;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocioSOAPStub;
import com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.TipoElementoEntradalista_negocios_vs_usua;
import com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.TipoElementoSalidalista_negocios_vs_usua;
import com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.TipoEntradalista_negocios_vs_usua;
import com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegociosSOAPStub;
import com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.TipoElementoEntradalista_negocios;
import com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.TipoElementoSalidalista_negocios;
import com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.TipoEntradalista_negocios;
import com.osmosyscol.datasuite.modelatos.logica.clientes.negociosporadministrador.NegociosPorAdministradorSOAPStub;
import com.osmosyscol.datasuite.modelatos.logica.clientes.negociosporadministrador.TipoElementoEntradalista_usua_vs_fides;
import com.osmosyscol.datasuite.modelatos.logica.clientes.negociosporadministrador.TipoElementoSalidalista_usua_vs_fides;
import com.osmosyscol.datasuite.modelatos.logica.clientes.negociosporadministrador.TipoEntradalista_usua_vs_fides;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolesUsuarioSOAPStub;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradalistarRolesporLogin;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesporLogin;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ClienteWS {

	private static ClienteWS instance;

	public static ClienteWS getInstance() {
		if (instance == null) {
			instance = new ClienteWS();
		}
		return instance;
	}

	public TipoElementoSalidalista_negocios[] obtenerListaNegocios() {

		try {
			URL url = new URL(ParametrosInicio.getProperty("sippagos.endpoint") + "/ws/ListaNegocios");
			ListaNegociosSOAPStub stub = new ListaNegociosSOAPStub(url, null);
			
			TipoEntradalista_negocios entrada = new TipoEntradalista_negocios();
			TipoElementoEntradalista_negocios elementoEntrada = new TipoElementoEntradalista_negocios();
			entrada.setElementoEntrada(elementoEntrada);
			
			return stub.lista_negocios(entrada);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public TipoElementoSalidalista_usua_vs_fides[] obtenerNegociosPorAdministrador(String login) {

		try {

			URL url = new URL(ParametrosInicio.getProperty("sippagos.endpoint") + "/ws/NegociosPorAdministrador");
			
			NegociosPorAdministradorSOAPStub stub = new NegociosPorAdministradorSOAPStub(url, null);
			TipoEntradalista_usua_vs_fides entrada = new TipoEntradalista_usua_vs_fides();
			
			TipoElementoEntradalista_usua_vs_fides elementoEntrada = new TipoElementoEntradalista_usua_vs_fides();
			
			elementoEntrada.setLogin(login);
			
			entrada.setElementoEntrada(elementoEntrada);
			return stub.lista_usua_vs_fides(entrada);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public TipoElementoSalidalista_negocios_vs_usua[] obtenerAdministradoresPorNegocio(Integer id_negocio) {
		try {

			URL url = new URL(ParametrosInicio.getProperty("sippagos.endpoint") + "/ws/AdministradoresPorNegocio");

			AdministradoresPorNegocioSOAPStub stub = new AdministradoresPorNegocioSOAPStub(url, null);
			TipoEntradalista_negocios_vs_usua entrada = new TipoEntradalista_negocios_vs_usua();
			
			TipoElementoEntradalista_negocios_vs_usua elementoEntrada = new TipoElementoEntradalista_negocios_vs_usua();
			elementoEntrada.setNegocio(id_negocio);
			entrada.setElementoEntrada(elementoEntrada);
			return stub.lista_negocios_vs_usua(entrada);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public TipoElementoSalidalistarRolesporLogin[] obtenerRolesPorLogin(String login) {

		try {

			URL url = new URL(ParametrosInicio.getProperty("osmoautenticador.endpoint") + "/ws/RolesUsuario");
			
			RolesUsuarioSOAPStub stub = new RolesUsuarioSOAPStub(url, null);
			TipoEntradalistarRolesporLogin entrada = new TipoEntradalistarRolesporLogin();
			
			TipoElementoEntradalistarRolesporLogin elementoEntrada = new TipoElementoEntradalistarRolesporLogin();
			
			elementoEntrada.setLogin(login);
			
			entrada.setElementoEntrada(elementoEntrada);
			return stub.listarRolesporLogin(entrada);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
}
