package com.osmosyscol.datasuite.near.webdata;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.logica.dto.Notificacion;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.ContenidoServicio;
import com.osmosyscol.datasuite.mein.dtos.Deudor;
import com.osmosyscol.datasuite.mein.dtos.Imagen;
import com.osmosyscol.datasuite.mein.dtos.PdfConverterResponse;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.TokenMapping;
import com.osmosyscol.datasuite.mein.dtos.TokenReplaceResponse;
import com.osmosyscol.datasuite.mein.servicios.RespuestaRequerimientoServicio;
import com.osmosyscol.datasuite.mein.servicios.rest.client.OfficeToolsRestClient;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class NotificarNumeroRadicadoRtaReq implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {

		RespuestaRequerimiento solicitud = null;
		
		try {
			
			solicitud = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoCompleto(id_carga);
			
			if (solicitud == null) {
				SimpleLogger.setInfo("No se encuentra una solicitud de respuesta requerimiento con el id " + id_carga);
				return new SMessage(false, "");
			}
			
			Deudor deudor = solicitud.getDeudor_obj();
			
			Contenido contenido = ContenidoServicio.getInstance().obtenerContenido("General", "NOTIFICACION RADICADO");
			
			String rutabase = ParametrosInicio.getProperty("adjuntos.carpeta") + "/ADJUNTOS/NOTIFICACION/RADICADO/";
			Contenido nombre_archivo = null;
			if (solicitud.getIntendencia_regional() != null) {
				nombre_archivo = ContenidoServicio.getInstance().obtenerContenido("General", "ARCHIVO_NOTIFICACION_RADICADO_" + solicitud.getIntendencia_regional());				
			} else {
				nombre_archivo = ContenidoServicio.getInstance().obtenerContenido("General", "ARCHIVO_NOTIFICACION_RADICADO");
			}
			
			String mensaje = contenido.getTexto();
			mensaje = mensaje.replace("#numero_radicado#", solicitud.getNumero_radicado_postal());
			mensaje = mensaje.replace("#nombre_proceso#", "Respuesta Requerimiento");
			mensaje = mensaje.replace("#id_carga#", id_carga.toString());
			mensaje = mensaje.replace("#fecha_radicado#", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			
			Notificacion notificacion = new Notificacion();
			notificacion.setTitulo("Notificaciónn de Radicado");
			notificacion.setFecha_envio(new Date());
			notificacion.setContenido(mensaje);
			Map<String, String> parametros = JavaToXML.objectToParameters(
					"notificacion", notificacion);
			Map<String, String> archivos = new HashMap<String, String>();
			archivos.put("#logo#", "images/back/logo1.png");
			//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
			

			TokenMapping[] tokens = new TokenMapping[]{
				new TokenMapping("@{nombre_deudor}", deudor.getRazon_social()),
				new TokenMapping("@{identificacion_deudor}", deudor.getNumero_identificacion()),
				new TokenMapping("@{intendencia}", solicitud.getIntendencia_regional_obj().getNombre()),
				new TokenMapping("@{nombre_proceso}", "Respuesta Requerimiento"),
				new TokenMapping("@{numero_radicado}", solicitud.getNumero_radicado_postal())
			};
			
			TokenReplaceResponse tokenResponse = OfficeToolsRestClient.getInstance().applyTokens(rutabase + nombre_archivo.getTexto(), tokens);
			
			if (tokenResponse == null || tokenResponse.getWordbase64() == null) {
				SimpleLogger.setError("NotificarNumeroRadicadoRtaReq: Error al obtener plantilla parametrizada para la carga " + id_carga);
				return new SMessage(false, "Error al obtener plantilla parametrizada");
			}
			
			
			PdfConverterResponse pdfResponse = OfficeToolsRestClient.getInstance().pdfConverterB64(tokenResponse.getWordbase64(), new ArrayList<Imagen>());
			
			
			if (pdfResponse == null || pdfResponse.getPdf_base64() == null) {
				SimpleLogger.setError("NotificarNumeroRadicadoRtaReq: Error al obtener plantilla en pdf para la carga " + id_carga);
				return new SMessage(false, "Error al obtener plantilla en pdf");
			}
			
			Contenido nombre_archivo_salida = null;
			if (solicitud.getIntendencia_regional() != null) {
				nombre_archivo_salida = ContenidoServicio.getInstance().obtenerContenido("General", "ARCHIVO_NOTIFICACION_RADICADO_SALIDA_" + solicitud.getIntendencia_regional());				
			} else {
				nombre_archivo_salida = ContenidoServicio.getInstance().obtenerContenido("General", "ARCHIVO_NOTIFICACION_RADICADO_SALIDA");
			}
			
			String rutaAdjuntoParametrizado = ParametrosInicio.getProperty("adjuntos.carpeta") + "/ADJUNTOS/NOTIFICACION/RADICADO/" + id_carga + " " + nombre_archivo_salida.getTexto();
			
			File file = FileUtils.toFile(pdfResponse.getPdf_base64(), rutaAdjuntoParametrizado);
			
			archivos.put("#documento#", "file://" + rutaAdjuntoParametrizado);
			
			EnviaMails.enviar(deudor.getCorreo_electronico(), deudor.getRazon_social(), "Radicación Respuesta a Requerimiento",
					"notificacion/envioNotificacion.email",
					archivos, parametros);
			
			file.delete();
			
			return new SMessage(true, "");
			
		} catch (Exception e) {
			SimpleLogger.setError("NotificarNumeroRadicadoRtaReq: Error enviando la notificacion:", e);

			return new SMessage(false, "");
		}
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

	
	
}
