package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.SourceResolver;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Accion;
import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.CargaAccionAutomatica;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.InstanciaAccion;
import com.osmosyscol.datasuite.logica.dto.Notificacion;
import com.osmosyscol.datasuite.logica.dto.NotificacionAccion;
import com.osmosyscol.datasuite.logica.dto.OperacionInterna;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.AdministrativoServicio;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.logica.servicios.NotificacionServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaDinamica;
import com.osmosyscol.datasuite.persistencia.dao.AccionDao;
import com.osmosyscol.datasuite.persistencia.dao.CargaInstanciaDao;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

import co.htsoft.commons.lang.StringUtils;
import co.htsoft.commons.util.SMessage;

public class AccionServicio {

	private static AccionServicio instancia;

	private AccionServicio() {
	}

	public static AccionServicio getInstance() {
		if (instancia == null) {
			instancia = new AccionServicio();
		}
		return instancia;
	}

	public Boolean insertarAccion(Integer id_accion, String nombre, Integer id_instancia) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.insertarAccion(id_accion, nombre, id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.insertarAccion", e);
			return false;
		}

	}

	public Integer obtenerSiguienteIdAccion() {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.obtenerSiguienteIdAccion();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.obtenerSiguienteIdAccion", e);
			return null;
		}
	}

	public Accion obtenerAccion(Integer id_accion) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.obtenerAccion(id_accion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.obtenerAccion", e);
			return null;
		}

	}

	public List<Accion> obtenerAccionesPorInstancia(Integer id_instancia) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.obtenerAccionesPorInstancia(id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.obtenerAccionesPorInstancia", e);
			return null;
		}

	}

	public Boolean borrarAccion(Integer id_accion) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.borrarAccion(id_accion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.borrarAccion", e);
			return false;
		}

	}

	public Boolean actualizarNombre(Integer id_accion, String nombre) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.actualizarNombre(id_accion, nombre);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.actualizarNombre", e);
			return false;
		}

	}

	public Boolean insertarInstaciaDestino(Integer id_accion, Integer id_instancia_destino) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.insertarInstaciaDestino(id_accion, id_instancia_destino);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.insertarInstaciaDestino", e);
			return false;
		}

	}

	public Instancia obtenerInstanciaDestino(Integer id_accion, Integer id_instancia_destino) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.obtenerInstanciaDestino(id_accion, id_instancia_destino);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.obtenerInstanciaDestino", e);
			return null;
		}

	}

	public List<Integer> obtenerIdInstanciasDestinoPorAccion(Integer id_accion) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.obtenerIdInstanciasDestinoPorAccion(id_accion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.obtenerInstanciasDestinoPorAccion", e);
			return null;
		}

	}
	
	public List<Instancia> obtenerInstanciasDestinoPorAccion(Integer id_accion) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.obtenerInstanciasDestinoPorAccion(id_accion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.obtenerInstanciasDestinoPorAccion", e);
			return null;
		}

	}

	public Boolean borrarInstanciaDestino(Integer id_accion, Integer id_instancia_destino) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.borrarInstanciaDestino(id_accion, id_instancia_destino);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.borrarInstanciaDestino", e);
			return false;
		}

	}

	public static SMessage ejecutarOperacionesInternas(Integer id_accion, final Integer id_carga) {
		List<OperacionInterna> operaciones = OperacionInternaAccionServicio.getInstance().obtenerOperacionesInternasPorAccion(id_accion);

		if (operaciones == null) {
			return new SMessage(true, "No hay operaciones.");
		}

		for (OperacionInterna op : operaciones) {
			try {
				@SuppressWarnings("rawtypes")
				Class c = Class.forName(op.getClase());
				final AccionCarga ac = (AccionCarga) c.newInstance();

				if (op.getSincrona().equals("S")) {
					SimpleLogger.setDebug("EJECUTANDO OPERACION INTERNA SINCRONA");

					try {
						SMessage r = ac.ejecutar(id_carga);
						
						if (!r.getValid()) {
							return new SMessage(false, r.getMsg());
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						return new SMessage(false, "Error inesperado al realizar la operacion");
					}
					

				} else {

					SimpleLogger.setDebug("EJECUTANDO OPERACION INTERNA ASINCRONA "  + op.getSincrona());

					new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								ac.ejecutar(id_carga);
							} catch (Exception e) {
								SimpleLogger.setError("Error ejecutando accionservicio", e);
								e.printStackTrace();
							}
						}
					}).start();
				}
			} catch (Exception e) {
				e.printStackTrace();
				SimpleLogger.setError("Error ejecutando accionservicio", e);
				return new SMessage(false, e.getMessage());
			}

		}
		return new SMessage(true,"ok");
	}

	public SMessage ejecutarAccionDeInstanciaActual(Integer id_accion, Integer id_carga, Integer id_instancia_actual, Integer id_administrativo) {
		try {
			
			SMessage r = ejecutarOperacionesInternas(id_accion, id_carga);
			if (!r.getValid()) {
				return new SMessage(false, r.getMsg());
			}
			
			Boolean esInstanciaActualFinalizada = false;
			Boolean esInstanciasDestinoCreadas = true;
			Boolean esAccionEjecutada = false;

			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);

			try {

				// iniciamos la transaccion
				daoManager.startTransaction();

				// Se actualiza la fecha final de la relacion Carga-Instancia
				esInstanciaActualFinalizada = cargaInstanciaDao.actualizarFechaSalidaRelacionCargaInstancia(id_carga, id_instancia_actual, id_administrativo);

				// Se crean las relaciones carga con las instancia destino
				// asociadas a la accion
				AccionServicio accionServicio = AccionServicio.getInstance();
				List<Instancia> instanciasDestino = accionServicio.obtenerInstanciasDestinoPorAccion(id_accion);

				if (instanciasDestino != null && instanciasDestino.size() > 0) {

					// Calculamos la misma hora del servidor para las nuevas
					// relaciones
					HorarioServicio horarioServicio = HorarioServicio.getInstance();
					Date fechallegada = horarioServicio.obtenerFechaActual();

					for (Instancia instancia : instanciasDestino) {
						Boolean relacionInsertada = false;

						// Se debe verificar si la relacion existe, si existe se
						// debe actualizar la fecha de llegada
						// de lo contrario se debe crear la relacion

						Boolean existeRelacion = cargaInstanciaDao.existeRelacionCargaInstanciaSinFechaSalida(id_carga, instancia.getId_instancia());

						if (existeRelacion) {
							// Si existe la relacion se actualiza la fecha de
							// llegada
							relacionInsertada = cargaInstanciaDao.actualizarFechaEntradaRelacionCargaInstancia(id_carga, instancia.getId_instancia(), fechallegada);

						} else {
							// Si la relacion no existe entonces se crea
							relacionInsertada = cargaInstanciaDao.insertarRelacionCargaInstancia(id_carga, instancia.getId_instancia(), fechallegada);

						}

						/*
						 * Si alguna de las relaciones no se pudo insertar o modificar debe cambiar el estado de del flag instanciasDestinoCreadas a falso
						 */
						if (!relacionInsertada) {
							esInstanciasDestinoCreadas = false;

						}
					}

					CargaServicio.getInstance().validarYAprobarAutomaticamente(id_carga, instanciasDestino);
					
				} else {
					esInstanciasDestinoCreadas = false;
					SimpleLogger.setWarn("No se encontraron instancias asociadas a la accion " + id_accion);
				}

				/*
				 * Si la instancia actual es finalizada y las nuevas instancias fueron creadas la accion fue ejecutada con exito
				 */

				if (esInstanciaActualFinalizada && esInstanciasDestinoCreadas) {
					esAccionEjecutada = true;
					daoManager.commitTransaction();
					SimpleLogger.setInfo("La accion se completo con exito");
				} else {
					SimpleLogger.setError("La accion no se completo con exito, Actualfinalizada: " + esInstanciaActualFinalizada + "; InstanciasDestinoCreadas: " + esInstanciasDestinoCreadas);
				}

			} catch (Exception e) {
				SimpleLogger.setError("Error en cargaInstanciaDao.ejecutarAccionDeInstanciaActual", e);
			} finally {
				daoManager.endTransaction();
			}

			return  new SMessage(esAccionEjecutada, "") ;

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.ejecutarAccion", e);
			return new SMessage(false, "Ha ocurrido un error en AccionServicio.ejecutarAccion " + e) ;

		}

	}

	public List<Instancia> obtenerInstDisponiblesParaAsignar(Integer id_proceso_admin, Integer id_accion) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.obtenerInstDisponiblesParaAsignar(id_proceso_admin, id_accion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.obtenerInstDisponiblesParaAsignar", e);
			return null;
		}

	}

	public List<Accion> obtenerAccionesPorProceso(Integer id_proceso_admin) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.obtenerAccionesPorProceso(id_proceso_admin);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.obtenerAccionesPorProceso", e);
			return null;
		}

	}

	public boolean enviarNotificacionesPorAccion(InstanciaAccion instanciaAccion, SourceResolver resolver) {

		if (instanciaAccion == null) {
			return false;
		}

		Boolean notificacionesEnviadas = true;

		try {
			// Consultamos todas la notificaciones por accion
			NotificacionAccionServicio notificacionAccionServicio = NotificacionAccionServicio.getInstance();
			List<NotificacionAccion> notificacionesPorAccion = notificacionAccionServicio.obtenerNotificacionesAccPorAccion(instanciaAccion.getId_accion());

			// Consultamos la informacion del administrativo emisor
			AdministrativoServicio administrativoServicio = AdministrativoServicio.getInstance();
			Administrativo administrativoEmisor = administrativoServicio.obtenerAdministrativoPorID(instanciaAccion.getId_administrativo());

			String nombre_emisor = "AutoBot";

			if (administrativoEmisor != null) {
				nombre_emisor = StringUtils.trimToEmpty(StringUtils.trimToEmpty(administrativoEmisor.getNombre()) + " " + StringUtils.trimToEmpty(administrativoEmisor.getApellido()));
			}

			// Consultamos la informacion del proceso
			ProcesoAdminServicio procesoAdminServicio = ProcesoAdminServicio.getInstance();
			ProcesoAdmin procesoAdmin = procesoAdminServicio.obtenerProcesoAdmin(instanciaAccion.getId_proceso_admin());

			// Fecha de notificacion
			HorarioServicio horarioServicio = HorarioServicio.getInstance();
			Date fechaNotificacion = horarioServicio.obtenerFechaActual();

			// Revisamos los tipos de notificacion
			if (notificacionesPorAccion != null && notificacionesPorAccion.size() > 0) {
				for (NotificacionAccion notificacionAccion : notificacionesPorAccion) {

					Notificacion notificacion = new Notificacion();
					notificacion.setContenido("Carga " + instanciaAccion.getId_carga() + " - Proceso: " + procesoAdmin.getNombre() + ": " + notificacionAccion.getMensaje());
					notificacion.setEmisor(nombre_emisor);
					notificacion.setFecha_envio(fechaNotificacion);
					notificacion.setTitulo("Nueva notificacion, " + "Carga " + instanciaAccion.getId_carga() + " - Proceso: " + procesoAdmin.getNombre());
					notificacion.setLeida(Constantes.NO);
					notificacion.setNueva(Constantes.SI);

					// Si la notificacion es para un administrativo en
					// particular
					if (notificacionAccion.getTipo().equals(Constantes.TIPO_NOTIFICACION_ADMINISTRATIVO)) {

						notificacion.setId_administrativo(notificacionAccion.getId_administrativo());

						Boolean respuesta = NotificacionServicio.getInstance().enviarYGuardarNotificacion(notificacion, resolver);

						if (!respuesta) {
							SimpleLogger.setError("No se pudo enviar la notificacion" + notificacion.getTitulo());
							notificacionesEnviadas = false;
							return false;

						} else {
							SimpleLogger.setInfo("Se envia la notificacion" + notificacion.getTitulo());

						}

					}
					// Si la notificacion es para las instancias destino de la
					// accion
					else if (notificacionAccion.getTipo().equals(Constantes.TIPO_NOTIFICACION_DESTINO)) {

						// Se consultan los adminitrativos de las instancias
						// siguientes asociadas a la accion
						List<Administrativo> administrativos = administrativoServicio.obtenerAdministrativosParaNotificacion(instanciaAccion.getId_accion(), instanciaAccion.getId_carga(), instanciaAccion.getId_instancia(), instanciaAccion.getId_proceso_admin());

						if (administrativos != null && administrativos.size() > 0) {

							// Se crean las notificaciones para cada uno de los
							// administrativos
							for (Administrativo administrativo : administrativos) {
								notificacion.setId_administrativo(administrativo.getId_administrativo());

								Boolean respuesta = NotificacionServicio.getInstance().enviarYGuardarNotificacion(notificacion, resolver);

								if (!respuesta) {
									SimpleLogger.setError("No se pudo enviar la notificacion" + notificacion.getTitulo());
									notificacionesEnviadas = false;
									return false;

								} else {
									SimpleLogger.setInfo("Se envia la notificacion" + notificacion.getTitulo());

								}
							}

						} else {
							SimpleLogger.setWarn("Hay notificaciones para enviar pero no hay administrativos receptores");
							notificacionesEnviadas = false;
						}

					}
					// Si la notificacion es para un correo especifico
					else if (notificacionAccion.getTipo().equals(Constantes.TIPO_NOTIFICACION_CORREO)) {
						SimpleLogger.setInfo("Enviando correo...");
						// TODO implementar
						String correo = notificacionAccion.getCorreo();
						NotificacionServicio.getInstance().enviarCorreoNotificacion(correo, notificacion, resolver);

					}
					// Si la Notificacion es para enviar un correo al liberador
					else if (notificacionAccion.getTipo().equals(Constantes.TIPO_NOTIFICACION_LIBERADOR)) {

						Integer id_liberador = PersonaServicio.getInstance().obtenerIdLiberadorCarga(instanciaAccion.getId_carga());
						if (id_liberador == null) {
							SimpleLogger.setWarn("La carga aun no ha sido liberada");
							return false;
						}
						Persona liberador = PersonaServicio.getInstance().obtenerPersona(id_liberador);

						SimpleLogger.setInfo("Enviando correo...");
						String correo = liberador.getCorreo();
						NotificacionServicio.getInstance().enviarCorreoNotificacion(correo, notificacion, resolver);
					}
					// Si la notificacion es para enviar un correo al asesor
					// comercial
					else if (notificacionAccion.getTipo().equals(Constantes.TIPO_NOTIFICACION_ASESOR_COMERCIAL)) {

						Carga carga = CargaServicio.getInstance().obtenerCarga(instanciaAccion.getId_carga());
						Integer id_usuario = carga.getId_usuario();
						Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(id_usuario);
						String codigo_comercial = usuario.getCodigo_comercial();
						if (codigo_comercial == null || codigo_comercial == "") {
							SimpleLogger.setWarn("No tiene asociado un asesor comercial");
							return false;
						}
						Administrativo administrativo = AdministrativoServicio.getInstance().obtenerAdministrativoPorCodigo(codigo_comercial);
						Integer id_persona = administrativo.getId_persona();
						Persona asesorComercial = PersonaServicio.getInstance().obtenerPersona(id_persona);

						notificacion.setId_administrativo(administrativo.getId_administrativo());
						Boolean respuesta = NotificacionServicio.getInstance().enviarYGuardarNotificacion(notificacion, resolver);

						if (!respuesta) {
							SimpleLogger.setError("No se pudo enviar la notificacion" + notificacion.getTitulo());
							notificacionesEnviadas = false;
							return false;

						} else {
							SimpleLogger.setInfo("Se envia la notificacion" + notificacion.getTitulo());

						}

						SimpleLogger.setInfo("Enviando correo...");
						String correo = asesorComercial.getCorreo();
						NotificacionServicio.getInstance().enviarCorreoNotificacion(correo, notificacion, resolver);
					}

				}
			} else {
				SimpleLogger.setWarn("No hay notificaciones para enviar.");
				notificacionesEnviadas = false;
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("Error en enviarNotificacionesPorAccion ", e);
			return false;
		}

		return notificacionesEnviadas;

	}

	/**
	 * Actualiza si la accion es oculta
	 * 
	 * @param id_accion
	 * @param oculto
	 * @return
	 */
	public Boolean actualizarOculto(Integer id_accion, String oculto) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.actualizarOculto(id_accion, oculto);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.actualizarNombre", e);
			return false;
		}

	}

	/**
	 * Obtiene las acciones de la instancia origen que tengan por lo menos una instancia destino
	 * 
	 */

	public List<Accion> obtenerAccionesConInstanciaDestinoPorInstanciaOrigen(Integer id_instancia_origen) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.obtenerAccionesConInstanciaDestinoPorInstanciaOrigen(id_instancia_origen);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.obtenerAccionesConInstanciaDestinoPorInstanciaOrigen", e);
			return null;
		}

	}

	public ListaDinamica obtenerAccionAutomatica(Integer id_accion) {
		AccionDao dao = DaoConfig.getDao(AccionDao.class);
		return dao.obtenerAccionAutomatica(id_accion);
	}

	public List<ListaDinamica> obtenerListasDinamicas() {
		AccionDao dao = DaoConfig.getDao(AccionDao.class);
		return dao.obtenerListasDinamicas();
	}

	public Boolean asignarListaDinamica(Integer id_accion, Integer id_lista_dinamica) {
		AccionDao dao = DaoConfig.getDao(AccionDao.class);
		return dao.asignarListaDinamica(id_accion, id_lista_dinamica);
	}

	public List<CargaAccionAutomatica> obtenerCargasAccionesAutomaticas() {
		AccionDao dao = DaoConfig.getDao(AccionDao.class);
		return dao.obtenerCargasEnAccionesAutomaticas();
	}
	
	public Boolean actualizarMensajeEjecucion(Integer id_accion, String mensaje_ejecucion) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);

			return accionDao.actualizarMensajeEjecucion(id_accion, mensaje_ejecucion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionServicio.actualizarMensajeEjecucion", e);
			return null;
		}

	}
	
	public Boolean ejecutarAccion (Integer id_carga, Integer id_instancia, Integer id_accion) {
		try {
			
			if (id_accion != null) {
				DaoManager daoManager = DaoConfig.getDaoManager();
				CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);

				try {

					daoManager.startTransaction();
					cargaInstanciaDao.actualizarFechaSalidaRelacionCargaInstancia(id_carga, id_instancia, null);

					List<Instancia> instanciasDestino = AccionServicio.getInstance().obtenerInstanciasDestinoPorAccion(id_accion);

					if (instanciasDestino != null && instanciasDestino.size() > 0) {

						Date fechallegada = HorarioServicio.getInstance().obtenerFechaActual();

						for (Instancia instancia : instanciasDestino) {

							Boolean existeRelacion = cargaInstanciaDao.existeRelacionCargaInstanciaSinFechaSalida(id_carga, instancia.getId_instancia());

							if (existeRelacion) {
								cargaInstanciaDao.actualizarFechaEntradaRelacionCargaInstancia(id_carga, instancia.getId_instancia(), fechallegada);

							} else {
								cargaInstanciaDao.insertarRelacionCargaInstancia(id_carga, instancia.getId_instancia(), fechallegada);
							}
						}

						CargaServicio.getInstance().validarYAprobarAutomaticamente(id_carga, instanciasDestino);
						
						SimpleLogger.setInfo("AccionServicio.ejecutarAccion: La solicitud " + id_carga + " ha ejecutado la accion " + id_accion);
						
					} else {
						SimpleLogger.setWarn("No se encontraron instancias asociadas a la accion " + id_accion + " solicitud " + id_carga);
					}

					daoManager.commitTransaction();

				} catch (Exception e) {
					SimpleLogger.setError("Error en AccionServicio.ejecutarAccion para la solicitud " 
								+ id_carga, e);
					return false;
				} finally {
					daoManager.endTransaction();
				}
			}  else {
				SimpleLogger.setInfo("AccionServicio.ejecutarAccion: No se encuentra accion para la solicitud " + id_carga);
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("AccionServicio.ejecutarAccion: Error ", e);
			return false;
		}
		return true;
	}
	
	public Map<Integer, List<Accion>> obtenerInstanciaAccionesPorProcesoAdmin (Integer id_proceso_admin) {
		
		try {
			
			Map<Integer, List<Accion>> accionesInstancia = new HashMap<Integer, List<Accion>>();
			
			List<Instancia> instancias = InstanciaServicio.getInstance().obtenerInstanciasPorProceso(id_proceso_admin);
			
			for(Instancia instancia: instancias) {
				List<Accion> acciones = obtenerAccionesPorInstancia(instancia.getId_instancia());
				
				accionesInstancia.put(instancia.getId_instancia(), acciones);
			}
			
			return accionesInstancia;
			
		} catch (Exception e) {
			SimpleLogger.setError("AccionServicio.obtenerInstanciaAccionesPorProcesoAdmin: Error ", e);
		}
		
		return null;
	}
	
	public Map<Integer, Accion> obtenerAccionInstanciasDestinoPorProcesoAdmin (Integer id_proceso_admin) {
		
		try {
			
			Map<Integer, Accion> accionesInstanciaDestino = new HashMap<Integer, Accion>();
			
			List<Accion> acciones = obtenerAccionesPorProceso(id_proceso_admin);
			
			for(Accion accion: acciones) {
				List<Integer> idInstanciasDestino = obtenerIdInstanciasDestinoPorAccion(accion.getId_accion());
				accion.setInstancias_destino(idInstanciasDestino);
				accionesInstanciaDestino.put(accion.getId_accion(), accion);
			}
			
			return accionesInstanciaDestino;
			
		} catch (Exception e) {
			SimpleLogger.setError("AccionServicio.obtenerAccionInstanciasDestinoPorProcesoAdmin: Error ", e);
		}
		
		return null;
	}
	
	public Boolean actualizarAccion (Accion accion) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AccionDao accionDao = (AccionDao) daoManager.getDao(AccionDao.class);
			return accionDao.actualizarAccion(accion);

		} catch (Exception e) {
			SimpleLogger.setError("AccionServicio.actualizarAccion: Error ", e);
			return false;
		}
	}
}