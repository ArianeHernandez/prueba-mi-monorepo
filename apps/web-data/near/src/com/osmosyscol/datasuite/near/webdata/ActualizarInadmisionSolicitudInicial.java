package com.osmosyscol.datasuite.near.webdata;

import java.util.Date;
import java.util.List;

import co.htsoft.commons.util.SMessage;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Accion;
import com.osmosyscol.datasuite.logica.dto.CargaInstancia;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.RespuestaRequerimientoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionResponsableServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CargaInstanciaServicio;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.persistencia.dao.CargaInstanciaDao;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ActualizarInadmisionSolicitudInicial implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {
		
		RespuestaRequerimiento solicitud = null;
		
		try {
			solicitud = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoBase(id_carga);
			
			if (solicitud == null) {
				SimpleLogger.setError("No se encuentra informacion de respuesta requerimiento para la carga " + id_carga);

				return new SMessage(false, "");
			} else {
				
				Integer solicitud_inicial = solicitud.getNumero_solicitud();
				
				if (!Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(solicitud_inicial))) {
					SimpleLogger.setInfo("La solicitud inicial" + solicitud_inicial + "de la respuesta " + id_carga + " no corresponde a una solicitud NEAR.");
					return new SMessage(true, "");
				} else if (!Constantes.TIPO_SOLICITANTE_SOCIEDAD.equals(solicitud.getTipo_solicitante())) {
					SimpleLogger.setInfo("La solicitud inicial" + solicitud_inicial + "de la respuesta " + id_carga + " es una solicitud NEAR para otro tipo de solicitante distinto a Sociedad.");
					return new SMessage(true, "");
				}
				
				List<CargaInstancia> carga_instancias = CargaInstanciaServicio.getInstance().obtenerCargaInstanciaActual(solicitud_inicial);
				
				if (carga_instancias != null && carga_instancias.size() > 0) {
					
					Integer id_instancia = null;
					
					for (CargaInstancia carga_instancia: carga_instancias) {
						if (carga_instancia.getFecha_salida() == null) {							
							id_instancia = carga_instancia.getId_instancia();
						}
					}
					
					
					List<Accion> acciones = AccionServicio.getInstance().obtenerAccionesPorInstancia(id_instancia);
										
					if (acciones != null && acciones.size() > 0) {
						
						Accion accion_inadmitir = null;
						
						for(Accion accion: acciones) {
							if ("inadmitir".equalsIgnoreCase(accion.getNombre())) {
								accion_inadmitir = accion;
								break;
							}
						}
						
						if (accion_inadmitir != null) {
							DaoManager daoManager = DaoConfig.getDaoManager();
							CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);

							try {

								daoManager.startTransaction();
								cargaInstanciaDao.actualizarFechaSalidaRelacionCargaInstancia(solicitud_inicial, id_instancia, null);

								List<Instancia> instanciasDestino = AccionServicio.getInstance().obtenerInstanciasDestinoPorAccion(accion_inadmitir.getId_accion());

								if (instanciasDestino != null && instanciasDestino.size() > 0) {

									Date fechallegada = HorarioServicio.getInstance().obtenerFechaActual();

									for (Instancia instancia : instanciasDestino) {

										Boolean existeRelacion = cargaInstanciaDao.existeRelacionCargaInstanciaSinFechaSalida(solicitud_inicial, instancia.getId_instancia());

										if (existeRelacion) {
											cargaInstanciaDao.actualizarFechaEntradaRelacionCargaInstancia(solicitud_inicial, instancia.getId_instancia(), fechallegada);

										} else {
											cargaInstanciaDao.insertarRelacionCargaInstancia(solicitud_inicial, instancia.getId_instancia(), fechallegada);

										}

									}

									CargaServicio.getInstance().validarYAprobarAutomaticamente(solicitud_inicial, instanciasDestino);
									
									SimpleLogger.setInfo("La solicitud inicial " + solicitud_inicial + " ha sido inadmitida.");
									
								} else {
									SimpleLogger.setWarn("No se encontraron instancias asociadas a la accion " + accion_inadmitir.getId_accion() + " solicitud inicial" + solicitud_inicial);
								}

								daoManager.commitTransaction();


							} catch (Exception e) {
								SimpleLogger.setError("Error en ActualizarInadmisionSolicitudInicial para la solicitud " 
											+ id_carga + ", solicitud inicial " + solicitud_inicial, e);
								return new SMessage(false, "");
								
							} finally {
								daoManager.endTransaction();
							}

						}  else {
							SimpleLogger.setInfo("No se encuentra accion Inadmitir para la solicitud inicial " + solicitud_inicial + ", respuesta inadmision " + id_carga);
						}
						
						
						return new SMessage(true, "Ok");
						
					} else {
						SimpleLogger.setError("No se encontraron acciones asociadas "
								+ "a la solicitud padre " + solicitud_inicial + " de la respuesta con id " + id_carga);
						
						return new SMessage(true, "");
					}
				} else {
					SimpleLogger.setError("La solicitud inicial " + solicitud_inicial + " de la respuesta " + id_carga + " no se encuentra en una instancia pendiente.");

					return new SMessage(true, "");
				}
				
			}			
			
		} catch ( Exception e) {
			SimpleLogger.setError("Error en la asociacion de ponente solicitud " + id_carga, e);

			return new SMessage(false, "");
		}
		
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
