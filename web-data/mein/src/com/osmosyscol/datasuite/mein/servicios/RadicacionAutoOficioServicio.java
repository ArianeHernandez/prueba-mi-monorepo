package com.osmosyscol.datasuite.mein.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.lang.P;
import co.htsoft.commons.util.SMessage;

import com.google.gson.Gson;
import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaGruposDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaIdentificacionTiposDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaMedioEnvioDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaSeguridadTipoDto;
import com.osmosyscol.datasuite.bpm.servicios.BpmServicios;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.RolServicio;
import com.osmosyscol.datasuite.mein.acciones.PublicarAutoOficioPostal;
import com.osmosyscol.datasuite.mein.builder.RadicacionAutoOficioFetchBuilder;
import com.osmosyscol.datasuite.mein.dtos.APVistaCuaderno;
import com.osmosyscol.datasuite.mein.dtos.APVistaDependencias;
import com.osmosyscol.datasuite.mein.dtos.APVistaDocumentoTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaSerie;
import com.osmosyscol.datasuite.mein.dtos.APVistaSubserie;
import com.osmosyscol.datasuite.mein.dtos.APVistaTipoAutoActa;
import com.osmosyscol.datasuite.mein.dtos.APVistaTramite;
import com.osmosyscol.datasuite.mein.dtos.EnrolamientoCliente;
import com.osmosyscol.datasuite.mein.dtos.GenerarStickerRequest;
import com.osmosyscol.datasuite.mein.dtos.GenerarStickerResponse;
import com.osmosyscol.datasuite.mein.dtos.GenerarStickerServicio;
import com.osmosyscol.datasuite.mein.dtos.JSONResponse;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.ResponseFirmaFuncionario;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.near.webdata.Postal_RadicarInterna;
import com.osmosyscol.datasuite.near.webdata.Postal_RadicarSalida;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class RadicacionAutoOficioServicio {
	private static RadicacionAutoOficioServicio instance = new RadicacionAutoOficioServicio();

	private RadicacionAutoOficioServicio() {
	}
	
	public static RadicacionAutoOficioServicio getInstance() {
		return instance;
	}

	public Mensaje crearSolicitud(RadicacionAutoOficio solicitud,
			Integer id_persona, Integer id_usuario, Integer id_proceso) {
		SimpleLogger.setInfo("SolicitudNearSociedad : ");
		P.println(solicitud);

		

		try {

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return new Mensaje(e.getCause().toString(), false);
		}

	}
	
	
	public List<APVistaTramite> obtenerApVistaTramite() {
		String query = "SELECT * FROM $APVISTA TRAMITE$";
		return DS_SqlUtils.queryForList(APVistaTramite.class, query);

	}
	
	public List<APVistaTramite> obtenerApVistaTramitePasante() {
		String query = "SELECT * FROM $APVISTA TRAMITE$ WHERE $APVISTA TRAMITE.VER PASANTE$ = $B(TRUE)$";
		return DS_SqlUtils.queryForList(APVistaTramite.class, query);
	}
	
	
	public List<APVistaCuaderno> obtenerApVistaCuaderno() {
		String query = "SELECT * FROM $APVISTA CUADERNO$";
		return DS_SqlUtils.queryForList(APVistaCuaderno.class, query);

	}
	
	public List<APVistaDependencias> obtenerApVistaDependencia() {
		String query = "SELECT * FROM $APVISTA DEPENDENCIAS$";
		return DS_SqlUtils.queryForList(APVistaDependencias.class, query);

	}
	
	public List<APVistaDocumentoTipo> obtenerApVistaDocumentoTipo() {
		String query = "SELECT * FROM $APVISTA DOCUMENTO TIPO$";
		return DS_SqlUtils.queryForList(APVistaDocumentoTipo.class, query);

	}

	public List<ApVistaGruposDto> obtenerApVistaGrupos() {
		String query = "SELECT * FROM $APVISTA GRUPOS$";
		return DS_SqlUtils.queryForList(ApVistaGruposDto.class, query);

	}
	
	public List<ApVistaIdentificacionTiposDto> obtenerApVistaIdentificacionTipos() {
		String query = "SELECT * FROM $APVISTA IDENTIF TIPOS$";
		return DS_SqlUtils.queryForList(ApVistaIdentificacionTiposDto.class, query);
	}
	
	public List<ApVistaMedioEnvioDto> obtenerApVistaMedioEnvio() {
		String query = "SELECT * FROM $APVISTA MEDIO ENVIO$";
		return DS_SqlUtils.queryForList(ApVistaMedioEnvioDto.class, query);
	}
	
	public List<ApVistaSeguridadTipoDto> obtenerApVistaSeguridadTipo() {
		String query = "SELECT * FROM $APVISTA SEGURIDAD TIPO$";
		return DS_SqlUtils.queryForList(ApVistaSeguridadTipoDto.class, query);
	}
	
	public List<APVistaSerie> obtenerApVistaSeriePasante() {
		String query = "SELECT * FROM $APVISTA SERIE$ WHERE $APVISTA SERIE.VER PASANTE$ = $B(TRUE)$";
		return DS_SqlUtils.queryForList(APVistaSerie.class, query);
	}
	
	public List<APVistaSerie> obtenerApVistaSerie() {
		String query = "SELECT * FROM $APVISTA SERIE$";
		return DS_SqlUtils.queryForList(APVistaSerie.class, query);
	}
	
	public List<APVistaSubserie> obtenerApVistaSubseriePasante() {
		String query = "SELECT * FROM $APVISTA SUBSERIE$ WHERE $APVISTA SUBSERIE.VER PASANTE$ = $B(TRUE)$";
		return DS_SqlUtils.queryForList(APVistaSubserie.class, query);
	}
	
	public List<APVistaSubserie> obtenerApVistaSuberie() {
		String query = "SELECT * FROM $APVISTA SUBSERIE$";
		return DS_SqlUtils.queryForList(APVistaSubserie.class, query);
	}
	
	public List<RadicacionAutoOficio> obtenerRadicacionAutoOficio() {
		String query = "SELECT * FROM $RADICACION AUTO OFICIO$";
		return DS_SqlUtils.queryForList(RadicacionAutoOficio.class, query);
	}
	
	public RadicacionAutoOficio obtenerRadicacionAutoOficio (Integer id_carga) {
		RadicacionAutoOficioFetchBuilder builder = RadicacionAutoOficioFetchBuilder.newInstance();
		RadicacionAutoOficio radicacion = builder
			.withIdCarga(id_carga)
			.fetchRadicacion()
			.fetchRadicacion_Particular()
			.fetchRadicacion_Particular_TipoIdentificacion()
			.fetchRadicacion_Particular_CiudadCodigo()
			.fetchRadicacion_Dependencia()
			.fetchRadicacion_DependenciaAsignada()
			.fetchRadicacion_CorresponsalDependencia()
			.fetchRadicacion_CodigoTipoSeguridad()
			.fetchRadicacion_CodigoMedioEnvio()
			.fetchRadicacion_DocumentalTipo()
			.fetchRadicacion_CuadernoTipo()
			.fetchRadicacion_Tramite()
			.fetchRadicacion_TipoAutoActa()
			.fetchRadicacion_Deudor()
			.fetchRadicacion_Deudor_TipoIdentificacion()
			.fetchRadicacion_Deudor_Pais()
			.fetchRadicacion_Deudor_Departamento()
			.fetchRadicacion_Deudor_Municipio()
			.fetchRadicacion_Serie()
			.fetchRadicacion_Subserie()
			.getRadicacion();
		
		return radicacion;
	}
	
	public RadicacionAutoOficio obtenerRadicacionAutoOficioBase (Integer id_carga) {
		RadicacionAutoOficioFetchBuilder builder = RadicacionAutoOficioFetchBuilder.newInstance();
		RadicacionAutoOficio radicacion = builder
			.withIdCarga(id_carga)
			.fetchRadicacion()
			.getRadicacion();
		return radicacion;
	}
	
	public Boolean guardarRadicacionAutoOficio(RadicacionAutoOficio solicitud ) {
		
		
		DaoManager daoManager = DaoConfig.getDaoManager(2);
		
		daoManager.startTransaction();

		try {
			
			Integer id_carga = solicitud.getIdcarga();
			
			if (Constantes.ESTRUCTURA_RESPUESTA_REQUERIMIENTO.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga))) {
				RespuestaRequerimiento rta = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoBase(id_carga);
				
				if (rta != null) {
					id_carga = rta.getNumero_solicitud();
				}
			}
			
			String queryObtenerDeudor = "select cli.* from $persona$ persona inner join $enrolamiento cliente$ cli on persona.$persona.identificacion$ = cli.$enrolamiento cliente.numero identificacion$ where persona.idcarga = "+id_carga+" and rownum < 2 ";
			EnrolamientoCliente deudor = DS_SqlUtils.queryForObject(EnrolamientoCliente.class, queryObtenerDeudor);
			
			solicitud.setDeudor(deudor);
			solicitud.setDeudorId(deudor.getId());
			
			Integer id_radicado_interno = null;
			
			Boolean exito = true;
			
			//obtener el deudor
			
			if(solicitud.getDeudorId() != null){
				
				id_radicado_interno = DS_SqlUtils.nextId();
				
				String insert_datos_representante = "INSERT INTO $RADICACION AUTO OFICIO$ ( " 
						 + "$RADICACION AUTO OFICIO.ANEXOSFISICOS$, "
						 + "$RADICACION AUTO OFICIO.DEUDOR$, "
						 + "$RADICACION AUTO OFICIO.ENTREGAFISICA$, "
						 + "$RADICACION AUTO OFICIO.EXTENSIONARCHIVO$, "
						 + "$RADICACION AUTO OFICIO.FOLIOSNUMERO$, "
						 + "$RADICACION AUTO OFICIO.LOGINUSUARIO$, "
						 + "$RADICACION AUTO OFICIO.FUNCIONARIOID$, "
						 + "$RADICACION AUTO OFICIO.FUNCIONARIOASIGNADOID$, "
						 + "$RADICACION AUTO OFICIO.REFERENCIAEXTERNA$, "
						 + "$RADICACION AUTO OFICIO.PARTICULAR$, "
						 + "$RADICACION AUTO OFICIO.RADICACIONANTERIOR$, "
						 + "$RADICACION AUTO OFICIO.TIPO RADICADO$, "
						 + "$RADICACION AUTO OFICIO.NUMERO RADICADO$, "
						 + "$RADICACION AUTO OFICIO.CORRESPONSALDEPENDENCIA$, "
						 + "$RADICACION AUTO OFICIO.DEPENDENCIA$, "
						 + "$RADICACION AUTO OFICIO.DEPENDENCIAASIGNADA$, "
						 + "$RADICACION AUTO OFICIO.CODIGOTIPOSEGURIDAD$, "
						 + "$RADICACION AUTO OFICIO.CODIGOMEDIOENVIO$, "
						 + "$RADICACION AUTO OFICIO.DOCUMENTALTIPO$, "
						 + "$RADICACION AUTO OFICIO.CUADERNOTIPO$, "
						 + "$RADICACION AUTO OFICIO.TRAMITE$, "
						 + "$RADICACION AUTO OFICIO.ASUNTO$, "
						 + "$RADICACION AUTO OFICIO.CUMPLIMIENTO$, "
						 + "$RADICACION AUTO OFICIO.TERMINO DIAS$, "
						 + "$RADICACION AUTO OFICIO.TIPO BORRADOR$, "
						 + "$RADICACION AUTO OFICIO.SUBSERIE$, "
						 + "$RADICACION AUTO OFICIO.SERIE$, "
						 + "ID , "
						 + "IDCARGA "
						 + ") "
						 + "VALUES "
						 + "( "
						 + solicitud.getAnexosFisicos()+", "
						 + solicitud.getDeudorId()+", "
						 + "#entregaFisica#"+", "
						 + "#extensionArchivo#"+", "
						 + solicitud.getFoliosNumero()+", "
						 + "#loginUsuario#"+", "
						 + solicitud.getFuncionarioAsignadoId()+", "
						 + solicitud.getFuncionarioAsignadoId()+", "
						 + "#referenciaExterna#"+", "
						 + solicitud.getParticularId()+", "
						 + "#radicacionAnterior#"+", "
						 + "#tipo_radicado#"+", "
						 + "#numeroRadicado#"+", "
						 + solicitud.getCorresponsalDependenciaId()+", "
						 + solicitud.getDependenciaId()+", "
						 + solicitud.getDependenciaAsignadaId()+", "
						 + solicitud.getCodigoTipoSeguridadId()+", "
						 + solicitud.getCodigoMedioEnvioId()+", "
						 + solicitud.getDocumentalTipoId()+", "
						 + solicitud.getCuadernoTipoId()+", "
						 + solicitud.getTramiteId()+", "
						 + "#asunto#"+", "
						 + "#cumplimiento#"+", "
						 + "#termino_dias#"+", "
						 + "#tipoBorrador#"+", "
						 + solicitud.getSubserieId()+", "
						 + solicitud.getSerieId()+", "
						 + id_radicado_interno + ", "
						 + solicitud.getIdcarga()
						 + ") ";
				
				exito = exito && DS_SqlUtils.insert(insert_datos_representante, solicitud);
				if (exito) {
					daoManager.commitTransaction();
				}
				return exito;
				
			} else {
				SimpleLogger.setError("RadicacionAutoServicio.guardarRadicacionAutoOficio: Error al guardar registro para la carga " + solicitud.getIdcarga() + " - No se encuentra deudor");
				return false;
			}


		} catch (Exception e) {
			SimpleLogger.setError("Error guardando datos", e);
			return false;
		}finally{
			daoManager.endTransaction();
		}
	}
	
	public Boolean actualizarRadicacionAutoOficio (RadicacionAutoOficio radicacion) {
		
		DaoManager daoManager = DaoConfig.getDaoManager(2);
		daoManager.startTransaction();
		
		try {
			
			String sql = "UPDATE $RADICACION AUTO OFICIO$ SET "
					+ "$RADICACION AUTO OFICIO.ANEXOSFISICOS$ = $anexosFisicos$, "
					 + "$RADICACION AUTO OFICIO.ENTREGAFISICA$ = #entregaFisica#, "
					 + "$RADICACION AUTO OFICIO.EXTENSIONARCHIVO$ = #extensionArchivo#, "
					 + "$RADICACION AUTO OFICIO.FOLIOSNUMERO$ = $foliosNumero$, "
					 + "$RADICACION AUTO OFICIO.LOGINUSUARIO$ = #loginUsuario#, "
					 + "$RADICACION AUTO OFICIO.REFERENCIAEXTERNA$ = #referenciaExterna#, "
					 + "$RADICACION AUTO OFICIO.RADICACIONANTERIOR$ = #radicacionAnterior#, "
					 + "$RADICACION AUTO OFICIO.TIPO RADICADO$ = #tipo_radicado#, "
					 + "$RADICACION AUTO OFICIO.NUMERO RADICADO$ = #numeroRadicado#, "
					 + "$RADICACION AUTO OFICIO.CODIGOTIPOSEGURIDAD$ = $codigoTipoSeguridadId$, "
					 + "$RADICACION AUTO OFICIO.CODIGOMEDIOENVIO$ = $codigoMedioEnvioId$, "
					 + "$RADICACION AUTO OFICIO.DOCUMENTALTIPO$ = $documentalTipoId$, "
					 + "$RADICACION AUTO OFICIO.CUADERNOTIPO$ = $cuadernoTipoId$, "
					 + "$RADICACION AUTO OFICIO.TRAMITE$ = $tramiteId$, "
					 + "$RADICACION AUTO OFICIO.ASUNTO$ = #asunto#, "
					 + "$RADICACION AUTO OFICIO.CUMPLIMIENTO$ = #cumplimiento#, "
					 + "$RADICACION AUTO OFICIO.TERMINO DIAS$ = #termino_dias#, "
					 + "$RADICACION AUTO OFICIO.TIPO BORRADOR$ = #tipoBorrador#, "
					 + "$RADICACION AUTO OFICIO.FUNCIONARIOASIGNADOID$ = #funcionarioAsignadoId#, "
					 + "$RADICACION AUTO OFICIO.TIPO AUTO ACTA$ = $tipoAutoActaId$, "
					 + "$RADICACION AUTO OFICIO.SERIE$ = $serieId$, "
					 + "$RADICACION AUTO OFICIO.SUBSERIE$ = $subserieId$, "
					 + "$RADICACION AUTO OFICIO.FECHA RADICADO$ = #fechaRadicado# "
					 + "WHERE IDCARGA = " + radicacion.getIdcarga();
			
			Boolean exito = DS_SqlUtils.update(sql, radicacion);
			
			if (exito) {
				daoManager.commitTransaction();
			}
			
			return exito;
			
		} catch (Exception e) {
			SimpleLogger.setError("Error actualizando radicacion auto oficio ", e);
			return false;
		} finally {
			daoManager.endTransaction();
		}
		
	}
	
	public Boolean actualizarDependencia (Integer id_carga, Integer tipo_radicado, Integer id_administrativo, Integer id_instancia) {
		try {
			Integer dependencia = null;
			if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga))) {
				dependencia = RegimenInsolvenciaServicio.getInstance().obtenerDependencia(id_carga);
			} else if (Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga))){
				dependencia = SolicitudNearSociedadServicio.getInstance().obtenerDependencia(id_carga);
			} else if (Constantes.ESTRUCTURA_RESPUESTA_REQUERIMIENTO.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga))) {
				dependencia = RespuestaRequerimientoServicio.getInstance().obtenerDependencia(id_carga);
			}
			
			
			if (dependencia != null) {
				Integer dependenciaDestino = null;
				Integer dependenciaAsignada = null;
				
				if (Constantes.TIPO_RADICADO_AUTO.equals(tipo_radicado)) {
					dependenciaDestino = obtenerCorresponsalDependencia(dependencia);
				} else {
					dependenciaDestino = dependencia;
				}
				
				if (id_administrativo != null && id_instancia != null) {
					dependenciaAsignada = obtenerDependenciaAsignada(id_instancia, id_administrativo);
				} 
				
				if (dependenciaAsignada == null){
					SimpleLogger.setError("DependenciaAsignadaError id_carga " + id_carga);
					return false;
				}
				
				String sql = "UPDATE $RADICACION AUTO OFICIO$ SET "
						+ " $RADICACION AUTO OFICIO.DEPENDENCIA$ = " + dependencia
						+ ", $RADICACION AUTO OFICIO.CORRESPONSALDEPENDENCIA$ = " + dependenciaDestino
						+ ", $RADICACION AUTO OFICIO.DEPENDENCIAASIGNADA$ = " + dependenciaAsignada
						+ " WHERE IDCARGA = " + id_carga;
				
				return DS_SqlUtils.update(sql);
			}
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error actualizando la dependencia para la solicitud " + id_carga, e);
		}
		
		return false;
	}

	public String obtenerRadicacionFirmaSticker (RadicacionAutoOficio radicacion, Session sesion) {
		JSONResponse response = new JSONResponse();
		Integer id_carga = radicacion.getIdcarga();
		try {
			String identificacion = (String)sesion.getAttribute("identificacion");
			Integer id_administrativo = (Integer)sesion.getAttribute("id_administrativo");
			Integer id_instancia = (Integer)sesion.getAttribute("instancia_actual_pasante");
			
			radicacion.setFuncionarioAsignadoId(identificacion);
			
			APVistaTipoAutoActa tipo_auto_acta = obtenerTipoAutoActa(radicacion);
			
			if (tipo_auto_acta != null) {
				radicacion.setTipoAutoActaId(tipo_auto_acta.getId());
			}
			
			Boolean success = actualizarRadicacionAutoOficio(radicacion);
			
			if (success) {
				
				success = actualizarDependencia(id_carga, radicacion.getTipo_radicado(), id_administrativo, id_instancia);
				
				if (success) {
					
					SMessage radicar = null;
					
					if (radicacion.getNumeroRadicado() != null) {
						radicar = new SMessage();
						radicar.setValid(true);
					} else if (Constantes.TIPO_RADICADO_AUTO.equals(radicacion.getTipo_radicado())) {
						radicar = new Postal_RadicarInterna().ejecutar(id_carga);
					} else {
						radicar = new Postal_RadicarSalida().ejecutar(id_carga);
					}
					
					if (radicar != null && radicar.getValid()) {
						
						ResponseFirmaFuncionario firma = BpmServicios.getInstance().obtenerImagenFirmaFuncionario(identificacion);
						String sticker = obtenerSticker(id_carga);
						radicacion = obtenerRadicacionAutoOficio(id_carga);
						
						if (firma != null && firma.getImagen() != null && sticker != null) {
							Map<String, Object> data = new HashMap<>();
							Map<String, String> images = new HashMap<>();
							Map<String, String> user = new HashMap<>();
							
							images.put("signature", firma.getImagen());
							images.put("sticker", sticker);
							
							user.put("id", firma.getCedulaUsuario());
							user.put("name", firma.getNombreCompleto());
							user.put("position", firma.getCargo());
							
							data.put("images", images);
							data.put("user", user);
							data.put("form", radicacion);
							
							response.setStatus(Constantes.JSON_RESPONSE_STATUS_SUCCESS);
							response.setData(data);
						} else if ((firma == null || (firma != null && firma.getImagen() == null)) && sticker != null) {
							response.setStatus(Constantes.JSON_RESPONSE_STATUS_FAILED);
							response.setMessage("Error en la generacion de la firma");
						} else if (firma != null && sticker == null) {
							response.setStatus(Constantes.JSON_RESPONSE_STATUS_FAILED);
							response.setMessage("Error en la generacion del sticker");
						} else {
							response.setStatus(Constantes.JSON_RESPONSE_STATUS_FAILED);
							response.setMessage("Error en la generacion del sticker y de la firma");
						}
						
					} else if (radicar != null && !radicar.getValid()) {
						response.setStatus(Constantes.JSON_RESPONSE_STATUS_FAILED);
						response.setMessage("Error en la radicacion -> " + radicar.getMsg());
					} else {
						response.setStatus(Constantes.JSON_RESPONSE_STATUS_FAILED);
						response.setMessage("Error en la radicacion del auto/oficio");
					}
					
				} else {
					response.setStatus(Constantes.JSON_RESPONSE_STATUS_FAILED);
					response.setMessage("Error en la actualizacion de la dependencia");
				}
				
			} else {
				response.setStatus(Constantes.JSON_RESPONSE_STATUS_FAILED);
				response.setMessage("Error en la actualizaci�n del formulario");
			}
			
			
			
		} catch (Exception e) {
			SimpleLogger.setError("Error durante el proceso para obtener Firma y Sticker para la solicitud " + id_carga, e);
			response.setStatus(Constantes.JSON_RESPONSE_STATUS_FAILED);
			response.setMessage("Ocurrio un error durante el proceso para obtener Firma y Sticker");
		}
		
		return new Gson().toJson(response);
	}
	
	public String obtenerSticker (Integer id_carga) {
		String sticker = null;
		String numero_proceso = null;
		String numero_radicado = null;
		
		RadicacionAutoOficio radicacion = obtenerRadicacionAutoOficioBase(id_carga);
		
		if (radicacion != null) {
			numero_radicado = radicacion.getNumeroRadicado();
		}
		
		if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga))) {
			RegimenInsolvencia solicitud = RegimenInsolvenciaServicio.getInstance().obtenerRegimenInsolvenciaBase(id_carga);
			
			if (solicitud != null) {
				numero_proceso = solicitud.getNumero_proceso();
			}
			
		} else if (Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga))){
			SolicitudNearSociedad solicitud = SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadPorCargaBase(id_carga);
			
			if (solicitud != null) {
				numero_proceso = solicitud.getNumero_proceso();
			}
		} else if (Constantes.ESTRUCTURA_RESPUESTA_REQUERIMIENTO.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga))) {
			RespuestaRequerimiento solicitud = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoBase(id_carga);
			
			if (solicitud != null) {
				numero_proceso = solicitud.getNumero_proceso();
			}
		}
		
		if (numero_radicado != null && numero_proceso != null) {
			GenerarStickerRequest request = new GenerarStickerRequest();
			request.setFormatoRequerido(Constantes.GENERACION_STICKER_FORMATO_REQUERIDO);
			request.setNumeroProceso(numero_proceso);
			request.setNumRadicado(numero_radicado);
			
			WSData integracion = GenerarStickerServicio.getInstance().generarSticker(request, id_carga);
			
			GenerarStickerResponse response = new Gson().fromJson(integracion.getResponse(), GenerarStickerResponse.class);
			
			if (response != null) {
				sticker = response.get_buffer();
			}
			
		}
		
		return sticker;
	}

	public String radicarDocumentoPostal (Integer id_carga) {
		JSONResponse response = new JSONResponse();
		
		try {
			
			SMessage radicar = new PublicarAutoOficioPostal().ejecutar(id_carga);
			
			if (radicar != null) {
				if (radicar.getValid()) {
					response.setStatus("success");
					response.setMessage("Ok");
				} else {
					response.setStatus("failed");
					response.setMessage(radicar.getMsg());
				}
			} else {
				response.setStatus("failed");
				response.setMessage("Error en la respuesta de radicacion del documento PDF a postal");
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al intentar radicar el documento PDF a postal para la solicitud" + id_carga, e);
			response.setStatus("failed");
			response.setMessage("Error al intentar radicar el documento PDF a postal");
		}
		
		return new Gson().toJson(response);
	}
	
	public Integer obtenerCorresponsalDependencia (Integer dependencia) {
	
		APVistaDependencias dependenciaObj = DS_SqlUtils.queryForObject(APVistaDependencias.class, 
				"SELECT * FROM $APVISTA DEPENDENCIAS$ WHERE ID = " + dependencia);
		
		Integer corresponsalDependencia = null;
		String depDestinoIntendencia = null;
		if (Constantes.DEPENDENCIA_GRUPO_ADMISIONES.equals(dependenciaObj.getCodigo().toString())) {
			depDestinoIntendencia = Constantes.DEPENDENCIA_ARCHIVO_APOYO_JUDICIAL;
		} else {
			depDestinoIntendencia = dependenciaObj.getCodigo() + "1";
		}
		String sql = "SELECT * FROM $APVISTA DEPENDENCIAS$ WHERE $APVISTA DEPENDENCIAS.CODIGO$ = $S("+ depDestinoIntendencia +")$";
		APVistaDependencias dependenciaCorresponsalObj = DS_SqlUtils.queryForObject(APVistaDependencias.class, sql);
		
		if (dependenciaCorresponsalObj != null) {
			corresponsalDependencia = dependenciaCorresponsalObj.getId();
		} else {
			corresponsalDependencia = dependencia;
		}
		
		return corresponsalDependencia;
	}
	
	public Integer obtenerDependenciaAsignada (Integer id_instancia, Integer id_administrativo) {
		
		List<Rol> roles = RolServicio.getInstance().obtenerRolesPorAdministrativoInstancia(id_administrativo, id_instancia);
		
		Integer dependenciaAsignada = null;
		if (roles != null && roles.size() > 0) {
			for (Rol rol: roles) {
				String sql = "SELECT $HOMOLOGA ROL DEPENDENCIA.DEPENDENCIA ASIGNADA$ FROM $HOMOLOGA ROL DEPENDENCIA$ WHERE $HOMOLOGA ROL DEPENDENCIA.ROL ADMINISTRATIVO$ = $I(" + rol.getId_rol() + ")$";
				dependenciaAsignada = DS_SqlUtils.queryForObject(Integer.class, sql);
				if (dependenciaAsignada != null) {
					break;
				}
			}
		}
		
		if (dependenciaAsignada == null) {
			SimpleLogger.setError("No se encuentra dependencia asignada para administrativo " + id_administrativo + " - instancia " + id_instancia);
		}
		
		return dependenciaAsignada;
		
	}
	
	public Boolean actualizarTipoArchivoDocumentoSalida (Integer id_carga) {
		
		try {
			RadicacionAutoOficio radicacion = obtenerRadicacionAutoOficioBase(id_carga);
			
			if (Constantes.TIPO_BORRADOR_ADMISION.equals(radicacion.getTipoBorrador())) {
				ArchivoAdjuntoServicio.getInstance().actualizarTipoArchivo(id_carga, Constantes.TIPO_ARCHIVO_PDF_AUTO_OFICIO, Constantes.TIPO_ARCHIVO_ADMISION);
			} else if (Constantes.TIPO_BORRADOR_RECHAZO.equals(radicacion.getTipoBorrador()) || Constantes.TIPO_BORRADOR_RECHAZO_TERMINO.equals(radicacion.getTipoBorrador())) {
				ArchivoAdjuntoServicio.getInstance().actualizarTipoArchivo(id_carga, Constantes.TIPO_ARCHIVO_PDF_AUTO_OFICIO, Constantes.TIPO_ARCHIVO_RECHAZO);
			} else if (Constantes.TIPO_BORRADOR_INADMISION.equals(radicacion.getTipoBorrador())) {
				ArchivoAdjuntoServicio.getInstance().actualizarTipoArchivo(id_carga, Constantes.TIPO_ARCHIVO_PDF_AUTO_OFICIO, Constantes.TIPO_ARCHIVO_INADMISION);
			}
			
			return true;
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al actualizar tipo de archivo para el auto oficio de la solicitud " + id_carga);
			return false;
		}
		
	}
	
	public APVistaTipoAutoActa obtenerTipoAutoActa(RadicacionAutoOficio radicacion) {
		
		try {
			
			String sql = "SELECT * FROM $APVISTA TIPO AUTO ACTA$ WHERE $APVISTA TIPO AUTO ACTA.TIPO BORRADOR$ = $I("+ radicacion.getTipoBorrador() +")$";
			APVistaTipoAutoActa tipoAutoActa = DS_SqlUtils.queryForObject(APVistaTipoAutoActa.class, sql);
			
			return tipoAutoActa;
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener el tipo auto acta de la solicitud " + radicacion.getIdcarga(), e);
			return null;
		}
		
	}
	
}
