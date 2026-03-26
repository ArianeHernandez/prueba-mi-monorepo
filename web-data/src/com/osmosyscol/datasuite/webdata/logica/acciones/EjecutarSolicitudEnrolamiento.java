package com.osmosyscol.datasuite.webdata.logica.acciones;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.common.Logger;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.mail.EmailException;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Liberador;
import com.osmosyscol.datasuite.logica.dto.LiberadorTipoProceso;
import com.osmosyscol.datasuite.logica.dto.Notificacion;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Preparador;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.dto.TipoProcesoRol;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.NegocioServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.logica.servicios.TipoDocumentoServicio;
import com.osmosyscol.datasuite.mein.domain.Sociedad;
import com.osmosyscol.datasuite.mein.dtos.ListadoErrores;
import com.osmosyscol.datasuite.mein.servicios.rest.client.SigsAppRestClient;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.LiberadorServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.PreparadorServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class EjecutarSolicitudEnrolamiento implements AccionCarga {
	
	final static Logger logger = Logger.getLogger(SigsAppRestClient.class); 

	private static final int NEGOCIO_POR_DEFECTO = 1;
	private static final int PESO_LIBERADOR_DEFECTO = 10;
	private static final int TIPO_LIBERADOR_DEFECTO = 1;

	public SMessage ejecutar(Integer id_carga) {
		Integer numeroError = null; 
		SolicitudEnrolamiento solicitud = null;
		try {

			Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);

			solicitud = SolicitudEnrolamientoServicio.getInstance().obtenerSolicitudEnrolamiento(id_carga);

			SimpleLogger.setInfo("Ejecutando Solicitud Enrolamiento para solicitud " + solicitud.toString());

			solicitud.getDatos_representante().setTipoDocCPSuite(Integer.parseInt(solicitud.getDatos_representante().getTipo_identificacion_obj().getCodigo()));

			Sociedad sociedad = new Sociedad();
			sociedad.setNit(solicitud.getNumero_identificacion());
			sociedad.setRazon_social(solicitud.getRazon_social());

			String mensaje = null;
			

			Persona personaActual = actualizarPersona(sociedad, solicitud);

			if (personaActual == null) {
				numeroError = 6; 
				mensaje = consultarError(numeroError);
				SimpleLogger.setError("Persona no valida para la carga " + id_carga);
				DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.ESTADO OPERACION$ = $S("+ mensaje+ ")$ where ID = "+ solicitud.getId());
				return new SMessage(true, mensaje);
			}

			Usuario usuario = insertarUsuario(personaActual);

			Persona persona = null;

			Credencial credencial = new Credencial();
			credencial.setLogin(solicitud.getLogin());

			SimpleLogger.setInfo("CREDENCIAL" + credencial);

			Integer resp = null;

			String rol = null;

			persona = new Persona();
			persona.setActivo(null);
			persona.setCorreo(solicitud.getDatos_representante().getCorreo_electronico());

			persona.setTipo_documento(Integer.parseInt(solicitud.getDatos_representante().getTipo_identificacion_obj().getCodigo()));

			persona.setTipo_persona("N");
			persona.setTelefono(solicitud.getDatos_representante().getCelular());
			persona.setIdentificacion(solicitud.getDatos_representante().getNumero_identificacion());
			persona.setGenero("M");

			persona.setNombre(solicitud.getDatos_representante().getNombres());
			persona.setApellido(solicitud.getDatos_representante().getApellidos());

			if (carga.getId_formato().equals(SolicitudEnrolamientoServicio.ID_FORMATO_AC) 
					|| carga.getId_formato().equals(SolicitudEnrolamientoServicio.ID_FORMATO_REGISTRO_SIGN_APP)) {
				rol = Constantes.ROL_ADMIN_CLIENTE;
			} else if (carga.getId_formato().equals(SolicitudEnrolamientoServicio.ID_FORMATO_LC)) {
				rol = Constantes.ROL_LIBERADOR;
			}

			resp = PersonaServicio.getInstance().guardarPersonaUsuarioRol(persona, credencial, usuario.getId_usuario(), rol, null);
			
			SimpleLogger.setInfo("resp " + resp);
			
			if(resp != 1){
				numeroError = resp; 
				mensaje = consultarError(numeroError);
				SimpleLogger.setError(mensaje + " en la solicitud " + id_carga);
				DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.ESTADO OPERACION$ = $S("+ mensaje+ ")$ where ID = "+ solicitud.getId());
				return new SMessage(true, mensaje);
			}

			SimpleLogger.setInfo("guardarPersonaUsuarioRol id_carga: " + id_carga + " Respuesta " + resp);
			
			crearPersona(solicitud, usuario, persona, credencial, resp);

			SimpleLogger.setInfo("crearProcesoPorDefecto " + credencial);
			
			String tiposSolicitante = ParametrosInicio.getProperty("habilitarTiposSolicitante");
			
			if(StringUtils.esVerdad(tiposSolicitante)) {
				crearProcesoPorDefecto(usuario.getId_usuario(),persona.getIdentificacion(), persona.getTipo_documento(), solicitud.getTipo_solicitante());
			} else {
				crearProcesoPorDefecto(usuario.getId_usuario(),persona.getIdentificacion(), persona.getTipo_documento(), null);
			}
			

			if (solicitud.getDatos_representante().getCorreo_electronico() != null) {
				try{
					enviarCorreo(solicitud);
				}catch (Exception ex){
					SimpleLogger.setError("Error enviando el correo");
				}
			}
			
			String queryAct = "update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.ESTADO OPERACION$ = $S(OK)$ "
							+ " where ID = " + solicitud.getId();
			SimpleLogger.setInfo("Actualizacion Enrolamiento" +  queryAct);

			DS_SqlUtils.update(queryAct, solicitud);

			CargaServicio.getInstance().aplicarCarga(id_carga);
			
			return new SMessage(true, "OK");

		} catch (Exception e) {
			SimpleLogger.setError("Error al ejecutarSolicitudEnrolamiento ", e);
			if (solicitud != null) {
				numeroError = 7;
				DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.ESTADO OPERACION$ = $S(" + consultarError(numeroError) + ")$ where ID = " + solicitud.getId());
				DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.MENSAJE ERROR$ = $S(" + getStackTraceAsString(e) + ")$ where ID = " + solicitud.getId());

			}

			return new SMessage(false, "");
		}

	}
	
	public String consultarError(Integer id) {
		String nombre = null;
		try{
			String consulta = "SELECT * FROM $SOLICITUD ENROLAMIENTO ERRORES$ WHERE ID = $I("+id+")$";
			
			
			List<ListadoErrores> errores = DS_SqlUtils.queryForList(ListadoErrores.class, consulta);
			
			for(ListadoErrores error : errores ){
				nombre = error.getNombre();
			}
			
			return nombre;
		} catch (Exception e){
			SimpleLogger.setError("Error consultando errores");
		}
		
		return nombre;
	}
	
	private static String getStackTraceAsString(Throwable throwable) {
	        StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	        throwable.printStackTrace(pw);
	        return sw.toString();
	    }

	public void crearProcesoPorDefecto(Integer idUsuario, String identificacion, Integer tipoDocumento, Integer tipo_solicitante) throws Exception {

		Proceso procesoNuevo;
		SimpleLogger.setInfo("crearProcesoPorDefecto " + idUsuario);

		List<Proceso> procesosUsu = ProcesoServicio.getInstance().listarProcesos(idUsuario, NEGOCIO_POR_DEFECTO);
		List<Proceso> procesosUsuario = new ArrayList<Proceso>();
		if (!CollectionUtils.isEmpty(procesosUsu)) {
			for (Proceso proceso : procesosUsu) {
				if (idUsuario.equals(proceso.getId_usuario())) {
					procesosUsuario.add(proceso);
				}
			}
		}

		if (CollectionUtils.isNotEmpty(procesosUsuario)) {
			procesoNuevo = procesosUsu.get(0);
			SimpleLogger.setInfo("Proceso por defecto existe " + procesoNuevo.getId_proceso());
		} else {
			List<Proceso> procesos = tipo_solicitante == null ? ProcesoServicio.getInstance().listarProcesos(null, null) :
					ProcesoServicio.getInstance().listarProcesosDefectoTipoSolicitante(tipo_solicitante);
			System.out.println("Copiando procesos: " + procesos.size());
			for (Proceso proceso : procesos) {
				if (proceso.getId_usuario() == null) {
					SimpleLogger.setInfo("Proceso por defecto " + proceso.getId_proceso());
					
					Integer out = ProcesoServicio.getInstance().crearProceso(idUsuario, NEGOCIO_POR_DEFECTO, proceso.getNombre(), proceso.getId_tipo_proceso());
					procesoNuevo = ProcesoServicio.getInstance().obtenerProceso(out);
					procesoNuevo.setId_grupoformato(proceso.getId_grupoformato());
					procesoNuevo.setId_formato_salida(proceso.getId_formato_salida());
					procesoNuevo.setPeso(proceso.getPeso());
					procesoNuevo.setId_usuario(idUsuario);

					ProcesoServicio.getInstance().actualizarProceso(procesoNuevo);
					List<Formato> formatos = FormatoServicio.getInstance().obtenerFormatosProceso(proceso.getId_proceso());
					if (formatos != null) {
						SimpleLogger.setDebug("formatos" + formatos.size());
					}
					for (Formato formato : formatos) {
						ProcesoServicio.getInstance().agregarFormato(formato.getId_formato(),procesoNuevo.getId_proceso());
					}
					procesosUsuario.add(procesoNuevo);
				}
			}

		}

		SimpleLogger.setDebug("Asociando a  procesos: " + procesosUsuario.size());
		for (Proceso proceso : procesosUsuario) {

			Persona persona = PersonaServicio.getInstance().obtenerPersonaPorIdentificacion(identificacion,tipoDocumento);

			List<Preparador> preparadores_usuario = PreparadorServicio.getInstance().obtenerPreparadores(idUsuario,proceso.getId_tipo_proceso());
			SimpleLogger.setDebug("Preparadores " + (preparadores_usuario != null ? preparadores_usuario.size() : "0"));
			for (Preparador preparador : preparadores_usuario) {
				if (preparador.getId_persona().equals(persona.getId_persona())) {
					SimpleLogger.setDebug("Asociando preparador: " + proceso.getId_proceso() + " - " + preparador.getId_preparador());
					PreparadorServicio.getInstance().asociarPreparador(proceso.getId_proceso(),preparador.getId_preparador());
				}
			}
			List<Liberador> liberadores_usuario = LiberadorServicio.getInstance().obtenerLiberadores(idUsuario,proceso.getId_tipo_proceso());
			SimpleLogger.setDebug("liberadores_usuario " + (liberadores_usuario != null ? liberadores_usuario.size(): "0"));

			for (Liberador liberador : liberadores_usuario) {
				if (liberador.getId_persona().equals(persona.getId_persona())) {
					SimpleLogger.setDebug("Asociando liberador: " + proceso.getId_proceso() + " - " + liberador.getId_liberador());
					LiberadorServicio.getInstance().asociarLiberador(proceso.getId_proceso(),liberador.getId_liberador());
				}
			}
		}

	}

	private void crearPersona(SolicitudEnrolamiento solicitud, Usuario usuario, Persona persona, Credencial credencial, Integer resp) {

		PersonaServicio.getInstance().guardarPersonaUsuarioRol(persona, credencial, usuario.getId_usuario(), Constantes.ROL_LIBERADOR, null);

		PersonaServicio.getInstance().guardarPersonaUsuarioRol(persona, credencial, usuario.getId_usuario(), Constantes.ROL_PREPARADOR,
				null);

		if (resp.intValue() == 1) {
			Persona personaLiberador = PersonaServicio.getInstance().obtenerPersonaPorIdentificacion(persona.getIdentificacion(), persona.getTipo_documento());
			List<TipoProcesoRol> listadoTipoProcesoRol = new ArrayList<TipoProcesoRol>();

			TipoProcesoRol tipoProcesoRol = new TipoProcesoRol();
			tipoProcesoRol.setRol(Constantes.ROL_LIBERADOR);
			tipoProcesoRol.setId_persona(personaLiberador.getId_persona());
			tipoProcesoRol.setId_tipo_proceso(TIPO_LIBERADOR_DEFECTO);
			listadoTipoProcesoRol.add(tipoProcesoRol);

			PersonaServicio.getInstance().guardarProcesoRol(listadoTipoProcesoRol, usuario.getId_usuario(), persona.getIdentificacion(), persona.getTipo_documento());

			List<LiberadorTipoProceso> listado = new ArrayList<LiberadorTipoProceso>();
			LiberadorTipoProceso tipoLiberador = new LiberadorTipoProceso();
			tipoLiberador.setId_tipo_proceso(TIPO_LIBERADOR_DEFECTO);
			tipoLiberador.setPeso(PESO_LIBERADOR_DEFECTO);
			tipoLiberador.setValor_max_carga(solicitud.getValor_lote());
			tipoLiberador.setValor_max_individual(solicitud.getValor_individual());
			listado.add(tipoLiberador);

			LiberadorServicio.getInstance().actualizarLiberadorTipoProceso(listado, usuario.getId_usuario(), persona.getIdentificacion(), persona.getTipo_documento());

			tipoProcesoRol.setRol(Constantes.ROL_PREPARADOR);

			PersonaServicio.getInstance().guardarProcesoRol(listadoTipoProcesoRol, usuario.getId_usuario(), persona.getIdentificacion(), persona.getTipo_documento());

		}
	}

	private void enviarCorreo(SolicitudEnrolamiento solicitud) throws EmailException, MalformedURLException {
		
		String correo = solicitud.getDatos_representante().getCorreo_electronico();
		String nombre = solicitud.getDatos_representante().getNombres() + " " + solicitud.getDatos_representante().getApellidos();

		Map<String, String> archivos = new HashMap<String, String>();
		Notificacion notificacion = new Notificacion();
		notificacion.setTitulo("Notificación Recibido Solicitud Enrolamiento");
		notificacion.setFecha_envio(new Date());
		String texto = "";
		if (solicitud.getDatos_representante() != null) {
			texto = "La solicitud de enrolamiento del usuario " + solicitud.getDatos_representante().getNombres() 
					+ " " + solicitud.getDatos_representante().getApellidos() + "  ha sido Recibida.";
		} else {
			texto = "La solicitud de enrolamiento del usuario " + solicitud.getNombres() + " " + solicitud.getApellidos() + "  ha sido Recibida.";
		}

		notificacion.setContenido(texto);
		Map<String, String> parametros = JavaToXML.objectToParameters("notificacion", notificacion);
		archivos.put("#logo#", "images/back/logo1.png");
		//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");

		EnviaMails.enviar(correo, nombre, "Notificación Recibido", "notificacion/envioNotificacionSolicitudEnrolamiento.email", archivos, parametros);
	}

	private static Usuario insertarUsuario(Persona persona) {

		List<TipoDocumento> listaTiposDocumento = TipoDocumentoServicio.getInstance().listarTiposDocumento();
		Map<Integer, String> prefijosDocumento = new HashMap<Integer, String>();
		for (TipoDocumento i : listaTiposDocumento) {
			prefijosDocumento.put(i.getId(), i.getPrefijo());
		}

		Usuario existeUsuario = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(persona.getIdentificacion(),persona.getTipo_documento());

		boolean esNuevo = existeUsuario == null;
		existeUsuario = esNuevo ? new Usuario() : existeUsuario;

		if (esNuevo) {
			existeUsuario.setActivo("A");
			existeUsuario.setId_persona(persona.getId_persona());
			existeUsuario.setIdentificacion(persona.getIdentificacion());
			existeUsuario.setNombre(persona.getNombre());
			existeUsuario.setTipo_documento(prefijosDocumento.get(persona.getTipo_documento()));
			try {
				Integer idUsuario = UsuarioServicio.getInstance().guardarUsuario(existeUsuario);
				existeUsuario = UsuarioServicio.getInstance().obtenerUsuario(idUsuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		boolean existeUsuarioNegocio = NegocioServicio.getInstance().existeUsuarioNegocio(existeUsuario.getId_usuario(), NEGOCIO_POR_DEFECTO);
		if(!existeUsuarioNegocio){
			boolean exito = NegocioServicio.getInstance().guardarUsuarioNegocio(existeUsuario.getId_usuario(), NEGOCIO_POR_DEFECTO);

			if (!exito) {
				SimpleLogger.setError("No se puede asociar negocio a usuario: " + existeUsuario.getId_usuario());
			}
		}

		return existeUsuario;
	}

	private static Persona actualizarPersona(Sociedad sociedad, SolicitudEnrolamiento solicitud) {

		SimpleLogger.setDebug("SINCRONIZACION DE PERSONA...");

		int codigoTipoDoc = Integer.parseInt(solicitud.getTipo_identificacion_obj().getCodigo());
				
		Persona existePersona = PersonaServicio.getInstance().obtenerPersonaPorIdentificacion(sociedad.getNit(), codigoTipoDoc);

		boolean esNuevo = existePersona == null;
		existePersona = esNuevo ? new Persona() : existePersona;

		SimpleLogger.setDebug("PERSONA ACTUAL..." + esNuevo);

		existePersona.setActivo("A");
		existePersona.setCorreo(solicitud.getCorreo_electronico());
		existePersona.setDireccion("No registra");
		existePersona.setIdentificacion(solicitud.getNumero_identificacion());
		existePersona.setNombre(sociedad.getRazon_social());
		existePersona.setTelefono(solicitud.getDatos_representante().getCelular());
		existePersona.setTipo_documento(codigoTipoDoc);

		if (existePersona.getTipo_documento() == 1 || existePersona.getTipo_documento() == 2) {

			if (StringUtils.isBlank(existePersona.getApellido())) {
				existePersona.setApellido("Apl");
			}

			existePersona.setTipo_persona("N");
		} else {
			existePersona.setTipo_persona("J");
		}

		if (existePersona.getGenero() == null) {
			existePersona.setGenero("M");
		}

		if (esNuevo) {
			existePersona = PersonaServicio.getInstance().guardarPersonaSimple(existePersona);
		} else {
			existePersona = PersonaServicio.getInstance().actualizarPersona(existePersona);
		}

		SimpleLogger.setDebug("PERSONA GUARDADA...");

		return existePersona;
	}
	
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}
	

}
