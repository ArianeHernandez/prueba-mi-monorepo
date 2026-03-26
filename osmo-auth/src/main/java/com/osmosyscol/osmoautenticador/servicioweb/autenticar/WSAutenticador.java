// CVS $Id$

package com.osmosyscol.osmoautenticador.servicioweb.autenticar;

import java.util.HashMap;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.autenticador.Autenticador;
import com.osmosyscol.osmoautenticador.autenticador.EntradaAutenticador;
import com.osmosyscol.osmoautenticador.autenticador.GrupoAutenticacionFactory;
import com.osmosyscol.osmoautenticador.autenticador.RespuestaAutenticador;
import com.osmosyscol.osmoautenticador.constantes.ConfiguracionAutenticador;
import com.osmosyscol.osmoautenticador.constantes.ConstantesGenerales;
import com.osmosyscol.osmoautenticador.dominio.GrupoAutenticacion;

public class WSAutenticador {

	// ---------------------------------------------

	public RespuestaAutenticar autenticar(SolicitudAutenticar solicitudAutenticar) {

		RespuestaAutenticar respuesta = new RespuestaAutenticar();

		try {
			GrupoAutenticacion grupo = GrupoAutenticacionFactory.obtenerGrupoAutenticacion(solicitudAutenticar.getAplicacion());

			List<Autenticador> autenticadores = grupo.getAutenticadores();

			EntradaAutenticador entrada = new EntradaAutenticador();
			HashMap<String, Object> datos = new HashMap<String, Object>();
			datos.put(ConstantesGenerales.LOGIN, solicitudAutenticar.getLogin());
			datos.put(ConstantesGenerales.CLAVE, solicitudAutenticar.getClave());
			datos.put(ConstantesGenerales.CONF, solicitudAutenticar.getConfiguracion());
			datos.put(ConstantesGenerales.APLICACION, solicitudAutenticar.getAplicacion());
			datos.put(ConstantesGenerales.TIPO_CLAVE, solicitudAutenticar.getTipoclave());
			entrada.setDatos(datos);

			boolean autenticado = false;
			for (Autenticador autenticador : autenticadores) {
				boolean autentI = false;

				try {
					RespuestaAutenticador respuestaAut = autenticador.autenticar(entrada);
					autentI = respuestaAut.isAutenticado();
				} catch (UnsupportedOperationException e) {
					SimpleLogger.setInfo("Operacion no soportada en la autenticacion");
				}

				if (grupo.getModo_autenticacion().equals(ConfiguracionAutenticador.AUTENTICADOR_ALGUNO) && autentI) {
					autenticado = true;
					break;
				} else {
					if (grupo.getModo_autenticacion().equals(ConfiguracionAutenticador.AUTENTICADOR_TODOS)) {
						if (!autentI) {
							autenticado = false;
							break;
						} else {
							autenticado = true;
						}
					}
				}

			}

			respuesta.setAutenticado(autenticado);

		} catch (Exception e) {
			SimpleLogger.setError("No se puede realizar la autenticacion (WSAutenticador.autenticar)", e);
		}

		return respuesta;
	}

	// ---------------------------------------------

	public RespuestaAutorizar autorizar(SolicitudAutorizar solicitudAutorizar) {

		RespuestaAutorizar respuesta = new RespuestaAutorizar();

		try {

			GrupoAutenticacion grupo = GrupoAutenticacionFactory.obtenerGrupoAutenticacion(solicitudAutorizar.getAplicacion());

			List<Autenticador> autenticadores = grupo.getAutenticadores();

			EntradaAutenticador entrada = new EntradaAutenticador();
			HashMap<String, Object> datos = new HashMap<String, Object>();
			datos.put(ConstantesGenerales.LOGIN, solicitudAutorizar.getLogin());
			datos.put(ConstantesGenerales.APLICACION, solicitudAutorizar.getAplicacion());
			datos.put(ConstantesGenerales.URL, solicitudAutorizar.getUrl());
			entrada.setDatos(datos);

			boolean autorizado = false;
			for (Autenticador autenticador : autenticadores) {
				boolean autentI = false;

				try {
					RespuestaAutenticador respuestaAut = autenticador.autorizar(entrada);
					autentI = respuestaAut.isAutenticado();
				} catch (UnsupportedOperationException e) {
					SimpleLogger.setInfo("Operacion no soportada en la autorizacion");
				}

				if (grupo.getModo_autorizacion().equals(ConfiguracionAutenticador.AUTENTICADOR_ALGUNO) && autentI) {
					autorizado = true;
					break;
				} else {
					if (grupo.getModo_autorizacion().equals(ConfiguracionAutenticador.AUTENTICADOR_TODOS)) {
						if (!autentI) {
							autorizado = false;
							break;
						} else {
							autorizado = true;
						}
					}
				}

			}

			respuesta.setAutorizado(autorizado);

		} catch (Exception e) {
			SimpleLogger.setError("No se puede realizar la autorizacion (WSAutenticador.autorizar)", e);
		}

		return respuesta;
	}

	// ---------------------------------------------

}
