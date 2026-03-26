package com.osmosyscol.datasuite.mein.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.lang.P;
import co.htsoft.commons.lang.StringUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.dto.NotificacionSociedad;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.mein.builder.SolicitudFetchBuilder;
import com.osmosyscol.datasuite.mein.dtos.Acreedor;
import com.osmosyscol.datasuite.mein.dtos.CertificacionContador;
import com.osmosyscol.datasuite.mein.dtos.CertificacionRepLegal;
import com.osmosyscol.datasuite.mein.dtos.CertificacionRevisorFis;
import com.osmosyscol.datasuite.mein.dtos.Checklist;
import com.osmosyscol.datasuite.mein.dtos.Credito;
import com.osmosyscol.datasuite.mein.dtos.DepartamentoCiudad;
import com.osmosyscol.datasuite.mein.dtos.DocumentoAdjunto;
import com.osmosyscol.datasuite.mein.dtos.Garantia;
import com.osmosyscol.datasuite.mein.dtos.InformacionFinanciera;
import com.osmosyscol.datasuite.mein.dtos.Pasivo;
import com.osmosyscol.datasuite.mein.dtos.PersonaMein;
import com.osmosyscol.datasuite.mein.dtos.ProcesoEjecutivo;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.RelacionPasivos;
import com.osmosyscol.datasuite.mein.dtos.RepartoIntendencias;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.ResponsePasante;
import com.osmosyscol.datasuite.mein.dtos.Sociedad;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedadDto;
import com.osmosyscol.datasuite.mein.dtos.TipoSolicitud;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.rest.client.PasanteAppRestClient;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.utils.dummys.DummySession;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.ValorListaDinamicaCampo;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import edu.emory.mathcs.backport.java.util.Collections;

public class SolicitudNearSociedadServicio {

	private static SolicitudNearSociedadServicio instance = new SolicitudNearSociedadServicio();

	private SolicitudNearSociedadServicio() {
	}

	public Mensaje crearSolicitud(SolicitudNearSociedad solicitud,
			Integer id_persona, Integer id_usuario, Integer id_proceso) {
		SimpleLogger.setInfo("SolicitudNearSociedad : ");
		P.println(solicitud);
		Integer id_carga = solicitud.getIdcarga();
		
		String mensaje_respuesta = "La solicitud "
				+ id_carga
				+ " se cre� exitosamente, el sistema enviar� un correo electr�nico notificando cuando la solicitud haya sido revisada y aprobada por el Contador o Revisor Fiscal.";
		
		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(
				id_usuario);

		PersonaMein datos_basicos = solicitud.getDeudor().getDatos_basicos() == null? new PersonaMein(): solicitud.getDeudor().getDatos_basicos();
		datos_basicos.setId_persona_mein(usuario.getId_persona());
		solicitud.getDeudor().setDatos_basicos(datos_basicos);

		try {

			Integer id_negocio = Constantes.ID_NEGOCIO_SOLICITUD_NEAR;
			
			if (guardarSolicitudNearSociedad(solicitud, id_carga)) {

				Session session = new DummySession();

				CargaServicio.getInstance().realizarCarga(id_carga, id_persona,
						id_negocio, session, null);

				Carga carga = CargaServicio.getInstance()
						.obtenerCarga(id_carga);

				List<ValorListaDinamicaCampo> listaValoresDinamicos = new ArrayList<ValorListaDinamicaCampo>();

				CargaServicio.getInstance().guardarValoresLista(
						listaValoresDinamicos, carga.getId_formato(), true);
				CargaServicio.getInstance().finalizarCarga(carga);
				// enviar correo
				carga = CargaServicio.getInstance()
						.obtenerCarga(id_carga);
				try {
					
					if (com.osmosyscol.datasuite.logica.constantes.Constantes.CARGA_ESTADO_REVISION.equals(carga.getEstado())) {
						Integer id_persona_seleccionada = 0;
						if (solicitud.getDeudor().getRepresentante_legal() != null) {
							id_persona_seleccionada = solicitud.getDeudor()
									.getRepresentante_legal().getId_persona_mein();
						} else if (solicitud.getDeudor().getDatos_basicos() != null) {
							id_persona_seleccionada = solicitud.getDeudor()
									.getDatos_basicos().getId_persona_mein();
						}
						
						Persona dst_persona = PersonaServicio.getInstance()
								.obtenerPersona(id_persona_seleccionada);
						
						NotificacionSociedad notificacion = new NotificacionSociedad();
						notificacion
						.setTitulo("Certificaciones del Contador y/o Revisor fiscal");
						notificacion.setNombre(dst_persona.getNombreCompleto());
						notificacion.setProceso("Solicitud de insolvencia");
						Map<String, String> parametros = JavaToXML
								.objectToParameters("notificacion", notificacion);
						Map<String, String> archivos = new HashMap<String, String>();
						archivos.put("#logo#", "images/back/logo1.png");
						//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
						
						EnviaMails.enviar(dst_persona.getCorreo(),
								dst_persona.getNombreCompleto(),
								"Notificación Recibido",
								"notificacion/envioNotificacionSolicitud.email",
								archivos, parametros);
						
					}
					
				} catch (Exception emaile) {
					emaile.printStackTrace();
					return new Mensaje(
							"OK",
							mensaje_respuesta,
							true, id_carga);
				}
				
				if (com.osmosyscol.datasuite.logica.constantes.Constantes.CARGA_ESTADO_REVISION.equals(carga.getEstado())) {
					Persona dst_persona = PersonaServicio.getInstance()
							.obtenerPersona(
									solicitud.getDeudor().getRepresentante_legal()
									.getId_persona_mein());
					
					NotificacionSociedad notificacion = new NotificacionSociedad();
					notificacion
					.setTitulo("Certificaciones del Contador y/o Revisor fiscal");
					notificacion.setNombre(dst_persona.getNombreCompleto());
					notificacion
					.setProceso("Negociación de Emergencia de Acuerdos de Reorganización (Artículo 8 Decreto Legislativo 560 de 2020)");
					Map<String, String> parametros = JavaToXML.objectToParameters(
							"notificacion", notificacion);
					Map<String, String> archivos = new HashMap<String, String>();
					archivos.put("#logo#", "images/back/logo1.png");
					archivos.put("#logo2#", "images/back/logo2.png");
					//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
					try {
						EnviaMails.enviar(dst_persona.getCorreo(),
								dst_persona.getNombreCompleto(),
								"Notificación Recibido",
								"notificacion/envioNotificacionSolicitud.email",
								archivos, parametros);
						
					} catch (Exception errorEmail) {
						errorEmail.printStackTrace();
					}
				}
				

				return new Mensaje(
						"OK",
						mensaje_respuesta,
						true, id_carga);
			} else {
				return new Mensaje("Ocurri� un error al crear la solicitud "
						+ id_carga, false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Mensaje(e.getCause().toString(), false);
		}

	}

	public Mensaje crearSolicitud(SolicitudNearSociedad solicitud,
			Integer id_persona, Integer id_usuario, Integer id_proceso,
			Integer id_carga) {
		SimpleLogger.setInfo("SolicitudNearSociedad : ");
		P.println(solicitud);

		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(
				id_usuario);

		PersonaMein datos_basicos = solicitud.getDeudor().getDatos_basicos() == null? new PersonaMein(): solicitud.getDeudor().getDatos_basicos();
		datos_basicos.setId_persona_mein(usuario.getId_persona());
		solicitud.getDeudor().setDatos_basicos(datos_basicos);

		try {

			Integer id_negocio = Constantes.ID_NEGOCIO_SOLICITUD_NEAR;

			if (actualizarSolicitudNearSociedad(solicitud, id_carga)) {

				Session session = new DummySession();

				CargaServicio.getInstance().realizarCarga(id_carga, id_persona,
						id_negocio, session, null, false);

				Carga carga = CargaServicio.getInstance()
						.obtenerCarga(id_carga);

				List<ValorListaDinamicaCampo> listaValoresDinamicos = new ArrayList<ValorListaDinamicaCampo>();

				CargaServicio.getInstance().guardarValoresLista(
						listaValoresDinamicos, carga.getId_formato(), true);
				CargaServicio.getInstance().finalizarCarga(carga);
				// enviar correo

				carga = CargaServicio.getInstance()
						.obtenerCarga(id_carga);
				
				if (com.osmosyscol.datasuite.logica.constantes.Constantes.CARGA_ESTADO_REVISION.equals(carga.getEstado())) {					
					Persona dst_persona = PersonaServicio.getInstance()
							.obtenerPersona(
									solicitud.getDeudor().getRepresentante_legal()
									.getId_persona_mein());
					
					NotificacionSociedad notificacion = new NotificacionSociedad();
					notificacion
					.setTitulo("Certificaciones del Contador y/o Revisor fiscal");
					notificacion.setNombre(dst_persona.getNombreCompleto());
					notificacion
					.setProceso("Negociación de Emergencia de Acuerdos de Reorganización (Artículo 8 Decreto Legislativo 560 de 2020)");
					Map<String, String> parametros = JavaToXML.objectToParameters(
							"notificacion", notificacion);
					Map<String, String> archivos = new HashMap<String, String>();
					archivos.put("#logo#", "images/back/logo1.png");
					archivos.put("#logo2#", "images/back/logo2.png");
					//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
					try {
						EnviaMails.enviar(dst_persona.getCorreo(),
								dst_persona.getNombreCompleto(),
								"Notificación Recibido",
								"notificacion/envioNotificacionSolicitud.email",
								archivos, parametros);
						
					} catch (Exception errorEmail) {
						errorEmail.printStackTrace();
					}
				}
				

				return new Mensaje(
						"OK",
						"La solicitud "
								+ id_carga
								+ " se cre� exitosamente, el sistema enviar� un correo electr�nico notificando cuando la solicitud haya sido revisada y aprobada por el Contador o Revisor Fiscal.",
						true, id_carga);
			} else {
				return new Mensaje("Ocurri� un error al crear la solicitud "
						+ id_carga, false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Mensaje(e.getCause().toString(), false);
		}

	}

	public Mensaje guardarSolicitud(SolicitudNearSociedad solicitud,
			Integer id_persona, Integer id_usuario, Integer id_proceso) {
		SimpleLogger.setInfo("SolicitudNearSociedad : ");
		P.println(solicitud);

		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(
				id_usuario);

		PersonaMein datos_basicos = new PersonaMein();
		datos_basicos.setId_persona_mein(usuario.getId_persona());
		solicitud.getDeudor().setDatos_basicos(datos_basicos);

		try {

			Integer id_negocio = Constantes.ID_NEGOCIO_SOLICITUD_NEAR;

			String ip_usuario = "127.0.0.1";

			Integer id_carga = CargaServicio.getInstance()
					.crearCargaInteractiva("Solicitud NEAR", id_persona,
							Constantes.ID_FORMATO_SOLICITUD_NEAR, id_proceso,
							usuario.getId_usuario(), id_negocio, ip_usuario);

			if (guardarSolicitudNearSociedad(solicitud, id_carga)) {
				// guardar borrador

				Session session = new DummySession();

				CargaServicio.getInstance().realizarCarga(id_carga, id_persona,
						id_negocio, session, null, true);

				Carga carga = CargaServicio.getInstance()
						.obtenerCarga(id_carga);
				P.println(carga);

				/*
				 * List<ValorListaDinamicaCampo> listaValoresDinamicos = new
				 * ArrayList<ValorListaDinamicaCampo>();
				 * 
				 * CargaServicio.getInstance().guardarValoresLista(
				 * listaValoresDinamicos, carga.getId_formato(), true);
				 * CargaServicio.getInstance().finalizarCarga(carga);
				 */

				return new Mensaje(
						"OK",
						"La solicitud "
								+ id_carga
								+ " se cre� exitosamente, el sistema enviar� un correo electr�nico notificando cuando la solicitud haya sido revisada y aprobada por el Contador o Revisor Fiscal.",
						true, id_carga);
			} else {
				return new Mensaje("Ocurri� un error al crear la solicitud "
						+ id_carga, false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Mensaje(e.getCause().toString(), false);
		}

	}

	public Mensaje guardarSolicitud(SolicitudNearSociedad solicitud,
			Integer id_persona, Integer id_usuario, Integer id_proceso,
			Integer id_carga) {
		SimpleLogger.setInfo("SolicitudNearSociedad : ");
		P.println(solicitud);

		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(
				id_usuario);

		PersonaMein datos_basicos = new PersonaMein();
		datos_basicos.setId_persona_mein(usuario.getId_persona());
		solicitud.getDeudor().setDatos_basicos(datos_basicos);

		try {

			Integer id_negocio = Constantes.ID_NEGOCIO_SOLICITUD_NEAR;

			//String ip_usuario = "127.0.0.1";
			// todo borrar todos los datos

			if (actualizarSolicitudNearSociedad(solicitud, id_carga)) {
				// guardar borrador

				Session session = new DummySession();

				CargaServicio.getInstance().realizarCarga(id_carga, id_persona,
						id_negocio, session, null, true);

				Carga carga = CargaServicio.getInstance()
						.obtenerCarga(id_carga);
				P.println(carga);

				/*
				 * List<ValorListaDinamicaCampo> listaValoresDinamicos = new
				 * ArrayList<ValorListaDinamicaCampo>();
				 * 
				 * CargaServicio.getInstance().guardarValoresLista(
				 * listaValoresDinamicos, carga.getId_formato(), true);
				 * CargaServicio.getInstance().finalizarCarga(carga);
				 */

				return new Mensaje(
						"OK",
						"La solicitud "
								+ id_carga
								+ " se cre� exitosamente, el sistema enviar� un correo electr�nico notificando cuando la solicitud haya sido revisada y aprobada por el Contador o Revisor Fiscal.",
						true, id_carga);
			} else {
				return new Mensaje("Ocurri� un error al crear la solicitud "
						+ id_carga, false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Mensaje(e.getCause().toString(), false);
		}

	}

//	public Mensaje crearPdf(Integer id_carga) {
//		try {
//			PdfRadicacionServicio.getInstance().generarPdfPorIdCarga(id_carga);
//			return new Mensaje("OK", "El pdf de la solicitud " + id_carga
//					+ " se cre� exitosamente", true, id_carga);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Mensaje(e.getCause().toString(), false);
//		}
//	}

	public Mensaje EnviarInfoPasante(Integer id_carga) {
		try {
			PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();

			// Integer idCarga = 110889;

			ResponsePasante respuesta = pasante
					.obtenerDatosRegistro(id_carga);
			if (respuesta == null){
				return new Mensaje("OK", "La informaci�n con id " + id_carga
						+ " no fue enviada", true, id_carga);
			}

			return new Mensaje("OK", "La informaci�n con id " + id_carga
					+ " se envi� exitosamente", true, id_carga);
		} catch (Exception e) {
			e.printStackTrace();
			return new Mensaje(e.getCause().toString(), false);
		}
	}
	
	public Mensaje ConsultaMiPasante(Integer id_carga) {
		try {
			PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();

			// Integer idCarga = 110889;

			SolicitudNearSociedadDto respuesta = pasante
					.ConsultaMiPasante(id_carga);
			if (respuesta == null){
				return new Mensaje("OK", "La informaci�n con id " + id_carga
						+ " no fue enviada", true, id_carga);
			}

			return new Mensaje("OK", "La informaci�n con id " + id_carga
					+ " se envi� exitosamente", true, respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return new Mensaje(e.getCause().toString(), false);
		}
	}

	
	public String obtenerInfoPasante(Integer id_carga) {
		try {

			PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();

			String respuesta = pasante.obtenterSolicitudPorId(id_carga
					.toString());

			return respuesta;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String comunicacionPasante(String url, String method, String body){
		try{
		
			PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
			
			String respuesta = pasante.comunicacionPasante(url, method, body);
	
			return respuesta;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public static SolicitudNearSociedadServicio getInstance() {
		return instance;
	}

	public SolicitudNearSociedad obtenerSolicitudNearSociedadBorrador(
			Integer id_usuario) {
		try {
			String sql = "SELECT  *  FROM dsv_carga where ESTADO = 'B' AND ID_CARGA = ( select max(ID_CARGA) from dsv_carga ) AND ID_USUARIO="
					+ id_usuario;

			Integer id_carga = DS_SqlUtils.queryForObject(Integer.class, sql);

			String sql_solicitud = "SELECT * "
					+ "FROM $SOLICITUD NEAR SOCIEDAD$ WHERE IDCARGA="
					+ id_carga;

			return DS_SqlUtils.queryForObject(SolicitudNearSociedad.class,
					sql_solicitud);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Hace consulta de solicitud por id carga.
	 * 
	 * @param idCarga
	 * @return
	 */
	public SolicitudNearSociedad obtenerSolicitudNearSociedadPorCarga(
			Integer id_carga) {
		SolicitudFetchBuilder builder = SolicitudFetchBuilder.newInstance();
		SolicitudNearSociedad solicitud = builder.withIdCarga(id_carga)
				.fetchSolicitud().fetchSolicitud_TipoSolicitud()
				.fetchSolicitud_TipoSolicitante()
				.fetchSolicitud_TipoSolicitud_GrupoNIIF()
				.fetchSolicitud_TipoSolicitud_TipoSolicitud()
				.fetchSolicitud_Deudor().fetchSolicitud_Deudor_DatosBasicos()
				.fetchSolicitud_Deudor_RepresentanteLegal()
				.fetchSolicitud_Deudor_Contador()
				.fetchSolicitud_Deudor_RevisorFiscal()
				.fetchSolicitud_Deudor_Apoderado()
//				.fetchSolicitud_Deudor_DeptoCiudad()
				.fetchSolicitud_Deudor_DeptoDane()
				.fetchSolicitud_Deudor_MunicipioDane()
				.fetchSolicitud_Deudor_PaisDane()
				.fetchSolicitud_Deudor_ReplegalLimitacion()
				.fetchSolicitud_PasivosRetenciones()
				.fetchSolicitud_RelacionPasivos_Retenciones()
				.fetchSolicitud_RelacionPasivos_Descuentos()
				.fetchSolicitud_RelacionPasivos_Aportes()
				.fetchSolicitud_InformacionFinanciera()
				.fetchSolicitud_InfoFinanciera_TieneInversiones()
				.fetchSolicitud_Deudor_Macrosector()
				.fetchSolicitud_Deudor_MacrosectorAPVista()
				.fetchSolicitud_Deudor_Naturaleza()
//				.fetchSolicitud_PorcentajeParticipacion()
				.fetchSolicitud_SituacionPresentada()
				.fetchSolicitud_AdelAcreedores()
				.fetchSolicitud_Checklist()
				.fetchSolicitud_Checklist_RemisionPrevia()
				.fetchSolicitud_Checklist_SociedadDisolucion()
				.fetchSolicitud_Checklist_SociedadPasivosPensionales()
				.fetchSolicitud_Checklist_PasivosPensionales()
				.fetchSolicitud_Checklist_SociedadGaranteCodeudor()
				.fetchSolicitud_Checklist_Garantias()
				.fetchSolicitud_Checklist_Controlante()
				.fetchSolicitud_intendencia_regional()
				.fetchSolicitud_Dependencia()
				.fetchSolicitud_MedioEnvio()
				.fetchSolicitud_Cuaderno()
				.fetchSolicitud_TipoSeguridad()
				.fetchSolicitud_Tramite()
				.fetchSolicitud_ProcesosClases()
				.fetchSolicitud_TipoAutoActa()
				.fetchSolicitud_Serie()
				.fetchSolicitud_Subserie()
				.getSolicitud();

		return solicitud;
	}
	
	public SolicitudNearSociedad obtenerSolicitudNearSociedadPorCargaBase (Integer id_carga) {
		SolicitudFetchBuilder builder = SolicitudFetchBuilder.newInstance();
		SolicitudNearSociedad solicitud = builder.withIdCarga(id_carga)
				.fetchSolicitud()
				.getSolicitud();

		return solicitud;

	}

	public SolicitudNearSociedad obtenerDatosCargaPorId(Integer id_carga) {
		P.println(id_carga);
		SolicitudNearSociedad solicitud = obtenerSolicitudNearSociedadPorCarga(id_carga);
		return solicitud;
	}

	public List<ArchivoAdjunto> obtenerArchivosCargaPorId(Integer id_carga) {
		P.println(id_carga);
		List<ArchivoAdjunto> adjuntos = ArchivoAdjuntoServicio.getInstance()
				.obtenerArchivosAdjuntosPorCarga(id_carga, null, null);
		return adjuntos;
	}

	public boolean guardarSolicitudNearSociedad(
			SolicitudNearSociedad solicitud, Integer id_carga) {

		DaoManager daoManager = DaoConfig.getDaoManager(2);
		daoManager.startTransaction();

		Boolean exito = true;

		try {

			Integer id_deudor = null;
			Integer id_informacion_financiera = null;
			Integer id_certificacion_replegal = null;
			Integer id_relacion_pasivos = null;
			Integer id_proceso_ejecutivo = null;
			Integer id_certificacion_revisor_fis = null;
			Integer id_certificacion_contador = null;
			Integer id_acreedor = null;
			Integer id_checklist = null;
			Integer id_tipoSolicitud = null;

			if (solicitud.getInformacion_financiera() != null) {
				id_informacion_financiera = DS_SqlUtils.nextId();

				Boolean guardar_info = guardarInformacionFinanciera(
						id_informacion_financiera, id_carga,
						solicitud.getInformacion_financiera());

				if (guardar_info
						&& solicitud.getInformacion_financiera()
								.getValor_activos() != null) {
					String categoria = CalculoCategoriaTamanoServicio
							.getInstance().calcularCatEmpresa(
									solicitud.getInformacion_financiera()
											.getValor_activos(), solicitud.getTipo_solicitud().getTipo_solicitud());

					if (!categoria.equals("") && solicitud.getDeudor() != null) {
						solicitud.getDeudor().setCategoria(categoria);
					}
				}

				BigDecimal ingresos_calculo_tam = new BigDecimal(0);
				if (solicitud.getInformacion_financiera()
						.getTotal_ingresos_ordinarios() != null
						&& solicitud.getInformacion_financiera()
								.getTotal_otros_ingresos() != null
						&& Constantes.MI_CAMPO_SI.equals(solicitud.getInformacion_financiera()
								.getTiene_inversiones())) {
					ingresos_calculo_tam = ingresos_calculo_tam
							.add(solicitud.getInformacion_financiera()
									.getTotal_ingresos_ordinarios())
							.add(solicitud.getInformacion_financiera()
									.getTotal_otros_ingresos())
							.add(solicitud.getInformacion_financiera()
									.getValor_p_participacion())
							.add(solicitud.getInformacion_financiera()
									.getValor_p_costo())
							.add(solicitud.getInformacion_financiera()
									.getValor_p_razonable());

				} else if (solicitud.getInformacion_financiera()
						.getTotal_ingresos_ordinarios() != null
						&& solicitud.getInformacion_financiera()
								.getTotal_otros_ingresos() != null) {
					ingresos_calculo_tam = ingresos_calculo_tam.add(
							solicitud.getInformacion_financiera()
									.getTotal_ingresos_ordinarios()).add(
							solicitud.getInformacion_financiera()
									.getTotal_otros_ingresos());
				}

				if (solicitud.getDeudor() != null
						&& solicitud.getDeudor().getMacrosector() != null) {
					Integer tam = CalculoCategoriaTamanoServicio.getInstance()
							.calcularTamEmpresa(ingresos_calculo_tam,
									solicitud.getDeudor().getMacrosector());

					solicitud.getDeudor().setTamano_empresa(tam);
				}

				exito = exito && guardar_info;
			}

			if (solicitud.getDeudor() != null) {
				id_deudor = DS_SqlUtils.nextId();
				exito = exito
						&& guardarSociedad(id_deudor, id_carga,
								solicitud.getDeudor());
			}

			if (solicitud.getCertificacion_representan() != null) {
				id_certificacion_replegal = DS_SqlUtils.nextId();
				exito = exito
						&& guardarCertificacionRepLegal(
								id_certificacion_replegal, id_carga,
								solicitud.getCertificacion_representan());
			}

			if (solicitud.getRelacion_de_pasivos() != null) {
				id_relacion_pasivos = DS_SqlUtils.nextId();
				exito = exito
						&& guardarRelacionPasivos(id_relacion_pasivos,
								id_carga, solicitud.getRelacion_de_pasivos());
			}

			if (solicitud.getProyecto_de_calificacion() != null) {
				id_acreedor = DS_SqlUtils.nextId();
				exito = exito
						&& guardarAcreedor(id_acreedor, id_carga,
								solicitud.getProyecto_de_calificacion());
			}

			if (solicitud.getProcesos_ejecutivos() != null) {
				id_proceso_ejecutivo = DS_SqlUtils.nextId();
				exito = exito
						&& guardarProcesoEjecutivo(id_proceso_ejecutivo,
								id_carga, solicitud.getProcesos_ejecutivos());
			}

			if (solicitud.getCertificacion_revisor_fis() != null) {
				id_certificacion_revisor_fis = DS_SqlUtils.nextId();
				exito = exito
						&& guardarCertificacionRevisorFis(
								id_certificacion_revisor_fis, id_carga,
								solicitud.getCertificacion_revisor_fis());
			}

			if (solicitud.getCertificacion_contador() != null) {
				id_certificacion_contador = DS_SqlUtils.nextId();
				exito = exito
						&& guardarCertificacionContador(
								id_certificacion_contador, id_carga,
								solicitud.getCertificacion_contador());
			}

			if (solicitud.getChecklist_solicitud_obj() != null) {
				id_checklist = DS_SqlUtils.nextId();
				exito = exito
						&& guardarChecklist(id_checklist, id_carga,
								solicitud.getChecklist_solicitud_obj());
			}

			if (solicitud.getTipo_solicitud() != null) {
				id_tipoSolicitud = DS_SqlUtils.nextId();
				exito = exito
						&& guardarTipoSolicitud(id_tipoSolicitud, id_carga,
								solicitud.getTipo_solicitud());
			}

			if (solicitud.getNumero_radicado_postal() == "0") {
				solicitud.setNumero_radicado_postal(null);
			}
			
			//Se agrega informaci�n constante para una solicitud NEAR
//			Integer idDependencia = Integer.parseInt(ParametrosInicio.getProperty("solicitud.near.apvista_dependencias.id"));
			Integer idMedioEnvio = Integer.parseInt(ParametrosInicio.getProperty("solicitud.near.apvista_medioenvio.id"));
			Integer idCuaderno = Integer.parseInt(ParametrosInicio.getProperty("solicitud.near.apvista_cuaderno.id"));
			Integer idTipoSeguridad = Integer.parseInt(ParametrosInicio.getProperty("solicitud.near.apvista_seguridad.id"));
			Integer idTramite = Integer.parseInt(ParametrosInicio.getProperty("solicitud.near.apvista_tramite.id"));
			Integer idProcesosClases = Integer.parseInt(ParametrosInicio.getProperty("solicitud.near.apvista_procesosclases.id"));
			Integer idTipoAutoActa = Integer.parseInt(ParametrosInicio.getProperty("solicitud.near.apvista_tipoautoacta.id"));
			Integer idSerie = Integer.parseInt(ParametrosInicio.getProperty("solicitud.near.apvista_serie.id"));
			Integer idSubserie = Integer.parseInt(ParametrosInicio.getProperty("solicitud.near.apvista_subserie.id"));

			Integer id_solicitud_nier = DS_SqlUtils.nextId();

			String insert_solicitud_enrolamiento = "INSERT INTO $SOLICITUD NEAR SOCIEDAD$ ( "
					+ "$SOLICITUD NEAR SOCIEDAD.DEUDOR$, "
					+ "$SOLICITUD NEAR SOCIEDAD.SITUACION PRESENTADA$, "
					+ "$SOLICITUD NEAR SOCIEDAD.ADEL ACREEDORES$, "
					+ "$SOLICITUD NEAR SOCIEDAD.EMERGENCIA ECONOMICA$, "
					+ "$SOLICITUD NEAR SOCIEDAD.INFORMACION FINANCIERA$, "
					+ "$SOLICITUD NEAR SOCIEDAD.CERTIFICACION REPRESENTAN$, "
					+ "$SOLICITUD NEAR SOCIEDAD.MEMORIA EXPLICATIVA$, "
					+ "$SOLICITUD NEAR SOCIEDAD.RELACION DE PASIVOS$, "
					+ "$SOLICITUD NEAR SOCIEDAD.PROYECTO DE CALIFICACION$, "
					+ "$SOLICITUD NEAR SOCIEDAD.PROCESOS EJECUTIVOS$, "
					+ "$SOLICITUD NEAR SOCIEDAD.CERTIFICACION REVISOR FIS$, "
					+ "$SOLICITUD NEAR SOCIEDAD.CERTIFICACION CONTADOR$, "
					+ "$SOLICITUD NEAR SOCIEDAD.NUMERO RADICADO POSTAL$, "
					+ "$SOLICITUD NEAR SOCIEDAD.CHECKLIST SOLICITUD$, "
					+ "$SOLICITUD NEAR SOCIEDAD.TIPO SOLICITANTE$, "
					+ "$SOLICITUD NEAR SOCIEDAD.TIPO SOLICITUD$, "					
//					+ "$SOLICITUD NEAR SOCIEDAD.DEPENDENCIA$, "
					+ "$SOLICITUD NEAR SOCIEDAD.MEDIO ENVIO$, "
					+ "$SOLICITUD NEAR SOCIEDAD.CUADERNO$, "
					+ "$SOLICITUD NEAR SOCIEDAD.TIPO SEGURIDAD$, "
					+ "$SOLICITUD NEAR SOCIEDAD.TRAMITE$, "
					+ "$SOLICITUD NEAR SOCIEDAD.PROCESOS CLASES$, "
					+ "$SOLICITUD NEAR SOCIEDAD.TIPO AUTO ACTA$, "
					+ "$SOLICITUD NEAR SOCIEDAD.SERIE$, "
					+ "$SOLICITUD NEAR SOCIEDAD.SUBSERIE$, "
					+ "ID , "
					+ "IDCARGA ," + "ID_CARGA_CIF" + ") " + "VALUES " + "( "
					+ id_deudor
					+ ", "
					+ "#situacion_presentada#, "
					+ "#adel_acreedores#, "
					+ "#emergencia_economica#, "
					+ id_informacion_financiera
					+ ", "
					+ id_certificacion_replegal
					+ ", "
					+ "#memoria_explicativa#, "
					+ id_relacion_pasivos
					+ ", "
					+ id_acreedor
					+ ", "
					+ id_proceso_ejecutivo
					+ ", "
					+ id_certificacion_revisor_fis
					+ ", "
					+ id_certificacion_contador
					+ ", "
					+ "#numero_radicado_postal#, "
					+ id_checklist
					+ ", "
					+ "#tipo_solicitante#, "
					+ id_tipoSolicitud
					+ ", "
//					+ idDependencia
//					+ ", "
					+ idMedioEnvio
					+ ", "
					+ idCuaderno
					+ ", "
					+ idTipoSeguridad
					+ ", "
					+ idTramite
					+ ", "
					+ idProcesosClases
					+ ", "
					+ idTipoAutoActa
					+ ", "
					+ idSerie
					+ ", "
					+ idSubserie
					+ ", "
					+ id_solicitud_nier
					+ ", "
					+ id_carga
					+ ", "
					+ "$I("
					+ id_carga + ")$" + ") ";

			if (exito)
				exito = DS_SqlUtils.insert(insert_solicitud_enrolamiento,
						solicitud);

			if (exito) {
				daoManager.commitTransaction();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		daoManager.endTransaction();
		return exito;
	}

	public boolean actualizarSolicitudNearSociedad(
			SolicitudNearSociedad solicitud, Integer id_carga) {

		DaoManager daoManager = DaoConfig.getDaoManager(2);
		daoManager.startTransaction();

		Boolean exito = true;

		try {

			Integer id_deudor = null;
			Integer id_informacion_financiera = null;
			Integer id_certificacion_replegal = null;
			Integer id_relacion_pasivos = null;
			Integer id_proceso_ejecutivo = null;
			Integer id_certificacion_revisor_fis = null;
			Integer id_certificacion_contador = null;
			Integer id_acreedor = null;
			Integer id_checklist = null;
			Integer id_tipoSolicitud = null;

			if (solicitud.getInformacion_financiera() != null) {
				id_informacion_financiera = DS_SqlUtils.nextId();

				Boolean guardar_info = guardarInformacionFinanciera(
						id_informacion_financiera, id_carga,
						solicitud.getInformacion_financiera()); 

				if (guardar_info
						&& solicitud.getInformacion_financiera()
								.getValor_activos() != null) {
					String categoria = CalculoCategoriaTamanoServicio
							.getInstance().calcularCatEmpresa(
									solicitud.getInformacion_financiera()
											.getValor_activos(), solicitud.getTipo_solicitud().getId());

					if (!categoria.equals("") && solicitud.getDeudor() != null) {
						solicitud.getDeudor().setCategoria(categoria);
					}
				}

				BigDecimal ingresos_calculo_tam = new BigDecimal(0);
				if (solicitud.getInformacion_financiera()
						.getTotal_ingresos_ordinarios() != null
						&& solicitud.getInformacion_financiera()
								.getTotal_otros_ingresos() != null
						&& Constantes.MI_CAMPO_SI.equals(solicitud.getInformacion_financiera()
								.getTiene_inversiones())) {
					ingresos_calculo_tam = ingresos_calculo_tam
							.add(solicitud.getInformacion_financiera()
									.getTotal_ingresos_ordinarios())
							.add(solicitud.getInformacion_financiera()
									.getTotal_otros_ingresos())
							.add(solicitud.getInformacion_financiera()
									.getValor_p_participacion())
							.add(solicitud.getInformacion_financiera()
									.getValor_p_costo())
							.add(solicitud.getInformacion_financiera()
									.getValor_p_razonable());

				} else if (solicitud.getInformacion_financiera()
						.getTotal_ingresos_ordinarios() != null
						&& solicitud.getInformacion_financiera()
								.getTotal_otros_ingresos() != null) {
					ingresos_calculo_tam = ingresos_calculo_tam.add(
							solicitud.getInformacion_financiera()
									.getTotal_ingresos_ordinarios()).add(
							solicitud.getInformacion_financiera()
									.getTotal_otros_ingresos());
				}

				if (solicitud.getDeudor() != null
						&& solicitud.getDeudor().getMacrosector() != null) {
					Integer tam = CalculoCategoriaTamanoServicio.getInstance()
							.calcularTamEmpresa(ingresos_calculo_tam,
									solicitud.getDeudor().getMacrosector());

					solicitud.getDeudor().setTamano_empresa(tam);
				}

				exito = exito && guardar_info;
			}

			if (solicitud.getDeudor() != null) {
				id_deudor = DS_SqlUtils.nextId();
				exito = exito
						&& guardarSociedad(id_deudor, id_carga,
								solicitud.getDeudor());
			}

			if (solicitud.getCertificacion_representan() != null) {
				id_certificacion_replegal = DS_SqlUtils.nextId();
				exito = exito
						&& guardarCertificacionRepLegal(
								id_certificacion_replegal, id_carga,
								solicitud.getCertificacion_representan());
			}

			if (solicitud.getRelacion_de_pasivos() != null) {
				id_relacion_pasivos = DS_SqlUtils.nextId();
				exito = exito
						&& guardarRelacionPasivos(id_relacion_pasivos,
								id_carga, solicitud.getRelacion_de_pasivos());
			}

			if (solicitud.getProyecto_de_calificacion() != null) {
				id_acreedor = DS_SqlUtils.nextId();
				exito = exito
						&& guardarAcreedor(id_acreedor, id_carga,
								solicitud.getProyecto_de_calificacion());
			}

			if (solicitud.getProcesos_ejecutivos() != null) {
				id_proceso_ejecutivo = DS_SqlUtils.nextId();
				exito = exito
						&& guardarProcesoEjecutivo(id_proceso_ejecutivo,
								id_carga, solicitud.getProcesos_ejecutivos());
			}

			if (solicitud.getCertificacion_revisor_fis() != null) {
				id_certificacion_revisor_fis = DS_SqlUtils.nextId();
				exito = exito
						&& guardarCertificacionRevisorFis(
								id_certificacion_revisor_fis, id_carga,
								solicitud.getCertificacion_revisor_fis());
			}

			if (solicitud.getCertificacion_contador() != null) {
				id_certificacion_contador = DS_SqlUtils.nextId();
				exito = exito
						&& guardarCertificacionContador(
								id_certificacion_contador, id_carga,
								solicitud.getCertificacion_contador());
			}

			if (solicitud.getChecklist_solicitud() != null) {
				id_checklist = DS_SqlUtils.nextId();
				exito = exito
						&& guardarChecklist(id_checklist, id_carga,
								solicitud.getChecklist_solicitud_obj());
			}

			if (solicitud.getTipo_solicitud() != null) {
				id_tipoSolicitud = DS_SqlUtils.nextId();
				exito = exito
						&& guardarTipoSolicitud(id_tipoSolicitud, id_carga,
								solicitud.getTipo_solicitud());
			}

			if (solicitud.getNumero_radicado_postal() == "0") {
				solicitud.setNumero_radicado_postal(null);
			}

			String update_solicitud_enrolamiento = "UPDATE $SOLICITUD NEAR SOCIEDAD$ SET "
					+ "$SOLICITUD NEAR SOCIEDAD.DEUDOR$ ="
					+ id_deudor
					+ ", "
					+ "$SOLICITUD NEAR SOCIEDAD.SITUACION PRESENTADA$ = #situacion_presentada#, "
					+ "$SOLICITUD NEAR SOCIEDAD.ADEL ACREEDORES$ = #adel_acreedores#, "
					+ "$SOLICITUD NEAR SOCIEDAD.EMERGENCIA ECONOMICA$ = #emergencia_economica#, "
					+ "$SOLICITUD NEAR SOCIEDAD.INFORMACION FINANCIERA$ = "
					+ id_informacion_financiera
					+ ", "
					+ "$SOLICITUD NEAR SOCIEDAD.CERTIFICACION REPRESENTAN$ = "
					+ id_certificacion_replegal
					+ ", "
					+ "$SOLICITUD NEAR SOCIEDAD.MEMORIA EXPLICATIVA$ = #memoria_explicativa#, "
					+ "$SOLICITUD NEAR SOCIEDAD.RELACION DE PASIVOS$ ="
					+ id_relacion_pasivos
					+ ", "
					+ "$SOLICITUD NEAR SOCIEDAD.PROYECTO DE CALIFICACION$ ="
					+ id_acreedor
					+ ", "
					+ "$SOLICITUD NEAR SOCIEDAD.PROCESOS EJECUTIVOS$ ="
					+ id_proceso_ejecutivo
					+ ", "
					+ "$SOLICITUD NEAR SOCIEDAD.CERTIFICACION REVISOR FIS$ ="
					+ id_certificacion_revisor_fis
					+ ", "
					+ "$SOLICITUD NEAR SOCIEDAD.CERTIFICACION CONTADOR$ ="
					+ id_certificacion_contador
					+ ", "
					+ "$SOLICITUD NEAR SOCIEDAD.NUMERO RADICADO POSTAL$ = #numero_radicado_postal#,"
					+ "$SOLICITUD NEAR SOCIEDAD.CHECKLIST SOLICITUD$ = "
					+ id_checklist + " WHERE IDCARGA = " + id_carga;

			if (exito)
				exito = DS_SqlUtils.update(update_solicitud_enrolamiento,
						solicitud);

			if (exito) {
				daoManager.commitTransaction();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		daoManager.endTransaction();
		return exito;
	}

	/**
	 * Hace consulta de solicitud por id carga.
	 * 
	 * @param idCarga
	 * @return
	 */

	public Integer obtenerTipoSolicitante(Integer id_carga) {
		String query = "SELECT $SOLICITUD NEAR SOCIEDAD.TIPO SOLICITANTE$ FROM $SOLICITUD NEAR SOCIEDAD$ WHERE IDCARGA ="
				+ id_carga;
		return DS_SqlUtils.queryForObject(Integer.class, query);

	}

	private boolean guardarTipoSolicitud(Integer id_tipoSolicitud,
			Integer id_carga, TipoSolicitud tipo_solicitud) {
		String insert = "INSERT INTO $TIPO SOLICITUD$ ("
				+ "$TIPO SOLICITUD.GRUPO NIIF$, " 
				+ "$TIPO SOLICITUD.METODO$, "
				+ "$TIPO SOLICITUD.TIPO SOLICITUD$, "
				+ "ID , " + "IDCARGA" + ") " 
				+ "VALUES (" 
				+ "#grupo_niif#, "
				+ "#metodo#, "
				+ "#tipo_solicitud#, "
				+ id_tipoSolicitud + ", " 
				+ id_carga + ")";

		return DS_SqlUtils.insert(insert, tipo_solicitud);
	}

	private boolean guardarChecklist(Integer id_checklist, Integer id_carga,
			Checklist checklist) {

		String insert = "INSERT INTO $CHECKLIST$ ("
				+ "$CHECKLIST.EXISTENCIA CERL$, "
				+ "$CHECKLIST.VIGENCIA CERL$, "
				+ "$CHECKLIST.MATRICULA VIGENTE$, "
				+ "$CHECKLIST.APODERADO VALIDO$, "
				+ "$CHECKLIST.NOTIFICACIONES$, "
				+ "$CHECKLIST.COHERENCIA SOLICITUD$, "
				+ "$CHECKLIST.SUPERVISADA SSOC$, "
				+ "$CHECKLIST.SOLICITUD POR REPRESENTAN$, "
				+ "$CHECKLIST.APODERADO EXISTENTE$, "
				+ "$CHECKLIST.NO PASIVOS SS$, "
				+ "$CHECKLIST.PLAN PAGOS PASIVOS$, "
				+ "$CHECKLIST.NO PASIVOS RETENCIONES$, "
				+ "$CHECKLIST.REV AFIRMA CONTABILIDAD$, "
				+ "$CHECKLIST.NPR REV FIS$, " + "$CHECKLIST.NPR REP$, "
				+ "$CHECKLIST.NO PASIVOS TRABAJADORES$, "
				+ "$CHECKLIST.CESACION DE PAGOS$, "
				+ "$CHECKLIST.OBLIGACIONES VENCIDAS$, "
				+ "$CHECKLIST.CONTABILIDAD REGULAR$, "
				+ "$CHECKLIST.REPLEG AFIRMA CONTABILIDA$, "
				+ "$CHECKLIST.CONTADOR AFIRMA CONTABILI$, "
				+ "$CHECKLIST.REMISION PREVIA INFO$, "
				+ "$CHECKLIST.SOCIEDAD EN DISOLUCION$, "
				+ "$CHECKLIST.SOCIEDAD PASIVOS PENSIONA$, "
				+ "$CHECKLIST.SOCIEDAD GARANTE CODEUDOR$, "
				+ "$CHECKLIST.GARANTIAS MOBILIARIAS$, "
				+ "$CHECKLIST.PASIVOS PENSIONALES$, "
				+ "$CHECKLIST.SOLICITANTE CONTROLANTE$, "
				+ "ID , "
				+ "IDCARGA" + ") " + "VALUES (" + "#existencia_cerl#, "
				+ "#vigencia_cerl#, " + "#matricula_vigente#, "
				+ "#apoderado_valido#, " + "#notificaciones#, "
				+ "#coherencia_solicitud#, " + "#supervisada_ssoc#, "
				+ "#solicitud_por_representan#, " + "#apoderado_existente#, "
				+ "#no_pasivos_ss#, " + "#plan_pagos_pasivos#, "
				+ "#no_pasivos_retenciones#, " + "#rev_afirma_contabilidad#, "
				+ "#npr_rev_fis#, " + "#npr_rep#, "
				+ "#no_pasivos_trabajadores#, " + "#cesacion_de_pagos#, "
				+ "#obligaciones_vencidas#, " + "#contabilidad_regular#, "
				+ "#repleg_afirma_contabilida#, "
				+ "#contador_afirma_contabili#, " 
				+ "#remision_previa_info#, " 
				+ "#sociedad_en_disolucion#, " 
				+ "#sociedad_pasivos_pensiona#, " 
				+ "#sociedad_garante_codeudor#, " 
				+ "#garantias_mobiliarias#, " 
				+ "#pasivos_pensionales#, " 
				+ "#solicitante_controlante#, "
				+ id_checklist + ", "
				+ id_carga + ")";
		
		SimpleLogger.setDebug("CONSULTA CHECK: \n" + checklist);

		return DS_SqlUtils.insert(insert, checklist);
	}

	private boolean guardarCertificacionContador(
			Integer id_certificacion_contador, Integer id_carga,
			CertificacionContador certificacion_contador) {
		String insert = "INSERT INTO $CERTIFICACION CONTADOR$ ("
				+ "$CERTIFICACION CONTADOR.CUENTA CON AUTORIZACION$, "
				+ "$CERTIFICACION CONTADOR.CUENTA CON AUTORIZACION O$, "
				+ "$CERTIFICACION CONTADOR.CONTABILIDAD REGULAR$, "
				+ "$CERTIFICACION CONTADOR.MARCO CONTABILIDAD OBS$, "
				+ "$CERTIFICACION CONTADOR.CONTABILIDAD REGULAR OBS$, "
				+ "$CERTIFICACION CONTADOR.PASIVOS PENSIONALES$, "
				+ "$CERTIFICACION CONTADOR.PASIVOS PENSIONALES OBS$, "
				+ "$CERTIFICACION CONTADOR.MARCO CONTABILIDAD$, " + "ID , "
				+ "IDCARGA" + ") " + "VALUES (" + "#cuenta_con_autorizacion#, "
				+ "#cuenta_con_autorizacion_o#, " + "#contabilidad_regular#, "
				+ "#marco_contabilidad_obs#, " + "#contabilidad_regular_obs#, "
				+ "#pasivos_pensionales#, " + "#pasivos_pensionales_obs#, "
				+ "#marco_contabilidad#, " + id_certificacion_contador + ", "
				+ id_carga + ")";

		return DS_SqlUtils.insert(insert, certificacion_contador);
	}

	private boolean guardarCertificacionRevisorFis(
			Integer id_certificacion_revisor_fis, Integer id_carga,
			CertificacionRevisorFis certificacion_revisor_fis) {
		String insert = "INSERT INTO $CERTIFICACION REVISOR FIS$ ("
				+ "$CERTIFICACION REVISOR FIS.CUENTA CON AUTORIZACION$, "
				+ "$CERTIFICACION REVISOR FIS.CUENTA CON AUTORIZACION O$, "
				+ "$CERTIFICACION REVISOR FIS.CONTABILIDAD REGULAR$, "
				+ "$CERTIFICACION REVISOR FIS.MARCO CONTABILIDAD OBS$, "
				+ "$CERTIFICACION REVISOR FIS.CONTABILIDAD REGULAR OBS$, "
				+ "$CERTIFICACION REVISOR FIS.PASIVOS PENSIONALES$, "
				+ "$CERTIFICACION REVISOR FIS.PASIVOS PENSIONALES OBS$, "
				+ "$CERTIFICACION REVISOR FIS.MARCO CONTABILIDAD$, " + "ID , "
				+ "IDCARGA" + ") " + "VALUES (" + "#cuenta_con_autorizacion#, "
				+ "#cuenta_con_autorizacion_o#, " + "#contabilidad_regular#, "
				+ "#marco_contabilidad_obs#, " + "#contabilidad_regular_obs#, "
				+ "#pasivos_pensionales#, " + "#pasivos_pensionales_obs#, "
				+ "#marco_contabilidad#, " + id_certificacion_revisor_fis
				+ ", " + id_carga + ")";

		return DS_SqlUtils.insert(insert, certificacion_revisor_fis);
	}

	private boolean guardarProcesoEjecutivo(Integer id_proceso_ejecutivo,
			Integer id_carga, ProcesoEjecutivo procesos_ejecutivos) {
		String insert = "INSERT INTO $PROCESO EJECUTIVO$ ("
				+ "$PROCESO EJECUTIVO.NUMERO PROCESO$, "
				+ "$PROCESO EJECUTIVO.JUZGADO$, "
				+ "$PROCESO EJECUTIVO.NOMBRE DEMANDANTE$, "
				+ "$PROCESO EJECUTIVO.CORREO NOTIFICACION$, "
				+ "$PROCESO EJECUTIVO.VALOR$, " + "ID , " + "IDCARGA" + ") "
				+ "VALUES (" + "#numero_proceso#, " + "#juzgado#, "
				+ "#nombre_demandante#, " + "#correo_notificacion#, "
				+ "#valor#, " + id_proceso_ejecutivo + ", " + id_carga + ")";

		return DS_SqlUtils.insert(insert, procesos_ejecutivos);
	}

	public boolean guardarAcreedor(Integer id_acreedor, Integer id_carga,
			Acreedor acreedor) {

		Integer id_persona = null;
		Integer id_credito = null;
		boolean exito = true;

		if (acreedor.getDatos_basicos() != null) {
			id_persona = DS_SqlUtils.nextId();
			exito = exito
					&& guardarPersona(id_persona, id_carga,
							acreedor.getDatos_basicos(), acreedor
									.getDatos_basicos().getId_persona_mein());
		}

		if (acreedor.getAcreencia() != null) {
			id_credito = DS_SqlUtils.nextId();
			exito = exito
					&& guardarCredito(id_credito, id_carga,
							acreedor.getAcreencia());
		}

		String insert = "INSERT INTO $ACREEDOR$ ( " + "$ACREEDOR.CATEGORIA$, "
				+ "$ACREEDOR.DATOS BASICOS$, " + "$ACREEDOR.ACREENCIA$, "
				+ "ID , " + "IDCARGA " + ") " + "VALUES " + "( "
				+ "#categoria#, " + id_persona + ", " + id_credito + ", "
				+ id_acreedor + ", " + id_carga + ") ";

		return DS_SqlUtils.insert(insert, acreedor);
	}

	private boolean guardarCredito(Integer id_credito, Integer id_carga,
			Credito credito) {

		Integer id_garantia = null;
		boolean exito = true;

		if (credito.getGarantia() != null) {
			id_garantia = DS_SqlUtils.nextId();
			exito = exito
					&& guardarGarantia(id_garantia, id_carga,
							credito.getGarantia());
		}

		String insert = "INSERT INTO $CREDITO$ (" + "$CREDITO.CLASE$, "
				+ "$CREDITO.VALOR INDEXADO$, "
				+ "$CREDITO.DOCUMENTO SOPORTE$, "
				+ "$CREDITO.DERECHOS DE VOTO$, "
				+ "$CREDITO.FECHA VENCIMIENTO$, " + "$CREDITO.FECHA CORTE$, "
				+ "$CREDITO.OBLIGACION VENCIDA$, "
				+ "$CREDITO.DIAS VENCIDOS$, "
				+ "$CREDITO.PORCENTAJE PARTICIPACION$, "
				+ "$CREDITO.GARANTIA$, " + "ID , " + "IDCARGA" + ") "
				+ "VALUES (" + "#clase#, " + "#valor_indexado#, "
				+ "#documento_soporte#, " + "#derechos_de_voto#, "
				+ "#fecha_vencimiento#, " + "#fecha_corte#, "
				+ "#obligacion_vencida#, " + "#dias_vencidos#, "
				+ "#porcentaje_participacion#, " + id_garantia + ", "
				+ id_credito + ", " + id_carga + ")";

		return DS_SqlUtils.insert(insert, credito);
	}

	private boolean guardarGarantia(Integer id_garantia, Integer id_carga,
			Garantia garantia) {
		String insert = "INSERT INTO $GARANTIA$ ("
				+ "$GARANTIA.OBLIGACION CUBIERTA$, "
				+ "$GARANTIA.IDENTIFICACION$, " + "$GARANTIA.VALOR DEL BIEN$, "
				+ "$GARANTIA.VALOR MAXIMO$, " + "$GARANTIA.BIEN NECESARIO$, "
				+ "ID , " + "IDCARGA" + ") " + "VALUES ("
				+ "#obligacion_cubierta#, " + "#identificacion#, "
				+ "#valor_del_bien#, " + "#valor_maximo#, "
				+ "#bien_necesario#, " + id_garantia + ", " + id_carga + ")";

		return DS_SqlUtils.insert(insert, garantia);
	}

	public boolean guardarSociedad(Integer id_deudor, Integer id_carga,
			Sociedad sociedad) {

		Integer id_datos_basicos = null;
		Integer id_rep_legal = null;
		Integer id_contador = null;
		Integer id_revisor_fiscal = null;
		Integer id_apoderado = null;
		boolean exito = true;

		if (sociedad.getDatos_basicos() != null) {
			id_datos_basicos = DS_SqlUtils.nextId();
			exito = exito
					&& guardarPersona(id_datos_basicos, id_carga,
							sociedad.getDatos_basicos(), sociedad
									.getDatos_basicos().getId_persona_mein());
		}

		if (sociedad.getRepresentante_legal() != null
				&& sociedad.getRepresentante_legal().getId_persona_mein() != null) {
			id_rep_legal = DS_SqlUtils.nextId();
			exito = exito
					&& guardarPersona(id_rep_legal, id_carga,
							sociedad.getRepresentante_legal(), sociedad
									.getRepresentante_legal()
									.getId_persona_mein());
		}

		if (sociedad.getContador() != null
				&& sociedad.getContador().getId_persona_mein() != null) {
			id_contador = DS_SqlUtils.nextId();
			exito = exito
					&& guardarPersona(id_contador, id_carga,
							sociedad.getContador(), sociedad.getContador()
									.getId_persona_mein());
		}

		if (sociedad.getRevisor_fiscal() != null
				&& sociedad.getRevisor_fiscal().getId_persona_mein() != null) {
			id_revisor_fiscal = DS_SqlUtils.nextId();
			exito = exito
					&& guardarPersona(id_revisor_fiscal, id_carga,
							sociedad.getRevisor_fiscal(), sociedad
									.getRevisor_fiscal().getId_persona_mein());
		}

		if (sociedad.getApoderado() != null
				&& sociedad.getApoderado().getId_persona_mein() != null) {
			id_apoderado = DS_SqlUtils.nextId();
			exito = exito
					&& guardarPersona(id_apoderado, id_carga,
							sociedad.getApoderado(), sociedad.getApoderado()
									.getId_persona_mein());
		}

		if (StringUtils.isEmpty(sociedad.getActividad_economica())) {
			sociedad.setActividad_economica(null);
		}

		String insert = "INSERT INTO $SOCIEDAD$ ( "
				+ "$SOCIEDAD.DATOS BASICOS$, "
				+ "$SOCIEDAD.REPRESENTANTE LEGAL$, " + "$SOCIEDAD.CONTADOR$, "
				+ "$SOCIEDAD.REVISOR FISCAL$, "
				+ "$SOCIEDAD.PORCENTAJE PARTICIPACION$, "
				+ "$SOCIEDAD.APODERADO$, " + "$SOCIEDAD.OBJETO SOCIAL$, "
				+ "$SOCIEDAD.NATURALEZA$, "
				+ "$SOCIEDAD.TRABAJADORES HOMBRES$, "
				+ "$SOCIEDAD.TRABAJADORES MUJERES$, "
				+ "$SOCIEDAD.ACTIVIDAD ECONOMICA$, "
				+ "$SOCIEDAD.MACROSECTOR$, "
				+ "$SOCIEDAD.DEPARTAMENTO DANE$, " + "$SOCIEDAD.MUNICIPIO DANE$, "
				+ "$SOCIEDAD.TAMANO EMPRESA$, " + "$SOCIEDAD.CATEGORIA$, "
				+ "$SOCIEDAD.NIT SOCIEDAD CONTROLADA$, " + "$SOCIEDAD.NAME SOCIEDAD CONTROLADA$, "
				+ "$SOCIEDAD.PAIS DANE$, "
				+ "$SOCIEDAD.RADICADO SOCIEDAD CONTROLADA$, "
				+ "$SOCIEDAD.REPLEGAL TIENE LIMITACION$, "
				+ "ID , " + "IDCARGA " + ") " + "VALUES " + "( "
				+ id_datos_basicos
				+ ", "
				+ id_rep_legal
				+ ", "
				+ id_contador
				+ ", "
				+ id_revisor_fiscal
				+ ", "
				+ "#porcentaje_participacion#, "
				+ id_apoderado
				+ ", "
				+ "#objeto_social#, "
				+ "#naturaleza#, "
				+ "#trabajadores_hombres#, "
				+ "#trabajadores_mujeres#, "
				+ "#actividad_economica#, "
				+ "#macrosector#, "
				+ "$departamento_dane$, "
				+ "$municipio_dane$, "
				+ "#tamano_empresa#, "
				+ "#categoria#, "
				+ "#nit_sociedad_controlada#, "
				+ "#name_sociedad_controlada#, "
				+ "$pais_dane$, "
				+ "#radicado_sociedad_controlada#, "
				+ "#replegal_tiene_limitacion#, "
				+ id_deudor + ", " + id_carga + ") ";

		return DS_SqlUtils.insert(insert, sociedad);
	}

	public boolean actualizarSociedad(Integer id_deudor, Integer id_carga,
			Sociedad sociedad) {

		Integer id_datos_basicos = null;
		Integer id_rep_legal = null;
		Integer id_contador = null;
		Integer id_revisor_fiscal = null;
		Integer id_apoderado = null;
		boolean exito = true;

		if (sociedad.getDatos_basicos() != null) {
			id_datos_basicos = sociedad.getDatos_basicos().getId();
			exito = exito
					&& actualizarPersona(id_datos_basicos, id_carga,
							sociedad.getDatos_basicos(), sociedad
									.getDatos_basicos().getId_persona_mein());
		}

		if (sociedad.getRepresentante_legal() != null
				&& sociedad.getRepresentante_legal().getId_persona_mein() != null) {
			id_rep_legal = sociedad.getRepresentante_legal().getId();
			exito = exito
					&& actualizarPersona(id_rep_legal, id_carga,
							sociedad.getRepresentante_legal(), sociedad
									.getRepresentante_legal()
									.getId_persona_mein());
		}

		if (sociedad.getContador() != null
				&& sociedad.getContador().getId_persona_mein() != null) {
			id_contador = sociedad.getContador().getId();
			exito = exito
					&& actualizarPersona(id_contador, id_carga,
							sociedad.getContador(), sociedad.getContador()
									.getId_persona_mein());
		}

		if (sociedad.getRevisor_fiscal() != null
				&& sociedad.getRevisor_fiscal().getId_persona_mein() != null) {
			id_revisor_fiscal = sociedad.getRevisor_fiscal().getId();
			exito = exito
					&& actualizarPersona(id_revisor_fiscal, id_carga,
							sociedad.getRevisor_fiscal(), sociedad
									.getRevisor_fiscal().getId_persona_mein());
		}

		if (sociedad.getApoderado() != null
				&& sociedad.getApoderado().getId_persona_mein() != null) {
			id_apoderado = sociedad.getApoderado().getId();
			exito = exito
					&& actualizarPersona(id_apoderado, id_carga,
							sociedad.getApoderado(), sociedad.getApoderado()
									.getId_persona_mein());
		}

		if (StringUtils.isEmpty(sociedad.getActividad_economica())) {
			sociedad.setActividad_economica(null);
		}

		String update = "UPDATE $SOCIEDAD$ "
				+ "SET $SOCIEDAD.DATOS BASICOS$ = "
				+ id_datos_basicos
				+ ", "
				+ "$SOCIEDAD.REPRESENTANTE LEGAL$ = "
				+ id_rep_legal
				+ ", "
				+ "$SOCIEDAD.CONTADOR$ = "
				+ id_contador
				+ ", "
				+ "$SOCIEDAD.REVISOR FISCAL$ = "
				+ id_revisor_fiscal
				+ ", "
				+ "$SOCIEDAD.PORCENTAJE PARTICIPACION$ = #porcentaje_participacion#, "
				+ "$SOCIEDAD.APODERADO$= "
				+ id_apoderado
				+ ", "
				+ "$SOCIEDAD.OBJETO SOCIAL$ = #objeto_social#, "
				+ "$SOCIEDAD.NATURALEZA$ = #naturaleza#, "
				+ "$SOCIEDAD.TRABAJADORES HOMBRES$ = #trabajadores_hombres#, "
				+ "$SOCIEDAD.TRABAJADORES MUJERES$ = #trabajadores_mujeres#, "
				+ "$SOCIEDAD.ACTIVIDAD ECONOMICA$ = #actividad_economica#, "
				+ "$SOCIEDAD.MACROSECTOR$ = #macrosector#, "
				+ "$SOCIEDAD.DEPARTAMENTO DANE$ = $departamento_dane$, " 
				+ "$SOCIEDAD.MUNICIPIO DANE$ = $municipio_dane$, "
				+ "$SOCIEDAD.TAMANO EMPRESA$ = #tamano_empresa#, "
				+ "$SOCIEDAD.CATEGORIA$ = #categoria#, "
				+ "$SOCIEDAD.NIT SOCIEDAD CONTROLADA$ = #nit_sociedad_controlada#, "
				+ "$SOCIEDAD.NAME SOCIEDAD CONTROLADA$ = #name_sociedad_controlada#, "
				+ "$SOCIEDAD.RADICADO SOCIEDAD CONTROLADA$ = #radicado_sociedad_controlada#, "
				+ "$SOCIEDAD.PAIS DANE$ = $pais_dane$, "
				+ "WHERE ID = "
				+ id_deudor + " AND " + "IDCARGA =" + id_carga;

		return DS_SqlUtils.update(update, sociedad);
	}

	public boolean guardarInformacionFinanciera(Integer id_informacion_financiera, Integer id_carga, InformacionFinanciera informacionFinanciera){
		String insert = "INSERT INTO $INFORMACION FINANCIERA$ ("
				+ "$INFORMACION FINANCIERA.VALOR ACTIVOS$, "
				+ "$INFORMACION FINANCIERA.VALOR PASIVOS$, "
				+ "$INFORMACION FINANCIERA.VALOR PATRIMONIO$, "
				+ "$INFORMACION FINANCIERA.TOTAL INGRESOS ORDINARIOS$, "
				+ "$INFORMACION FINANCIERA.TOTAL OTROS INGRESOS$, "
				+ "$INFORMACION FINANCIERA.TIENE INVERSIONES$, "
				+ "$INFORMACION FINANCIERA.VALOR P PARTICIPACION$, "
				+ "$INFORMACION FINANCIERA.VALOR P COSTO$, "
				+ "$INFORMACION FINANCIERA.VALOR P RAZONABLE$, "
				+ "$INFORMACION FINANCIERA.FECHA ESTADOS FINANCIEROS$, "
				+ "$INFORMACION FINANCIERA.VALOR PASIVOS ULTIMO ANIO$,"
				+ "$INFORMACION FINANCIERA.VALOR ACTIVOS ULTIMO ANIO$,"
				+ "$INFORMACION FINANCIERA.ULTIMO RADICADO DICTAMEN$,"
				+ "$INFORMACION FINANCIERA.PENULTIMO RADICADO DICTAM$,"
				+ "$INFORMACION FINANCIERA.ANTEPENULTIMO RADICA DICT$,"
				+ "$INFORMACION FINANCIERA.FECHA EEFF ANIO ANTERIOR$, "
				+ "$INFORMACION FINANCIERA.FECHA R BIENES ACREEDORES$, "
				+ "ID , "
				+ "IDCARGA"
				+ ") "
				+ "VALUES ("
				+ "#valor_activos#, "
				+ "#valor_pasivos#, "
				+ "#valor_patrimonio#, "
				+ "#total_ingresos_ordinarios#, "
				+ "#total_otros_ingresos#, "
				+ "#tiene_inversiones#, "
				+ "#valor_p_participacion#, "
				+ "#valor_p_costo#, "
				+ "#valor_p_razonable#, "
				+ "#fecha_estados_financieros#,"
				+ "#valor_pasivos_ultimoanio#,"
				+ "#valor_activos_ultimoanio#,"
				+ "#ultimo_radicado_dictamen#,"
				+ "#penultimo_radicado_dictamen#,"
				+ "#antepenultimo_radicado_dictamen#,"
				+ "#fecha_eeff_anio_anterior#,"
				+ "#fecha_r_bienes_acreedores#,"
			    + id_informacion_financiera + ", "
			    + id_carga + ")";
		
		return DS_SqlUtils.insert(insert, informacionFinanciera);
	}

	public boolean guardarCertificacionRepLegal(
			Integer id_certificacion_replegal, Integer id_carga,
			CertificacionRepLegal certificacionRepLegal) {
		String insert = "INSERT INTO $CERTIFICACION REPLEGAL$ ("
				+ "$CERTIFICACION REPLEGAL.CUENTA CON AUTORIZACION$, "
				+ "$CERTIFICACION REPLEGAL.CUENTA CON AUTORIZACION O$, "
				+ "$CERTIFICACION REPLEGAL.CONTABILIDAD REGULAR$, "
				+ "$CERTIFICACION REPLEGAL.MARCO CONTABILIDAD OBS$, "
				+ "$CERTIFICACION REPLEGAL.LEY 222$, "
				+ "$CERTIFICACION REPLEGAL.CONTABILIDAD REGULAR OBS$, "
				+ "$CERTIFICACION REPLEGAL.PASIVOS PENSIONALES$, "
				+ "$CERTIFICACION REPLEGAL.PASIVOS PENSIONALES OBS$, "
				+ "$CERTIFICACION REPLEGAL.MARCO CONTABILIDAD$, "
				+ "$CERTIFICACION REPLEGAL.LEY 222 OBS$, "
				+ "$CERTIFICACION REPLEGAL.DECRETO 1074$, " + "ID , "
				+ "IDCARGA" + ") " + "VALUES (" + "#cuenta_con_autorizacion#, "
				+ "#cuenta_con_autorizacion_o#, " + "#contabilidad_regular#, "
				+ "#marco_contabilidad_obs#, " + "#ley_222#, "
				+ "#contabilidad_regular_obs#, " + "#pasivos_pensionales#, "
				+ "#pasivos_pensionales_obs#, " + "#marco_contabilidad#, "
				+ "#ley_222_obs#, " + "#decreto_1074#, "
				+ id_certificacion_replegal + ", " + id_carga + ")";

		return DS_SqlUtils.insert(insert, certificacionRepLegal);
	}

	public boolean guardarRelacionPasivos(Integer id_relacion_pasivos,
			Integer id_carga, RelacionPasivos relacion_pasivos) {

		Integer id_pasivo = null;
		boolean exito = true;

		String insert = "INSERT INTO $RELACION PASIVOS$ ("
				+ "$RELACION PASIVOS.PASIVOS POR RETENCIONES$, "
				+ "$RELACION PASIVOS.PASIVOS POR DESCUENTOS$, "
				+ "$RELACION PASIVOS.PASIVOS POR APORTES$, " + "ID , "
				+ "IDCARGA" + ") " + "VALUES (" + "#pasivos_por_retenciones#, "
				+ "#pasivos_por_descuentos#, " + "#pasivos_por_aportes#, "
				+ id_relacion_pasivos + ", " + id_carga + ")";

		DS_SqlUtils.insert(insert, relacion_pasivos);

		if (relacion_pasivos.getPasivos() != null) {
			id_pasivo = DS_SqlUtils.nextId();
			exito = exito
					&& guardarPasivos(id_relacion_pasivos, id_pasivo, id_carga,
							relacion_pasivos.getPasivos());
		}
		return exito;
	}

	private boolean guardarPasivos(Integer id_relacion_pasivo,
			Integer id_pasivo, Integer id_carga, List<Pasivo> pasivos) {
		Integer id_persona = null;
		boolean exito = true;
		for (Pasivo pasivo : pasivos) {

			if (pasivo.getPersona() != null) {
				id_persona = DS_SqlUtils.nextId();
				exito = exito
						&& guardarPersona(id_persona, id_carga,
								pasivo.getPersona(), pasivo.getPersona()
										.getId_persona_mein());
			}

			String insert = "INSERT INTO $PASIVO$ (" + "$PASIVO.TIPO PASIVO$, "
					+ "$PASIVO.VALOR$, " + "$PASIVO.PERSONA$, " + "ID , "
					+ "IDCARGA" + ") " + "VALUES (" + "#tipo_pasivo#, "
					+ "#valor#, " + id_persona + ", " + id_pasivo + ", "
					+ id_carga + ")";

			DS_SqlUtils.insert(insert, pasivo);

			Integer id = DS_SqlUtils.nextId();

			String insert_relacion = "INSERT INTO $RELACION PASIVOS$$RELACION PASIVOS.PASIVOS$ ("
					+ "ID_P, "
					+ "ID_V, "
					+ "ID, "
					+ "IDCARGA "
					+ ") "
					+ "VALUES ("
					+ id_relacion_pasivo
					+ ", "
					+ id_pasivo
					+ ", "
					+ id + ", " + id_carga + ")";

			exito = exito && DS_SqlUtils.insert(insert_relacion);
		}

		return exito;
	}

	private boolean guardarPersona(Integer id_persona, Integer id_carga,
			PersonaMein persona, Integer id_persona_ds) {

		String insert = "INSERT INTO $PERSONA$ ("
				+ "$PERSONA.TIPO IDENTIFICACION$, "
				+ "$PERSONA.IDENTIFICACION$, "
				+ "$PERSONA.FECHA EXPEDICION DOC$, " + "$PERSONA.DIRECCION$, "
				+ "$PERSONA.NOMBRE COMPLETO$, " + "$PERSONA.CORREO$, "
				+ "$PERSONA.CELULAR$, " + "$PERSONA.TELEFONO$, "
				+ "$PERSONA.TARJETA PROFESIONAL$, " + "ID , " + "IDCARGA, "
				+ "$PERSONA.PAIS$, $PERSONA.DEPARTAMENTO$, $PERSONA.MUNICIPIO$) " + "VALUES (" + "#tipo_identificacion#, "
				+ "#identificacion#, " + "#fecha_expedicion_doc#, "
				+ "#direccion#, " + "#nombre_completo#, " + "#correo#, "
				+ "#celular#, " + "#telefono#, " + "#tarjeta_profesional#, "
				+ id_persona + ", " + id_carga + ", #pais_id#, #departamento_id#, #municipio_id#)";

		if (id_persona_ds != null) {

//			persona = new PersonaMein();

			Persona dst_persona = PersonaServicio.getInstance().obtenerPersona(
					id_persona_ds);
			persona.setCorreo(dst_persona.getCorreo());
			if (persona.getDireccion() == null){
				persona.setDireccion(dst_persona.getDireccion());				
			}
			persona.setIdentificacion(dst_persona.getIdentificacion());
			persona.setNombre_completo(dst_persona.getNombreCompleto());
			persona.setTelefono(dst_persona.getTelefono());
			String tipoIdentificacionCodigo = null; 
			Integer tipoDocumentoId = dst_persona.getTipo_documento(); 
			if (tipoDocumentoId != null) {
			    if (tipoDocumentoId == 1) { 
			        tipoIdentificacionCodigo = "C"; 
			    } else if (tipoDocumentoId == 3) {
			        tipoIdentificacionCodigo = "E"; 
			    } else if (tipoDocumentoId == 4) { 
			        tipoIdentificacionCodigo = "P"; 
			    } else if (tipoDocumentoId == 10) { 
			        tipoIdentificacionCodigo = "N"; 
			    } else {
			        throw new IllegalArgumentException("Tipo de documento inesperado o no mapeado para la identificaci�n: " + tipoDocumentoId);
			    }
			} else {
			    throw new IllegalArgumentException("Tipo de documento no puede ser nulo para mapeo.");
			}
			persona.setTipo_identificacion(tipoIdentificacionCodigo);
			persona.setPais_id(dst_persona.getPais_id());
			persona.setDepartamento_id(dst_persona.getDepartamento_id());
			persona.setMunicipio_id(dst_persona.getMunicipio_id());
		}

		return DS_SqlUtils.insert(insert, persona);

	}

	private boolean actualizarPersona(Integer id_persona, Integer id_carga,
			PersonaMein persona, Integer id_persona_ds) {

		String update = "UPDATE $PERSONA$ SET "
				+ "$PERSONA.TIPO IDENTIFICACION$ = #tipo_identificacion#, "
				+ "$PERSONA.IDENTIFICACION$ = #identificacion#, "
				+ "$PERSONA.FECHA EXPEDICION DOC$ = #fecha_expedicion_doc#, "
				+ "$PERSONA.DIRECCION$ = #direccion#, "
				+ "$PERSONA.NOMBRE COMPLETO$ = #nombre_completo#, "
				+ "$PERSONA.CORREO$ = #correo#, "
				+ "$PERSONA.CELULAR$ = #celular#, "
				+ "$PERSONA.TELEFONO$ = #telefono#, "
				+ "$PERSONA.TARJETA PROFESIONAL$ = #tarjeta_profesional#, "
				+ "IDCARGA = " + id_carga + ", $PERSONA.PAIS$=#pais_id#, $PERSONA.DEPARTAMENTO$=#departamento_id#, $PERSONA.MUNICIPIO$=#municipio_id#" + "WHERE ID = " + id_persona;

		if (id_persona_ds != null) {

			persona = new PersonaMein();

			Persona dst_persona = PersonaServicio.getInstance().obtenerPersona(
					id_persona_ds);
			persona.setCorreo(dst_persona.getCorreo());
			persona.setDireccion(dst_persona.getDireccion());
			persona.setIdentificacion(dst_persona.getIdentificacion());
			persona.setNombre_completo(dst_persona.getNombreCompleto());
			persona.setTelefono(dst_persona.getTelefono());
			String tipoIdentificacionCodigo = null; 
			Integer tipoDocumentoId = dst_persona.getTipo_documento(); 
			if (tipoDocumentoId != null) {
			    if (tipoDocumentoId == 1) { 
			        tipoIdentificacionCodigo = "C"; 
			    } else if (tipoDocumentoId == 3) {
			        tipoIdentificacionCodigo = "E"; 
			    } else if (tipoDocumentoId == 4) { 
			        tipoIdentificacionCodigo = "P"; 
			    } else if (tipoDocumentoId == 10) { 
			        tipoIdentificacionCodigo = "N"; 
			    } else {
			        throw new IllegalArgumentException("Tipo de documento inesperado o no mapeado para la identificaci�n: " + tipoDocumentoId);
			    }
			} else {
			    throw new IllegalArgumentException("Tipo de documento no puede ser nulo para mapeo.");
			}
			persona.setTipo_identificacion(tipoIdentificacionCodigo);
		}

		return DS_SqlUtils.update(update, persona);

	}

	public List<ValorLista> obtenerRevisoresPorUsuario(Integer id_usuario,
			Integer id_tipo_revisor) {
		ArrayList<ValorLista> lista = new ArrayList<ValorLista>();
		List<Persona> personas = PersonaServicio.getInstance()
				.obtenerRevisoresPorUsuario(id_usuario, id_tipo_revisor);
		for (Persona persona : personas) {
			ValorLista v = new ValorLista();
			v.setId(persona.getId_persona() + "");
			v.setNombre(persona.getNombreCompleto() + " CC "
					+ persona.getIdentificacion());
			lista.add(v);
		}
		return lista;
	}

	public List<ValorLista> obtenerAdministradoresClientePorUsuario(
			Integer id_usuario) {
		ArrayList<ValorLista> lista = new ArrayList<ValorLista>();
		List<Persona> personas = PersonaServicio.getInstance()
				.obtenerAdministradoresClientePorUsuario(id_usuario);
		for (Persona persona : personas) {
			ValorLista v = new ValorLista();
			v.setId(persona.getId_persona() + "");
			v.setNombre(persona.getNombreCompleto() + " CC "
					+ persona.getIdentificacion());
			lista.add(v);
		}
		return lista;
	}

	public List<ValorLista> obtenerPreparadoresNoAdminClientePorUsuario(
			Integer id_usuario) {
		ArrayList<ValorLista> lista = new ArrayList<ValorLista>();
		List<Persona> personas = PersonaServicio.getInstance()
				.obtenerPreparadoresNoAdminClientePorUsuario(id_usuario);
		for (Persona persona : personas) {
			ValorLista v = new ValorLista();
			v.setId(persona.getId_persona() + "");
			v.setNombre(persona.getNombreCompleto() + " CC "
					+ persona.getIdentificacion());
			lista.add(v);
		}
		return lista;
	}

	public Sociedad obtenerSociedad(String identificacion) {
		String sql_cr_persona = "SELECT $PERSONA$.ID FROM $PERSONA$ WHERE $PERSONA.TIPO IDENTIFICACION$ = $S(N)$ AND $PERSONA.IDENTIFICACION$ = $S("
				+ identificacion + ")$";

		Integer id_cr_persona = DS_SqlUtils.queryForObject(Integer.class,
				sql_cr_persona);

		if (id_cr_persona != null) {
			String sql = "SELECT * "
					+ "FROM $SOCIEDAD$ WHERE $SOCIEDAD.DATOS BASICOS$ = "
					+ id_cr_persona;

			return DS_SqlUtils.queryForObject(Sociedad.class, sql);

		}

		return null;
	}

	public List<String> obtenerDepartamentos() {

		SimpleLogger.setInfo("Obteniendo departamentos");

		String sql = "SELECT DISTINCT($DEPARTAMENTO CIUDAD.DEPARTAMENTO$) FROM $DEPARTAMENTO CIUDAD$";

		List<String> departamentos = DS_SqlUtils
				.queryForList(String.class, sql);

		Collections.sort(departamentos);

		return departamentos;
	}

	public String obtenerNombreTipoSolicitante(Integer solicitante) {
		String sql = "SELECT T.NOMBRE FROM $TIPO SOLICITANTE$ T WHERE T.ID= $I("
				+ solicitante + ")$";

		String nombreTipoSolicitantes = DS_SqlUtils.queryForObject(
				String.class, sql);

		return nombreTipoSolicitantes;
	}

	public String obtenerNombreTipoDocumento(Integer iddocumento) {
		String sql = "SELECT $TIPO DE DOCUMENTO.NOMBRE$ FROM $TIPO DE DOCUMENTO$ WHERE ID="
				+ iddocumento;

		String nombreTipoDocumento = DS_SqlUtils.queryForObject(String.class,
				sql);

		return nombreTipoDocumento;
	}

	public List<ValorLista> obtenerCiudadesPorDepartamento(String departamento) {
		String sql = "SELECT ID || '' ID, $DEPARTAMENTO CIUDAD.MUNICIPIO$ NOMBRE FROM $DEPARTAMENTO CIUDAD$ WHERE $DEPARTAMENTO CIUDAD.DEPARTAMENTO$ = #departamento# order by  $DEPARTAMENTO CIUDAD.MUNICIPIO$";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("departamento", departamento);

		List<ValorLista> ciudades_departamento = DS_SqlUtils.queryForList(
				ValorLista.class, sql, params);

		return ciudades_departamento;
	}

	public Integer obtenerIdDepartamentoCiudad(String departamento,
			String ciudad) {
		String sql = "SELECT $DEPARTAMENTO CIUDAD$.ID FROM $DEPARTAMENTO CIUDAD$ WHERE $DEPARTAMENTO CIUDAD.DEPARTAMENTO$ = #departamento# AND $DEPARTAMENTO CIUDAD.MUNICIPIO$ = #ciudad#";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("departamento", departamento);
		params.put("ciudad", ciudad);

		return DS_SqlUtils.queryForObject(Integer.class, sql, params);
	}

	public DepartamentoCiudad obtenerDepartamentoCiudad(Integer idDeptoCiudad) {
		String sql = "SELECT * FROM $DEPARTAMENTO CIUDAD$ WHERE ID="
				+ idDeptoCiudad;

		return DS_SqlUtils.queryForObject(DepartamentoCiudad.class, sql);
	}

	public List<ValorLista> obtenerPaisess() {
		String sql = "SELECT * FROM $PAIS$";

		List<ValorLista> paises = DS_SqlUtils.queryForList(ValorLista.class,
				sql);

		Collections.sort(paises);

		return paises;
	}

	public RepartoIntendencias obtenerReparto (Integer id_carga) {
		try {
			String categoria = DS_SqlUtils.queryForObject(String.class, 
					"select $sociedad.categoria$" + 
					"  from $solicitud near sociedad$ solicitud " +
					" left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id " +
					" where solicitud.idcarga = " + id_carga);
			Integer idDepartamentoCiudad = DS_SqlUtils.queryForObject(Integer.class,"select $sociedad.departamento dane$ from $solicitud near sociedad$ solicitud left join $sociedad$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id  where solicitud.idcarga = "+id_carga);
			Integer codDane = null;//validar en estructura solicitud near sociedad
			String sql = "";
			if(categoria != null && !categoria.equals("A")){
				codDane = DS_SqlUtils.queryForObject(Integer.class,"select $departamentos dane.codigo departamento$ from $departamentos dane$ where id = "+idDepartamentoCiudad);
				sql = "SELECT * FROM $REPARTO DE INTENDENCIAS$  WHERE $REPARTO DE INTENDENCIAS.CATEGORIA$ = $S("+categoria+")$ AND $REPARTO DE INTENDENCIAS.CODIGO DANE DEPARTAMENTO$ = $I("+codDane+")$";
			}else if(categoria != null && categoria.equals("A")){
				codDane = 11;//bogota
				sql = "SELECT * FROM $REPARTO DE INTENDENCIAS$  WHERE $REPARTO DE INTENDENCIAS.CATEGORIA$ = $S("+categoria+")$ AND $REPARTO DE INTENDENCIAS.CODIGO DANE DEPARTAMENTO$ = $I("+codDane+")$";
			}else{
				codDane = DS_SqlUtils.queryForObject(Integer.class,"select $departamentos dane.codigo departamento$ from $departamentos dane$ where id = "+idDepartamentoCiudad);
				sql = "SELECT * FROM $REPARTO PNNC$  WHERE $REPARTO PNNC.CODIGO DANE DEPARTAMENTO$ = $I("+codDane+")$";
			}
			return DS_SqlUtils.queryForObject(RepartoIntendencias.class, sql);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtener la intendencia para la solicitud " + id_carga);
			return null;
		}
		
	}
	
	public Integer obtenerDependencia (Integer id_carga) {
		
		try {
			RepartoIntendencias reparto = obtenerReparto(id_carga);
			if (reparto != null) {
				return reparto.getApvista_dependencias();
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtener la dependencia de la solicitud " + id_carga, e);
		}
		return null;
		
	}

	public List<DocumentoAdjunto> obtenerDocumentosPorTipoArchivo (Integer id_carga, Integer id_tipo_archivo) {
		List<DocumentoAdjunto> documentos = new ArrayList<>();

		List<ArchivoAdjunto> adjuntos = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCargaTipo(id_carga, id_tipo_archivo);
		
		if (adjuntos != null) {
			for (ArchivoAdjunto adjunto: adjuntos) {
				DocumentoAdjunto documento = new DocumentoAdjunto();
				
				documento.setDescripcion(adjunto.getDescripcion());
				documento.setUrl(ParametrosInicio.getProperty("pasante.endpoint.descargaarchivos")+"?id_archivo_adjunto="+adjunto.getId_archivo_adjunto());
				documento.setNombreArchivo(adjunto.getNombre_archivo());
				documento.setNombreInterno(adjunto.getRuta().substring(29));
				documento.setExtension(adjunto.getExtension_archivo());
				documento.setIdCarga(adjunto.getId_carga());
				documento.setIdArchivoInterno(adjunto.getId_archivo_adjunto().toString());
				
				documentos.add(documento);
			}
		}
		
		return documentos;
		
	}
	
	public List<DocumentoAdjunto> obtenerDocumentosPorTiposArchivo (Integer id_carga, Integer[] tipos_archivo) {
		List<DocumentoAdjunto> documentos = new ArrayList<>();

		for (int i = 0; i < tipos_archivo.length; i++) {
			List<ArchivoAdjunto> adjuntos = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCargaTipo(id_carga, tipos_archivo[i]);
			
			if (adjuntos != null) {
				for (ArchivoAdjunto adjunto: adjuntos) {
					DocumentoAdjunto documento = new DocumentoAdjunto();
					
					documento.setDescripcion(adjunto.getDescripcion());
					documento.setUrl(ParametrosInicio.getProperty("pasante.endpoint.descargaarchivos")+"?id_archivo_adjunto="+adjunto.getId_archivo_adjunto());
					documento.setNombreArchivo(adjunto.getNombre_archivo());
					documento.setNombreInterno(adjunto.getRuta().substring(29));
					documento.setExtension(adjunto.getExtension_archivo());
					documento.setIdCarga(adjunto.getId_carga());
					documento.setIdArchivoInterno(adjunto.getId_archivo_adjunto().toString());
					
					documentos.add(documento);
				}
			}
		}
		
		return documentos;
		
	}
	
	public Integer obtenerTipoSolicitudPorCargaNear (Integer id_carga) {
		return DS_SqlUtils.queryForObject(Integer.class, 
				"SELECT t.$TIPO SOLICITUD.TIPO SOLICITUD$ "
				+ "FROM $SOLICITUD NEAR SOCIEDAD$ s, "
				+ "$TIPO SOLICITUD$ t "
				+ "WHERE s.$SOLICITUD NEAR SOCIEDAD.TIPO SOLICITUD$ = t.ID "
				+ "AND s.IDCARGA = " + id_carga);
	}
	
	public List<ValorLista> obtenerTipoSolicitudPorCarga(Integer id_carga){
		try {
			Integer tipo_solicitud = null;
			List<ValorLista> lista_tipo_solicitud = new ArrayList<ValorLista>();
			
			ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Tipo de Solicitud");
			if(lista != null){
				List<ValorLista> lista_valor = ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
				
				if (Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga))) {
					tipo_solicitud = obtenerTipoSolicitudPorCargaNear(id_carga);
					
				} else if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga))) {
					RegimenInsolvencia solicitud = RegimenInsolvenciaServicio.getInstance().obtenerRegimenInsolvenciaBase(id_carga);
					
					if (solicitud != null) {
						tipo_solicitud = solicitud.getTipo_solicitud();
					}
				} else if (Constantes.ESTRUCTURA_RESPUESTA_REQUERIMIENTO.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga))) {
					RespuestaRequerimiento solicitud = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoBase(id_carga);
					
					if (solicitud != null) {
						tipo_solicitud = solicitud.getTipo_solicitud();
					}
				}
				
				for (ValorLista valor: lista_valor) {
					if (tipo_solicitud.equals(Integer.parseInt(valor.getId()))) {
						lista_tipo_solicitud.add(valor);
					}
				}
				
				return lista_tipo_solicitud;
				
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener Tipo solicitud para la carga " + id_carga, e);
		}
		return null;
	}
	
	public Map<Integer, String> obtenerMapaAdjuntosPorCarga (Integer id_carga, Integer tipo_solicitud ) {
		List<ArchivoAdjunto> adjuntos = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCarga(id_carga, false, tipo_solicitud);
		Map<Integer, String> mapaAdjuntos = new HashMap<>();
		
		for (ArchivoAdjunto adjunto: adjuntos) {
			String nombre_archivo = adjunto.getExtension_archivo() != null ? adjunto.getNombre_archivo() + "." + adjunto.getExtension_archivo(): adjunto.getNombre_archivo();
			mapaAdjuntos.put(adjunto.getId_tipo_archivo(), nombre_archivo);
		}
		
		return mapaAdjuntos;
	}
	
	public Map<Integer, String> obtenerMapaAdjuntosAdicionalesPorCarga (Integer id_carga, Integer tipo_solicitud ) {
		List<ArchivoAdjunto> adjuntos = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCarga(id_carga, false, tipo_solicitud);
	    Map<Integer, String> mapaAdjuntos = new HashMap<>();
	    
	    Map<Integer, Integer> contadores = new HashMap<>();
	    for (ArchivoAdjunto adjunto : adjuntos) {
	        String nombre_archivo = adjunto.getExtension_archivo() != null 
	            ? adjunto.getNombre_archivo() + "." + adjunto.getExtension_archivo() 
	            : adjunto.getNombre_archivo();
	        
	        Integer tipoArchivo = adjunto.getId_tipo_archivo();
	        
	        Integer indice = contadores.get(tipoArchivo);
	        if (indice == null) {
	            indice = 0; 
	        }
	        contadores.put(tipoArchivo, indice + 1); 
	        Integer claveUnica = tipoArchivo + indice;
	        
	        mapaAdjuntos.put(claveUnica, nombre_archivo);
	    }
	    
	    return mapaAdjuntos;
	}
	
}