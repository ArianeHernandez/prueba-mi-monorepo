package com.osmosyscol.datasuite.mein.servicios;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.lang.P;
import co.htsoft.commons.net.CallPage;
import co.htsoft.commons.util.SMessage;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.NotificacionSociedad;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.S3Servicio;
import com.osmosyscol.datasuite.mein.domain.SignAppRegistroIn;
import com.osmosyscol.datasuite.mein.domain.SignAppRegistroOut;
import com.osmosyscol.datasuite.mein.dtos.UsoToken;
import com.osmosyscol.datasuite.webdata.logica.dto.DatosRepresentante;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class SignAppServicio {

	private static SignAppServicio instance = new SignAppServicio();
	private static CallPage CALLPAGE = new CallPage();

	
	private static Map<Integer, String> MAPEO_TIPO_DOCUMENTO = new HashMap<Integer, String>();
	static{
		MAPEO_TIPO_DOCUMENTO.put(1, "CC");
		MAPEO_TIPO_DOCUMENTO.put(2, "TI");
		MAPEO_TIPO_DOCUMENTO.put(3, "CE");
		MAPEO_TIPO_DOCUMENTO.put(4, "PA");
	}	
	private SignAppServicio(){
	}
	
	public static SignAppServicio getInstance() {
		return instance;
	}

	public SMessage validarToken(String token, Integer id_persona) {
		try {
			Persona persona = PersonaServicio.getInstance().obtenerPersona(id_persona);
			
			UsoToken usoToken = new UsoToken();
//			usoToken.setCelular(persona.getTelefono());
//			usoToken.setCorreo(persona.getCorreo());
			usoToken.setNumero_documento(persona.getIdentificacion());
			usoToken.setTipo_documento(homologarTipoDocumento(persona.getTipo_documento()));
			usoToken.setToken(token);
			
			
			
			String siggnAppApiEndpoint = ParametrosInicio.getProperty("signappapi.endpoint");
			if(StringUtils.isNotBlank(siggnAppApiEndpoint)){
				SimpleLogger.setDebug("Validando token " + siggnAppApiEndpoint +  " "  + token );
//				String resp = CALLPAGE.callPost(siggnAppApiEndpoint + "/token/usar", new Gson().toJson(usoToken), null);
				String resp = CALLPAGE.callHttps(siggnAppApiEndpoint + "/token/usar", new Gson().toJson(usoToken), null, "POST");
				
				SMessage atr = new Gson().fromJson(resp, SMessage.class);
				SimpleLogger.setDebug("Validar token: " + atr.getValid() +  " " + atr.getMsg());
				return atr;
			} else {
				SimpleLogger.setInfo("No se encuentra configurado el endpoint signappapi.endpoint para validar token.");
				return new SMessage(true, "No se encuentra configurado el endpoint para validar token.");
			}
			
		} catch (Throwable e) {
			SimpleLogger.setError("SignAppServicio.validarToken: Ocurrio un error ", e);
			return new SMessage(false, "Ocurrio un error al validar el token.");
		}
	}
	
	
	public SMessage validarTokenSinSesion(String identificacion, Integer tipo_identificacion, String token){
		try {
			UsoToken usoToken = new UsoToken();
			usoToken.setNumero_documento(identificacion);
			usoToken.setTipo_documento(homologarTipoDocumento(tipo_identificacion));
			usoToken.setToken(token);
			
			String siggnAppApiEndpoint = ParametrosInicio.getProperty("signappapi.endpoint");
			if(StringUtils.isNotBlank(siggnAppApiEndpoint)){
				//String resp = CALLPAGE.callPost(siggnAppApiEndpoint + "/token/usar", new Gson().toJson(usoToken), null);
				String resp = CALLPAGE.callHttps(siggnAppApiEndpoint + "/token/usar", new Gson().toJson(usoToken), null, "POST");
				
				SMessage atr = new Gson().fromJson(resp, SMessage.class);
				P.println("Validar token: " + token);
				P.println(atr);
				return atr;
			} else {
				SimpleLogger.setInfo("No se encuentra configurado el endpoint signappapi.endpoint para validar token.");
				return new SMessage(true, "No se encuentra configurado el endpoint para validar token.");
			}
			
		} catch (Throwable e) {
			SimpleLogger.setError("SignAppServicio.validarTokenSinSesion: Ocurrio un error ", e);
			return new SMessage(false, "Ocurrio un error al validar el token.");
		}
	}
	
	private String homologarTipoDocumento(int id_tipo_documento){
		if(Constantes.TIPODOCUMENTO_NIT == id_tipo_documento){
			return "NIT";
		} else if(id_tipo_documento == Constantes.TIPODOCUMENTO_CEXTRANJERIA){
			return "CE";
		}else if(id_tipo_documento == Constantes.TIPODOCUMENTO_PASAPORTE){
			return "PA";
		}else {
			return "CC";
		}
	}
	

	public SMessage registrarUsuario(SolicitudEnrolamiento solicitud) {
		
		try {
			Gson gson = new Gson();
			SignAppRegistroIn in = new SignAppRegistroIn();
			DatosRepresentante rep = solicitud.getDatos_representante();
			
			in.setApellidos(rep.getApellidos());
			in.setCelular(rep.getCelular());
			in.setCorreo(rep.getCorreo_electronico());
//			in.setFechaExpedicion(DateFormatUtils.format(rep.getFecha_expedicion(), "yyyy-MM-dd"));
//			in.setFechaNacimiento(DateFormatUtils.format(rep.getFecha_nacimiento(), "yyyy-MM-dd"));
			in.setFechaExpedicion(rep.getFecha_expedicion());
			in.setFechaNacimiento(rep.getFecha_nacimiento());
			in.setLugarNacimiento(rep.getLugar_nacimiento());
			in.setNombres(rep.getNombres());
			in.setNumeroDocumento(rep.getNumero_identificacion());
			in.setSexo(rep.getSexo());
			in.setTipoDocumento(MAPEO_TIPO_DOCUMENTO.get(rep.getTipoDocCPSuite()));
			
			String fotoPerfil = codificarAdjunto(6, solicitud.getIdcarga());
			String fotoFrontal = codificarAdjunto(7, solicitud.getIdcarga());
			String fotoPosterior = codificarAdjunto(8, solicitud.getIdcarga());
			
			in.setFotoPerfil(fotoPerfil);
			in.setFotoDocumentoFrontal(fotoFrontal);
			in.setFotoDocumentoPosterior(fotoPosterior);
			
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			
			String siggnAppApiEndpoint = ParametrosInicio.getProperty("signappapi.endpoint");
			if (StringUtils.isNotBlank(siggnAppApiEndpoint)) {

				//String response = CALLPAGE.callPost(siggnAppApiEndpoint + "/cnt/registro", gson.toJson(in), headers);
				String response = CALLPAGE.callHttps(siggnAppApiEndpoint + "/cnt/registro", gson.toJson(in), headers, "POST");
				SignAppRegistroOut resp = gson.fromJson(response, SignAppRegistroOut.class);
				return new SMessage("000".equals(resp.getCodigoRespuesta()), resp.getCodigoRespuesta() + " - " + resp.getDescripcion());
			} else {
				SimpleLogger.setInfo("No se encuentra configurado el endpoint signappapi.endpoint para validar token.");
				return new SMessage(false, "No se encuentra configurado el endpoint para validar token.");
			}
		} catch (Throwable e) {
			SimpleLogger.setError("Error registrando en signapp", e);
			return new SMessage(false, "Error registrando en signapp");
		}
		
	}

	private String codificarAdjunto(Integer tipoArchivo, Integer idcarga) {
		ArchivoAdjuntoServicio adjser = ArchivoAdjuntoServicio.getInstance();
		List<ArchivoAdjunto> adjuntos = adjser.obtenerAdjuntosSegunTipologia(idcarga, "A", false, tipoArchivo);
		
		if (!CollectionUtils.isEmpty(adjuntos)) {
			ArchivoAdjunto adjunto = adjuntos.get(0);
			File file = ArchivoAdjuntoServicio.getInstance().obtenerArchivo(adjunto.getRuta());
			return FileUtils.encodeBase64(file);
		}
		
		return null;
	}
	
}
