package com.osmosyscol.datasuite.webdata.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cocoon.environment.Session;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.ServletUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.InstanciaServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class SubirArchAdjuntoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doUpload(request, response);
		} catch (FileUploadException e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	private void doUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {

		Map<String, Object> param = ServletUtils.getParameters(request);

		try {

			Session session = CocoonUtils.getCocoonSession(request);

			FileItem fileItem = (FileItem) param.get("caja_archivo_adjunto");

			Integer id_carga = Integer.parseInt((String) param.get("id_carga"));

			String rutabase = ParametrosInicio.getProperty("adjuntos.carpeta") + "/ADJUNTOS";

			String id_archivo_adjunto = (String) param.get("id_archivo_adjunto");
			Integer id = null;
			
			if (id_archivo_adjunto != null) {
				id = Integer.parseInt(id_archivo_adjunto);
			} else {
				id = ArchivoAdjuntoServicio.getInstance().obtenerSiguiente();
			}
			

			String id_instancia_str = StringUtils.trimToNull((String) param.get("id_instancia"));
			
			
			String descripcion = (String) param.get("descripcion_archivo_adj");
			descripcion = URLDecoder.decode(descripcion,"UTF-8");
			
			String interno = (String) param.get("interno_nota_guardar");
			
			Integer id_tipo_archivo = null;
			if (StringUtils.isNotEmpty((String)param.get("id_tipo_archivo"))) {
				id_tipo_archivo = Integer.parseInt((String) param.get("id_tipo_archivo"));
			}

			if (StringUtils.isBlank(interno) || session.getAttribute("id_usuario") != null) {
				interno = "N";
			}

			if (fileItem != null) {
				
				String nombre = FilenameUtils.getBaseName(fileItem.getName());
				SimpleLogger.setInfo( "SubirArchAdjuntoServlet Carga " + id_carga + " Nombre adjunto : " + nombre );
				String cs = charset(nombre, new String[] { "ISO-8859-1", "UTF-8" });
				if (StandardCharsets.ISO_8859_1.name().equals(cs)) {
					nombre = new String (nombre.getBytes (StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
					SimpleLogger.setInfo( "SubirArchAdjuntoServlet Nombre adjunto charset: " + nombre );
					
				}
				
				String extension = FilenameUtils.getExtension(fileItem.getName());

				SimpleLogger.setInfo("SubirArchAdjuntoServlet Carga " + id_carga + " Almacenando archivo " + nombre + " como " + "archivo_" + id_carga.toString() + "_" + id + ".osm");
				String ruta = ServletUtils.copyFileItem(rutabase, fileItem, "archivo_" + id_carga.toString() + "_" + id + ".osm");

				Long longBytes = fileItem.getSize();
				File f = new File(ruta);
				SimpleLogger.setInfo("SubirArchAdjuntoServlet Carga " + id_carga + " Validando almacenamiento del archivo " + nombre + " - " + f.exists());
				Long checkSum = FileUtils.checksumCRC32(f);

				ArchivoAdjunto adjunto = new ArchivoAdjunto();
				adjunto.setDescripcion(descripcion);
				adjunto.setInterno(interno);
				adjunto.setExtension_archivo(extension);
				adjunto.setFecha_subida(HorarioServicio.getInstance().obtenerFechaActual());
				adjunto.setId_archivo_adjunto(id);
				adjunto.setId_carga(id_carga);
				adjunto.setLong_bytes(longBytes.toString());
				adjunto.setNombre_archivo(nombre);
				adjunto.setResumen_hash(checkSum.toString());
				adjunto.setRuta(ruta);
				adjunto.setEstado("A"); // por defecto es autorizado
				adjunto.setId_tipo_archivo(id_tipo_archivo);
				
				if(id_instancia_str != null){

					Integer id_instancia = id_instancia_str == null?null:new Integer(id_instancia_str);
					Instancia instancia = InstanciaServicio.getInstance().obtenerInstancia(id_instancia);
					
					adjunto.setEstado("C"); // si es de una instancia esta pendiente a de autorizacion (Creado)
					adjunto.setNombre_instancia(instancia.getNombre());
				}
				Integer id_persona = null;
				try {
					id_persona = (Integer)session.getAttribute("id_persona");
				} catch (Exception e) {
					System.out.println("No tiene sesion, se cambia id_persona procesos automaticos.");
					String identificacion = SolicitudEnrolamientoServicio.NIT_SUPERSOCIEDADES;		//TODO: Dejar como parametro
					Integer tipoDocumento = SolicitudEnrolamientoServicio.TIPO_DOCUMENTO_USUARIO;
					Usuario usuario = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(identificacion, tipoDocumento );

					Persona persona = PersonaServicio.getInstance().obtenerPersona(usuario.getId_persona());
					id_persona = persona.getId_persona();
				}
				
				adjunto.setId_persona(id_persona);
				
				SimpleLogger.setInfo("SubirArchAdjuntoServlet Carga " + id_carga + " Guardando adjunto " + nombre + " en base de datos con id " + id);
				Boolean conf = ArchivoAdjuntoServicio.getInstance().agregarArchivoAdjunto(adjunto);

				if (!conf) {
					SimpleLogger.setError("No es posible guardar el archivo" + ruta);
				} else {
					SimpleLogger.setInfo("SubirArchAdjuntoServlet Carga " + id_carga + " Adjunto " + nombre + " guardado.");
				}

			}else{
				//System.out.println("No se encuentra el administrador.");
				SimpleLogger.setError("No hay archivos adjuntos");
			}

		} catch (Throwable e) {
			SimpleLogger.setError("Error adjuntando el archivo", e);
		}

	}
	
	public static String charset(String nombre, String charsets[]) {
		try {
			String base = StandardCharsets.UTF_8.name();
			  for(String c : charsets) {
			    Charset charset = Charset.forName(c);
			    if(charset != null) {
			    	String conversion = new String (nombre.getBytes (charset.name()),base);
			    	conversion = new String (conversion.getBytes (base),charset.name());
			    	
			      if(nombre.equals(conversion)) {
			        return c;
			      }
			    }
			  }
			  return StandardCharsets.UTF_8.name();
		} catch (Exception e) {
			SimpleLogger.setError("SubirArchAdjuntoServlet: Error detectando charset ", e);
			return null;
		}
	  
	}
}
