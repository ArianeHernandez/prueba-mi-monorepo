package com.osmosyscol.datasuite.mein.servicios;

import java.io.File;

import org.apache.commons.lang.BooleanUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.logica.servicios.S3Servicio;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.report.FormReportGenerator;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class GeneradorFormularioServicio {

	private static GeneradorFormularioServicio instance;

	private GeneradorFormularioServicio() {
	}

	public static GeneradorFormularioServicio getInstance() {
		if (instance == null) {
			instance = new GeneradorFormularioServicio();
		}
		return instance;
	}

	public void generarFormulario (Integer id_carga) {
		
		try {
			
			String capturar = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("CAPTURAR_FORMULARIO_SERVIDOR");
			
			if (!BooleanUtils.toBoolean(capturar)) return;
			
			Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
			Formato formatoEntrada = FormatoServicio.getInstance().obtenerFormato(carga.getId_formato());
			Integer id_formato_salida = formatoEntrada.getIdformato_salida();
			
			File pdf = FormReportGenerator.getInstance().generateReport(id_carga, id_formato_salida);
			
			if (pdf == null) {
				SimpleLogger.setError("GeneradorFormularioServicio.generarFormulario: Archivo nulo");
				return;
			}
			String nombre_archivo = "PDF Formulario";
			String extension_archivo = "pdf";
			
			ArchivoAdjunto adjunto = new ArchivoAdjunto();
			adjunto.setId_archivo_adjunto(ArchivoAdjuntoServicio.getInstance().obtenerSiguiente());
			adjunto.setId_carga(id_carga);
			adjunto.setNombre_archivo(nombre_archivo);
			adjunto.setExtension_archivo(extension_archivo);
			adjunto.setId_persona(-1);
			adjunto.setId_tipo_archivo(Constantes.TIPO_ARCHIVO_PDF_FORMULARIO);
			adjunto.setFecha_subida(HorarioServicio.getInstance().obtenerFechaActual());
			adjunto.setDescripcion("Archivo Formulario Diligenciado");
			adjunto.setEstado("A");
			adjunto.setInterno("N");
			adjunto.setLong_bytes(String.valueOf(pdf.length()));
			Long hash = FileUtils.checksumCRC32(pdf);
			adjunto.setResumen_hash(hash.toString());
			
			String ruta = ""; 
			Boolean cargue_exitoso = false;
			if (S3Servicio.getInstance().esAlmacenamientoS3()) {
				ruta = S3Servicio.getInstance().obtenerRutaAdjuntosS3();
				ruta += "/" + id_carga + "/" + adjunto.getId_archivo_adjunto() + "_" + nombre_archivo + "." + extension_archivo;
				adjunto.setRuta(ruta);
				cargue_exitoso = S3Servicio.getInstance().cargarArchivo(pdf, adjunto);
			} else {
				ruta = ParametrosInicio.getProperty("adjuntos.carpeta") + "/ADJUNTOS/" + "archivo_" + id_carga + "_" + adjunto.getId_archivo_adjunto() + ".osm";
				adjunto.setRuta(ruta);
				File output = new File(ruta);
				FileUtils.copy(pdf, output);
				cargue_exitoso = true;
			}
			
			if (cargue_exitoso) {
				ArchivoAdjuntoServicio.getInstance().agregarArchivoAdjunto(adjunto);
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("GeneradorFormularioServicio.generarFormulario: Ocurrio un error ", e);
		}
		
	}
	
}
