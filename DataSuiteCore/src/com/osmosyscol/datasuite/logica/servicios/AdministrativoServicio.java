package com.osmosyscol.datasuite.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.Atributo;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;
import com.osmosyscol.datasuite.logica.dto.RestriccionAdministrativo;
import com.osmosyscol.datasuite.logica.dto.ValorAtributo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CargaInstanciaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.InstanciaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoAdminServicio;
import com.osmosyscol.datasuite.persistencia.dao.AdministrativoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class AdministrativoServicio {

	private static AdministrativoServicio administrativoServicio;

	private AdministrativoServicio() {
	}

	public static AdministrativoServicio getInstance() {
		if (administrativoServicio == null) {
			administrativoServicio = new AdministrativoServicio();
		}
		return administrativoServicio;
	}

	// ----------------------------------------------------

	public List<RestriccionAdministrativo> obtenerRestriccionesAdministrativos() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.obtenerRestriccionesAdministrativos();
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.obtenerRestriccionesAdministrativos", e);
		}
		return null;

	}

	public Integer obtenerIdSiguiente() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.obtenerIdSiguiente();
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.obtenerIdSiguiente", e);
		}
		return null;

	}

	public Boolean crearRestriccionAdministrativo(Integer id_rol, Integer id_administrativo, Integer id_usuario, Integer id_negocio, Integer id_restriccion_administrativo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.crearRestriccionAdministrativo(id_rol, id_administrativo, id_usuario, id_negocio, id_restriccion_administrativo);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.crearRestriccionAdministrativo", e);
		}
		return null;

	}

	public RestriccionAdministrativo obtenerRestriccionAdministrativo(Integer id_restriccion_administrativo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.obtenerRestriccionAdministrativo(id_restriccion_administrativo);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.obtenerRestriccionAdministrativo", e);
		}
		return null;

	}

	public Boolean eliminarRestriccionAdministrativo(Integer id_restriccion_administrativo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.eliminarRestriccionAdministrativo(id_restriccion_administrativo);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.eliminarRestriccionAdministrativo", e);
		}
		return null;

	}

	public Boolean actualizarRol(Integer id_restriccion_administrativo, Integer id_rol) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.actualizarRol(id_restriccion_administrativo, id_rol);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.actualizarRol", e);
		}
		return null;

	}

	public List<Administrativo> obtenerAdministrativosPorRol(Integer id_rol) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.obtenerAdministrativosPorRol(id_rol);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.obtenerAdministrativosPorRol", e);
		}
		return null;

	}

	public Boolean actualizarAdministrativo(Integer id_restriccion_administrativo, Integer id_administrativo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.actualizarAdministrativo(id_restriccion_administrativo, id_administrativo);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.actualizarAdministrativo", e);
		}
		return null;

	}

	public Boolean actualizarCliente(Integer id_restriccion_administrativo, Integer id_usuario) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.actualizarCliente(id_restriccion_administrativo, id_usuario);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.actualizarCliente", e);
		}
		return null;

	}

	public Boolean actualizarNegocio(Integer id_restriccion_administrativo, Integer id_negocio) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.actualizarNegocio(id_restriccion_administrativo, id_negocio);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.actualizarNegocio", e);
		}
		return null;

	}

	public Integer obtenerIdAdministrativoPersona(Integer id_persona, Integer id_usuario) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.obtenerIdAdministrativoPersona(id_persona, id_usuario);

		} catch (DaoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio obtenerIdAdministrativoPersona", e);
		}
		return null;

	}

	public List<RestriccionAdministrativo> obtenerRestriccionesPorAdministrativo(Integer id_administrativo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.obtenerRestriccionesPorAdministrativo(id_administrativo);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.obtenerRestriccionesPorAdministrativo", e);
		}
		return null;

	}

	public List<Administrativo> obtenerAdministrativosActivos() {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.obtenerAdministrativosActivos();
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AdministrativoServicio.obtenerAdministrativosActivos", e);
			return null;
		}

	}

	public List<Administrativo> obtenerAdministrativosParaNotificacion(Integer id_accion, Integer id_carga, Integer id_instancia, Integer id_proceso_admin) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);

			// Se consulta la informacion del proceso
			ProcesoAdminServicio procesoAdminServicio = ProcesoAdminServicio.getInstance();
			ProcesoAdmin procesoAdmin = procesoAdminServicio.obtenerProcesoAdmin(id_proceso_admin);

			// Se consulta la informacion del usuario cliente
			CargaInstanciaServicio cargaInstanciaServicio = CargaInstanciaServicio.getInstance();
			Integer id_usuario = cargaInstanciaServicio.obtenerIDUsuarioDeRelacionCargaInstancia(id_carga, id_instancia);

			// Se consultan todos los administrativos asociados a las instancias
			// destino de la accion a ejecutar
			List<Administrativo> administrativosSinFiltrar = administrativoDao.obtenerAdministrativosParaNotificacion(id_accion);

			// Lista de administrativos filtrados luego de revisar restricciones
			List<Administrativo> administrativosFiltrados = null;

			// Se filtran los administrativos
			AdministrativoServicio administrativoServicio = AdministrativoServicio.getInstance();

			if (administrativosSinFiltrar != null && administrativosSinFiltrar.size() > 0) {
				for (Administrativo administrativo : administrativosSinFiltrar) {

					// Se filtran los administrativos segun las restricciones
					// por administrativo
					List<RestriccionAdministrativo> restricciones = administrativoServicio.obtenerRestriccionesPorAdministrativo(administrativo.getId_administrativo());

					if (restricciones != null && restricciones.size() > 0) {
						ArrayList<Administrativo> administrativosFiltradosPorNegocioPorCliente = new ArrayList<Administrativo>();
						// Verificamos si tiene permiso para todos los negocios
						if (!tienePermisoParaTodosLosNegocios(restricciones)) {

							if (!tienePermisoParaTodosLosClientes(restricciones)) {
								// Se debe filtrar los administrativos por
								// negocio y por cliente

								for (RestriccionAdministrativo restriccionAdministrativo : restricciones) {

									if (procesoAdmin.getId_negocio() == restriccionAdministrativo.getId_negocio() && restriccionAdministrativo.getId_usuario() == id_usuario) {

										administrativosFiltradosPorNegocioPorCliente.add(administrativo);

									}
								}

								administrativosFiltrados = administrativosFiltradosPorNegocioPorCliente;

							} else {

								// Solo se filtran los administrativos por
								// negocio
								for (RestriccionAdministrativo restriccionAdministrativo : restricciones) {

									if (procesoAdmin.getId_negocio().equals(restriccionAdministrativo.getId_negocio())) {

										administrativosFiltradosPorNegocioPorCliente.add(administrativo);

									}

								}

								administrativosFiltrados = administrativosFiltradosPorNegocioPorCliente;

							}

						} else {

							// Solo se filtran los administrativos por usuario
							// cliente
							for (RestriccionAdministrativo restriccionAdministrativo : restricciones) {

								if (restriccionAdministrativo.getId_usuario().equals(id_usuario)) {

									administrativosFiltradosPorNegocioPorCliente.add(administrativo);

								}

							}

							administrativosFiltrados = administrativosFiltradosPorNegocioPorCliente;

						}

					} else {
						// Si no hay restricciones debe retornar los
						// administrativos sin filtrar
						administrativosFiltrados = administrativosSinFiltrar;

					}

				} // fin for

			} // fin if principal

			return administrativosFiltrados;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdminServicio.obtenerProcesosAdminPorNegocio", e);

		}

		return null;

	}

	private Boolean tienePermisoParaTodosLosNegocios(List<RestriccionAdministrativo> restricciones) {
		Boolean tienePermisoParaTodosLosNegocios = false;

		for (RestriccionAdministrativo restriccionAdministrativo : restricciones) {
			if (restriccionAdministrativo.getId_negocio() == null) {
				tienePermisoParaTodosLosNegocios = true;
			}

		}

		return tienePermisoParaTodosLosNegocios;
	}

	private Boolean tienePermisoParaTodosLosClientes(List<RestriccionAdministrativo> restricciones) {
		Boolean tienePermisoParaTodosLosClientes = false;

		for (RestriccionAdministrativo restriccionAdministrativo : restricciones) {
			if (restriccionAdministrativo.getId_usuario() == null) {
				tienePermisoParaTodosLosClientes = true;
			}

		}

		return tienePermisoParaTodosLosClientes;
	}

	public Administrativo obtenerAdministrativoPorID(Integer id_administrativo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return administrativoDao.obtenerAdministrativoPorID(id_administrativo);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio ", e);
			return null;
		}

	}

	public List<Administrativo> obtenerAdminsHijoPorInstanciaParaAdminPadre(Integer id_administrativo_padre, Integer id_instancia) {
		return obtenerAdminsHijoPorInstanciaParaAdminPadre(id_administrativo_padre, id_instancia, null);
	}

	public List<Administrativo> obtenerAdminsHijoPorInstanciaParaAdminPadre(Integer id_administrativo_padre, Integer id_instancia, Integer id_tipo_proceso) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministrativoDao administrativoDao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);

			ProcesoAdminServicio procesoAdminServicio = ProcesoAdminServicio.getInstance();

			List<Administrativo> administrativosSinFiltrar = administrativoDao.obtenerAdminsHijoPorInstanciaParaAdminPadre(id_administrativo_padre, id_instancia);

			// Se filtran los administrativos segun las restricciones por administrativo hijo
			List<Administrativo> administrativosFiltrados = null;

			// Se consulta el proceso al cual esta asociada la instancia
			Instancia instancia = InstanciaServicio.getInstance().obtenerInstancia(id_instancia);
			Integer id_proceso_admin = instancia.getId_proceso_admin();

			if (administrativosSinFiltrar != null) {

				List<Administrativo> administrativosFiltradosPorProceso = new ArrayList<Administrativo>();

				for (Administrativo administrativoHijo : administrativosSinFiltrar) {
					List<ProcesoAdmin> procesosPorAdminHijo = null;
					// Se revisa si el administrativo hijo puede ver el proceso
					if (id_tipo_proceso == null) {
						procesosPorAdminHijo = procesoAdminServicio.obtenerProcesosPorAdministrativo(administrativoHijo.getId_administrativo());
					} else {
						procesosPorAdminHijo = procesoAdminServicio.obtenerProcesosAdminPorTipoProcesoAdministrativo(id_tipo_proceso, administrativoHijo.getId_administrativo());
					}

					if (procesosPorAdminHijo != null && !procesosPorAdminHijo.isEmpty()) {
						for (ProcesoAdmin procesoAdminHijo : procesosPorAdminHijo) {
							// Si el proceso del admin hijo es igual al proceso consultado se agrega el administrativo a la lista
							if (procesoAdminHijo.getId_proceso_admin().equals(id_proceso_admin)) {
								administrativosFiltradosPorProceso.add(administrativoHijo);
							}

						}
					}

				}

				administrativosFiltrados = administrativosFiltradosPorProceso;

			} else {
				administrativosFiltrados = administrativosSinFiltrar;
			}

			return administrativosFiltrados;

		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio ", e);
			return null;
		}

	}

	public Integer guardarAdministrativoTrans(Administrativo administrativo, DaoManager daoManager) {

		try {

			AdministrativoDao dao = (AdministrativoDao) daoManager.getDao(AdministrativoDao.class);
			return dao.guardarAdministrativo(administrativo);

		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio ", e);
		}
		return null;

	}

	public List<Atributo> obtenerAtributos(Integer id_usuario) {
		return DaoConfig.getDao(AdministrativoDao.class).obtenerAtributos(id_usuario);
	}

	public List<ValorAtributo> obtenerValoresAtributo(Integer id_atributo, Integer id_administrativo) {
		return DaoConfig.getDao(AdministrativoDao.class).obtenerValoresAtributo(id_atributo, id_administrativo);
	}

	public List<Administrativo> obtenerAdministrativosPor(Integer id_usuario) {
		return DaoConfig.getDao(AdministrativoDao.class).obtenerAdministrativosPor(id_usuario);
	}

	public Administrativo obtenerAdministrativosPor(Integer id_usuario, Integer id_persona) {
		List<Administrativo> lista = DaoConfig.getDao(AdministrativoDao.class).obtenerAdministrativosPor(id_usuario);

		if (lista != null) {
			for (Administrativo administrativo : lista) {
				if (administrativo.getId_persona().intValue() == id_persona) {
					return administrativo;
				}
			}
		}

		return null;
	}

	public Administrativo obtenerAdministrativoPorCodigo(String codigo) {
		return DaoConfig.getDao(AdministrativoDao.class).obtenerAdministrativoPorCodigo(codigo);
	}

}
