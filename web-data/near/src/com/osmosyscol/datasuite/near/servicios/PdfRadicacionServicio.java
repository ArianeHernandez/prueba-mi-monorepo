package com.osmosyscol.datasuite.near.servicios;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.Navegador;
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
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.InstanciaServicio;
import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;

public class PdfRadicacionServicio {
	private static PdfRadicacionServicio pdfRadicacionServicio;

	private PdfRadicacionServicio() {
	}

	// ---------------------------------------------------------------

	public static PdfRadicacionServicio getInstance() {
		if (pdfRadicacionServicio == null) {
			pdfRadicacionServicio = new PdfRadicacionServicio();
		}
		return pdfRadicacionServicio;
	}

	public String descargarArchivo(String dirUrl, String dirName, String fileName) {
		new File(dirName).mkdirs();
		String ruta = dirName + "/" + fileName;
		// convertir input stream a file
		try (InputStream inputStream = Navegador.navegarRaw(dirUrl);
				OutputStream outputStream = new FileOutputStream(new File(ruta))) {
			int length;
			byte[] bytes = new byte[1024];
			// copy data from input stream to output stream
			while ((length = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, length);
			}

		} catch (IOException ex) {
			SimpleLogger.setError("Error al generar pdf ", ex);
		}
		return ruta;
	}

	public void cargarArchivo(Integer id_carga, Integer id_archivo_adjunto,
			String descripcion, String interno, Integer id_tipo_archivo,
			String nombreArchivo, String ruta) {
		try {
			String nombre = FilenameUtils.getBaseName(nombreArchivo);
			String extension = FilenameUtils.getExtension(nombreArchivo);
			Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
			Integer id_persona = carga.getId_persona();

			ArchivoAdjunto adjunto = new ArchivoAdjunto();
			adjunto.setDescripcion(descripcion);
			adjunto.setInterno(interno);
			adjunto.setExtension_archivo(extension);
			adjunto.setFecha_subida(HorarioServicio.getInstance()
					.obtenerFechaActual());
			adjunto.setId_archivo_adjunto(id_archivo_adjunto);
			adjunto.setId_carga(id_carga);
			Path path = Paths.get(ruta);

			Long longBytes = Files.size(path);

			File f = new File(ruta);
			SimpleLogger.setDebug("" + f.exists());
			Long checkSum = null;
			checkSum = FileUtils.checksumCRC32(f);
			adjunto.setLong_bytes(longBytes.toString());
			adjunto.setResumen_hash(checkSum.toString());

			adjunto.setNombre_archivo(nombre);
			adjunto.setRuta(ruta);
			adjunto.setEstado("A"); // por defecto es autorizado
			adjunto.setId_tipo_archivo(id_tipo_archivo);
			adjunto.setId_persona(id_persona);

			
			Boolean conf = ArchivoAdjuntoServicio.getInstance()
					.agregarArchivoAdjunto(adjunto);

			if (!conf) {
				SimpleLogger
						.setError("No es posible guardar el archivo" + ruta);
			}
		} catch (IOException e1) {
			SimpleLogger.setError("Error al generar pdf ", e1);
		}
	}

	
//	public void generarPdfPorIdCarga(Integer idcarga){
//		SimpleLogger.setDebug("Archivo pdf de radicacion generado con exito con carga:" + idcarga);
//		
//		String formatSalida = ParametrosInicio.getProperty("formatos.pdf_puf");		
//		
//		Integer tipoSolicitante = SolicitudNearSociedadServicio.getInstance().obtenerTipoSolicitante(idcarga);
//		
//		String nombreArchivo = "Solicitud"+ idcarga + ".pdf";
//		String rutabase = ParametrosInicio.getProperty("adjuntos.carpeta")
//				+ "/ADJUNTOS";
//		
//		String endpoint = ParametrosInicio.getProperty("webdata.endpoint");
//		
//		String ruta = "";
//		String nombre = "";
//		
//		switch(tipoSolicitante) {
//			case 1: nombre = endpoint +"/pdf/ver_carga_solicitud.pub_pdf?id_carga=" +  idcarga + "&id_formato_salida=" + formatSalida;;
//			break;
//			case 2: nombre = endpoint +"/pdf/ver_carga_solicitud_pnc.pub_pdf?id_carga=" +  idcarga + "&id_formato_salida=" + formatSalida;;
//			break;
//			case 3: nombre = endpoint +"/pdf/ver_carga_solicitud_pn_noc.pub_pdf?id_carga=" +  idcarga + "&id_formato_salida=" + formatSalida;;
//			break;
//			default: nombre = endpoint +"/pdf/ver_carga.pub_pdf?id_carga=" +  idcarga + "&id_formato_salida=" + formatSalida;
//		}
//				
//		ruta = PdfRadicacionServicio
//					.getInstance()
//					.descargarArchivo(nombre, rutabase, nombreArchivo);
//		
//		Integer id_archivo_adjunto = ArchivoAdjuntoServicio.getInstance().obtenerSiguiente();
//		
//		String descripcion = "Radicación";
//		String interno_nota_guardar = "N";
//		Integer id_tipo_archivo = 9;
//		
//
//		PdfRadicacionServicio.getInstance().cargarArchivo(idcarga,
//				id_archivo_adjunto, descripcion, interno_nota_guardar,
//				id_tipo_archivo, nombreArchivo, ruta);
//		
//		SimpleLogger.setDebug("Archivo pdf de radicacion generado con exito con carga:" + idcarga);
//		
//	} 
	
	public static void main(String[] args) {
		TestUtils.startUp();
		
	}
}
