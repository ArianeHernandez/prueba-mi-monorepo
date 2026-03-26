package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.ManejadorDeValidadores;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.ResultadoValidacion;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoValidacion;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionEstructura;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ValidacionEstructuraDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ValidacionEstructuraServicio {
	// ---------------------------------------------------------------

	private static ValidacionEstructuraServicio validacionCargaServicio;

	private ValidacionEstructuraServicio() {
	}

	// ---------------------------------------------------------------

	public static ValidacionEstructuraServicio getInstance() {
		if (validacionCargaServicio == null) {
			validacionCargaServicio = new ValidacionEstructuraServicio();
		}
		return validacionCargaServicio;
	}

	// ---------------------------------------------------------------

	public List<Map<String, Object>> obtenerRegistroDeEstructuraPorCarga(Integer id_carga, Integer id_estructura) {
		try {

			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager("CREADATOS");
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			return validacionCargaDao.obtenerRegistrosDeEstructuraPorCarga(id_carga, id_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}
	
	public List<Map<String, Object>> obtenerRegistroDeEstructuraPorCargaVista(Integer id_carga, Integer id_estructura) {
		try {

			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager("CREADATOS");
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			return validacionCargaDao.obtenerRegistrosDeEstructuraPorCargaVista(id_carga, id_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public List<ValidacionEstructura> obtenerValidacionesPorEstructura(Integer id_estructura) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			// Se traen las validaciones por estructura sin la lista de
			// validacionesCampo
			List<ValidacionEstructura> validacionesPorEstructura = validacionCargaDao.obtenerValidacionesPorEstructura(id_estructura);

			// Para cada una de las validaciones se establecen validacionesCampo
			// que tiene asociadas
			for (ValidacionEstructura validacionEstructura : validacionesPorEstructura) {
				List<ValidacionCampo> validacionesPorCampo = validacionCargaDao.obtenerValidacionCampoPorValidacion(validacionEstructura.getId_validacion_estructura());

				// Se inicializa la lista de validacionesCampo de cada
				// validacion
				validacionEstructura.setListaValidacionCampo(validacionesPorCampo);
			}

			// Se retorna la lista de validacionesPorEstructura con las
			// validacionesPorCampo asociadas
			return validacionesPorEstructura;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	/***
	 * Esta funcion retorna un Map con dos elementos. El primer elemento es un
	 * mapa que retorna los resultados de las validaciones de cada uno de los
	 * registros asociados a la carga. La llave de busqueda es el ID del
	 * registro. El segundo elemento retorna una lista de las validaciones que
	 * fueron incumplidas en la carga.
	 * 
	 * Para el primer elemento la llave de busqueda es: registros_validados Para
	 * el segundo elemento la llave de busqueda es: validaciones_activadas <br/>
	 * <br/>
	 * 
	 * Se utiliza en para revisar las validaciones a una determinada carga segun
	 * un formato de salida
	 * 
	 * @param id_carga
	 * @param id_formato_salida
	 * 
	 * @author jcvargasj
	 * 
	 */

	@SuppressWarnings("unchecked")
	public Map<String, Object> verificarValidacionesPorCarga(Integer id_carga, Integer id_formato_salida) {
		try {
			// Se debe buscar todas las estructuras asociadas al formato de
			// salida
			EstructuraServicio estructuraServicio = EstructuraServicio.getInstance();
			List<Estructura> estructurasPorFormato = estructuraServicio.obtenerEstructurasPorFormatoDeSalida(id_formato_salida);

			// mapas que constituyen el retorno de la funcion
			Map<String, Object> respuestaVerificacion = new java.util.HashMap<String, Object>();
			Map<String, Object> registrosValidados = new java.util.HashMap<String, Object>();
			Map<String, Object> validacionesActivadas = new java.util.HashMap<String, Object>();

			// Por cada una de las estructuras se buscan los registros asociados
			// a la carga y las validaciones
			for (Estructura estructura : estructurasPorFormato) {

				Integer id_estructura = estructura.getId_estructura();

				// Se deben obtener las validaciones por cada una de las
				// estructuras

				List<ValidacionEstructura> validacionesPorEstructuraTotal = obtenerValidacionesPorEstructura(id_estructura);
				List<ValidacionEstructura> validacionesPorEstructura = new ArrayList<ValidacionEstructura>();

				for (ValidacionEstructura validacionEstructura : validacionesPorEstructuraTotal) {
					validacionesActivadas.put("" + validacionEstructura.getId_validacion_estructura(), validacionEstructura);
				}

				for (ValidacionEstructura validacionEstructura : validacionesPorEstructuraTotal) {
					if (StringUtils.esVerdad(validacionEstructura.getEnlinea())) {
						validacionesPorEstructura.add(validacionEstructura);
					}
				}

				SimpleLogger.setDebug("Numero de validaciones: " + validacionesPorEstructura.size());

				if (validacionesPorEstructura.size() > 0) {
					// Se obtienen los registros de la carga para cada uno de
					// las
					// estructuras del formato de salida
					List<Map<String, Object>> registros = obtenerRegistroDeEstructuraPorCarga(id_carga, id_estructura);

					// Para cada uno de los registros se deben verificar cada
					// una de
					// las validaciones

					for (Map<String, Object> registro : registros) {

						Map<String, Object> validacionesPorRegistro = new HashMap<String, Object>();

						for (ValidacionEstructura validacionEstructura : validacionesPorEstructura) {

							// El ManejadorDeValidadores es el encargado de
							// elegir
							// la implementacion
							// del validor segun el tipo de validacion que se
							// vaya a
							// verificar
							Boolean esRegistroValido = ManejadorDeValidadores.getInstance().esRegistroDeLaCargaValido(registro, registros, validacionEstructura);

							if (!esRegistroValido) {
								validacionesPorRegistro.put("" + validacionEstructura.getId_validacion_estructura(), esRegistroValido);
							}

						}

						// Por cada uno de los registro se asocian las respuesta
						// de
						// las validaciones
						if (validacionesPorRegistro.size() > 0) {
							registrosValidados.put("" + registro.get("ID"), validacionesPorRegistro);
						}

					}
				}

			}

			// Obtiene la informacion de las validaciones ya registradas.

			List<ResultadoValidacion> resultado = obtenerResultadoValidacion(id_carga);

			if (resultado != null) {
				for (ResultadoValidacion resultadoValidacion : resultado) {

					Map<String, Object> validacionesPorRegistro = (Map<String, Object>) registrosValidados.get(resultadoValidacion.getId_registro().toString());

					if (validacionesPorRegistro == null) {
						validacionesPorRegistro = new HashMap<String, Object>();
						registrosValidados.put(resultadoValidacion.getId_registro().toString(), validacionesPorRegistro);
					}
					
					validacionesPorRegistro.put(resultadoValidacion.getId_validacion().toString(), false);

				}
			}

			// Finalmente se construye la respuesta
			respuestaVerificacion.put("registros_validados", registrosValidados);
			respuestaVerificacion.put("validaciones_activadas", validacionesActivadas);

			return respuestaVerificacion;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	/**
	 * 
	 * Realiza las validaciones que no son en linea.
	 * 
	 * @param id_carga
	 * @param id_formato_salida
	 * @return mapa de la forma Map<ID_REGISTRO, Set<ID_VALIDACION>>
	 */
	public Map<Integer, Set<Integer>> verificarValidacionesNoEnLineaPorCarga(Integer id_carga) {
		try {
			// Se debe buscar todas las estructuras asociadas al formato de
			// salida
			EstructuraServicio estructuraServicio = EstructuraServicio.getInstance();
			List<Estructura> estructurasPorFormato = estructuraServicio.obtenerEstructurasPorEstado(Constantes.ESTRUCTURA_ESTADO_ACTIVA);

			// mapas que constituyen el retorno de la funcion
			Map<Integer, Set<Integer>> registrosValidados = new HashMap<Integer, Set<Integer>>();

			// Por cada una de las estructuras se buscan los registros asociados
			// a la carga y las validaciones
			for (Estructura estructura : estructurasPorFormato) {

				Integer id_estructura = estructura.getId_estructura();

				// Se deben obtener las validaciones por cada una de las
				// estructuras
				List<ValidacionEstructura> validacionesPorEstructuraTotal = obtenerValidacionesPorEstructura(id_estructura);
				List<ValidacionEstructura> validacionesPorEstructura = new ArrayList<ValidacionEstructura>();

				for (ValidacionEstructura validacionEstructura : validacionesPorEstructuraTotal) {
					if (!StringUtils.esVerdad(validacionEstructura.getEnlinea())) {
						validacionesPorEstructura.add(validacionEstructura);
					}
				}

				if (validacionesPorEstructura.size() > 0) {
					// Se obtienen los registros de la carga para cada uno de
					// las
					// estructuras del formato de salida
					List<Map<String, Object>> registros = obtenerRegistroDeEstructuraPorCarga(id_carga, id_estructura);

					// Para cada uno de los registros se deben verificar cada
					// una de
					// las validaciones

					for (Map<String, Object> registro : registros) {

						Set<Integer> validacionesPorRegistro = new HashSet<Integer>();

						for (ValidacionEstructura validacionEstructura : validacionesPorEstructura) {

							Boolean esRegistroValido = ManejadorDeValidadores.getInstance().esRegistroDeLaCargaValido(registro, registros, validacionEstructura);

							if (!esRegistroValido) {
								validacionesPorRegistro.add(validacionEstructura.getId_validacion_estructura());
							}

						}

						// Por cada uno de los registro se asocian las respuesta
						// de
						// las validaciones
						if (validacionesPorRegistro.size() > 0) {
							registrosValidados.put(Integer.parseInt("" + registro.get("ID")), validacionesPorRegistro);
						}

					}
				}

			}

			// Finalmente se construye la respuesta

			return registrosValidados;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}
	
	public Boolean generarValidacionesNoEnLineaHilo(Integer id_carga) {
	
		try {
			HiloValidacionNoEnLinea hiloValidacionNoEnLinea = new HiloValidacionNoEnLinea(id_carga);
			new Thread(hiloValidacionNoEnLinea).start();
			
			return true;
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al iniciar la validacion", e);
		}
		
		return false;
	}

	
	
	public Boolean generarValidacionesNoEnLinea(Integer id_carga) {

		try {
			
			Thread.sleep(30000);
			
			Map<Integer, Set<Integer>> resultadoValidacion = verificarValidacionesNoEnLineaPorCarga(id_carga);

			return guardarResultadoValidacion(id_carga, resultadoValidacion);

		} catch (Exception e) {
			SimpleLogger.setError("Error al realizar la validacion no en linea.", e);
		}

		return false;
	}

	public List<ResultadoValidacion> obtenerResultadoValidacion(Integer id_carga) {

		try {
			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			return validacionCargaDao.obtenerResultadoValidacion(id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public Boolean guardarResultadoValidacion(Integer id_carga, Map<Integer, Set<Integer>> resultadoValidacion) {

		try {
			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			validacionCargaDao.borrarResultadoValidacion(id_carga);

			if (resultadoValidacion != null) {

				Set<Integer> id_registros = resultadoValidacion.keySet();

				for (Integer id_registro : id_registros) {

					Set<Integer> validaciones = resultadoValidacion.get(id_registro);

					if (validaciones != null) {
						for (Integer id_validacion : validaciones) {
							validacionCargaDao.guardarResultadoValidacion(id_carga, id_registro, id_validacion);
						}
					}
				}
			}

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;
	}

	public List<TipoValidacion> obtenerTiposDeValidacion() {

		try {
			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			return validacionCargaDao.obtenerTiposDeValidacion();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}

	public TipoValidacion obtenerTipoValidacion(String id_tipovalidacion) {
		try {
			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			return validacionCargaDao.obtenerTipoDeValidacion(id_tipovalidacion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public Boolean crearValidacionPorEstructura(ValidacionEstructura validacionEstructura) {

		try {
			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			return validacionCargaDao.crearValidacionPorEstructura(validacionEstructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;

	}

	public Integer obtenerSiguienteIDValidacionEstructura() {
		try {
			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			return validacionCargaDao.obtenerSiguienteIDValidacionEstructura();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}

	public Boolean crearRelacionCampoValidacion(ValidacionCampo validacionCampo) {
		try {
			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			return validacionCargaDao.crearRelacionCampoValidacion(validacionCampo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;

	}

	public Boolean eliminarTodasLasRelacionesCampoValidacion(Integer id_validacion_estructura) {
		try {
			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			return validacionCargaDao.eliminarTodasLasRelacionesCampoValidacion(id_validacion_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;
	}

	public Boolean actualizarValidacionPorEstructura(ValidacionEstructura validacionEstructura) {

		try {
			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			return validacionCargaDao.actualizarValidacionPorEstructura(validacionEstructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;
	}

	public Boolean eliminarValidacionPorEstructura(Integer id_validacion_estructura) {

		try {
			// Se consulta la base de datos credatos
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			return validacionCargaDao.eliminarValidacionPorEstructura(id_validacion_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;
	}

	public ValidacionEstructura obtenerValidacionPorEstructura(Integer id_validacion_estructura) {
		try {
			if (id_validacion_estructura != null) {

				DaoManager daoManager = DaoConfig.getDaoManager();
				ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

				// Se traen las validaciones por estructura sin la lista de
				// validacionesCampo
				ValidacionEstructura validacionPorEstructura = validacionCargaDao.obtenerValidacionPorEstructura(id_validacion_estructura);

				// Para cada una de las validaciones se establecen
				// validacionesCampo que tiene asociadas

				List<ValidacionCampo> validacionesPorCampo = validacionCargaDao.obtenerValidacionCampoPorValidacion(validacionPorEstructura.getId_validacion_estructura());

				// Se inicializa la lista de validacionesCampo de cada
				// validacion
				validacionPorEstructura.setListaValidacionCampo(validacionesPorCampo);

				// Se retorna la lista de validacionesPorEstructura con las
				// validacionesPorCampo asociadas
				return validacionPorEstructura;
			} else {
				SimpleLogger.setWarn("el id_validacion_estructura es null");
				return null;
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public Boolean guardarValidacionEstructuraVariosCampos(ValidacionEstructura validacionEstructura, List<ValidacionCampo> campos) {
		Boolean esExitosa = true;
		// Se establece el id de la nueva validacion
		Integer id_validacion_estructura = obtenerSiguienteIDValidacionEstructura();
		validacionEstructura.setId_validacion_estructura(id_validacion_estructura);

		// Se guarda el encabezado de la validacion
		if (crearValidacionPorEstructura(validacionEstructura)) {

			Boolean sonRelacionesExitosas = true;

			// Se crean todas las relaciones validacion-campo
			for (ValidacionCampo validacionCampo : campos) {

				if (validacionCampo.getSeleccionado().equals("S")) {
					// Se establece el id de la nueva validacion para cada uno
					// de los objetos validacionCampo
					validacionCampo.setId_validacion_estructura(id_validacion_estructura);

					// se crea la relacion
					if (!crearRelacionCampoValidacion(validacionCampo)) {
						sonRelacionesExitosas = false;
					}
				}

			}

			if (!sonRelacionesExitosas) {
				// Se deben eliminar todas las relaciones que hayan sido creadas
				eliminarTodasLasRelacionesCampoValidacion(id_validacion_estructura);

				// Se elimina el encabezado de la validacion
				eliminarValidacionPorEstructura(id_validacion_estructura);

				esExitosa = false;
			}

		} else {
			// Si no se puede crear el encabezado se establece que la operacion
			// no es exitosa
			esExitosa = false;

		}

		return esExitosa;

	}

	public Boolean actualizarValidacionEstructuraVariosCampos(ValidacionEstructura validacionEstructura, List<ValidacionCampo> campos) {
		Boolean esExitosa = true;
		Integer id_validacion_estructura = validacionEstructura.getId_validacion_estructura();

		// Se actualiza el encabezado
		if (id_validacion_estructura != null && actualizarValidacionPorEstructura(validacionEstructura)) {

			Boolean sonRelacionesExitosas = true;

			// Se eliminan las relaciones existentes
			if (eliminarTodasLasRelacionesCampoValidacion(id_validacion_estructura)) {

				// Se crean todas las nuevas relaciones validacion-campo
				for (ValidacionCampo validacionCampo : campos) {

					if (validacionCampo.getSeleccionado().equals("S")) {
						// Se establece el id de la nueva validacion para cada
						// uno de los objetos validacionCampo
						validacionCampo.setId_validacion_estructura(id_validacion_estructura);

						// se crea la relacion
						if (!crearRelacionCampoValidacion(validacionCampo)) {
							sonRelacionesExitosas = false;
						}
					}
				}

				if (!sonRelacionesExitosas) {
					// Se deben eliminar todas las relaciones que hayan sido
					// creadas
					eliminarTodasLasRelacionesCampoValidacion(id_validacion_estructura);

					esExitosa = false;
				}
			} else {
				esExitosa = false;
			}

		} else {
			// Si no se puede actualizar el encabezado se establece que la
			// operacion no es exitosa
			esExitosa = false;

		}

		return esExitosa;

	}

	public Boolean eliminarValidacionEstructuraVariosCampos(Integer id_validacion_estructura) {

		Boolean esExitosa = true;

		// Se deben eliminar todas las relaciones validacion campo
		if (eliminarTodasLasRelacionesCampoValidacion(id_validacion_estructura)) {
			if (!eliminarValidacionPorEstructura(id_validacion_estructura)) {
				// Si no puede eliminar el encabezado la operacion no es exitosa
				esExitosa = false;
			}
		} else {
			// Si no puede eliminar las relaciones la opreacion no es exitosa
			esExitosa = false;
		}

		return esExitosa;
	}

}

class HiloValidacionNoEnLinea implements Runnable{

	private Integer id_carga;
	public HiloValidacionNoEnLinea(Integer id_carga) {
		this.id_carga = id_carga;
	}
	
	public void run() {
		ValidacionEstructuraServicio.getInstance().generarValidacionesNoEnLinea(id_carga);
	}
	
}


