package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.servicios.CreaDatosServicio;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.CampoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.EstructuraDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ListaValoresDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class EstructuraServicio {

	private static EstructuraServicio estructuraServicio;

	private EstructuraServicio() {
	}

	public static EstructuraServicio getInstance() {
		if (estructuraServicio == null) {
			estructuraServicio = new EstructuraServicio();
		}
		return estructuraServicio;
	}

	// ------------------------------

	public Estructura obtenerEstructura(Integer id_estructura) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.obtenerEstructura(id_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Estructura> obtenerEstructurasPorGrupo(Integer id_grupo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			List<Estructura> estructuras = estructuraDao.obtenerEstructurasPorGrupo(id_grupo);
			
			if (estructuras != null) {
				for (Estructura estructura : estructuras) {
					List<Estructura> listadopadre = obtenerEstructurasPadre(estructura.getId_estructura());
					for (Estructura estructurapadre : listadopadre) {
						if (estructura.getId_estructuras_relacionadas() == null) {
							estructura.setId_estructuras_relacionadas(new ArrayList<Integer>());
						}
						estructura.getId_estructuras_relacionadas().add(estructurapadre.getId_estructura());
					}
					estructura.setCampos(CampoServicio.getInstance().obtenerCamposPorEstructura(estructura.getId_estructura()));
				}
			}

			return estructuras;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Estructura> obtenerEstructuras() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.obtenerEstructuras();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Estructura> obtenerEstructurasPorPersona(Integer id_modelo, Integer numero_pagina) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			List<Estructura> listado = estructuraDao.obtenerEstructurasPorPersona(id_modelo, numero_pagina);

			for (Estructura estructura : listado) {

				List<Estructura> listadopadre = obtenerEstructurasPadre(estructura.getId_estructura());
				for (Estructura estructurapadre : listadopadre) {
					if (estructura.getId_estructuras_relacionadas() == null) {
						estructura.setId_estructuras_relacionadas(new ArrayList<Integer>());
					}
					estructura.getId_estructuras_relacionadas().add(estructurapadre.getId_estructura());
				}
			}

			return listado;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Integer totalEstructurasPorPersona(Integer id_modelo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.totalEstructurasPorPersona(id_modelo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<String> buscarNombresEstructurasAccesibles(String filtro){
		try {
			EstructuraDao estructuraDao = (EstructuraDao) DaoConfig.getDao(EstructuraDao.class);
			return estructuraDao.buscarNombresEstructurasAccesibles(filtro);
		} catch (Exception e) {
			SimpleLogger.setError("Error buscarNombresEstructurasAccesibles", e);
		}
		return null;
	}
	
	public List<Estructura> obtenerEstructurasAccesiblesPorPersona(Integer id_modelo, Integer numero_pagina, String filtro) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			List<Estructura> listado = null;

			if (numero_pagina == null) {
				listado = estructuraDao.obtenerEstructurasAccesiblesPorPersona(id_modelo);
			} else {
				listado = estructuraDao.obtenerEstructurasAccesiblesPorPersona(id_modelo, filtro, numero_pagina);
			}

			for (Estructura estructura : listado) {

				List<Estructura> listadopadre = obtenerEstructurasPadre(estructura.getId_estructura());
				for (Estructura estructurapadre : listadopadre) {
					if (estructura.getId_estructuras_relacionadas() == null) {
						estructura.setId_estructuras_relacionadas(new ArrayList<Integer>());
					}
					estructura.getId_estructuras_relacionadas().add(estructurapadre.getId_estructura());
				}

				estructura.setCampos(CampoServicio.getInstance().obtenerCamposPorEstructura(estructura.getId_estructura()));
			}

			return listado;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Integer totalEstructurasAccesiblesPorPersona(Integer id_modelo, String filtro) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.totalEstructurasAccesiblesPorPersona(id_modelo, filtro);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Boolean guardarEstructura(String accion, Estructura estructura, List<Campo> campos, Integer id_persona, Integer id_modelo) {

		RDServicio.clear();

		CreaDatosServicio creaDatosServicio = CreaDatosServicio.getInstance();

		try {
			if (accion != null && accion.equals("GuardarEstructura")) {

				Boolean esNuevaEstructura = (estructura.getId_estructura() == null);

				DaoManager daoManager = DaoConfig.getDaoManager();
				DaoManager daoManager2 = DaoConfig.getDaoManager(2);

				daoManager.startTransaction();
				daoManager2.startTransaction();

				EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);
				CampoDao campoDao = (CampoDao) daoManager.getDao(CampoDao.class);
				ListaValoresDao listaValoresDao = (ListaValoresDao) daoManager.getDao(ListaValoresDao.class);

				try {

					List<Campo> camposAnteriores = campoDao.obtenerCamposPorEstructura(estructura.getId_estructura());

					estructuraDao.guardarEstructura(estructura, id_persona, id_modelo);

					if (!esNuevaEstructura) {
						creaDatosServicio.eliminarVista(daoManager2, estructura.getId_estructura());
					}

					if (!creaDatosServicio.existeTabla(daoManager2, "T" + estructura.getId_estructura().toString())) {
						// -- Crea Tabla
						if (!creaDatosServicio.crearTabla(daoManager2, estructura)) {
							SimpleLogger.setError("Ha ocurrido un error inesperado (creaDatosServicio.crearTabla(estructura))).");
							return false;
						}
					}

					for (Campo campo : campos) {
						if (campo != null && campo.getNombre() != null && campo.getNombre().trim().length() > 0) {
							if (campo.getTiposeleccionado() != null) {
								if (campo.getTiposeleccionado().indexOf("tc_") >= 0) {
									campo.setId_tipocampo(Integer.parseInt(campo.getTiposeleccionado().substring(3)));
								} else if (campo.getTiposeleccionado().indexOf("es_") >= 0) {
									campo.setId_estructurarelacionada(Integer.parseInt(campo.getTiposeleccionado().substring(3)));
									campo.setId_tipocampo(Constantes.CAMPO_TIPOCAMPO_ESTRUCTURA);
								} else if (campo.getTiposeleccionado().indexOf("lv_") >= 0) {
									campo.setId_lista_valores(Integer.parseInt(campo.getTiposeleccionado().substring(3)));
									campo.setId_tipocampo(listaValoresDao.obtenerListaValores(campo.getId_lista_valores()).getId_tipocampo());
								}

								campo.setId_estructura(estructura.getId_estructura());

								if (campoDao.guardarCampo(campo) == false) {
									return false;
								}

								// Se crean los formatos campo correspondiente
								Boolean relacionesFormatoCampoCreadas = FormatoServicio.getInstance().guardarRelacionFormatoCampoPorEstructuraTransaccional(estructura.getId_estructura(), campo, daoManager);

								if (relacionesFormatoCampoCreadas == null || relacionesFormatoCampoCreadas == false) {
									// se debe terminar la transaccion
									return false;
								}

								// -- Guardar campo
								creaDatosServicio.guardarCampo(daoManager, daoManager2, campo, campoDao.obtenerTipoCampo(campo), camposAnteriores);
							}
						} else if (campo != null && campo.getId_campo() != null) {

							campo.setId_estructura(estructura.getId_estructura());

							// Se eliminan las relaciones formato campo relacionados con el campo
							Boolean relacionesFormatoCampoEliminadas = FormatoServicio.getInstance().eliminarRelacionFormatoCampoPorCampoTransaccional(campo, daoManager);

							if (relacionesFormatoCampoEliminadas == null || relacionesFormatoCampoEliminadas == false) {
								// se debe terminar la transaccion
								return false;
							}

							campoDao.eliminarCampo(campo);
							creaDatosServicio.eliminarCampo(daoManager2, campo);

						}
					}

					// Obtiene los campos que eran de identificacion
					List<Campo> camposIdAnteriores = new ArrayList<Campo>();
					for (Campo campoAnterior : camposAnteriores) {
						if (StringUtils.esVerdad(campoAnterior.getLlaveprimaria())) {
							camposIdAnteriores.add(campoAnterior);
						}
					}

					if (!camposIdAnteriores.isEmpty()) {
						creaDatosServicio.eliminarPKUnique(daoManager2, estructura.getId_estructura());
					}

					// Obtiene los campos que son de identificacion
					List<Campo> camposIdNuevos = new ArrayList<Campo>();
					for (Campo campoNuevo : campos) {
						if (StringUtils.esVerdad(campoNuevo.getLlaveprimaria())) {
							camposIdNuevos.add(campoNuevo);
						}
					}

					if (!camposIdNuevos.isEmpty()) {
						creaDatosServicio.crearPKUnique(daoManager2, camposIdNuevos, estructura.getId_estructura());
					}

					// Crea Vista
					creaDatosServicio.crearVista(daoManager2, estructura.getId_estructura());

					// Crea Archivo Conf de las estructura por campos
					// actualizables
					// crearEstructuraConf(estructura);

					daoManager.commitTransaction();
					daoManager2.commitTransaction();

				} catch (Exception e) {
					SimpleLogger.setError("Ha ocurrido un error inesperado (EstructuraServicio.guardarEstructura).", e);
					return false;
				} finally {
					daoManager.endTransaction();
					daoManager2.endTransaction();
				}

				return true;
			}
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error inesperado (EstructuraServicio.guardarEstructura).", e);
			return false;
		}

		return false;
	}

	// ---------------------------

	private Boolean crearEstructuraConf(Estructura estructura) {
		try {

			String DATASUITE = System.getenv(ParametrosInicio.SYSVAR);

			if (DATASUITE == null || DATASUITE.equals("")) {
				SimpleLogger.setError("Se debe configurar la Variable de entorno DATASUITE.");
				return false;
			}

			String nombrecarpeta = DATASUITE + "/servicios";
			new File(nombrecarpeta).mkdirs();

			String nombrearchivo = nombrecarpeta + "/" + StringUtils.toFileName("act_" + estructura.getNombre() + "" + estructura.getId_estructura()) + ".conf";
			SimpleLogger.setInfo("Creando Archivo: " + nombrearchivo);

			String texto = "# " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(HorarioServicio.getInstance().obtenerFechaActual()) + "\n";
			texto += "# --------------------------------------------------------\n";

			texto += "nombre     " + StringUtils.toFileName("act_" + estructura.getNombre() + "" + estructura.getId_estructura()) + "\n";
			texto += concatenarConexion(2);
			texto += concatenarOperaciones(estructura);
			FileUtils.setContentFile(nombrearchivo, texto);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	// ---------------------------

	private static String concatenarOperaciones(Estructura estructura) {
		String texto = "";
		List<Campo> camposActualizables = new ArrayList<Campo>();
		String identificador = "";

		List<Campo> campos = CampoServicio.getInstance().obtenerCamposPorEstructura(estructura.getId_estructura());

		for (Campo campo : campos) {
			if (campo.getLlaveprimaria().equals(Constantes.SI)) {
				identificador = "C" + campo.getId_campo();
			}
			if (Constantes.SI.equals(campo.getActualizable())) {
				camposActualizables.add(campo);
			}
		}

		Integer numoperacion = 1;
		for (Campo campo : camposActualizables) {
			texto += concatenarUNAOperacion("operaciones:" + numoperacion, campo, estructura, identificador);
			numoperacion++;
		}
		return texto;
	}

	// ------------------------------

	private static String concatenarUNAOperacion(String nameoperacion, Campo campo, Estructura estructura, String identificador) {

		String nombreOperacion = StringUtils.toFileName((campo.getNombre()).toLowerCase() + "_" + campo.getId_campo());

		String texto = "";

		texto += "\n# --------------------------------------------------------\n";

		texto += nameoperacion + ".nombre             	  " + nombreOperacion + "\n";

		texto += nameoperacion + ".consulta.sql            update T" + estructura.getId_estructura() + " set C" + campo.getId_campo() + "= #valor# where idcarga=#id_carga# and ID=#id_registro#\n";

		// ----------------- parametros de entrada

		texto += "\n";
		texto += nameoperacion + ".parametros:0.nombre          id_carga\n";
		texto += nameoperacion + ".parametros:0.tipo   		   int\n";

		texto += "\n";
		texto += nameoperacion + ".parametros:1.nombre          id_registro\n";
		texto += nameoperacion + ".parametros:1.tipo   		   int\n";

		texto += "\n";
		texto += nameoperacion + ".parametros:2.nombre          valor\n";
		texto += nameoperacion + ".parametros:2.tipo   		    string\n";
		texto += nameoperacion + ".parametros:2.encriptado   	S\n";

		// ----------------- parametros de salida

		texto += "\n";
		texto += nameoperacion + ".resultados:0.nombre          actualizados\n";
		texto += nameoperacion + ".resultados:0.tipo            int\n";

		return texto;
	}

	// ------------------------------

	private static String concatenarConexion(Integer idConexion) {

		String texto = "";
		texto += "\n# --------------------------------------------------------\n";
		texto += "conexion.nombre           CREADATOS \n";

		return texto;
	}

	// ------------------------------

	public List<Estructura> obtenerEstructurasPadre(Integer id_estructura) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.obtenerEstructurasPadre(id_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Estructura> obtenerEstructurasRelacionadas(Integer id_estructura) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.obtenerEstructurasRelacionadas(id_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ---------------------------

	public Estructura obtenerEstructuraPorNombre(String nombreEstructura, Integer id_modelo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.obtenerEstructuraPorNombre(nombreEstructura.toUpperCase(), id_modelo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ---------------------------

	public Boolean eliminarEstructura(Integer id_estructura) {

		try {
			RDServicio.clear();
			DaoManager daoManager = DaoConfig.getDaoManager();

			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			List<Estructura> estructuras = estructuraDao.obtenerEstructurasRelacionadas(id_estructura);
			if (estructuras != null && !estructuras.isEmpty()) {
				return false;
			}

			return estructuraDao.eliminarEstructura(id_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	// ---------------------------

	public Boolean guardarEstructuraImport(Estructura estructura, Integer id_persona, Integer id_modelo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			DaoManager daoManager2 = DaoConfig.getDaoManager(2);
			daoManager.startTransaction();

			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			try {
				estructuraDao.guardarEstructura(estructura, id_persona, id_modelo);

				if (!CreaDatosServicio.getInstance().existeTabla(daoManager2, ("T" + estructura.getId_estructura()).toUpperCase())) {
					// -- Crea Tabla
					if (!CreaDatosServicio.getInstance().crearTabla(daoManager2, estructura)) {
						SimpleLogger.setError("Ha ocurrido un error inesperado (creaDatosServicio.crearTabla(estructura))).");
						return false;
					}
				}

				daoManager.commitTransaction();
			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error inesperado (EstructuraServicio.guardarEstructura).", e);
				return false;
			} finally {
				daoManager.endTransaction();
			}

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error inesperado (EstructuraServicio.guardarEstructura).", e);
			return false;
		}

	}

	// --------------------------------------

	public Boolean guardarCampoImport(List<Campo> campos, Estructura estructura) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			DaoManager daoManager2 = DaoConfig.getDaoManager(2);

			CampoDao campoDao = (CampoDao) daoManager.getDao(CampoDao.class);

			List<Campo> camposAnteriores = campoDao.obtenerCamposPorEstructura(estructura.getId_estructura());

			for (Campo campo : campos) {
				if (campo != null && campo.getNombre() != null && campo.getNombre().trim().length() > 0) {
					if (campo.getTiposeleccionado() != null) {

						campo.setId_estructura(estructura.getId_estructura());
						campoDao.guardarCampo(campo);

						// -- Guardar campo
						CreaDatosServicio.getInstance().guardarCampo(daoManager, daoManager2, campo, campoDao.obtenerTipoCampo(campo), camposAnteriores);
					}
				}
			}
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return true;
	}

	// ------------------------------

	public List<Estructura> obtenerEstructurasPorEstado(String estado) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.obtenerEstructurasPorEstado(estado);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	// **************************************************************************

	public Integer getSiguienteID_Objeto() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.getSiguienteID_Objeto();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// --------------------------------

	public List<Estructura> obtenerEstructurasPorFormatoDeSalida(Integer id_formato_salida) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.obtenerEstructurasPorFormatoDeSalida(id_formato_salida);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	// --------------------------------

	public Boolean actualizarPosicion(Integer id_estructura, Integer xpos, Integer ypos) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.actualizarPosicion(id_estructura, xpos, ypos);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;

	}

	// --------------------------------

	public Boolean actualizarPosicionGrupo(Integer id_estructura, Integer xpos, Integer ypos, Integer id_grupo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EstructuraDao estructuraDao = (EstructuraDao) daoManager.getDao(EstructuraDao.class);

			return estructuraDao.actualizarPosicionGrupo(id_estructura, xpos, ypos, id_grupo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	// --------------------------------

}
