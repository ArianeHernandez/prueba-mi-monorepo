/**
 * 
 */
package com.osmosyscol.osmoautenticador.autenticador;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.dominio.GrupoAutenticacion;
import com.osmosyscol.osmoautenticador.dominio.TipoAutenticacion;
import com.osmosyscol.osmoautenticador.servicio.UsuarioServicio;

/**
 * @author ojcchar
 * 
 */
public class GrupoAutenticacionFactory {

	private static HashMap<Integer, Autenticador> autenticadores = new HashMap<Integer, Autenticador>();
	private static HashMap<String, GrupoAutenticacion> gruposAutenticacion = new HashMap<String, GrupoAutenticacion>();

	public static GrupoAutenticacion obtenerGrupoAutenticacion(String nombreAplicacion) throws Exception {

		GrupoAutenticacion grupo = gruposAutenticacion.get(nombreAplicacion);

		if (grupo != null) {
			return grupo;
		}

		grupo = UsuarioServicio.getInstance().obtenerGrupoAutenticacion(nombreAplicacion);
		List<TipoAutenticacion> tiposAutenticacion = UsuarioServicio.getInstance().obtenerTiposAutenticacionGrupo(
				grupo.getId_grupo_autenticacion());

		if (tiposAutenticacion == null) {
			throw new Exception("El grupo no tiene tipos de autenticacion");
		} else if (tiposAutenticacion.size() < 1) {
			throw new Exception("El grupo no tiene tipos de autenticacion");
		}

		List<Autenticador> autenticadoresGrupo = new LinkedList<Autenticador>();

		for (int i = 0; i < tiposAutenticacion.size(); i++) {

			TipoAutenticacion tipo = tiposAutenticacion.get(i);
			Autenticador autenticador = autenticadores.get(tipo.getId_tipo_autenticacion());

			if (autenticador == null) {
				throw new Exception("El autenticador para el tipo de autenticacion " + tipo.getId_tipo_autenticacion()
						+ " no esta configurado");
			}

			autenticadoresGrupo.add(autenticador);

		}

		grupo.setAutenticadores(autenticadoresGrupo);
		gruposAutenticacion.put(nombreAplicacion, grupo);

		return grupo;

	}
	
	public static Autenticador obtenerAutenticador(int id) {
		return autenticadores.get(id);		
	}

	public static void adicionarAutenticador(int id, Autenticador aut) {
		autenticadores.put(id, aut);
	}

	@SuppressWarnings("unchecked")
	public static int cargarAutenticadores(List<String> listaAutenticadores, List<String> idsAutenticadores,
			List<String> rutasConfiguracion) throws Exception {

		int respuesta = 0;

		if (listaAutenticadores == null || rutasConfiguracion == null || idsAutenticadores == null) {
			throw new Exception("Error al cargar los autenticadores");
		}

		for (int i = 0; i < listaAutenticadores.size(); i++) {

			String idAutenticador = null;
			String claseAutenticador = null;
			String rutaConfiguracion = null;

			try {
				idAutenticador = idsAutenticadores.get(i);
				claseAutenticador = listaAutenticadores.get(i);
				rutaConfiguracion = rutasConfiguracion.get(i);

				Class<?> clase = Class.forName(claseAutenticador);
				Constructor<Autenticador> constructor = (Constructor<Autenticador>) clase.getConstructor();
				Autenticador autenticador = constructor.newInstance();

				autenticador.cargarConfiguracion(rutaConfiguracion);

				GrupoAutenticacionFactory.autenticadores.put(Integer.valueOf(idAutenticador), autenticador);
				
			} catch (InvocationTargetException ex) {

				SimpleLogger.setError("Ha ocurrido un error cargando el autenticador " + idAutenticador + " de la clase " + claseAutenticador + "\n\rError: " + ex.getTargetException().getMessage(), ex);

			} catch (Exception ex) {

				SimpleLogger.setError("Ha ocurrido un error cargando el autenticador " + idAutenticador + " de la clase " + claseAutenticador, ex);

			}

		}

		return respuesta;

	}
}
